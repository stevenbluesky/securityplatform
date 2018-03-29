package cn.com.isurpass.house.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.AddressPO;

import java.util.List;


@Repository
@SuppressWarnings("unchecked")
public interface AddressDAO extends CrudRepository<AddressPO,Integer>/* extends BaseDAO */{
	AddressPO findByAddressid(Integer addressid);
	AddressPO save(AddressPO address);

//    Page<AddressDAO> findByCityCode(Pageable pageable,String citycode);

	List<AddressPO> findByCity(String city);

}
