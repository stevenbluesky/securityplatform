package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.RolePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDAO extends CrudRepository<RolePO, Integer> {

    RolePO findByRoleid(Integer roleid);

    Page<RolePO> findAll(Pageable pageable);

    List<RolePO> findAll();
}
