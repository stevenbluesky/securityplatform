package cn.com.isurpass.house.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.PhonecardUserPO;

@Repository
public interface PhonecarduserDAO extends CrudRepository<PhonecardUserPO,Integer>{
	PhonecardUserPO save(PhonecardUserPO pcud);
	PhonecardUserPO findByUserid(Integer userid);

	PhonecardUserPO findByPhonecardid(Integer phonecardid);
}
