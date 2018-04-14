package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.AddressPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressDAO extends CrudRepository<AddressPO,Integer>/* extends BaseDAO */{
	AddressPO findByAddressid(Integer addressid);
	AddressPO save(AddressPO address);

//    Page<AddressDAO> findByCityCode(Pageable pageable,String citycode);

	List<AddressPO> findByCity(String city);

    List<AddressPO> findByCityIn(List<String> citynamelist);

    List<AddressPO> findByCityContaining(String searchCity);
}