package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.GatewayPO;
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

	List<GatewayPO> findByDeviceidIn(List<String> emptydeviceidlist);


    Long countByDeviceidIn(List<String> citynamedeviceidlist);

    List<GatewayPO> findByNameContaining(String devicename);

	List<GatewayPO> findByDeviceidIn(List<String> orgglist, Pageable pageable);

	@Query(value = "SELECT deviceid FROM gateway WHERE deviceid LIKE :s",nativeQuery = true)
    List<String> findDeviceidByDeviceid(@Param("s") String s);
	//ameta
	@Query(value = "SELECT g.deviceid as deviceid,IFNULL(g.name,'') AS name,g.status as status,IFNULL(u.loginname,'') AS customer,IFNULL(c.cityname,'')AS cityname,IFNULL(o1.name,'') AS serviceprovider,IFNULL(o2.name,'') AS installerorg,IFNULL(e.loginname,'') AS installer FROM gateway g\n" +
			"\tLEFT JOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tLEFT JOIN user u ON u.userid=gu.userid\n" +
			"\tLEFT JOIN city c ON u.citycode=c.citycode\n" +
			"\tLEFT JOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tLEFT JOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	List<Object[]> findInforByPage(Pageable pageable);
	//服务商
	@Query(value = "SELECT g.deviceid as deviceid,IFNULL(g.name,'') AS name,g.status as status,IFNULL(u.loginname,'') AS customer,IFNULL(c.cityname,'')AS cityname,IFNULL(o1.name,'') AS serviceprovider,IFNULL(o2.name,'') AS installerorg,IFNULL(e.loginname,'') AS installer FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.organizationid=:id\n" +
			"\tJOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	List<Object[]> findInfoBySupplier(@Param("id") Integer id, Pageable pageable);
	@Query(value = "SELECT COUNT(*) FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.organizationid=:id\n" +
			"\tJOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	Long countBySupplier(@Param("id")Integer id);
	//安装员
	@Query(value = "SELECT g.deviceid as deviceid,IFNULL(g.name,'') AS name,g.status as status,IFNULL(u.loginname,'') AS customer,IFNULL(c.cityname,'')AS cityname,IFNULL(o1.name,'') AS serviceprovider,IFNULL(o2.name,'') AS installerorg,IFNULL(e.loginname,'') AS installer FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.installerid=:id\n" +
			"\tJOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	List<Object[]> findInfoByInstaller(@Param("id")Integer id, Pageable pageable);
	@Query(value = "SELECT COUNT(*) FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.installerid=:id\n" +
			"\tJOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	Long countByInstaller(@Param("id")Integer id);
	//安装商
	@Query(value = "SELECT g.deviceid as deviceid,IFNULL(g.name,'') AS name,g.status as status,IFNULL(u.loginname,'') AS customer,IFNULL(c.cityname,'')AS cityname,IFNULL(o1.name,'') AS serviceprovider,IFNULL(o2.name,'') AS installerorg,IFNULL(e.loginname,'') AS installer FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.installerorgid=:id\n" +
			"\tJOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	List<Object[]> findInfoByInstallerorg(@Param("id")Integer id, Pageable pageable);
	@Query(value = "SELECT COUNT(*) FROM gateway g\n" +
			"\tJOIN gatewayuser gu ON g.deviceid=gu.deviceid\n" +
			"\tJOIN user u ON u.userid=gu.userid AND u.installerorgid=:id\n" +
			"\tJOIN city c ON u.citycode=c.citycode\n" +
			"\tJOIN organization o1 ON u.organizationid=o1.organizationid\n" +
			"\tJOIN organization o2 ON u.installerorgid=o2.organizationid\n" +
			"\tLEFT JOIN employee e ON u.installerid=e.employeeid\n",nativeQuery = true)
	Long countByInstallerorg(@Param("id")Integer id);
}
