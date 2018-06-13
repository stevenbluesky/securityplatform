package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.PhonecardPO;
@Repository
public interface PhonecardDAO extends CrudRepository<PhonecardPO,String>{

	PhonecardPO findBySerialnumber(String serialnumber);
	
	PhonecardPO save(PhonecardPO gw);
	
	Page<PhonecardPO> findAll(Pageable pageable);

	PhonecardPO findByPhonecardid(Object string);

    List<PhonecardPO> findBySerialnumberContaining(String searchSerialnumber);

    void deleteByPhonecardid(Integer phonecardid);

	@Query(value = "SELECT p.phonecardid ,p.serialnumber,p.status,p.model,p.firmwareversion,p.rateplan,p.activationdate FROM phonecard p\n" +
			"LEFT JOIN phonecarduser pu ON p.phonecardid=pu.phonecardid LEFT JOIN user u ON pu.userid=u.userid\n" +
			"WHERE p.serialnumber LIKE :serialnumber AND p.rateplan LIKE :rateplan AND p.status IN :statuslist and u.installerid= :employeeid ", nativeQuery = true)
    List<Object[]> findByInstaller(@Param("statuslist")List<Integer> statuslist, @Param("serialnumber")String serialnumber, @Param("rateplan")String rateplan,@Param("employeeid") Integer employeeid, Pageable pageable);

	@Query(value = "SELECT count(*) FROM phonecard p\n" +
			"LEFT JOIN phonecarduser pu ON p.phonecardid=pu.phonecardid LEFT JOIN user u ON pu.userid=u.userid\n" +
			"WHERE p.serialnumber LIKE :serialnumber AND p.rateplan LIKE :rateplan AND p.status IN :statuslist and u.installerid= :employeeid ", nativeQuery = true)
	long countByInstaller(@Param("statuslist")List<Integer> statuslist, @Param("serialnumber")String serialnumber, @Param("rateplan")String rateplan,@Param("employeeid") Integer employeeid);

	@Query(value = "SELECT p.phonecardid ,p.serialnumber,p.status,p.model,p.firmwareversion,p.rateplan,p.activationdate FROM phonecard p\n" +
			"LEFT JOIN phonecarduser pu ON p.phonecardid=pu.phonecardid LEFT JOIN user u ON pu.userid=u.userid\n" +
			"WHERE p.serialnumber LIKE :serialnumber AND p.rateplan LIKE :rateplan AND p.status IN :statuslist and u.installerorgid in :childrenOrgid ", nativeQuery = true)
	List<Object[]> findByInstallerOrg(@Param("statuslist")List<Integer> statuslist, @Param("serialnumber")String serialnumber, @Param("rateplan")String rateplan,@Param("childrenOrgid") List<Integer> childrenOrgid, Pageable pageable);

	@Query(value = "SELECT count(*) FROM phonecard p\n" +
			"LEFT JOIN phonecarduser pu ON p.phonecardid=pu.phonecardid LEFT JOIN user u ON pu.userid=u.userid\n" +
			"WHERE p.serialnumber LIKE :serialnumber AND p.rateplan LIKE :rateplan AND p.status IN :statuslist and u.installerorgid in :childrenOrgid ", nativeQuery = true)
	long countByInstallerOrg(@Param("statuslist")List<Integer> statuslist, @Param("serialnumber")String serialnumber, @Param("rateplan")String rateplan,@Param("childrenOrgid") List<Integer> childrenOrgid);

	@Query(value = "SELECT p.phonecardid ,p.serialnumber,p.status,p.model,p.firmwareversion,p.rateplan,p.activationdate FROM phonecard p\n" +
			"LEFT JOIN phonecarduser pu ON p.phonecardid=pu.phonecardid LEFT JOIN user u ON pu.userid=u.userid\n" +
			"WHERE p.serialnumber LIKE :serialnumber AND p.rateplan LIKE :rateplan AND p.status IN :statuslist and u.organizationid in :childrenOrgid ", nativeQuery = true)
	List<Object[]> findBySupplier(@Param("statuslist")List<Integer> statuslist, @Param("serialnumber")String serialnumber, @Param("rateplan")String rateplan,@Param("childrenOrgid") List<Integer> childrenOrgid, Pageable pageable);

	@Query(value = "SELECT count(*) FROM phonecard p\n" +
			"LEFT JOIN phonecarduser pu ON p.phonecardid=pu.phonecardid LEFT JOIN user u ON pu.userid=u.userid\n" +
			"WHERE p.serialnumber LIKE :serialnumber AND p.rateplan LIKE :rateplan AND p.status IN :statuslist and u.organizationid in :childrenOrgid ", nativeQuery = true)
	long countBySupplier(@Param("statuslist")List<Integer> statuslist, @Param("serialnumber")String serialnumber, @Param("rateplan")String rateplan,@Param("childrenOrgid") List<Integer> childrenOrgid);

	@Query(value = "SELECT p.phonecardid ,p.serialnumber,p.status,p.model,p.firmwareversion,p.rateplan,p.activationdate FROM phonecard p\n" +
			"LEFT JOIN phonecarduser pu ON p.phonecardid=pu.phonecardid LEFT JOIN user u ON pu.userid=u.userid\n" +
			"WHERE p.serialnumber LIKE :serialnumber AND p.rateplan LIKE :rateplan AND p.status IN :statuslist ", nativeQuery = true)
	List<Object[]> findByAmeta(@Param("statuslist")List<Integer> statuslist, @Param("serialnumber")String serialnumber, @Param("rateplan")String rateplan, Pageable pageable);

	long countByStatusInAndSerialnumberLikeAndRateplanLike(List<Integer> statuslist, String serialnumber, String rateplan);
}
