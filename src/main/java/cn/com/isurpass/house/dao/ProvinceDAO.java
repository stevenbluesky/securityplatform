package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.ProvincePO;

@Repository
//@SuppressWarnings("unchecked")
public interface ProvinceDAO extends CrudRepository<ProvincePO,Integer>/* extends BaseDAO */{
	List<ProvincePO> findByCountryid(Integer countryid);
	ProvincePO findByProvinceid(Integer id);
}
