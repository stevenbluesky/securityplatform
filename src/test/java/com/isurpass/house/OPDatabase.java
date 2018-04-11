package com.isurpass.house;


import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.po.*;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class OPDatabase {

    @Autowired
    AddressDAO ad;
    @Autowired
    EmployeeDAO emp;
    @Autowired
    RolePrivilegeDAO rp;
    @Autowired
    EmployeeroleDAO erd;
    @Autowired
    UserDAO ud;

    @Test
    public void addRP() {
        for (int i = 4; i < 17; i++) {
            RolePrivilegePO rolep = new RolePrivilegePO();
            rolep.setCreatetime(new Date());
            rolep.setPrivilegeid(i);
            rolep.setRoleid(6);
            rp.save(rolep);
        }
    }

    /**
     * 给数据库中没有员工的角色添加角色
     */
    @Test
    public void addEmployeerole() {
        List<EmployeePO> all = emp.findAll();
        List<Integer> employeeid = all.stream().map(EmployeePO::getEmployeeid).collect(toList());
        System.out.println(employeeid);
        employeeid.forEach(e -> {
            if (erd.findByEmployeeid(e).size() == 0) {
                //员工存在,而employeerole里面没有他的角色信息
                EmployeeRolePO erp = new EmployeeRolePO();
                erp.setCreatetime(new Date());
                erp.setEmployeeid(e);
                erp.setRoleid(5);
                erd.save(erp);
            }
        });
    }

    @Test
    public void search() {
        List byAddressidIn = emp.findByAddressidIn(null);
        System.out.println(byAddressidIn);
    }
}
