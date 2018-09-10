package cn.com.isurpass.house.vo;

import java.util.Date;

public class RegistUserSearchVO {
    private String searchName;
    private String searchPhonenumber;
    private String searchStarttime;
    private String searchEndtime;
    private String searchGatewayid;

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchPhonenumber() {
        return searchPhonenumber;
    }

    public void setSearchPhonenumber(String searchPhonenumber) {
        this.searchPhonenumber = searchPhonenumber;
    }

    public String getSearchStarttime() {
        return searchStarttime;
    }

    public void setSearchStarttime(String searchStarttime) {
        this.searchStarttime = searchStarttime;
    }

    public String getSearchEndtime() {
        return searchEndtime;
    }

    public void setSearchEndtime(String searchEndtime) {
        this.searchEndtime = searchEndtime;
    }

    public String getSearchGatewayid() {
        return searchGatewayid;
    }

    public void setSearchGatewayid(String searchGatewayid) {
        this.searchGatewayid = searchGatewayid;
    }
}