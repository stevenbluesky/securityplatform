package cn.com.isurpass.house.vo;

public class UserSearchVO {
    private String searchName;
    private String searchCity;
    private String searchPhonenumber;
    private String searchGatewayid;
    private String searchSerialnumber;
    private String searchDealername;

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

    public String getSearchPhonenumber() {
        return searchPhonenumber;
    }

    public void setSearchPhonenumber(String searchPhonenumber) {
        this.searchPhonenumber = searchPhonenumber;
    }

    public String getSearchDealername() {
        return searchDealername;
    }

    public void setSearchDealername(String searchDealername) {
        this.searchDealername = searchDealername;
    }
}