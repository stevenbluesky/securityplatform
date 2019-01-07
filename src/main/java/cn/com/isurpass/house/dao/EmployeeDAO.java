package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.EmployeePO;

@Repository
public interface EmployeeDAO extends CrudRepository<EmployeePO,Integer>{
	@Override
	EmployeePO save(EmployeePO emp);
	
	@Override
	List<EmployeePO> findAll();
	Page<EmployeePO> findAll(Pageable pageable);

	List<EmployeePO> findByOrganizationidAndCodeAndStatusNot(Integer orgid,String code,Integer status);

	@Override
	long count();

	List<EmployeePO> findByOrganizationidAndLoginname(Integer id,String loginname);
	EmployeePO findByLoginname(String loginname);
	EmployeePO findByEmployeeid(Integer id);

	Page<EmployeePO> findByOrganizationidIn(Pageable pageable,List<Integer> list);

    Integer countByOrganizationidIn(List<Integer> list);

    List<EmployeePO> findByOrganizationidAndLoginnameAndStatus(Integer organizationid, String loginname, Integer statusNormal);

    List<EmployeePO> findByNameContaining(String searchinstaller);

    EmployeePO findByEmployeeidAndOrganizationid(Integer empid, Integer orgid);

    EmployeePO findByCodeAndOrganizationid(String code, Integer organizationid);

    EmployeePO findByOrganizationid(Integer organizationid);

	long countByTypeIn(List<Integer> typelist);

	Page<EmployeePO> findByTypeIn(Pageable pageable, List<Integer> typelist);

	List<EmployeePO> findByTypeIn(List<Integer> typelist);

	Page<EmployeePO> findByOrganizationidInAndTypeIn(Pageable pageable, List<Integer> list, List<Integer> typelist);

	List<EmployeePO> findByOrganizationidInAndTypeIn(List<Integer> list, List<Integer> typelist);

	Object countByOrganizationidInAndTypeIn(List<Integer> list, List<Integer> typelist);
}