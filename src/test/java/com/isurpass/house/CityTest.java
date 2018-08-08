package com.isurpass.house;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;

import cn.com.isurpass.house.dao.AddressDAO;
import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.po.AddressPO;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.service.OrganizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.isurpass.house.dao.CityDAO;
import cn.com.isurpass.house.dao.ProvinceDAO;
import cn.com.isurpass.house.po.ProvincePO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
/*	@Test
	public void testCitySave() {
	*//*	CityPO city = new CityPO("fda","12312","312fdsf");
		city.setProvinceid(1);
		cityDAO.save(city);*//*
		AddressPO a0 = new AddressPO();
		AddressPO a = ad.findByAddressid(103);
        System.out.println(a);
        a0.setAddressid(a.getAddressid());
        a0.setDetailaddress("详细地址");
        a0.setCity("City");
        a0.setCountry("Country");
        a0.setProvince("Province");
        ad.save(a0);
	}*/

	@Test
	public void testCount() {
//        Integer count = od.countByStatus(1);
//        Integer count = od.countByStatusAndContactid(1,60);
//        System.out.println(count);
            List<OrganizationPO> list = od.findByNameLikeAndCitycodeLike("%" + "supplier" + "%", "%" + "" + "%");
        System.out.println("=============");
        System.out.println(list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void testNull() {
        //List<OrganizationPO> byOrgtype = od.findByOrgtype(null);//不能通过null取所有的数据
        //System.out.println(byOrgtype.size());
        String s = ",,,,23,,";
        String[] split = s.split(",");
        System.out.println(split.length);
        System.out.println(split[0]+"-"+split[1]+"-"+split[2]);
        System.out.println(s.substring(0,s.indexOf(",")));
        //System.out.println(s.substring(s.indexOf(",")));
        /*String d = "khbjikjbhuijbhk3";
        String f = "jnkjkjkjnknk4";
        List<String> a = new ArrayList<>();
        a.add(d);a.add(f);
        List<String> b = new ArrayList<>();
        b.add(d);
        a.retainAll(b);
        System.out.println(a);*/
    }
    @Test
    public void transfertimezone(){
	    String ot = "2018-05-24 10:44:24.651+0000";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ot.substring(0,ot.indexOf("."));
        try {
            Date nd = sdf.parse(ot);
            System.out.println(nd.toString());
            String format = sdf.format(new Date(nd.getTime() - (long) 4 * 60 * 60 * 1000));
            System.out.println(format.toString());
            System.out.println(new Date(nd.getTime() - (long) 4 * 60 * 60 * 1000));
        } catch (ParseException e) {

        }
    }
}
