package cn.com.isurpass.house.dao;

import org.springframework.data.repository.CrudRepository;

import cn.com.isurpass.house.po.PhonecardUserPO;

public interface PhonecarduserDAO extends CrudRepository<PhonecardUserPO,Integer>{
	PhonecardUserPO save(PhonecardUserPO pcud);
}
