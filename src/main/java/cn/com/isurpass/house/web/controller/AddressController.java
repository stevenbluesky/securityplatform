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
		/*
		List<CountryVO> list = new ArrayList<>();
		list.add(new CountryVO(1,"中国","dfa"));
		list.add(new CountryVO(2,"美国","asdf"));
		list.add(new CountryVO(3,"韩国","dfasdffa"));
		list.add(new CountryVO(4,"德国","121"));
		*/
		return as.getCountry();
	}

	@RequestMapping("getProvince")
	@ResponseBody
	public List<ProvincePO> getProvince(Integer countryid) {
		/*
		List<ProvinceVO> list = new ArrayList<>();
		list.add(new CountryVO(1,"中国","dfa"));
		list.add(new CountryVO(2,"美国","asdf"));
		list.add(new CountryVO(3,"韩国","dfasdffa"));
		list.add(new CountryVO(4,"德国","121"));*/
		return as.getProvince(countryid);
	}
	
	@RequestMapping("getCity")
	@ResponseBody
	public List<CityPO> getCity(Integer provinceid) {
		/*
		List<CountryVO> list = new ArrayList<>();
		list.add(new CountryVO(1,"中国","dfa"));
		list.add(new CountryVO(2,"美国","asdf"));
		list.add(new CountryVO(3,"韩国","dfasdffa"));
		list.add(new CountryVO(4,"德国","121"));*/
		return as.getCity(provinceid);
	}

}
