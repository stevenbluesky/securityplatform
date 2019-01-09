package cn.com.isurpass.house.vo;

import java.util.Date;

/**
 * @author liwenxiang
 * Date:2019/1/9
 * Time:10:46
 */
public class SimCardVO {
    private String serialnumber;
    private String status;
    private String model;
    private String firmwareversion;
    private String rateplan;
    private Date activationdate;

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFirmwareversion() {
        return firmwareversion;
    }

    public void setFirmwareversion(String firmwareversion) {
        this.firmwareversion = firmwareversion;
    }

    public String getRateplan() {
        return rateplan;
    }

    public void setRateplan(String rateplan) {
        this.rateplan = rateplan;
    }

    public Date getActivationdate() {
        return activationdate;
    }

    public void setActivationdate(Date activationdate) {
        this.activationdate = activationdate;
    }
}
