package cn.com.isurpass.house.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.EmployeeRolePO;

@Repository
public interface EmployeeroleDAO extends CrudRepository<EmployeeRolePO,Integer>{
	EmployeeRolePO findByEmployeeid(Integer id);
}
