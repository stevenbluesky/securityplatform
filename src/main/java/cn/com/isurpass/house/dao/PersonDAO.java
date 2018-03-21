package cn.com.isurpass.house.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.PersonPO;

@Repository
public interface PersonDAO extends CrudRepository<PersonPO,Integer>/* extends BaseDAO*/{

	PersonPO save(PersonPO person);
	PersonPO findByPersonid(Integer personid);
}
