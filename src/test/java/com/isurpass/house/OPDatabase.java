package com.isurpass.house;


import cn.com.isurpass.house.dao.EmployeeDAO;
import cn.com.isurpass.house.dao.EmployeeroleDAO;
import cn.com.isurpass.house.dao.RolePrivilegeDAO;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.EmployeeRolePO;
import cn.com.isurpass.house.po.RolePrivilegePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class OPDatabase {

    @Autowired
    EmployeeDAO emp;
    @Autowired
    RolePrivilegeDAO rp;
    @Autowired
    EmployeeroleDAO erd;

    @Test
    public void addRP() {
        for (int i = 6; i < 17; i++) {
            RolePrivilegePO rolep = new RolePrivilegePO();
            rolep.setCreatetime(new Date());
            rolep.setPrivilegeid(i);
            rolep.setRoleid(3);
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
}
