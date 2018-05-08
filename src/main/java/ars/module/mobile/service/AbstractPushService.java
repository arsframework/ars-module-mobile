package ars.module.mobile.service;

import java.util.Map;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ars.util.Beans;
import ars.util.Servers;
import ars.util.AbstractTimerServer;
import ars.invoke.request.Requester;
import ars.module.mobile.app.Device;
import ars.module.mobile.app.Messager;
import ars.module.mobile.model.Push;
import ars.module.mobile.model.Apper;
import ars.database.repository.Repositories;
import ars.database.service.StandardGeneralService;

/**
 * App消息推送业务操作接口抽象实现
 *
 * @param <T> 数据模型
 * @author wuyongqiang
 */
public abstract class AbstractPushService<T extends Push> extends StandardGeneralService<T> implements PushService<T> {
    private int batch = 1000; // 消息同步批次
    private Map<Device, Messager> messagers;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractPushService() {
        this.initSynchronServer();
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        if (batch < 1) {
            throw new IllegalArgumentException("Batch number must not be less than 1");
        }
        this.batch = batch;
    }

    public Map<Device, Messager> getMessagers() {
        return messagers;
    }

    public void setMessagers(Map<Device, Messager> messagers) {
        this.messagers = messagers;
    }

    /**
     * 初始化消息同步服务
     */
    protected void initSynchronServer() {
        new AbstractTimerServer() {

            @Override
            protected void execute() throws Exception {
                synchron();
            }

        }.start();
    }

    /**
     * 消息同步
     */
    protected void synchron() {
        int count = this.getRepository().query().count();
        for (int page = 1, total = (int) Math.ceil((double) count / (double) this.batch); page <= total; page++) {
            List<T> pushs = this.getRepository().query().paging(page, this.batch).asc("dateJoined").list();
            for (final T push : pushs) {
                final Apper apper = Repositories.getRepository(Apper.class).query().eq("user", push.getUser()).single();
                if (apper == null) {
                    this.getRepository().delete(push);
                } else if (apper.getOnline() == Boolean.TRUE) {
                    try {
                        Servers.submit(new Callable<Object>() {

                            @Override
                            public Object call() throws Exception {
                                messagers.get(apper.getDevice()).push(push.getMessage(), push.getParameters(),
                                    apper.getChannel());
                                return null;
                            }

                        }).get();
                        this.getRepository().delete(push);
                    } catch (Exception e) {
                        logger.error("Message push failure", e);
                        push.setResend(push.getResend() + 1);
                        this.getRepository().update(push);
                    }
                }
            }
        }
    }

    @Override
    public void message(Requester requester, String user, final String message, final Map<String, Object> parameters)
        throws Exception {
        if (this.messagers == null || this.messagers.isEmpty()) {
            throw new IllegalStateException("Messager not initialized");
        }
        Apper apper = Repositories.getRepository(Apper.class).query().eq("user", user).single();
        if (apper == null) {
            throw new RuntimeException("User does not exist:" + user);
        }
        if (apper.getOnline() == Boolean.TRUE) {
            Messager messager = this.messagers == null ? null : this.messagers.get(apper.getDevice());
            if (messager == null) {
                throw new IllegalStateException("Device messager not found:" + apper.getDevice());
            }
            try {
                messager.push(message, parameters, apper.getChannel());
            } catch (Exception e) {
                logger.error("Message push failure", e);
                T push = Beans.getInstance(this.getModel());
                push.setUser(user);
                push.setMessage(message);
                push.setParameters(parameters);
                this.getRepository().save(push);
            }
        } else {
            T push = Beans.getInstance(this.getModel());
            push.setUser(user);
            push.setMessage(message);
            push.setParameters(parameters);
            this.getRepository().save(push);
        }
    }

}
