package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.GatewayBindingPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GatewayBindingDAO extends CrudRepository<GatewayBindingPO,Integer>{

    //List<GatewayBindingPO> findAllByOrganizationid(List<Integer> organizationidlist);
    List<GatewayBindingPO> findAllByOrganizationidInAndBindingtype(List<Integer> serviceprovideridlist,
                                                                   Integer orgtypeSupplier);
    GatewayBindingPO findByDeviceid(String deviceid);

    GatewayBindingPO findByDeviceidAndOrganizationid(String deviceid, Integer organizationid);

    List<GatewayBindingPO> findByOrganizationid(Integer organizationid);

    List<GatewayBindingPO> findByOrganizationidIn(List<Integer> oolist);
}
