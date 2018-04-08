package com.isurpass.house;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.isurpass.house.dao.EmployeeDAO;
import cn.com.isurpass.house.po.EmployeePO;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:application.xml")
public class EmployeeTest {

	@Autowired
	EmployeeDAO emp;
	
	@Test
	public void test() {
		List<EmployeePO> list = emp.findByOrganizationidAndCodeAndStatusNot(1,"ff",9);
		list.forEach(e -> System.out.println(e.toString()));
	}
}
