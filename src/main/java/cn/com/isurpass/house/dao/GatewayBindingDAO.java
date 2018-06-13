package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.GatewayBindingPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GatewayBindingDAO extends CrudRepository<GatewayBindingPO,Integer>{

    GatewayBindingPO findByDeviceid(String deviceid);

    List<GatewayBindingPO> findByOrganizationid(Integer organizationid);

    void deleteByDeviceid(String  string);
}
