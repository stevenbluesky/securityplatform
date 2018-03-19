package cn.com.isurpass.house.dao;

import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.PersonPO;

@Repository
public class PersonDAO extends BaseDAO{

	public Integer add(PersonPO person) {
		getSession().save(person);
		return person.getPersonid();
	}
	

}
