package cn.com.isurpass.house.vo;

import java.util.Date;

public class DeviceDetailVO {

    private String devicetype;
    private String devicename;
    private String suppliename;
    private String warningstatuses;
    private Integer status;
    private Integer zwavedeviceid;
    private String channelcount;
    private String deviceid;
    private String name;
    private String statuses;
    private Date createtime;
    private Integer area;
    private Integer battery;

    public Integer getZwavedeviceid() {
        return zwavedeviceid;
    }

    public void setZwavedeviceid(Integer zwavedeviceid) {
        this.zwavedeviceid = zwavedeviceid;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getSuppliename() {
        return suppliename;
    }

    public void setSuppliename(String suppliename) {
        this.suppliename = suppliename;
    }

    public String getWarningstatuses() {
        return warningstatuses;
    }

    public void setWarningstatuses(String warningstatuses) {
        this.warningstatuses = warningstatuses;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public boolean getStatusopen() {
        if (this.status == null) {
            return false;
        }
        return this.status == 255;
    }

    public boolean getWarningstatusesopen() {
        if (this.warningstatuses == null) {
            return false;
        }
        return this.warningstatuses == "[255]";
    }

    public String getChannelcount() {
        return channelcount;
    }

    public void setChannelcount(String channelcount) {
        this.channelcount = channelcount;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatuses() {
        return statuses;
    }

    public void setStatuses(String statuses) {
        this.statuses = statuses;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }
}
