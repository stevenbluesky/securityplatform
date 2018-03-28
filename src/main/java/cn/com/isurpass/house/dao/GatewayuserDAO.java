package cn.com.isurpass.house.dao;

import java.util.List;

import cn.com.isurpass.house.po.ZwaveDevicePO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.GatewayUserPO;


@Repository
public interface GatewayuserDAO extends CrudRepository<GatewayUserPO, Integer> {

	GatewayUserPO findByDeviceid(String deviceid);

	List<GatewayUserPO> findByUseridIn(List<Integer> userid);

    List<GatewayUserPO> findByDeviceidIn(List<String> zdevicelist);
    // GatewayUserPO findUseridByDeviceid(String deviceid);
}
