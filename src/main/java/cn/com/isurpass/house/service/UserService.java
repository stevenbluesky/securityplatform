package cn.com.isurpass.house.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.isurpass.house.dao.AddressDAO;
import cn.com.isurpass.house.dao.CityDAO;
import cn.com.isurpass.house.dao.CountryDAO;
import cn.com.isurpass.house.dao.GatewayuserDAO;
import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.dao.PersonDAO;
import cn.com.isurpass.house.dao.PhonecarduserDAO;
import cn.com.isurpass.house.dao.ProvinceDAO;
import cn.com.isurpass.house.dao.UserDAO;
import cn.com.isurpass.house.po.AddressPO;
import cn.com.isurpass.house.po.CityPO;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.GatewayUserPO;
import cn.com.isurpass.house.po.PersonPO;
import cn.com.isurpass.house.po.PhonecardUserPO;
import cn.com.isurpass.house.po.UserPO;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.EmployeeParentOrgIdVO;
import cn.com.isurpass.house.vo.UserAddVO;
import cn.com.isurpass.house.vo.UserInfoListVO;

@Service
public class UserService {

	@Autowired
	UserDAO ud;
	@Autowired
	CountryDAO country;
	@Autowired
	ProvinceDAO province;
	@Autowired
	CityDAO city;
	@Autowired
	AddressDAO ad;
	@Autowired
	PersonDAO pd;
	@Autowired
	OrganizationDAO od;
	@Autowired
	PhonecarduserDAO pcud;
	@Autowired
	EmployeeService emps;
	@Autowired
	GatewayuserDAO gd;

	@Transactional(rollbackFor = Exception.class)
	public void add(UserAddVO u, HttpServletRequest request) {
		UserPO user = new UserPO();
		user.setLoginname(u.getPhonenumber());
		user.setName(u.getFirstname() + " " + u.getLastname());
		user.setCitycode("");

		EmployeePO emp = (EmployeePO) SecurityUtils.getSubject().getPrincipal();
		EmployeeParentOrgIdVO empp = emps.findEmpParentOrgid(emp);
		user.setOrganizationid(1);
		user.setInstallerorgid(1);
		user.setInstallerid(1);
		if (empp != null) {
			user.setOrganizationid(empp.getOrganizationid());
			user.setInstallerorgid(empp.getInstallerorgid());
			user.setInstallerid(empp.getInstallerid());
		}
		user.setCodepostfix(u.getCodepostfix());
		user.setUsercode("ameta" + u.getPhonenumber());

		AddressPO address = new AddressPO();
		Integer addressid = null;
		address.setDetailaddress(u.getDetailaddress());
		if (u.getCountry() != null)
			address.setCountry(country.findByCountryid(u.getCountry()).getCountryname());
		if (u.getProvince() != null)
			address.setProvince(province.findByProvinceid(u.getProvince()).getProvincename());
		if (u.getCity() != null) {
			CityPO c = city.findByCityid(u.getCity());
			address.setCity(c.getCityname());
			user.setCitycode(c.getCitycode());
		}
		if (!FormUtils.isEmpty(address))
			addressid = ad.save(address).getAddressid();
		PersonPO person = new PersonPO();
		person.setFirstname(u.getFirstname());
		person.setLastname(u.getLastname());
		person.setName(u.getFirstname() + " " + u.getLastname());
		person.setSsn(u.getSsn());
		person.setGender(u.getGender());
		person.setPhonenumber(u.getPhonenumber());
		person.setAddressid(addressid);
		person.setEmail(u.getEmail());
		person.setFax(u.getFax());
		person.setAddressid(addressid);
		Integer personid = pd.save(person).getPersonid();

		user.setPersonid(personid);
		UserPO userSave = ud.save(user);
		if(u.getDeviceid()!= null) {
			GatewayUserPO gateway = new GatewayUserPO();
			gateway.setDeviceid(u.getDeviceid());
			gateway.setUserid(userSave.getUserid());
			gd.save(gateway);
		}
		
		if (u.getPhonecardid() != null) {
			PhonecardUserPO pcup = new PhonecardUserPO();
			pcup.setUserid(userSave.getUserid());
			pcup.setPhonecardid(1);
			pcud.save(pcup);
		}

	}

	public Map<String, Object> listUserInfoByOrgtype(Pageable pageable, Integer orgType) {
		Map<String, Object> map = new HashMap<>();

		// TODO 先要判断目前登陆的是哪角色,服务商|安装商|ameta 然后查询相应的数据(如果是 ameta 则查询所有的)..对应的总数也要进行相应的改变
		
		map.put("total", ud.count());
		Page<UserPO> userList = ud.findAll(pageable);
		List<UserInfoListVO> list = new ArrayList<>();
		userList.forEach(u -> {
			PersonPO personPO = pd.findByPersonid(u.getPersonid());
			AddressPO adrs = ad.findByAddressid(personPO.getAddressid());
			UserInfoListVO user = new UserInfoListVO();
			user.setUserid(u.getUserid());
			user.setName(u.getName());
			user.setPhonenumber(personPO.getPhonenumber());
			if (adrs != null) {
				user.setCity(adrs.getCity());
			}
			if (od.findByOrganizationid(u.getOrganizationid()) != null)
				user.setSuppliername(od.findByOrganizationid(u.getOrganizationid()).getName());

			list.add(user);
			// System.out.println(list.toString()+"fff");
		});
		map.put("rows", list);
		return map;
	}
	
	public List<UserPO> findUser(Integer orgid){
		//TODO 先要判断角色,然后查找相应的机构.
		List<UserPO> userList = ud.findByOrganizationid(orgid);
		return userList;
	}
}
