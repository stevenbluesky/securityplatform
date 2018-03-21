package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.OrganizationPO;

@Repository
public interface EmployeeDAO extends CrudRepository<EmployeePO,Integer>{
	EmployeePO save(EmployeePO emp);
	
//	List<EmployeePO> listAllEmployee(Pageable pageable);
	
	Page<EmployeePO> findAll(Pageable pageable);
/*	public List<EmployeePO> listAllEmployee(PageResult pr) {
		return (List<EmployeePO>) getSession().createCriteria(EmployeePO.class).setFirstResult(pr.getStart())
				.setMaxResults(pr.getRows()).list();
	}*/

	List<EmployeePO> findByOrganizationidAndCodeAndStatus(Integer orgid,String code,Integer status);
	long count();
	/*public Integer getTotal() {
		Number count = (Number) getSession()
				.createSQLQuery("select count(employeeid) from employee").uniqueResult();
		if (count != null)
			return count.intValue();
		return 0;
	}*/


}
