package com.isurpass.house.service;

import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.service.OrganizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class OrganizationServiceTest {
    @Resource
    OrganizationDAO od;

    @Test
    public void testMethod() {
        Integer id = 0;
        List<Integer> list = new ArrayList<>();
        findChildrenOrgid(1, list);
        list.forEach(System.out::println);
    }

    public List<Integer> findChildrenOrgid(Integer id, List<Integer> list) {
        List<OrganizationPO> orgList = od.findByParentorgid(id);
        if (orgList == null) {
            list.add(id);
            return list;
        }
        orgList.forEach(o -> {
            list.add(o.getOrganizationid());
            findChildrenOrgid(o.getOrganizationid(), list);
        });
        return list;

    }
}
