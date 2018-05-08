package ars.module.mobile.service;

import ars.invoke.local.Api;
import ars.invoke.local.Param;
import ars.invoke.request.Token;
import ars.invoke.request.Requester;
import ars.module.mobile.app.Device;

/**
 * App用户认证接口
 *
 * @author wuyongqiang
 */
@Api("mobile/auth")
public interface MobileAuthService {
    /**
     * 用户登陆
     *
     * @param requester 请求对象
     * @param code      用户编号
     * @param password  用户密码
     * @param device    请求设备
     * @return 令牌对象
     */
    @Api("login")
    public Token login(Requester requester, @Param(name = "code", required = true) String code,
                       @Param(name = "password", required = true) String password,
                       @Param(name = "device", required = true) Device device);

    /**
     * 用户注销
     *
     * @param requester 请求对象
     */
    @Api("logout")
    public void logout(Requester requester);

}
