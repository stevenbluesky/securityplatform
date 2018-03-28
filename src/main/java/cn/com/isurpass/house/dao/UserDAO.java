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
}
