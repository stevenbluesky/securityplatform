package cn.com.isurpass.house.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 服务商,安装商与员工搜索的实体类
 */
public class OrgSearchVO {
    private String searchcode;
    private String searchname;
    private String searchcity;
    private String searchparentname;
    private String searchparentcode;
    private String searchorgname;
    private Integer usertype;
    private Integer status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date starttime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endtime;

    public String getSearchorgname() {
        return searchorgname;
    }

    public void setSearchorgname(String searchorgname) {
        this.searchorgname = searchorgname;
    }

    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) throws UnsupportedEncodingException {
        this.searchname = searchname;
    }

    public String getSearchcity() {
        return searchcity;
    }

    public void setSearchcity(String searchcity) throws UnsupportedEncodingException {
        this.searchcity = searchcity;
    }

    public String getSearchparentname() {
        return searchparentname;
    }

    public void setSearchparentname(String searchparentname) {
        this.searchparentname = searchparentname;
    }

    public String getSearchparentcode() {
        return searchparentcode;
    }

    public void setSearchparentcode(String searchparentcode) {
        this.searchparentcode = searchparentcode;
    }

    public String getSearchcode() {
        return searchcode;
    }

    public void setSearchcode(String searchcode) {
        this.searchcode = searchcode;
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

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "OrgSearchVO{" +
                "searchcode='" + searchcode + '\'' +
                ", searchname='" + searchname + '\'' +
                ", searchcity='" + searchcity + '\'' +
                ", searchparentname='" + searchparentname + '\'' +
                ", searchparentcode='" + searchparentcode + '\'' +
                ", searchorgname='" + searchorgname + '\'' +
                ", usertype=" + usertype +
                ", status=" + status +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                '}';
    }
}
