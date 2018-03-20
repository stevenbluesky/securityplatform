package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.CityPO;

@Repository
public interface CityDAO extends CrudRepository<CityPO,Integer>{
	List<CityPO> findByProvinceid(Integer provinceid);
}
