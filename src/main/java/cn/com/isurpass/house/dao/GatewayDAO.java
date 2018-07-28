package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.GatewayPO;
import cn.com.isurpass.house.vo.TypeGatewayInfoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayDAO extends CrudRepository<GatewayPO,Integer>{
	GatewayPO save(GatewayPO gw);

	Page<GatewayPO> findAll(Pageable pageable);

	GatewayPO findByDeviceid(Object string);

	List<GatewayPO> findByDeviceidContaining(String deviceid);

	List<GatewayPO> findByDeviceidIn(Pageable pageable, List<String> citynamedeviceidlist);


    Long countByDeviceidIn(List<String> citynamedeviceidlist);

	@Query(value = "SELECT deviceid FROM gateway WHERE deviceid LIKE :s",nativeQuery = true)
    List<String> findDeviceidByDeviceid(@Param("s") String s);
	//ameta
	@Query(value = "SELECT g.deviceid as deviceid,IFNULL(g.name,'') AS name,g.status as status,IFNULL(u.appaccount,'') AS customer,IFNULL(c.cityname,'')AS cityname,IFNULL(o1.name,'') AS serviceprovider,IFNULL(o2.name,'') AS installerorg,IFNULL(e.loginname,'') AS installer FROM gateway g\n" +
			"\tLEFT JOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tLEFT JOIN user u ON u.userid=gu.userid\n" +
			"\tLEFT JOIN city c ON u.citycode=c.citycode\n" +
			"\tLEFT JOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tLEFT JOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	List<Object[]> findInforByPage(Pageable pageable);
	//服务商
	@Query(value = "SELECT g.deviceid as deviceid,IFNULL(g.name,'') AS name,g.status as status,IFNULL(u.appaccount,'') AS customer,IFNULL(c.cityname,'')AS cityname,IFNULL(o1.name,'') AS serviceprovider,IFNULL(o2.name,'') AS installerorg,IFNULL(e.loginname,'') AS installer FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.organizationid=:id\n" +
			"\tLEFT JOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	List<Object[]> findInfoBySupplier(@Param("id") Integer id, Pageable pageable);
	@Query(value = "SELECT COUNT(*) FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.organizationid=:id\n" +
			"\tLEFT JOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	Long countBySupplier(@Param("id")Integer id);
	//安装员
	@Query(value = "SELECT g.deviceid as deviceid,IFNULL(g.name,'') AS name,g.status as status,IFNULL(u.appaccount,'') AS customer,IFNULL(c.cityname,'')AS cityname,IFNULL(o1.name,'') AS serviceprovider,IFNULL(o2.name,'') AS installerorg,IFNULL(e.loginname,'') AS installer FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.installerid=:id\n" +
			"\t left JOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	List<Object[]> findInfoByInstaller(@Param("id")Integer id, Pageable pageable);
	@Query(value = "SELECT COUNT(*) FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.installerid=:id\n" +
			"\t LEFT JOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	Long countByInstaller(@Param("id")Integer id);
	//安装商
	@Query(value = "SELECT g.deviceid as deviceid,IFNULL(g.name,'') AS name,g.status as status,IFNULL(u.appaccount,'') AS customer,IFNULL(c.cityname,'')AS cityname,IFNULL(o1.name,'') AS serviceprovider,IFNULL(o2.name,'') AS installerorg,IFNULL(e.loginname,'') AS installer FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.installerorgid=:id\n" +
			"\tLEFT JOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	List<Object[]> findInfoByInstallerorg(@Param("id")Integer id, Pageable pageable);
	@Query(value = "SELECT COUNT(*) FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.installerorgid=:id\n" +
			"\tLEFT JOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	Long countByInstallerorg(@Param("id")Integer id);

    void deleteByDeviceid(String s);

    List<GatewayPO> findByAppaccount(String appaccount);

    @Query(value = "SELECT count(*) from (SELECT count(*) FROM\n" +
			"gateway g LEFT JOIN zwavedevice z ON g.deviceid=z.deviceid \n" +
			"LEFT JOIN gatewayuser gu ON g.deviceid=gu.deviceid \n" +
			"LEFT JOIN user u ON gu.userid=u.userid\n" +
			"LEFT JOIN city c ON u.citycode=c.citycode\n" +
			"LEFT JOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"LEFT JOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"LEFT JOIN employee e ON u.installerid=e.employeeid \n" +
			"WHERE g.deviceid LIKE :deviceid AND z.name LIKE :name AND c.cityname LIKE :cityname AND o1.name LIKE :serviceprovider AND o2.name LIKE :installerorg AND e.loginname LIKE :installer AND u.appaccount LIKE :customer  \n" +
			"GROUP BY g.deviceid) as a ",nativeQuery = true)
    long countAllGateway(@Param("deviceid")String deviceid,@Param("cityname") String cityname,@Param("name") String name,@Param("serviceprovider") String serviceprovider,@Param("installerorg") String installerorg, @Param("installer")String installer,@Param("customer") String customer);

	@Query(value = "SELECT g.deviceid ,g.status,g.name ,c.cityname,o1.name as sup,o2.name as ins,e.loginname,u.appaccount FROM \n" +
			"gateway g LEFT JOIN zwavedevice z ON g.deviceid=z.deviceid \n" +
			"LEFT JOIN gatewayuser gu ON g.deviceid=gu.deviceid \n" +
			"LEFT JOIN user u ON gu.userid=u.userid\n" +
			"LEFT JOIN city c ON u.citycode=c.citycode\n" +
			"LEFT JOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"LEFT JOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"LEFT JOIN employee e ON u.installerid=e.employeeid \n" +
			"WHERE g.deviceid LIKE :deviceid  AND z.name LIKE :name  AND c.cityname LIKE :cityname AND o1.name LIKE :serviceprovider  AND o2.name LIKE :installerorg "+
			" AND e.loginname LIKE :installer AND u.appaccount LIKE :customer   \n" +
			"GROUP BY g.deviceid",nativeQuery = true)
	List<Object[]> findAllGateway(@Param("deviceid")String deviceid,@Param("cityname") String cityname,@Param("name") String name,@Param("serviceprovider") String serviceprovider,@Param("installerorg") String installerorg, @Param("installer")String installer,@Param("customer") String customer, Pageable pageable);


	@Query(value="SELECT g.deviceid as deviceid,IFNULL(g.name,'') AS name,g.status as status,IFNULL(u.appaccount,'') AS customer,IFNULL(c.cityname,'')AS cityname,IFNULL(o1.name,'') AS serviceprovider,IFNULL(o2.name,'') AS installerorg,IFNULL(e.loginname,'') AS installer FROM \n" +
			"gateway g   \n" +
			"JOIN gatewayuser gu ON g.deviceid=gu.deviceid \n" +
			"JOIN USER u ON gu.userid=u.userid\n" +
			"JOIN organization o ON u.monitoringstationid=o.organizationid OR u.organizationid = :organizationid \n" +
			"LEFT JOIN city c ON u.citycode=c.citycode\n" +
			"LEFT JOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"LEFT JOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"LEFT JOIN employee e ON u.installerid=e.employeeid \n" +
			"WHERE o.organizationid =:organizationid  \n" +
			"GROUP BY g.deviceid",nativeQuery = true)
	List<Object[]> findAllGatewayByMonitoringStation(@Param("organizationid") Integer organizationid, Pageable pageable);

	@Query(value="select count(*) from (SELECT count(*) FROM \n" +
			"gateway g   \n" +
			"JOIN gatewayuser gu ON g.deviceid=gu.deviceid \n" +
			"JOIN USER u ON gu.userid=u.userid\n" +
			"JOIN organization o ON u.monitoringstationid=o.organizationid OR u.organizationid = :organizationid \n" +
			"LEFT JOIN city c ON u.citycode=c.citycode\n" +
			"LEFT JOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"LEFT JOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"LEFT JOIN employee e ON u.installerid=e.employeeid \n" +
			"WHERE o.organizationid =:organizationid  \n" +
			"GROUP BY g.deviceid) as b",nativeQuery = true)
	Long countByMonitoringStation(@Param("organizationid")Integer organizationid);
}
