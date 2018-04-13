package cn.com.isurpass.house.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.GatewayUserPO;


@Repository
public interface GatewayuserDAO extends CrudRepository<GatewayUserPO, Integer> {

	List<GatewayUserPO> findByDeviceid(String deviceid);//一个deviceid只能绑定一个用户

	List<GatewayUserPO> findByUseridIn(List<Integer> userid);
	// GatewayUserPO findUseridByDeviceid(String deviceid);

	List<GatewayUserPO> findByDeviceidIn(List<String> citynamedeviceidlist);

    List<GatewayUserPO> findByUseridIn(Set<Integer> useridset);
}
