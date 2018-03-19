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
import cn.com.isurpass.house.dao.PersonDAO;
import cn.com.isurpass.house.dao.SupplierDAO;
import cn.com.isurpass.house.po.AddressPO;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.po.PersonPO;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.util.PageResult;
import cn.com.isurpass.house.vo.AddSupplierVO;
import cn.com.isurpass.house.vo.SupplierListVO;

@Service
public class SupplierService {

	@Autowired
	SupplierDAO sd;
	@Autowired
	PersonDAO pd;
	@Autowired
	AddressDAO ad;
	@Autowired
	EmployeeDAO ed;

	/**
	 * 添加一个机构
	 * 
	 * @param as
	 */
	public void addSupplier(AddSupplierVO as) {
		if (!FormUtils.checkNull(as)) // 必填项为空时
			throw new RuntimeException("必填字段不能为空!");
		OrganizationPO org = new OrganizationPO();
		FormUtils.copyO2O(org, as);//将一些机构的属性复制到 org 中
		//公司账务地址
		AddressPO bAddress = new AddressPO(as.getBcountry(), as.getBprovince(), as.getBcity(), as.getBdetailaddress(),
				as.getBpostal());
		// 服务商联系人
		PersonPO sPerson = new PersonPO(as.getSname(), as.getSphonenumber(), as.getSemail(), as.getSfax());
		AddressPO csAddress = new AddressPO(as.getCscountry(), as.getCsprovince(), as.getCscity(), "",
				as.getCspostal());
		// 服务商总公司联系人
		PersonPO csPerson = new PersonPO(as.getCspname(), as.getCspphonenumber(), as.getCspemail(), as.getCspfax());
		EmployeePO emp = new EmployeePO(as.getLoginname(), as.getPassword(), as.getQuestion(), as.getAnswer());

		Integer csAId = null;
		Integer bAId = null;
		Integer sPId = null;
		Integer csPId = null;
		
		if (!FormUtils.isEmpty(csAddress))
			 csAId = ad.add(csAddress);
		if (!FormUtils.isEmpty(bAddress))
			bAId = ad.add(bAddress);
		if (!FormUtils.isEmpty(sPerson)) //服务商联系人不为空
			sPId = pd.add(sPerson);
		if (!FormUtils.isEmpty(csPerson))//  服务商总公司联系人不为空
			csPId = pd.add(csPerson);
		if (!FormUtils.isEmpty(emp))// 管理员不为空..由于此方法一开始就对管理员账号不存在的情况进行了处理,所以此处管理员对象是肯定存在的.
			ed.add(emp);
		
		//取到各分支方法返回的主键id后将其存入 org 对象中,然后再进行保存
		org.setBillingaddressid(csAId);
		sd.add(org);
		
	}

	public Map<String, Object> listSupplier(PageResult pr) {
		Map<String, Object> map = new HashMap<>();
		map.put("total", sd.getTotal());
		List<OrganizationPO> org = sd.listOrg(pr);
		List<SupplierListVO> list = new ArrayList<>();
		SupplierListVO supplier = new SupplierListVO();
		org.forEach(o -> {
			supplier.setName(o.getName());
			if (o.getOfficeaddressid() != null)
				supplier.setCity(ad.getCityName(o.getOfficeaddressid()));
			else
				supplier.setCity("");
			supplier.setCode(o.getCode());
			supplier.setStatus(o.getStatus());
			list.add(supplier);
		});
		map.put("rows", sd.listOrg(pr));
		return map;
	}
}
