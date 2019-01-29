package cn.com.isurpass.house.vo;

import java.util.ArrayList;
import java.util.List;

public class RoleChangeVO {
    private Integer id;
    private List<Integer> list;
    private String rolename;
    private List<Integer> employeeids;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(String id) {
        if ("null".equals(id)) {
            this.id = null;
        }else {
            this.id = Integer.valueOf(id);
        }
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<String> list) {
        List<Integer> list0 = new ArrayList<>();
        list.forEach(role -> list0.add(Integer.valueOf(role)));
        this.list = list0;
    }

    public List<Integer> getEmployeeids() {
        return employeeids;
    }

    public void setEmployeeids(List<String> employeeids) {
        List<Integer> list0 = new ArrayList<>();
        employeeids.forEach(role -> list0.add(Integer.valueOf(role)));
        this.employeeids = list0;
    }

    public boolean isNew(){
        if (this.id == null || "".equals(String.valueOf(this.id))) {
            return true;
        }
        return false;
    }
}
