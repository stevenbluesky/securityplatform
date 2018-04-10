package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.GatewayPO;

@Repository
public interface GatewayDAO extends CrudRepository<GatewayPO,Integer>{
	GatewayPO save(GatewayPO gw);

	Page<GatewayPO> findAll(Pageable pageable);

	GatewayPO findByDeviceid(Object string);

	List<GatewayPO> findByDeviceidContaining(String deviceid);

	List<GatewayPO> findByDeviceidIn(Pageable pageable, List<String> citynamedeviceidlist);

	List<GatewayPO> findByDeviceidIn(List<String> emptydeviceidlist);


    Long countByDeviceidIn(List<String> citynamedeviceidlist);

    List<GatewayPO> findByNameContaining(String devicename);

}
