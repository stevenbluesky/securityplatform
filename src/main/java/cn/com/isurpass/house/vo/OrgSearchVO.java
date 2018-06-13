package cn.com.isurpass.house.vo;

import java.io.UnsupportedEncodingException;

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

    @Override
    public String toString() {
        return "OrgSearchVO{" +
                "searchcode='" + searchcode + '\'' +
                ", searchname='" + searchname + '\'' +
                ", searchcity='" + searchcity + '\'' +
                ", searchparentname='" + searchparentname + '\'' +
                ", searchparentcode='" + searchparentcode + '\'' +
                ", searchorgname='" + searchorgname + '\'' +
                '}';
    }
}
