package ars.module.mobile.service;

import java.util.Map;

import ars.invoke.local.Api;
import ars.invoke.local.Param;
import ars.invoke.request.Requester;
import ars.module.mobile.model.Push;
import ars.database.service.Service;
import ars.database.service.SearchService;
import ars.database.service.DeleteService;

/**
 * App消息推送业务操作接口
 *
 * @author wuyongqiang
 */
@Api("mobile/push")
public interface PushService<T extends Push> extends Service<T>, SearchService<T>, DeleteService<T> {
    /**
     * App消息推送
     *
     * @param requester  请求对象
     * @param user       用户标识
     * @param message    消息内容
     * @param parameters 附加参数
     * @throws Exception 操作异常
     */
    @Api("message")
    public void message(Requester requester, @Param(name = "user", required = true) String user,
                        @Param(name = "message", required = true) String message, Map<String, Object> parameters) throws Exception;

}
