package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.util.PageResult;

@Repository
public class EmployeeDAO extends BaseDAO{
	
	public Integer add(EmployeePO emp) {
		getSession().save(emp);
		return emp.getEmployeeid();
	}

	public List<EmployeePO> listAllEmployee(PageResult pr) {
		return (List<EmployeePO>) getSession().createCriteria(EmployeePO.class).setFirstResult(pr.getStart())
				.setMaxResults(pr.getRows()).list();
	}
	
	public Integer getTotal() {
		Number count = (Number) getSession()
				.createSQLQuery("select count(employeeid) from employee").uniqueResult();
		if (count != null)
			return count.intValue();
		return 0;
	}


}
