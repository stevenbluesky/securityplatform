package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.PersonPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false)
@Repository
public interface PersonDAO extends CrudRepository<PersonPO,Integer>{

	PersonPO save(PersonPO person);
	PersonPO findByPersonid(Integer personid);

    void deleteByPersonid(Integer personid);
}
