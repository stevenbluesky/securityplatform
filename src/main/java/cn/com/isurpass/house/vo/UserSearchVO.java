package cn.com.isurpass.house.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserSearchVO {
    private String searchName;
    private String searchCity;
    private String searchAppAccount;
    private String searchGatewayid;
    private String searchSerialnumber;
    private String searchDealername;
    private String searchCode;
    private Integer status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date starttime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endtime;
    private Integer searchStation;


    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchCity() {
        return searchCity;
    }

    public void setSearchCity(String searchCity) {
        this.searchCity = searchCity;
    }

    public String getSearchSerialnumber() {
        return searchSerialnumber;
    }

    public void setSearchSerialnumber(String searchSerialnumber) {
        this.searchSerialnumber = searchSerialnumber;
    }

    public String getSearchGatewayid() {
        return searchGatewayid;
    }

    public void setSearchGatewayid(String searchGatewayid) {
        this.searchGatewayid = searchGatewayid;
    }

    public String getSearchAppAccount() {
        return searchAppAccount;
    }

    public void setSearchAppAccount(String searchAppAccount) {
        this.searchAppAccount = searchAppAccount;
    }

    public String getSearchDealername() {
        return searchDealername;
    }

    public void setSearchDealername(String searchDealername) {
        this.searchDealername = searchDealername;
    }

    public String getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
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

    public Integer getSearchStation() {
        return searchStation;
    }

    public void setSearchStation(Integer searchStation) {
        this.searchStation = searchStation;
    }
}