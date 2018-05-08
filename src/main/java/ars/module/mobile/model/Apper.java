package ars.module.mobile.model;

import java.util.Date;
import java.io.Serializable;

import ars.module.mobile.app.Device;

/**
 * App用户数据模型
 *
 * @author wuyongqiang
 */
public class Apper implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id; // 主键
    private String user; // 用户编号
    private Device device; // 移动设备类型
    private String channel; // 移动设备唯一标识
    private Boolean online = false; // 是否在线
    private Date dateUpdate = new Date(); // 更新时间

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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

}
