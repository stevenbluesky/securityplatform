package cn.com.isurpass.house.service;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.isurpass.house.dao.CityDAO;
import cn.com.isurpass.house.po.CityPO;

public class CityService {

	@Autowired
	CityDAO cd;

	public CityPO add(CityPO city) {
		return cd.save(city);
	}
}
