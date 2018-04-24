package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.PrivilegePO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PrivilegeDAO extends CrudRepository<PrivilegePO,Integer>{

    List<PrivilegePO> findByPrivilegeidIn(List<Integer> privilegeidList);
	List<PrivilegePO> findByParentprivilegeid(Integer privilegeid);
	List<PrivilegePO> findAll();
}
