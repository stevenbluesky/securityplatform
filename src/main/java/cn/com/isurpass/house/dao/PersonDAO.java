package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.PersonPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDAO extends CrudRepository<PersonPO,Integer>{

	PersonPO save(PersonPO person);
	PersonPO findByPersonid(Integer personid);

}
