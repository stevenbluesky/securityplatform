package cn.com.isurpass.house.vo;

import java.util.ArrayList;
import java.util.List;

public class RoleChangeVO {
    private Integer id;
    private List<Integer> list;

    public Integer getId() {
        return Integer.valueOf(id);
    }

    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<String> list) {
        List<Integer> list0 = new ArrayList<>();
        list.forEach(role -> list0.add(Integer.valueOf(role)));
        this.list = list0;
    }
}
