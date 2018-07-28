package cn.com.isurpass.house.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.isurpass.house.po.CityPO;
import cn.com.isurpass.house.po.CountryPO;
import cn.com.isurpass.house.po.ProvincePO;
import cn.com.isurpass.house.service.AddressService;

@Controller
@RequestMapping("address")
public class AddressController {

	@Autowired
	AddressService as;
	@RequestMapping("getCountry")
	@ResponseBody
	public List<CountryPO> getCountry() {
		return as.getCountry();
	}

	@RequestMapping("getProvince")
	@ResponseBody
	public List<ProvincePO> getProvince(Integer countryid) {
		return as.getProvince(countryid);
	}
	
	@RequestMapping("getCity")
	@ResponseBody
	public List<CityPO> getCity(Integer provinceid) {
		return as.getCity(provinceid);
	}

}
