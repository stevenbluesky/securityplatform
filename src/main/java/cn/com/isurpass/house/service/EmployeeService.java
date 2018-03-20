package cn.com.isurpass.house.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.isurpass.house.dao.AddressDAO;
import cn.com.isurpass.house.dao.EmployeeDAO;
import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.dao.PersonDAO;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.AddressPO;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.PersonPO;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.util.PageResult;
import cn.com.isurpass.house.vo.EmployeeAddVO;
import cn.com.isurpass.house.vo.EmployeeListVO;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDAO ed;
	@Autowired
	AddressDAO ad;
	@Autowired
	PersonDAO pd;
	@Autowired
	OrganizationDAO od;

	public void add(EmployeeAddVO emp) throws MyArgumentNullException {
		if (emp.getOrganizationid() == null || !FormUtils.checkNUll(emp.getLoginname())
				|| !FormUtils.checkNUll(emp.getPassword()))
			throw new MyArgumentNullException("必填字段不能为空!");
		if (od.getOrgType(emp.getOrganizationid()) == Constants.ORGTYPE_INSTALLER
				&& !FormUtils.checkNUll(emp.getCode()))// 是安装员且code为空时
			throw new MyArgumentNullException("安装员必须要有员工代码!");
		EmployeePO empPO = new EmployeePO();
		PersonPO personPO = new PersonPO();
		AddressPO addressPO = new AddressPO(emp.getCountry(), emp.getProvince(), emp.getCity(), emp.getDetailaddress(),
				null);

		empPO.setLoginname(emp.getLoginname());
		empPO.setCode(emp.getCode());
		empPO.setPassword(FormUtils.encrypt(emp.getPassword()));//加密
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
			empPO.setAddressid(ad.add(addressPO));
		if (!FormUtils.isEmpty(personPO))
			empPO.setPersonid(pd.add(personPO));

		ed.add(empPO);
	}

	public Map<String, Object> listAllEmployee(PageResult pr) {
		Map<String, Object> map = new HashMap<>();
		map.put("total", ed.getTotal());
		List<EmployeePO> empList = ed.listAllEmployee(pr);
		List<EmployeeListVO> list = new ArrayList<>();
		empList.forEach(e -> {
			EmployeeListVO emp = new EmployeeListVO();
			emp.setName(e.getName());
			emp.setEmployeeid(e.getEmployeeid());
			emp.setCode(e.getCode());
			emp.setStatus(e.getStatus());
			emp.setParentOrgName(od.getOrgNameById(e.getOrganizationid()));
			list.add(emp);
		});
		map.put("rows", list);
		return map;
	}

}
