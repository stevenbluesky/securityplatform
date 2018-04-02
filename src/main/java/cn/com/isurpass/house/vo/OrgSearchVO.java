package cn.com.isurpass.house.vo;

import java.io.UnsupportedEncodingException;

/**
 * 服务商,安装商与员工搜索的实体类
 */
public class OrgSearchVO {
    private String searchname;
    private String searchcity;
    private String searchcitycode;

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

    public String getSearchcitycode() {
        return searchcitycode;
    }

    public void setSearchcitycode(String searchcitycode) {
        this.searchcitycode = searchcitycode;
    }

    @Override
    public String toString() {
        return "OrgSearchVO{" +
                "searchname='" + searchname + '\'' +
                ", searchcity='" + searchcity + '\'' +
                ", searchcitycode='" + searchcitycode + '\'' +
                '}';
    }
}
