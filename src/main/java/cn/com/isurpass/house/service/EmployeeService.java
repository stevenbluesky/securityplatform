package cn.com.isurpass.house.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.isurpass.house.dao.AddressDAO;
import cn.com.isurpass.house.dao.CityDAO;
import cn.com.isurpass.house.dao.CountryDAO;
import cn.com.isurpass.house.dao.EmployeeDAO;
import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.dao.PersonDAO;
import cn.com.isurpass.house.dao.ProvinceDAO;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.AddressPO;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.PersonPO;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.EmployeeAddVO;
import cn.com.isurpass.house.vo.EmployeeListVO;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDAO ed;
	@Autowired
	AddressDAO ad;
	@Autowired
	CountryDAO country;
	@Autowired
	ProvinceDAO province;
	@Autowired
	CityDAO city;
	@Autowired
	PersonDAO pd;
	@Autowired
	OrganizationDAO od;

	public void add(EmployeeAddVO emp) throws MyArgumentNullException {
		if (emp.getOrganizationid() == null || !FormUtils.checkNUll(emp.getLoginname())
				|| !FormUtils.checkNUll(emp.getPassword()))
			throw new MyArgumentNullException("必填字段不能为空!");

		if (od.findByOrganizationid(emp.getOrganizationid()).getOrgtype() == Constants.ORGTYPE_INSTALLER
				&& !FormUtils.checkNUll(emp.getCode()))// 是安装员且code为空时
			throw new MyArgumentNullException("安装员必须要有员工代码!");

		if (emp.getCode() != null && ed
				.findByOrganizationidAndCodeAndStatus(emp.getOrganizationid(), emp.getCode(), Constants.STATUS_DELETED)
				.isEmpty())
			throw new MyArgumentNullException("员工代码不能重复!");

		EmployeePO empPO = new EmployeePO();
		PersonPO personPO = new PersonPO();

		// ID => name
		String countryname = null;
		String provincename = null;
		String cityname = null;
		if (emp.getCountry() != null)
			countryname = country.findByCountryid(emp.getCountry()).getCountryname();
		if (emp.getProvince() != null)
			provincename = province.findByProvinceid(emp.getProvince()).getProvincename();
		if (emp.getCity() != null)
			cityname = city.findByCityid(emp.getCity()).getCityname();

		AddressPO addressPO = new AddressPO(countryname, provincename, cityname, emp.getDetailaddress(), null);

		empPO.setLoginname(emp.getLoginname());
		empPO.setCode(emp.getCode());
		empPO.setPassword(FormUtils.encrypt(emp.getPassword()));// 加密
		empPO.setQuestion(emp.getQuestion());
		empPO.setAnswer(FormUtils.encrypt(emp.getAnswer()));
		empPO.setStatus(emp.getStatus());
		empPO.setExpiredate(new Date());
		empPO.setCreatetime(new Date());
		empPO.setOrganizationid(emp.getOrganizationid());
		empPO.setName(emp.getLastname() + " " + emp.getFirstname());

		personPO.setName(emp.getLastname() + " " + emp.getFirstname());
		personPO.setSsn(emp.getSsn());
		personPO.setGender(emp.getGender());
		personPO.setTitle(emp.getTitle());
		personPO.setFirstname(emp.getFirstname());
		personPO.setLastname(emp.getLastname());
		personPO.setPhonenumber(emp.getPhonenumber());
		personPO.setEmail(emp.getEmail());
		personPO.setFax(emp.getFax());

		if (!FormUtils.isEmpty(addressPO))
			empPO.setAddressid(ad.save(addressPO).getAddressid());
		if (!FormUtils.isEmpty(personPO))
			empPO.setPersonid(pd.save(personPO).getPersonid());

		ed.save(empPO);
	}

	public Map<String, Object> listAllEmployee(Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		map.put("total", ed.count());
		Page<EmployeePO> empList = ed.findAll(pageable);
		List<EmployeeListVO> list = new ArrayList<>();
		empList.forEach(e -> {
			EmployeeListVO emp = new EmployeeListVO();
			emp.setName(e.getName());
			emp.setEmployeeid(e.getEmployeeid());
			emp.setCode(e.getCode());
			emp.setStatus(e.getStatus());
			emp.setParentOrgName(od.findByOrganizationid(e.getOrganizationid()).getName());
			list.add(emp);
		});
		map.put("rows", list);
		return map;
	}

}
