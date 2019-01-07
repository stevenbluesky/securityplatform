package cn.com.isurpass.house.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.EmployeeRolePO;

import java.util.List;

@Repository
public interface EmployeeroleDAO extends CrudRepository<EmployeeRolePO,Integer>{
	List<EmployeeRolePO>  findByEmployeeid(Integer employeeid);

	List<EmployeeRolePO> findAll();

    void deleteByEmployeeid(Integer employeeid);

    @Query(value = "SELECT employeeid FROM employeerole WHERE roleid in :roleidlist",nativeQuery = true)
    List<Integer> findEmployeeidByRoleidIn(@Param("roleidlist")List<Integer> roleidlist);

}
