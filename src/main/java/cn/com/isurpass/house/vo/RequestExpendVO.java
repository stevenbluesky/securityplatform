package cn.com.isurpass.house.vo;

import java.util.List;

public class RequestExpendVO {
    private Integer employeeid;
    private List<Integer> employeerole;
    private Integer orgtype;
    private Integer orgid;

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public List<Integer> getEmployeerole() {
        return employeerole;
    }

    public void setEmployeerole(List<Integer> employeerole) {
        this.employeerole = employeerole;
    }

    public Integer getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(Integer orgtype) {
        this.orgtype = orgtype;
    }

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }
}
