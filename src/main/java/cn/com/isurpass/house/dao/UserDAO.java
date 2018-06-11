package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.UserPO;

@Repository
public interface UserDAO extends CrudRepository<UserPO, Integer> {
    UserPO save(UserPO user);

    Page<UserPO> findAll(Pageable pageable);

    UserPO findByUserid(Integer id);

    List<UserPO> findByOrganizationid(Integer id);

    List<UserPO> findByInstallerorgid(Integer id);

    List<UserPO> findByInstallerid(Integer id);

    List<UserPO> findAll();

    Page<UserPO> findByInstallerorgid(Integer organizationid, Pageable pageable);

    Page<UserPO> findByOrganizationid(Integer organizationid, Pageable pageable);

    Page<UserPO> findByInstallerid(Integer employeeid, Pageable pageable);

    List<UserPO> findByCitycodeContaining(String findCode);

    List<UserPO> findByInstalleridIn(List<Integer> employeeidlist);

    List<UserPO> findByUseridIn(List<Integer> useridlist);

    Integer countByInstallerid(Integer employeeid);

    Integer countByInstallerorgid(Integer organizationid);

    List<UserPO> findAll(Specification<UserPO> specification);

    Page<UserPO> findAll(Specification<UserPO> specification, Pageable pageable);

    Integer count(Specification<UserPO> specification);

    List<UserPO> findByCitycodeIn(List<String> citycodelist);

    List<UserPO> findByOrganizationidIn(List<Integer> orgidlist);

    Page<UserPO> findByUseridIn(List<Integer> ids, Pageable pageable);

    Integer countByUseridIn(List<Integer> ids);

    Page<UserPO> findByUseridInAndInstallerid(List<Integer> ids, Integer employeeid, Pageable pageable);

    Integer countByUseridInAndInstallerid(List<Integer> ids, Integer employeeid);

    Page<UserPO> findByUseridInAndOrganizationid(List<Integer> ids, Integer organizationid, Pageable pageable);

    Integer countByUseridInAndOrganizationid(List<Integer> ids, Integer organizationid);

    Page<UserPO> findByUseridInAndInstallerorgid(List<Integer> ids, Integer organizationid, Pageable pageable);

    Integer countByUseridInAndInstallerorgid(List<Integer> ids, Integer organizationid);

    List<UserPO> findByInstallerorgidIn(List<Integer> orgidlist);

    List<UserPO> findByNameContaining(String searchName);
    @Query(value = "SELECT * FROM user WHERE userid =:userid",nativeQuery = true)
    UserPO findUserByUserid(@Param("userid") Integer userid);

    @Query(value = "select userid from user where loginname=:loginname",nativeQuery = true)
    Integer getUserid(@Param("loginname") String loginname);

    List<UserPO> findByCodepostfixContaining(String searchCode);

    void deleteByUserid(Integer integer);

    UserPO findByAppaccount(String appaccount);

    Integer countByOrganizationid(Integer organizationid);

    List<UserPO> findByAppaccountContaining(String searchAppAccount);

}
