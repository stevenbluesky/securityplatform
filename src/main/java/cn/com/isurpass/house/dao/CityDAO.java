package cn.com.isurpass.house.dao;

import java.util.List;

import cn.com.isurpass.house.po.AddressPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.CityPO;

@Repository
public interface CityDAO extends CrudRepository<CityPO, Integer> {
    List<CityPO> findByProvinceid(Integer provinceid);

    CityPO findByCityid(Integer id);

    CityPO findByCitycode(String citycode);

    List<CityPO> findByCitynameContaining(String cityname);

    List<CityPO> findByCitynameLike(String city1);
}
