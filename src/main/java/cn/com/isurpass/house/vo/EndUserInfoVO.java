package cn.com.isurpass.house.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liwenxiang
 * Date:2019/1/4
 * Time:11:07
 */
public class EndUserInfoVO {
    private String usercode;
    private String name;
    private String deviceid;
    private String serialnumber;
    private String appaccount;
    private String city;
    private String suppliername;
    private String status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createtime;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getAppaccount() {
        return appaccount;
    }

    public void setAppaccount(String appaccount) {
        this.appaccount = appaccount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
