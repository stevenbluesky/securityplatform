package cn.com.isurpass.house.vo;

import java.util.Date;

/**
 * @author liwenxiang
 * Date:2019/1/9
 * Time:9:53
 */
public class RegisteredEndUsersVO {
    private String username;
    private String apploginemail;
    private Date registertime;
    private String gatewayid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApploginemail() {
        return apploginemail;
    }

    public void setApploginemail(String apploginemail) {
        this.apploginemail = apploginemail;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public String getGatewayid() {
        return gatewayid;
    }

    public void setGatewayid(String gatewayid) {
        this.gatewayid = gatewayid;
    }
}
