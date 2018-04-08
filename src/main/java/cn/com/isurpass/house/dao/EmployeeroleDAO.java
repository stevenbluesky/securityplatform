package cn.com.isurpass.house.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.EmployeeRolePO;

import java.util.List;

@Repository
public interface EmployeeroleDAO extends CrudRepository<EmployeeRolePO,Integer>{
	List<EmployeeRolePO> findByEmployeeid(Integer id);
}
