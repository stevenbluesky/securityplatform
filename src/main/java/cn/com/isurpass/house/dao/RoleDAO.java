package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.PrivilegePO;
import cn.com.isurpass.house.po.RolePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDAO extends CrudRepository<RolePO, Integer> {

    RolePO findByRoleid(Integer roleid);

    Page<RolePO> findAll(Pageable pageable);

    List<RolePO> findAll();
    @Query(value = "SELECT r.* FROM role r\n" +
            "JOIN roleprivilege rp ON r.roleid=rp.roleid\n" +
            "JOIN privilege p ON rp.privilegeid=p.privilegeid WHERE p.label = 'user/typeUserInfo' ",nativeQuery = true)
    List<RolePO> findRolesByPrivilegeOfInstaller();
}
