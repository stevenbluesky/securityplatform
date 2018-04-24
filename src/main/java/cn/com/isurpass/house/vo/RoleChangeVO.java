package cn.com.isurpass.house.vo;

import java.util.ArrayList;
import java.util.List;

public class RoleChangeVO {
    private Integer id;
    private List<Integer> list;
    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getId() {
        return Integer.valueOf(id);
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

    public boolean isNew(){
        if (this.id == null || "".equals(this.id)) {
            return true;
        }
        return false;
    }
}
