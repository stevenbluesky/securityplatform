package cn.com.isurpass.house.vo;

import java.util.Date;

/**
 * @author liwenxiang
 * Date:2018/9/8
 * Time:10:43
 */
public class RegistUserListVO {
    private int phoneuserid;
    private String name;
    private String phonenumber;
    private Date createtime;
    private String deviceid;

    public int getPhoneuserid() {
        return phoneuserid;
    }

    public void setPhoneuserid(int phoneuserid) {
        this.phoneuserid = phoneuserid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
}
