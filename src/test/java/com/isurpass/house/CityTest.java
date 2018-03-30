package com.isurpass.house;

import javax.annotation.Resource;

import cn.com.isurpass.house.dao.AddressDAO;
import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.po.AddressPO;
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
	@Autowired
	AddressDAO ad;
	@Autowired
    OrganizationDAO od;
	@Test
	public void testCitySave() {
	/*	CityPO city = new CityPO("fda","12312","312fdsf");
		city.setProvinceid(1);
		cityDAO.save(city);*/
		AddressPO a0 = new AddressPO();
		AddressPO a = ad.findByAddressid(103);
        System.out.println(a);
        a0.setAddressid(a.getAddressid());
        a0.setDetailaddress("详细地址");
        a0.setCity("City");
        a0.setCountry("Country");
        a0.setProvince("Province");
        ad.save(a0);
	}

	@Test
	public void testCount() {
//        Integer count = od.countByStatus(1);
        Integer count = od.countByStatusAndContactid(1,60);
        System.out.println(count);
    }
}
