package cn.com.isurpass.house.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.AddressPO;

@Repository
@SuppressWarnings("unchecked")
public interface AddressDAO extends CrudRepository<AddressPO,Integer>/* extends BaseDAO */{
	String findCityByAddressid(Integer officeaddressid);
	
	AddressPO save(AddressPO address);
}
