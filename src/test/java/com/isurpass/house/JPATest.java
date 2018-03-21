package com.isurpass.house;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.util.Constants;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:application.xml") 
public class JPATest {

	@Autowired
	OrganizationDAO org;
	
	@Test
	public void testOrg() {
		List<OrganizationPO> list = org.findAllOrgSelect();
		list.forEach(o ->{
			System.out.println(o.toString());
		});
	}
	
	@Test
	public void testPage() {
		//@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
		Pageable pageable = PageRequest.of(0,3, Sort.Direction.ASC,"organizationid");
		Page<OrganizationPO> list = org.findByOrgtype(pageable,Constants.ORGTYPE_INSTALLER);
		list.forEach(o ->{
			System.out.println(o.toString());
		});
	}
}
