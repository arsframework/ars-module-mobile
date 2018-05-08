package ars.module.mobile.app.baidu;

import java.util.Map;
import java.util.Collections;

import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.model.PushRequest;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushBatchUniMsgRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;

import ars.module.mobile.app.Messager;

/**
 * 基于百度云推送的App消息推送抽象实现
 *
 * @author wuyongqiang
 */
public abstract class AbstractBaiduMessager implements Messager {
    protected final String url; // 百度云推送服务器地址
    protected final String app; // App应用标识
    protected final String token; // 消息推送访问令牌

    public AbstractBaiduMessager(String app, String token) {
        this(BaiduPushConstants.CHANNEL_REST_URL, app, token);
    }

    public AbstractBaiduMessager(String url, String app, String token) {
        if (url == null) {
            throw new IllegalArgumentException("Illegal url:" + url);
        }
        if (app == null) {
            throw new IllegalArgumentException("Illegal app:" + app);
        }
        if (token == null) {
            throw new IllegalArgumentException("Illegal token:" + token);
        }
        this.url = url;
        this.app = app;
        this.token = token;
    }

    /**
     * 获取百度云推送请求对象
     *
     * @param message    消息
     * @param parameters 参数
     * @param channels   设备列表
     * @return 百度云推送请求对象
     */
    protected abstract PushRequest getPushRequest(String message, Map<String, Object> parameters, String... channels);

    @Override
    public void push(String message, String... channels) throws Exception {
        this.push(message, Collections.<String, Object>emptyMap(), channels);
    }

    @Override
    public void push(String message, Map<String, Object> parameters, String... channels) throws Exception {
        PushKeyPair pair = new PushKeyPair(this.app, this.token);
        BaiduPushClient client = new BaiduPushClient(pair, this.url);
        PushRequest request = this.getPushRequest(message, parameters, channels);
        if (request instanceof PushMsgToAllRequest) {
            client.pushMsgToAll((PushMsgToAllRequest) request);
        } else if (request instanceof PushBatchUniMsgRequest) {
            client.pushBatchUniMsg((PushBatchUniMsgRequest) request);
        } else if (request instanceof PushMsgToSingleDeviceRequest) {
            client.pushMsgToSingleDevice((PushMsgToSingleDeviceRequest) request);
        }
    }

}
