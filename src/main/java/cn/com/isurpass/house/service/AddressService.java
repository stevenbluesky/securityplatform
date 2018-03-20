package cn.com.isurpass.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.isurpass.house.dao.CityDAO;
import cn.com.isurpass.house.dao.CountryDAO;
import cn.com.isurpass.house.dao.ProvinceDAO;
import cn.com.isurpass.house.po.CityPO;
import cn.com.isurpass.house.po.CountryPO;
import cn.com.isurpass.house.po.ProvincePO;

@Service
public class AddressService{

	@Autowired
	CountryDAO country;
	@Autowired
	ProvinceDAO province;
	@Autowired
	CityDAO city;
	
	public List<CountryPO> getCountry() {
		return country.findAll();
	}

	public List<CityPO> getCity(Integer provinceid) {
		return city.findByProvinceid(provinceid);
	}

	public List<ProvincePO> getProvince(Integer countryid) {
		return province.findByCountryid(countryid);
	}


}
