package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.AddressPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
@Repository
public interface AddressDAO extends CrudRepository<AddressPO,Integer>{
	AddressPO findByAddressid(Integer addressid);

	AddressPO save(AddressPO address);

	void deleteByAddressid(Integer addressid);
}