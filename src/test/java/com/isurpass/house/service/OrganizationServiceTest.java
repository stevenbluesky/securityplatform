package com.isurpass.house.service;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.service.OrganizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class OrganizationServiceTest {
    @Resource
    OrganizationDAO od;
    @Autowired
    EmployeeroleDAO erd;
    @Autowired
    RoleDAO rd;
    @Autowired
    RolePrivilegeDAO rpd;
    @Autowired
    PrivilegeDAO privilegedao;

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

    @Test
    public void testEmployeeRole() {
        Set set = getEmployeeRolesNameSet(28);
        System.out.println(set.toString());
        Set<String> ep = getEmployeePermissionsName(28);
        System.out.println(ep.toString());
    }

    public Set getEmployeeRolesNameSet(Integer employeedid) {
        Set<String> set = new HashSet();
        List<EmployeeRolePO> emprolelist = erd.findByEmployeeid(employeedid);
        if (emprolelist != null && !emprolelist.isEmpty()) {
            emprolelist.forEach(emprole -> {
                        RolePO role = rd.findByRoleid(emprole.getRoleid());
                        if (role != null) {
                            set.add(role.getName());
                        }
                    }
            );
        }
        return set;
    }
    public Set<String> getEmployeePermissionsName(Integer employeeid) {
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        List<EmployeeRolePO> emprolelist = erd.findByEmployeeid(employeeid);
        if (emprolelist != null && !emprolelist.isEmpty()) {
            emprolelist.forEach(emprole -> {
                List<RolePrivilegePO> rplist = rpd.findByRoleid(emprole.getRoleid());
                if (rplist != null) {
                    List<Integer> privilegeidlist = rplist.stream().map(RolePrivilegePO::getPrivilegeid).collect(toList());
                    List<PrivilegePO> privilegelist = privilegedao.findByPrivilegeidIn(privilegeidlist);
                    if (privilegelist != null && privilegelist.size() != 0) {
                        set.addAll(privilegelist.stream().map(PrivilegePO::getCode).collect(toSet()));
                    }
                }
            });
            return set;
        }
        return null;
    }
}
