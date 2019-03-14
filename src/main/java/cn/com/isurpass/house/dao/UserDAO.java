package cn.com.isurpass.house.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import cn.com.isurpass.house.po.UserPO;

@Repository
public interface UserDAO extends CrudRepository<UserPO, Integer> {
    UserPO save(UserPO user);

    Page<UserPO> findAll(Pageable pageable);

    UserPO findByUserid(Integer id);

    List<UserPO> findByOrganizationid(Integer id);

    List<UserPO> findByInstallerorgid(Integer id);

    List<UserPO> findByInstallerid(Integer id);

    List<UserPO> findAll();

    Page<UserPO> findByOrganizationid(Integer organizationid, Pageable pageable);

    List<UserPO> findByCitycodeContaining(String findCode);

    List<UserPO> findByInstalleridIn(List<Integer> employeeidlist);

    List<UserPO> findByUseridIn(List<Integer> useridlist);

    List<UserPO> findAll(Specification<UserPO> specification);

    Page<UserPO> findAll(Specification<UserPO> specification, Pageable pageable);

    Integer count(Specification<UserPO> specification);

    List<UserPO> findByCitycodeIn(List<String> citycodelist);

    List<UserPO> findByOrganizationidIn(List<Integer> orgidlist);

    List<UserPO> findByInstallerorgidIn(List<Integer> orgidlist);

    List<UserPO> findByNameContaining(String searchName);
    @Query(value = "SELECT * FROM user WHERE userid =:userid",nativeQuery = true)
    UserPO findUserByUserid(@Param("userid") Integer userid);

    @Query(value = "select userid from user where loginname=:loginname",nativeQuery = true)
    Integer getUserid(@Param("loginname") String loginname);

    void deleteByUserid(Integer integer);

    UserPO findByAppaccount(String appaccount);

    List<UserPO> findByAppaccountContaining(String searchAppAccount);

    List<UserPO> findByUsercodeContaining(String searchCode);

    int countByMonitoringstationid(Integer organizationid);

    @Query(value = "SELECT o.name as oname,p.status as pstatus,p.activationdate,gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid " +
            "LEFT JOIN organization o ON u.monitoringstationid=o.organizationid\n" +
            "LEFT JOIN phonecard p ON gp.serialnumber=p.serialnumber order by u.userid desc limit :size1 ,:number1 ",nativeQuery = true)
    List<Object[]> findUserWithGateway(@Param("size1")int size, @Param("number1")int number);

    @Query(value = "SELECT gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid order by u.userid desc ",nativeQuery = true)
    List<Object[]> findUserWithGateway();

    @Query(value = "SELECT count(*) FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid \n" +
            "left JOIN gateway g ON gu.deviceid=g.deviceid",nativeQuery = true)
    Integer countUserWithGateway();

    @Query(value = "SELECT o.name as oname,p.status as pstatus,p.activationdate,g.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM user u \n" +
            "LEFT JOIN gatewayuser gu ON u.userid=gu.userid " +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid " +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid " +
            "LEFT JOIN organization o ON u.monitoringstationid=o.organizationid\n" +
            "LEFT JOIN phonecard p ON gp.serialnumber=p.serialnumber where u.installerid=:employeeid order by u.userid desc limit :size1 ,:number1 ",nativeQuery = true)
    List<Object[]> findUserByInstallerid(@Param("employeeid")Integer employeeid, @Param("size1")int size, @Param("number1")int number);

    @Query(value = "SELECT g.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM user u \n" +
            "LEFT JOIN gatewayuser gu ON u.userid=gu.userid " +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid " +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid where u.installerid=:employeeid order by u.userid desc ",nativeQuery = true)
    List<Object[]> findUserByInstallerid(@Param("employeeid")Integer employeeid);

    @Query(value = "SELECT count(1) FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid \n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid where u.installerid=:employeeid",nativeQuery = true)
    Integer countUserByInstallerid(@Param("employeeid")Integer employeeid);

    @Query(value = "SELECT o.name as oname,p.status as pstatus,p.activationdate,gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid " +
            "LEFT JOIN organization o ON u.monitoringstationid=o.organizationid\n" +
            "LEFT JOIN phonecard p ON gp.serialnumber=p.serialnumber where u.installerorgid=:installerorg order by u.userid desc limit :size1 ,:number1 ",nativeQuery = true)
    List<Object[]> findUserByInstallerorg(@Param("installerorg") Integer installerorg,@Param("size1")int size, @Param("number1")int number);

    @Query(value = "SELECT gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid where u.installerorgid=:installerorg order by u.userid desc ",nativeQuery = true)
    List<Object[]> findUserByInstallerorg(@Param("installerorg") Integer installerorg);

    @Query(value = "SELECT count(*) FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid \n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid where u.installerorgid=:installerorg",nativeQuery = true)
    Integer countUserByInstallerorg(@Param("installerorg") Integer installerorg);

    @Query(value = "SELECT o.name as oname,p.status as pstatus,p.activationdate,gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid " +
            "LEFT JOIN organization o ON u.monitoringstationid=o.organizationid\n" +
            "LEFT JOIN phonecard p ON gp.serialnumber=p.serialnumber where u.organizationid=:organizationid order by u.userid desc limit :size1 ,:number1 ",nativeQuery = true)
    List<Object[]> findUserByOrganizationorg(@Param("organizationid") Integer organizationid, @Param("size1")int size, @Param("number1")int number);

    @Query(value = "SELECT gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid where u.organizationid=:organizationid order by u.userid desc ",nativeQuery = true)
    List<Object[]> findUserByOrganizationorg(@Param("organizationid") Integer organizationid);

    @Query(value = "SELECT count(*) FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid \n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid where u.organizationid=:organizationid",nativeQuery = true)
    Integer countUserByOrganizationorg(@Param("organizationid")Integer organizationid);

    @Query(value = "SELECT o.name as oname,p.status as pstatus,p.activationdate,gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid " +
            "LEFT JOIN organization o ON u.monitoringstationid=o.organizationid\n" +
            "LEFT JOIN phonecard p ON gp.serialnumber=p.serialnumber where u.userid in :ids and u.installerid=:employeeid order by u.userid desc limit :size1 ,:number1  ",nativeQuery = true)
    List<Object[]> findByUserlistAndInstalleridS(@Param("ids")List<Integer> ids, @Param("employeeid")Integer employeeid, @Param("size1")int size, @Param("number1")int number);

    @Query(value = "SELECT gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid where u.userid in :ids and u.installerid=:employeeid order by u.userid desc ",nativeQuery = true)
    List<Object[]> findByUserlistAndInstalleridS(@Param("ids")List<Integer> ids, @Param("employeeid")Integer employeeid);

    @Query(value = "SELECT count(*) FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid where u.userid in :ids and u.installerid=:employeeid ",nativeQuery = true)
    Integer countByUserlistAndInstalleridS(@Param("ids")List<Integer> ids, @Param("employeeid")Integer employeeid);

    @Query(value = "SELECT o.name as oname,p.status as pstatus,p.activationdate,gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid " +
            "LEFT JOIN organization o ON u.monitoringstationid=o.organizationid\n" +
            "LEFT JOIN phonecard p ON gp.serialnumber=p.serialnumber where u.userid in :ids order by u.userid desc limit :size1 ,:number1 ",nativeQuery = true)
    List<Object[]> findByUserlistS(@Param("ids")List<Integer> ids, @Param("size1")int size, @Param("number1")int number);

    @Query(value = "SELECT gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid where u.userid in :ids order by u.userid desc ",nativeQuery = true)
    List<Object[]> findByUserlistS(@Param("ids")List<Integer> ids);

    @Query(value = "SELECT count(*) FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid where u.userid in :ids",nativeQuery = true)
    int countByUserlistS(@Param("ids")List<Integer> ids);

    @Query(value = "SELECT o.name as oname,p.status as pstatus,p.activationdate,gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid " +
            "LEFT JOIN organization o ON u.monitoringstationid=o.organizationid\n" +
            "LEFT JOIN phonecard p ON gp.serialnumber=p.serialnumber where u.userid in :ids and u.organizationid=:organizationid order by u.userid desc limit :size1 ,:number1 ",nativeQuery = true)
    List<Object[]> findByUserlistAndOrganizationS(@Param("ids")List<Integer> ids, @Param("organizationid")Integer organizationid,@Param("size1")int size, @Param("number1")int number);

    @Query(value = "SELECT gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid where u.userid in :ids and u.organizationid=:organizationid order by u.userid desc ",nativeQuery = true)
    List<Object[]> findByUserlistAndOrganizationS(@Param("ids")List<Integer> ids, @Param("organizationid")Integer organizationid);

    @Query(value = "SELECT count(*) FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid where u.userid in :ids and u.organizationid=:organizationid ",nativeQuery = true)
    int countByUserlistAndOrganizationS(@Param("ids")List<Integer> ids, @Param("organizationid")Integer organizationid);

    @Query(value = "SELECT o.name as oname,p.status as pstatus,p.activationdate,gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid " +
            "LEFT JOIN organization o ON u.monitoringstationid=o.organizationid\n" +
            "LEFT JOIN phonecard p ON gp.serialnumber=p.serialnumber where u.userid in :ids and u.installerorgid=:organizationid order by u.userid desc limit :size1 ,:number1 ",nativeQuery = true)
    List<Object[]> findByUserlistAndInstallerorgS(@Param("ids")List<Integer> ids, @Param("organizationid")Integer organizationid, @Param("size1")int size, @Param("number1")int number);

    @Query(value = "SELECT gu.deviceid,u.userid,u.name,u.status,u.appaccount,u.citycode,u.organizationid,u.personid,u.usercode, gp.serialnumber,u.createtime FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid where u.userid in :ids and u.installerorgid=:organizationid order by u.userid desc ",nativeQuery = true)
    List<Object[]> findByUserlistAndInstallerorgS(@Param("ids")List<Integer> ids, @Param("organizationid")Integer organizationid);

    @Query(value = "SELECT count(*) FROM \n" +
            "user u LEFT JOIN gatewayuser gu ON u.userid=gu.userid\n" +
            "LEFT JOIN gateway g ON gu.deviceid=g.deviceid\n" +
            "LEFT JOIN gatewayphonecard gp ON gp.deviceid=g.deviceid where u.userid in :ids and u.installerorgid=:organizationid ",nativeQuery = true)
    int countByUserlistAndInstallerorgS(@Param("ids")List<Integer> ids, @Param("organizationid")Integer organizationid);


    List<UserPO> findByStatus(int status);

    List<UserPO> findByCreatetimeAfter(Date starttime);

    List<UserPO> findByCreatetimeBefore(Date endtime);

    @Query(value = "SELECT * FROM \n" +
            "user u group by monitoringstationid ",nativeQuery = true)
    List<UserPO> findMonitoringStationIdList();

    List<UserPO> findByMonitoringstationidIn(List<Integer> monitoringstationidlist);
}
