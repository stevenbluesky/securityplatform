package cn.com.isurpass.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.isurpass.house.dao.AddressDAO;
import cn.com.isurpass.house.po.CityPO;
import cn.com.isurpass.house.po.CountryPO;
import cn.com.isurpass.house.po.ProvincePO;
import cn.com.isurpass.house.service.AddressService;

@Service
public class AddressService{

	@Autowired
	AddressDAO ad;
	
	public List<CountryPO> getCountry() {
		return ad.getCountry();
	}

	public List<CityPO> getCity(Integer provinceid) {
		return ad.getCity(provinceid);
	}

	public List<ProvincePO> getProvince(Integer countryid) {
		return ad.getProvince(countryid);
	}


}
