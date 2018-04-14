package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.ZwaveDevicePO;

@Repository
public interface ZwaveDeviceDAO extends CrudRepository<ZwaveDevicePO, Integer> {
    Page<ZwaveDevicePO> findByDeviceidIn(Pageable pageable, List<String> deviceid);

    List<ZwaveDevicePO> findByDeviceidIn(List<String> deviceid);

    Page<ZwaveDevicePO> findAll(Pageable pageable);

    ZwaveDevicePO findByZwavedeviceid(Integer id);

    List<ZwaveDevicePO> findByNameContaining(String devicename);

    List<ZwaveDevicePO> findByDeviceid(String deviceid);

    Integer countByDeviceidIn(List<String> filterlist);

    List<ZwaveDevicePO> findByDeviceidContaining(String searchgatewayid);

    Page<ZwaveDevicePO> findByZwavedeviceidIn(List<Integer> ids, Pageable pageable);

    Integer countByZwavedeviceidIn(List<Integer> ids);

    Page<ZwaveDevicePO> findAll(Specification<ZwaveDevicePO> specification, Pageable pageable);

    long count();

    @Query(value = "select z.zwavedeviceid as zwavedeviceid, z.name as name,z.devicetype as devicetype,z.warningstatuses as warningstatuses,z.status as status,z.battery as battery,c.cityname as city,o.name as organizationname,o1.name as installerorgname,e.name as installername,u.name as username " +
            " from zwavedevice z" +
            " join gatewayuser gu on z.deviceid =gu.deviceid" +
            " join user u on gu.userid=u.userid" +
            " join city c on u.citycode=c.citycode" +
            " join organization o on o.organizationid = u.organizationid" +
            " join organization o1 on o1.organizationid = u.installerorgid" +
            " join employee e on e.employeeid = u.installerid LIMIT :page,:row", nativeQuery = true)
    List<Object[]> listZwaveDeviceListVO(@Param("page") Integer page, @Param("row") Integer row);

    @Query(value = "select z.zwavedeviceid as zwavedeviceid, z.name as name,z.devicetype as devicetype,z.warningstatuses as warningstatuses,z.status as status,z.battery as battery,c.cityname as city,o.name as organizationname,o1.name as installerorgname,e.name as installername,u.name as username " +
            " from zwavedevice z" +
            " join gatewayuser gu on z.deviceid =gu.deviceid" +
            " join user u on gu.userid=u.userid" +
            " join city c on u.citycode=c.citycode" +
            " join organization o on o.organizationid = u.organizationid" +
            " join organization o1 on o1.organizationid = u.installerorgid" +
            " join employee e on e.employeeid = u.installerid where z.zwavedeviceid in :list LIMIT :page,:row", nativeQuery = true)
    List<Object[]> listZwaveDeviceListVOList(@Param("page") Integer page, @Param("row") Integer row,@Param("list")List<Integer> list);

    @Query(value = "select COUNT(z.zwavedeviceid) from zwavedevice z " +
            " join gatewayuser gu on z.deviceid =gu.deviceid" +
            " join user u on gu.userid=u.userid" +
            " join city c on u.citycode=c.citycode" +
            " join organization o on o.organizationid = u.organizationid" +
            " join organization o1 on o1.organizationid = u.installerorgid" +
            " join employee e on e.employeeid = u.installerid", nativeQuery = true)
    long getCountDeviceListVO();

    @Query(value = "SELECT z.zwavedeviceid AS zwavedeviceid,z. NAME AS NAME,z.devicetype AS devicetype,z.warningstatuses AS warningstatuses," +
            " z. STATUS AS STATUS,z.battery AS battery,c.cityname AS city,o. NAME AS organizationname,o1. NAME AS installerorgname," +
            " e. NAME AS installername,u. NAME AS username" +
            " FROM zwavedevice z" +
            " JOIN gatewayuser gu ON z.deviceid = gu.deviceid" +
            " JOIN user u ON gu.userid = u.userid" +
            " JOIN city c ON u.citycode = c.citycode" +
            " JOIN organization o ON o.organizationid = u.organizationid" +
            " JOIN organization o1 ON o1.organizationid = u.installerorgid" +
            " JOIN employee e ON e.employeeid = u.installerid" +
            " where u.organizationid=:orgid" +
            " LIMIT :page,:row", nativeQuery = true)
    List<Object[]> listZwaveDeviceListVOSupplier(@Param("orgid") Integer orgid, @Param("page") Integer page, @Param("row") Integer row);

    @Query(value = "SELECT count(z.zwavedeviceid)" +
            "FROM zwavedevice z" +
            " JOIN gatewayuser gu ON z.deviceid = gu.deviceid" +
            " JOIN user u ON gu.userid = u.userid" +
            " JOIN city c ON u.citycode = c.citycode" +
            " JOIN organization o ON o.organizationid = u.organizationid" +
            " JOIN organization o1 ON o1.organizationid = u.installerorgid" +
            " JOIN employee e ON e.employeeid = u.installerid" +
            " where u.organizationid=:orgid ", nativeQuery = true)
    long getCountDeviceListVOSupplier(@Param("orgid") Integer orgid);

    @Query(value = "SELECT z.zwavedeviceid AS zwavedeviceid,z. NAME AS NAME,z.devicetype AS devicetype,z.warningstatuses AS warningstatuses," +
            " z. STATUS AS STATUS,z.battery AS battery,c.cityname AS city,o. NAME AS organizationname,o1. NAME AS installerorgname," +
            " e. NAME AS installername,u. NAME AS username" +
            " FROM zwavedevice z" +
            " JOIN gatewayuser gu ON z.deviceid = gu.deviceid" +
            " JOIN user u ON gu.userid = u.userid" +
            " JOIN city c ON u.citycode = c.citycode" +
            " JOIN organization o ON o.organizationid = u.organizationid" +
            " JOIN organization o1 ON o1.organizationid = u.installerorgid" +
            " JOIN employee e ON e.employeeid = u.installerid" +
            " where u.installerorgid=:orgid" +
            " LIMIT :page,:row", nativeQuery = true)
    List<Object[]> listZwaveDeviceListVOInstallerorg(@Param("orgid") Integer orgid, @Param("page") Integer page, @Param("row") Integer row);

    @Query(value = "SELECT count(z.zwavedeviceid)" +
            " FROM zwavedevice z" +
            " JOIN gatewayuser gu ON z.deviceid = gu.deviceid" +
            " JOIN user u ON gu.userid = u.userid" +
            " JOIN city c ON u.citycode = c.citycode" +
            " JOIN organization o ON o.organizationid = u.organizationid" +
            " JOIN organization o1 ON o1.organizationid = u.installerorgid" +
            " JOIN employee e ON e.employeeid = u.installerid" +
            " where u.installerorgid=:orgid ", nativeQuery = true)
    long getCountDeviceListVOInstallerorg(@Param("orgid") Integer orgid);


    @Query(value = "SELECT count(z.zwavedeviceid)" +
            " FROM zwavedevice z" +
            " JOIN gatewayuser gu ON z.deviceid = gu.deviceid" +
            " JOIN user u ON gu.userid = u.userid" +
            " JOIN city c ON u.citycode = c.citycode" +
            " JOIN organization o ON o.organizationid = u.organizationid" +
            " JOIN organization o1 ON o1.organizationid = u.installerorgid" +
            " JOIN employee e ON e.employeeid = u.installerid" +
            " where u.installerid=:empid ", nativeQuery = true)
    long getCountDeviceListVOInstaller(@Param("empid") Integer empid);

    @Query(value = "SELECT z.zwavedeviceid AS zwavedeviceid,z. NAME AS NAME,z.devicetype AS devicetype,z.warningstatuses AS warningstatuses," +
            " z. STATUS AS STATUS,z.battery AS battery,c.cityname AS city,o. NAME AS organizationname,o1. NAME AS installerorgname," +
            " e. NAME AS installername,u. NAME AS username" +
            " FROM zwavedevice z" +
            " JOIN gatewayuser gu ON z.deviceid = gu.deviceid" +
            " JOIN user u ON gu.userid = u.userid" +
            " JOIN city c ON u.citycode = c.citycode" +
            " JOIN organization o ON o.organizationid = u.organizationid" +
            " JOIN organization o1 ON o1.organizationid = u.installerorgid" +
            " JOIN employee e ON e.employeeid = u.installerid" +
            " where u.installerid=:empid" +
            " LIMIT :page,:row", nativeQuery = true)
    List<Object[]> listZwaveDeviceListVOInstaller(@Param("empid") Integer empid, @Param("page") Integer page, @Param("row") Integer row);

    @Query(value = "select z.zwavedeviceid from zwavedevice z join gatewayuser gu on z.deviceid = gu.deviceid join user u on gu.userid = u.userid where u.organizationid = :orgid", nativeQuery = true)
    List<Integer> listZwavedeivceidBySupplier(@Param("orgid") Integer orgid);

    @Query(value = "select z.zwavedeviceid from zwavedevice z join gatewayuser gu on z.deviceid = gu.deviceid join user u on gu.userid = u.userid where u.installerorgid = :orgid", nativeQuery = true)
    List<Integer> listZwavedeivceidByInstallerorg(@Param("orgid") Integer orgid);

    @Query(value = "select z.zwavedeviceid from zwavedevice z join gatewayuser gu on z.deviceid = gu.deviceid join user u on gu.userid = u.userid where u.installerid = :empid", nativeQuery = true)
    List<Integer> listZwavedeivceidByInstaller(@Param("empid") Integer empid);

    @Query(value = "select z.zwavedeviceid from zwavedevice z join gatewayuser gu on z.deviceid = gu.deviceid join user u on gu.userid = u.userid join city c on u.citycode = c.citycode where c.cityname LIKE :cityname",nativeQuery = true)
    List<Integer> listByCityname(@Param("cityname")String cityname);

    @Query(value = "select z.zwavedeviceid from zwavedevice z join gatewayuser gu on z.deviceid = gu.deviceid join user u on gu.userid = u.userid join city c on u.citycode = c.citycode where c.citycode LIKE :citycode ",nativeQuery = true)
    List<Integer> listByCitycode(@Param("citycode")String citycode);

    @Query(value = "select z.zwavedeviceid from zwavedevice z join gatewayuser gu on z.deviceid = gu.deviceid join user u on gu.userid = u.userid where u.name LIKE :name ",nativeQuery = true)
    List<Integer> listByCusumer(@Param("name")String name);

    @Query(value = "select z.zwavedeviceid from zwavedevice z join gatewayuser gu on z.deviceid = gu.deviceid join user u on gu.userid = u.userid join organization o on u.organizationid = o.organizationid where o.name LIKE :name ",nativeQuery = true)
    List<Integer> listBySupplier(@Param("name")String name);

    @Query(value = "select z.zwavedeviceid from zwavedevice z join gatewayuser gu on z.deviceid = gu.deviceid join user u on gu.userid = u.userid join organization o on u.installerorgid = o.organizationid where o.name LIKE :name ",nativeQuery = true)
    List<Integer> listByInstallerorg(@Param("name")String name);

    @Query(value = "select z.zwavedeviceid from zwavedevice z join gatewayuser gu on z.deviceid = gu.deviceid join user u on gu.userid = u.userid join employee e on u.installerid = e.employeeid where e.name LIKE :name ",nativeQuery = true)
    List<Integer> listByInstaller(@Param("name")String name);

}

