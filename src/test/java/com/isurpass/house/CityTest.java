package com.isurpass.house;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.isurpass.house.dao.CityDAO;
import cn.com.isurpass.house.dao.ProvinceDAO;
import cn.com.isurpass.house.po.ProvincePO;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:application.xml")  
public class CityTest {

	@Resource
	CityDAO cityDAO;
	@Autowired
	ProvinceDAO pd;
	@Test
	public void testCitySave() {
	/*	CityPO city = new CityPO("fda","12312","312fdsf");
		city.setProvinceid(1);
		cityDAO.save(city);*/
		ProvincePO pp = new ProvincePO();
		pp.setCountryid(2);
		pp.setProvinceabbreviation("X");
		pp.setProvincename("嘻嘻");
		pd.save(pp);
	}
}
