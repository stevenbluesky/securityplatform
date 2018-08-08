package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.GatewayPhonecardPO;
import cn.com.isurpass.house.vo.GatewayPhonecardVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liwenxiang
 * Date:2018/8/2
 * Time:9:51
 */
@Repository
public interface GatewayPhonecardDAO extends CrudRepository<GatewayPhonecardPO,Integer> {

    @Query(value = "SELECT g.deviceid,g.status AS gatewaystatus,p.serialnumber,p.status AS phonecardstatus,p.phonecardid FROM user u \n" +
            "JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gu.deviceid=gp.deviceid\n" +
            "LEFT JOIN phonecard p ON gp.serialnumber=p.serialnumber WHERE g.deviceid=:deviceid",nativeQuery = true)
    List<Object[]> findGatewayPhonecardByDeviceid(@Param("deviceid") String deviceid);

    GatewayPhonecardPO findByDeviceid(String s);

    GatewayPhonecardPO findBySerialnumber(String s);

    void deleteByDeviceid(String mydata);

    void deleteBySerialnumber(String serialnumber);
}
