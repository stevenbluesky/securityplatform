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
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.po.PersonPO;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.OrgAddVO;
import cn.com.isurpass.house.vo.OrgListVO;

@Service
public class OrganizationService {

	@Autowired
	OrganizationDAO od;
	@Autowired
	PersonDAO pd;
	@Autowired
	AddressDAO ad;
	@Autowired
	EmployeeDAO ed;
	@Autowired
	CountryDAO country;
	@Autowired
	ProvinceDAO province;
	@Autowired
	CityDAO city;

	/**
	 * 添加一个机构
	 * 
	 * @param as
	 * @throws MyArgumentNullException 
	 */
	public void add(OrgAddVO as) throws MyArgumentNullException {
		if (!FormUtils.checkOrgNull(as)) // 必填项为空时
			throw new MyArgumentNullException("必填字段不能为空!");
		OrganizationPO org = new OrganizationPO();

		FormUtils.copyO2O(org, as);// 将一些机构的属性复制到 org 中

		if (as.getParentorgid() == null) {// 没有填写上级机构id,则表明这是一个服务商
			Integer ametaId = od.findByOrgtype(Constants.ORGTYPE_AMETA).get(0).getOrganizationid();// 取 Ameta 的机构id
			as.setParentorgid(ametaId);
		}
		
		// 公司地址
		AddressPO address = new AddressPO(as.getCountry(), as.getProvince(), as.getCity(), as.getDetailaddress(),as.getPostal());
		// 公司账务地址
		AddressPO bAddress = new AddressPO(as.getBcountry(),as.getBprovince(), as.getBcity(),as.getBdetailaddress(),
				as.getBpostal());
		// 服务商联系人
		PersonPO sPerson = new PersonPO(as.getSname(), as.getSphonenumber(), as.getSemail(), as.getSfax());
		// 总公司地址
		AddressPO csAddress = new AddressPO(as.getCscountry(),as.getCsprovince(), as.getCscity(), "",
				as.getCspostal());
		// 服务商总公司联系人
		PersonPO csPerson = new PersonPO(as.getCspname(), as.getCspphonenumber(), as.getCspemail(), as.getCspfax());
		EmployeePO emp = new EmployeePO(as.getLoginname(), FormUtils.encrypt(as.getPassword()), as.getQuestion(),FormUtils.encrypt(as.getAnswer()),
				new Date());

		System.out.println(address.toString());
		System.out.println(bAddress.toString());
		System.out.println(csAddress.toString());
		
		Integer aId = null;// 公司地址id
		Integer csAId = null;
		Integer bAId = null;
		Integer sPId = null;
		Integer csPId = null;

		if (!FormUtils.isEmpty(address))
			aId = ad.save(address).getAddressid();
		if (!FormUtils.isEmpty(csAddress))
			csAId = ad.save(csAddress).getAddressid();
		if (!FormUtils.isEmpty(bAddress))
			bAId = ad.save(bAddress).getAddressid();
		if (!FormUtils.isEmpty(sPerson)) // 服务商联系人不为空
			sPId = pd.save(sPerson).getAddressid();
		if (!FormUtils.isEmpty(csPerson)) {// 服务商总公司联系人不为空
			csPId = pd.save(csPerson).getPersonid();
			org.setParentorgid(od.getParentorgidByName(as.getCsname()));
		}

		// 取到各分支方法返回的主键id后将其存入 org 对象中,然后再进行保存
		org.setOfficeaddressid(aId);
		org.setBillingaddressid(csAId);
		org.setCsaddressid(csAId);
		org.setBillingaddressid(bAId);
		org.setContactid(sPId);
		org.setCscontactid(csPId);
		
		Integer orgId = od.save(org).getOrganizationid();
		
		emp.setOrganizationid(orgId);
		emp.setStatus(1);
		if (!FormUtils.isEmpty(emp))// 管理员不为空..由于此方法一开始就对管理员账号不存在的情况进行了处理,所以此处管理员对象是肯定存在的.
			ed.save(emp);
	}

	/**
	 * 通过分页形式返回指定类型的机构列表
	 * 
	 * @param pr
	 * @param orgType
	 * @return
	 */
	public Map<String, Object> listOrgByType(Pageable pageable, Integer orgType) {
		Map<String, Object> map = new HashMap<>();
		map.put("total", od.count());
		Page<OrganizationPO> orgList = od.findByOrgtype(pageable, orgType);
		List<OrgListVO> list = new ArrayList<>();
		orgList.forEach(o -> {
			OrgListVO orgVO = new OrgListVO();
			orgVO.setOrganizationid(o.getOrganizationid());
			orgVO.setName(o.getName());
			if (o.getOfficeaddressid() != null) {
				orgVO.setCity(ad.findByAddressid(o.getOfficeaddressid()).getCity());
			}
			else
				orgVO.setCity("-");
			orgVO.setCode(o.getCode());
			orgVO.setStatus(o.getStatus());
			list.add(orgVO);
//			System.out.println(list.toString()+"fff");
		});
		map.put("rows", list);
		return map;
	}

	/**
	 * 通过机构类型列出所有的机构
	 * 
	 * @return
	 */
	public List<OrganizationPO> listOrgByType(Integer orgType) {
		return od.findByOrgtype(orgType);
	}

	/**
	 * 与 listOrgByTyep 不同的是此方法只返回 organizationid 和 name.
	 * @param orgType
	 * @return
	 */
	public List<OrganizationPO> listOrgSelectByType(Integer orgType) {
		return od.findAllOrgSelect(orgType);
	}
	
	public List<OrganizationPO> listAllOrg() {
		return od.findAll();
	}

	public List<OrganizationPO> listAllOrgSelect () {
		return od.findAllOrgSelect();
	}

}
