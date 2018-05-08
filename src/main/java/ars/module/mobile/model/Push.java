package ars.module.mobile.model;

import java.util.Map;
import java.util.Date;
import java.io.Serializable;

/**
 * App消息推送数据模型
 *
 * @author wuyongqiang
 */
public class Push implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id; // 主键
    private String user; // 用户标识
    private String message; // 消息内容
    private Map<String, Object> parameters; // 消息参数
    private Integer resend = 0; // 重发次数
    private Date dateJoined = new Date(); // 创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Integer getResend() {
        return resend;
    }

    public void setResend(Integer resend) {
        this.resend = resend;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

}
