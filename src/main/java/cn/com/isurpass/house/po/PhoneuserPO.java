package cn.com.isurpass.house.po;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liwenxiang
 * Date:2018/9/7
 * Time:16:57
 */
@Entity
@Table(name = "phoneuser")
public class PhoneuserPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer phoneuserid;
    private String countrycode;
    private String phonenumber;
    private String mail;
    private String name ;
    private String password;
    private Date createtime;
    private Date lastupdatetime;
    private String alias;
    private Integer longitude;
    private Integer latitude;
    private Integer smscount = 0;
    private Integer callcount = 0 ;
    private Integer platform ;
    private String openId ;
    private String token ;
    private Integer armstatus;
    private Integer familyid;
    private String language;
    private Integer usertype = 0 ;
    private Integer status;

    public Integer getPhoneuserid() {
        return phoneuserid;
    }

    public void setPhoneuserid(Integer phoneuserid) {
        this.phoneuserid = phoneuserid;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getSmscount() {
        return smscount;
    }

    public void setSmscount(Integer smscount) {
        this.smscount = smscount;
    }

    public Integer getCallcount() {
        return callcount;
    }

    public void setCallcount(Integer callcount) {
        this.callcount = callcount;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getArmstatus() {
        return armstatus;
    }

    public void setArmstatus(Integer armstatus) {
        this.armstatus = armstatus;
    }

    public Integer getFamilyid() {
        return familyid;
    }

    public void setFamilyid(Integer familyid) {
        this.familyid = familyid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
