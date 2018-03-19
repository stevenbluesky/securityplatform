package cn.com.isurpass.house.dao;

import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.EmployeePO;

@Repository
public class EmployeeDAO extends BaseDAO{
	
	public Integer add(EmployeePO emp) {
		getSession().save(emp);
		return emp.getEmployeeid();
	}
}
