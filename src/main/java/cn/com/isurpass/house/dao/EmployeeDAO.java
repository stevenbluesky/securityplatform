package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.EmployeePO;

@Repository
public interface EmployeeDAO extends CrudRepository<EmployeePO,Integer>{
	EmployeePO save(EmployeePO emp);
	
//	List<EmployeePO> listAllEmployee(Pageable pageable);
	
	Page<EmployeePO> findAll(Pageable pageable);
/*	public List<EmployeePO> listAllEmployee(PageResult pr) {
		return (List<EmployeePO>) getSession().createCriteria(EmployeePO.class).setFirstResult(pr.getStart())
				.setMaxResults(pr.getRows()).list();
	}*/

	List<EmployeePO> findByOrganizationidAndCodeAndStatusNot(Integer orgid,String code,Integer status);
	long count();
	/*public Integer getTotal() {
		Number count = (Number) getSession()
				.createSQLQuery("select count(employeeid) from employee").uniqueResult();
		if (count != null)
			return count.intValue();
		return 0;
	}*/
	List<EmployeePO> findByOrganizationidAndLoginname(Integer id,String loginname);
	EmployeePO findByLoginname(String loginname);
	EmployeePO findByEmployeeid(Integer id);
	EmployeePO findByLoginnameAndPassword(String loginname, String password);

	EmployeePO findByLoginnameAndPasswordAndOrganizationid(String loginname, String password, Integer organizationid);

	Page<EmployeePO> findByOrganizationidIn(Pageable pageable,List<Integer> list);

    Integer countByOrganizationidIn(List<Integer> list);

    List<EmployeePO> findByNameContaining(String installer);
}