package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.PrivilegePO;
import netscape.security.Privilege;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PrivilegeDAO extends CrudRepository<PrivilegePO, Integer> {

    List<PrivilegePO> findByPrivilegeidIn(List<Integer> privilegeidList);

    List<PrivilegePO> findByParentprivilegeid(Integer privilegeid);

    List<PrivilegePO> findAll();

    List<PrivilegePO> findByCodeAndLabel(String privilgecode, String privilegelabel);

    @Query(value = "SELECT p.* FROM role r\n" +
            "JOIN roleprivilege rp ON r.roleid=rp.roleid\n" +
            "JOIN privilege p ON rp.privilegeid=p.privilegeid WHERE r.roleid =:roleid AND p.label = 'user/typeUserInfo' ",nativeQuery = true)
    PrivilegePO findPoOfInstallerPrivilege(@Param("roleid") Integer roleid);
}
