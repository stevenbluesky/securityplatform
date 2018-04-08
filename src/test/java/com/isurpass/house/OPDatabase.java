package com.isurpass.house;


import cn.com.isurpass.house.dao.RolePrivilegeDAO;
import cn.com.isurpass.house.po.RolePrivilegePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class OPDatabase {

    @Autowired
    RolePrivilegeDAO rp;

    @Test
    public void addRP(){
        for (int i = 2; i < 17; i++) {
            RolePrivilegePO rolep = new RolePrivilegePO();
            rolep.setCreatetime(new Date());
            rolep.setPrivilegeid(i);
            rolep.setRoleid(1);
            rp.save(rolep);
        }
    }
}
