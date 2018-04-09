package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.RolePrivilegePO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePrivilegeDAO extends CrudRepository<RolePrivilegePO,Integer>{
    List<RolePrivilegePO> findByRoleid(Integer roleid);
}
