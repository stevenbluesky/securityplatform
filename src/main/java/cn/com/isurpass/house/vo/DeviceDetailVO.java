package cn.com.isurpass.house.vo;

public class DeviceDetailVO {

    private String devicetype;
    private String devicename;
    private String suppliename;
    private String warningstatuses;
    private Integer status;

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
}
