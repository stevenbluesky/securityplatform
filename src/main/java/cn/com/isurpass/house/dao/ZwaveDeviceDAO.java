package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.ZwaveDevicePO;

@Repository
public interface ZwaveDeviceDAO extends CrudRepository<ZwaveDevicePO, Integer> {
	Page<ZwaveDevicePO> findByDeviceidIn(Pageable pageable,List<String> deviceid);
	List<ZwaveDevicePO> findByDeviceidIn(List<String> deviceid);
	Page<ZwaveDevicePO> findAll(Pageable pageable);
	ZwaveDevicePO findByZwavedeviceid(Integer id);
}
