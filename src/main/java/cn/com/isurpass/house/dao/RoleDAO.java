package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.EmployeeRolePO;
import cn.com.isurpass.house.po.RolePO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends CrudRepository<RolePO,Integer> {
}
