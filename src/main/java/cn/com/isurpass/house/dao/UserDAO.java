package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.UserPO;

@Repository
public interface UserDAO extends CrudRepository<UserPO,Integer>{
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
	List<UserPO> findByCitycode(String cl);


	List<UserPO> findByInstalleridIn(List<Integer> employeeidlist);

	List<UserPO> findByLoginnameContainingOrNameContaining(String customer, String customer2);
	List<UserPO> findByUseridIn(List<Integer> useridlist);

    Integer countByInstallerid(Integer employeeid);

 //    Integer countAll();

    Integer countByInstallerorgid(Integer organizationid);

    List<UserPO> findByNameContaining(String customer);
}
