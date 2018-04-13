package cn.com.isurpass.house.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.GatewayUserPO;


@Repository
public interface GatewayuserDAO extends CrudRepository<GatewayUserPO, Integer> {

	List<GatewayUserPO> findByDeviceid(String deviceid);

	List<GatewayUserPO> findByUseridIn(List<Integer> userid);
	// GatewayUserPO findUseridByDeviceid(String deviceid);

	List<GatewayUserPO> findByDeviceidIn(List<String> citynamedeviceidlist);

    List<GatewayUserPO> findByUseridIn(Set<Integer> useridset);

	@Query(value = "SELECT g.deviceid FROM gatewayuser g\n" +
			"\tJOIN USER u ON g.userid = u.userid\n" +
			"\tJOIN employee e ON u.installerid = e.employeeid AND e.status=:status AND e.loginname LIKE :s \n" +
			"\tJOIN  employeerole r ON e.employeeid=r.employeeid AND r.roleid=:role",nativeQuery = true)
	List<String> findDeviceidByInstaller(@Param("s") String s, @Param("role") Integer role, @Param("status") Integer status);

	@Query(value = "SELECT g.deviceid FROM gatewayuser g\n" +
			"\tJOIN USER u ON g.userid = u.userid\n" +
			"\tJOIN organization o ON o.organizationid = u.installerorgid AND o.name LIKE :s AND o.status=:status AND o.orgtype =:orgtype",nativeQuery = true)
	List<String> findDeviceidByInstallerorg(@Param("s") String s, @Param("orgtype") Integer orgtype, @Param("status") Integer status);

	@Query(value = "SELECT g.deviceid FROM gatewayuser g\n" +
			"\tJOIN USER u ON g.userid = u.userid WHERE u.loginname LIKE :s",nativeQuery = true)
	List<String> findDeviceidByCustomer(@Param("s") String s);

	@Query(value = "SELECT g.deviceid FROM gatewayuser g\n" +
			"\tJOIN zwavedevice z ON g.deviceid=z.deviceid AND z.name LIKE :s",nativeQuery = true)
	List<String> findDeviceidByDevicename(@Param("s") String s);

	@Query(value = "SELECT g.deviceid FROM gatewayuser g\n" +
			"\tJOIN USER u ON g.userid = u.userid WHERE u.citycode LIKE :s",nativeQuery = true)
	List<String> findDeviceidByCitycode(@Param("s") String s);

	@Query(value = "SELECT g.deviceid FROM gatewayuser g\n" +
			"\tJOIN USER u ON g.userid=u.userid \n" +
			"\tJOIN city c ON u.citycode  = c.citycode AND c.cityname LIKE :s",nativeQuery = true)
	List<String> findDeviceidByCityname(@Param("s") String s);

	@Query(value = "SELECT g.deviceid FROM gatewayuser g\n" +
			"\tJOIN USER u ON g.userid = u.userid\n" +
			"\tJOIN organization o ON o.organizationid = u.organizationid AND o.name LIKE :s AND o.status=:status AND o.orgtype =:orgtype",nativeQuery = true)
	List<String> findDeviceidBySupplier(@Param("s") String s, @Param("status") Integer status, @Param("orgtype") Integer orgtype);

	@Query(value = "SELECT g.deviceid FROM gatewayuser g\n" +
			"\tJOIN USER u ON g.userid = u.userid AND u.organizationid= :id\n" +
			"\tJOIN organization o ON o.organizationid = u.organizationid AND o.status=:status AND o.orgtype =:orgtype",nativeQuery = true)
	List<String> findDeviceidByOwnSupplier(@Param("id") Integer id, @Param("status") Integer status, @Param("orgtype") Integer orgtype);

	@Query(value = "SELECT g.deviceid FROM gatewayuser g\n" +
			"\tJOIN USER u ON g.userid = u.userid AND u.installerid=:id",nativeQuery = true)
	List<String> findDeviceidByOwnInstaller(@Param("id") Integer id);

	@Query(value = "SELECT g.deviceid FROM gatewayuser g\n" +
			"\tJOIN USER u ON g.userid = u.userid AND u.installerorgid=:id",nativeQuery = true)
	List<String> findDeviceidByOwnInstallerorg(@Param("id") Integer id);
}
