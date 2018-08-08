package cn.com.isurpass.house.vo;

import java.io.Serializable;

/**
 * @author liwenxiang
 * Date:2018/8/2
 * Time:10:09
 */
public class GatewayPhonecardVO implements Serializable{
    private String deviceid;
    private Integer gatewaystatus;
    private String serialnumber;
    private Integer phonecardstatus;
    private Integer phonecardid;

    public Integer getPhonecardid() {
        return phonecardid;
    }

    public void setPhonecardid(Integer phonecardid) {
        this.phonecardid = phonecardid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Integer getGatewaystatus() {
        return gatewaystatus;
    }

    public void setGatewaystatus(Integer gatewaystatus) {
        this.gatewaystatus = gatewaystatus;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Integer getPhonecardstatus() {
        return phonecardstatus;
    }

    public void setPhonecardstatus(Integer phonecardstatus) {
        this.phonecardstatus = phonecardstatus;
    }
}
