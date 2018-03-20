package com.isurpass.house;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.isurpass.house.dao.CityDAO;
import cn.com.isurpass.house.po.CityPO;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:application.xml")  
public class CityTest {

	@Resource
	CityDAO cityDAO;
	@Test
	public void testCitySave() {
		CityPO city = new CityPO("fda","12312","312fdsf");
		city.setProvinceid(1);
		cityDAO.save(city);
	}
}
