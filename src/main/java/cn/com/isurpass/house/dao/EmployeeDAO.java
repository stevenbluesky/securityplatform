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
	List<EmployeePO> findAll();
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

	List<EmployeePO> findByLoginnameContainingOrNameContaining(String installer, String installer2);

    Integer countByOrganizationidIn(List<Integer> list);

    Page<EmployeePO> findByNameLike(Pageable pageable, String name);

	Integer countByNameLike(String name);

	Page<EmployeePO> findByOrganizationidInAndNameLike(Pageable pageable, List<Integer> ids, String name);

	Integer countByOrganizationidInAndNameLike(List<Integer> ids, String name);

	Page<EmployeePO> findByNameLikeAndOrganizationid(Pageable pageable, String name, Integer orgid);

	Integer countByNameLikeAndOrganizationid(String name, Integer orgid);

	Page<EmployeePO> findByAddressidIn(Pageable pageable,List<Integer> addressidlist);

	Integer countByAddressidIn(List<Integer> addressidlist);

	Page<EmployeePO> findByNameLikeAndAddressidIn(Pageable pageable, String searchname, List<Integer> addressidlist);

	Integer countByNameLikeAndAddressidIn(String searchname, List<Integer> addressidlist);

    List<EmployeePO> findByLoginnameContaining(String installer);

    Page<EmployeePO> findByAddressidInAndOrganizationidIn(Pageable pageable, List<Integer> addressidlist, List<Integer> childrenOrgid);

	Page<EmployeePO> findByOrganizationidInAndNameLikeAndAddressidIn(Pageable pageable, List<Integer> ids, String name, List<Integer> addressidlist);

	Integer countByOrganizationidInAndNameLikeAndAddressidIn(List<Integer> ids, String name, List<Integer> addressidlist);

	Page<EmployeePO> findByNameLikeAndOrganizationidAndAddressidIn(Pageable pageable, String name, Integer orgid, List<Integer> addressidlist);

	Integer countByNameLikeAndOrganizationidAndAddressidIn(String name, Integer orgid, List<Integer> addressidlist);

    List<EmployeePO> findByOrganizationidAndLoginnameAndStatus(Integer organizationid, String loginname, Integer statusNormal);

	List<EmployeePO> findByAddressidIn(List<Integer> list);
}