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
import java.util.*;

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
    @Autowired
    ZwaveDeviceDAO zdd;

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
    public void search() throws InterruptedException {
        Random rand = new Random();
        ZwaveDevicePO z = new ZwaveDevicePO();
        z.setBattery(100);
        z.setDevicetype("4");
        z.setCreatetime(new Date());
        z.setStatus(255);
        z.setStatuses(null);
        z.setWarningstatuses("[255]");

        for (int i = 11545; i < 20001; i++) {
            int i1 = rand.nextInt(6) + 1;
            z.setZwavedeviceid(i);
            z.setDeviceid("iRemote800500000000"+i1);//[1,7)
            z.setName("testzwavedevice"+i1);
            zdd.save(z);
            Thread.sleep(10);
        }
    }

    @Test
    public void search1() throws InterruptedException {

    }
}
