package com.isurpass.house.service;

import cn.com.isurpass.house.dao.PhoneuserDAO;
import cn.com.isurpass.house.service.PhoneuserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.List;

/**
 * @author liwenxiang
 * Date:2018/9/7
 * Time:16:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class PhoneUserTest {
    @PersistenceContext(unitName = "iremote")
    protected EntityManager em;
    @Resource(name = "entityManagerFactory2")
    protected EntityManagerFactory emf;
    @Test
    public void test() {
        /*String sqlString = "select count(*) from phoneuser";
        Query q = em.createNativeQuery(sqlString);
        int firstResult = q.getFirstResult();
        List re = q.getResultList();
        Object o = re.get(0);
        System.out.println("已注册人数："+o);*/
        /*EntityManagerFactory mysql = Persistence.createEntityManagerFactory("house");
        EntityManagerFactory oracle = Persistence.createEntityManagerFactory("iremote");
        System.out.println(mysql);
        System.out.println(oracle);*/
    }
}
