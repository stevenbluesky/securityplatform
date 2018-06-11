package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.CountryPO;

@Repository
//@SuppressWarnings("unchecked")
public interface CountryDAO extends CrudRepository<CountryPO,Integer>{
	List<CountryPO> findAll();

	CountryPO findByCountryid(Integer id);
}
