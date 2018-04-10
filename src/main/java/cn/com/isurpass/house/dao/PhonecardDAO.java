package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.GatewayPO;
import cn.com.isurpass.house.po.PhonecardPO;
@Repository
public interface PhonecardDAO extends CrudRepository<PhonecardPO,String>{

	PhonecardPO findBySerialnumber(String serialnumber);
	
	PhonecardPO save(PhonecardPO gw);
	
	Page<PhonecardPO> findAll(Pageable pageable);

	PhonecardPO findByPhonecardid(Object string);
	
	//SELECT * FROM  phonecard WHERE serialnumber LIKE '%f%' AND rateplan LIKE '%f%' AND STATUS=1  ORDER BY activationdate ASC LIMIT 0,9;
	Page<PhonecardPO> findByStatusInAndSerialnumberContainingAndRateplanContaining(List<Integer> statuslist,
			String serialnumber, String rateplan, Pageable pageable);


    long countByStatusInAndSerialnumberContainingAndRateplanContaining(List<Integer> statuslist, String serialnumber, String rateplan);

    List<PhonecardPO> findBySerialnumberContaining(String searchSerialnumber);
}
