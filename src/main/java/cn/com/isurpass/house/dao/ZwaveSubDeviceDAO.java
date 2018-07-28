package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.ZwaveSubDevicePO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZwaveSubDeviceDAO extends CrudRepository<ZwaveSubDevicePO,Integer> {

    int countByZwavedeviceid(Integer zwavedeviceid);
}
