package cn.com.isurpass.house.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.po.PersonPO;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.EmployeeAddVO;
import cn.com.isurpass.house.vo.EmployeeListVO;
import cn.com.isurpass.house.vo.EmployeeParentOrgIdVO;

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
	@Autowired
	OrganizationService os;

	@Transactional(rollbackFor = Exception.class)
	public void add(EmployeeAddVO emp) throws MyArgumentNullException {
		if (emp.getOrganizationid() == null || !FormUtils.checkNUll(emp.getLoginname())
				|| !FormUtils.checkNUll(emp.getPassword()))
			throw new MyArgumentNullException("必填字段不能为空!");

		if (od.findByOrganizationid(emp.getOrganizationid()).getOrgtype() == Constants.ORGTYPE_INSTALLER
				&& !FormUtils.checkNUll(emp.getCode()))// 是安装员且code为空时
			throw new MyArgumentNullException("安装员必须要有员工代码!");

		if (emp.getCode() != null && !ed.findByOrganizationidAndCodeAndStatusNot(emp.getOrganizationid(), emp.getCode(),
				Constants.STATUS_DELETED).isEmpty())
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

	public Map<String, Object> listEmployee(Pageable pageable, HttpServletRequest request) {
		// 只显示对应的服务商所具有权限的安装商,如果是ameta,则可以看见所有的
		// 首先判断当前登录的员工角色,
		// 如果角色是员工,over.
		// 如果是安装商,再通过员工的机构id查询所有属于它的员工,
		// 如果是服务商,则查询它的所有的安装商,通过服务商,安装商id list查询其所有的员工
		// 如果是ameta,直接查询所有的员工

		// 另一种办法,首先判断当前登录的员工角色,如果角色是员工,over.
		// 通过当前员工的机构id,查询此机构是否具有父机构,如果有,查询其父机构id.这样遍历取此机构所有
		EmployeePO emp = (EmployeePO)request.getSession().getAttribute("emp");
		
		List<Integer> list0 = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		List<Integer> list = os.findParentOrgid(emp.getOrganizationid(), list0);
		list.add(emp.getOrganizationid());
//		System.out.println(list);
		Page<EmployeePO> empList = ed.findByOrganizationidIn(pageable,list);
		map.put("total", empList.getTotalElements());
		List<EmployeeListVO> listt = new ArrayList<>();
		empList.forEach(e -> {
			EmployeeListVO empv = new EmployeeListVO();
			empv.setName(e.getName());
			empv.setEmployeeid(e.getEmployeeid());
			empv.setCode(e.getCode());
			empv.setStatus(e.getStatus());
			if (od.findByOrganizationid(e.getOrganizationid()) != null)
				empv.setParentOrgName(od.findByOrganizationid(e.getOrganizationid()).getName());
			listt.add(empv);
		});
		map.put("rows", listt);
		return map;
	}

	public Map<String, Object> listAllEmployee(Pageable pageable,HttpServletRequest request) {
		EmployeePO em = (EmployeePO)request.getSession().getAttribute("emp");
//		System.out.println(em.toString());
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
			if (od.findByOrganizationid(e.getOrganizationid()) != null)
				emp.setParentOrgName(od.findByOrganizationid(e.getOrganizationid()).getName());
			list.add(emp);
		});
		map.put("rows", list);
		return map;
	}

	public EmployeePO findByLoginname(String loginname) {
		return ed.findByLoginname(loginname);
	}

	public EmployeePO login(String loginname, String password) {
		return ed.findByLoginnameAndPassword(loginname, password);
	}

	public EmployeePO login(String loginname, String password, String code0) {
		OrganizationPO org = null;
		// System.out.println(od.findByCode(code0));
		if ((org = od.findByCode(code0)) != null) {
			return ed.findByLoginnameAndPasswordAndOrganizationid(loginname, password, org.getOrganizationid());
		} else {
			return null;
		}
	}

	/**
	 * 查询一个employee的所有父类机构id
	 * 
	 * @param emp
	 * @return
	 */
	public EmployeeParentOrgIdVO findEmpParentOrgid(EmployeePO emp) {
		EmployeeParentOrgIdVO empp = new EmployeeParentOrgIdVO();
		empp.setInstallerid(emp.getEmployeeid());
		OrganizationPO org0 = od.findByOrganizationid(emp.getOrganizationid());
		if (org0 != null) {
			empp.setInstallerorgid(org0.getOrganizationid());
			if (org0.getParentorgid() != null)// parentid的对象不为空,说明org0是一个安装商,emp是安装商员工
				empp.setOrganizationid(org0.getParentorgid());
			else// 说明Org0是一个服务商
				empp.setOrganizationid(org0.getOrganizationid());
		}
		return empp;
	}

}
