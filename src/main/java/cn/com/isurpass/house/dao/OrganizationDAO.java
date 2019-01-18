package cn.com.isurpass.house.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.vo.EmployeeListVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.OrganizationPO;

@Repository
// @SuppressWarnings("unchecked")
public interface OrganizationDAO extends CrudRepository<OrganizationPO, Integer>, JpaSpecificationExecutor {

    OrganizationPO save(OrganizationPO org);

    Page<OrganizationPO> findAll(Pageable pageable);

    List<OrganizationPO> findByOrgtype(Integer orgtype);

    Page<OrganizationPO> findByOrgtype(Pageable pageable, Integer orgtype);

    long count();

    List<OrganizationPO> findAll();

    @Query(value = "select new OrganizationPO(organizationid,name) from OrganizationPO o where o.orgtype = :orgtype and o.status in (1,2) ")
    List<OrganizationPO> findAllOrgSelect(@Param("orgtype") Integer orgtype);

    @Query("select new OrganizationPO(organizationid,name) from OrganizationPO")
    List<OrganizationPO> findAllOrgSelect();

    OrganizationPO findByOrganizationid(Integer organizationid);

    OrganizationPO findByCode(String code);

    List<OrganizationPO> findByParentorgid(Integer id);

    Page<OrganizationPO> findByNameLikeAndCitycodeLike(Pageable pageable, String s, String s1);

    Integer countByOrgtype(Integer orgType);

    Page<OrganizationPO> findByOrgtypeAndParentorgid(Pageable pageable, Integer orgtypeInstaller, Integer organizationid);

    Integer countByOrgtypeAndParentorgid(Integer orgtypeInstaller,Integer parentorgid);

    List<OrganizationPO> findByNameLikeAndCitycodeLike(String s, String s1);

    List<OrganizationPO> findByNameContaining(String searchName);

    Page<OrganizationPO> findByCodeLikeAndNameLikeAndCitycodeInAndOrgtype(String code, String name, List<String> list0, Integer orgtype1, Pageable pageable);

    Object countByCodeLikeAndNameLikeAndCitycodeInAndOrgtype(String code, String name, List<String> list0, Integer orgtype1);

    OrganizationPO findByCodeAndParentorgid(String code, Integer parentorgid);

    @Query(value = "select * from organization where parentorgid is NULL ",nativeQuery = true)
    List<OrganizationPO> findNUllParentorgid();

    Object countByCodeContainingAndNameContainingAndOrgtypeAndParentorgid(String code, String name, Integer orgtype1, Integer organizationid);

    Page<OrganizationPO> findByCodeContainingAndNameContainingAndOrgtypeAndParentorgid(Pageable pageable, String code, String name, Integer orgtype1, Integer organizationid);

    @Query(value = "SELECT e.employeeid,e.code,e.loginname,o.name,e.status,e.createtime  FROM employee e \n" +
            "\tJOIN organization o ON e.organizationid=o.organizationid \n" +
            "\tAND IFNULL(e.code,'') LIKE :code AND e.loginname LIKE :name AND o.name LIKE :orgname AND e.type IN :typelist and e.status in :statuslist \n " +
            "\tand e.createtime >= :starttime and e.createtime <= :endtime and e.employeeid in :tempempidlist ",nativeQuery = true)
    List<Object[]> findAllEmpByType(Pageable pageable, @Param("name")String name, @Param("code") String code, @Param("orgname") String orgname, @Param("typelist") List<Integer>typelist,
                                    @Param("statuslist") List<Integer>statuslist, @Param("starttime") Date starttime, @Param("endtime") Date enttime, @Param("tempempidlist") List<Integer>tempempidlist);
    @Query(value = "SELECT e.employeeid,e.code,e.loginname,o.name,e.status,e.createtime  FROM employee e \n" +
            "\tJOIN organization o ON e.organizationid=o.organizationid \n" +
            "\tAND IFNULL(e.code,'') LIKE :code AND e.loginname LIKE :name AND o.name LIKE :orgname AND e.type IN :typelist and e.status in :statuslist \n " +
            "\tand e.createtime >= :starttime and e.createtime <= :endtime and e.employeeid in :tempempidlist ",nativeQuery = true)
    List<Object[]> findAllEmpByType(@Param("name")String name, @Param("code") String code, @Param("orgname") String orgname, @Param("typelist") List<Integer>typelist,
                                    @Param("statuslist") List<Integer>statuslist, @Param("starttime") Date starttime, @Param("endtime") Date enttime, @Param("tempempidlist") List<Integer>tempempidlist);

    @Query(value = "SELECT COUNT(*) FROM employee e \n" +
            "\t JOIN organization o ON e.organizationid=o.organizationid \n" +
            "\tAND IFNULL(e.code,'') LIKE :code AND e.loginname LIKE :name AND o.name LIKE :orgname AND e.type IN :typelist and e.status in :statuslist \n " +
            "\tand e.createtime >= :starttime and e.createtime <= :endtime and e.employeeid in :tempempidlist ",nativeQuery = true)
    Long countAllEmpByType(@Param("name")String name,@Param("code") String code,@Param("orgname") String orgname,@Param("typelist") List<Integer>typelist,
                           @Param("statuslist") List<Integer>statuslist, @Param("starttime") Date starttime, @Param("endtime") Date enttime, @Param("tempempidlist") List<Integer>tempempidlist);

    @Query(value = "SELECT e.employeeid,e.code,e.loginname,o.name,e.status,e.createtime  FROM employee e \n" +
            "\tJOIN organization o ON e.organizationid=o.organizationid \n" +
            "\tAND IFNULL(e.code,'') LIKE :code AND e.loginname LIKE :name AND o.name LIKE :orgname AND o.organizationid IN :childrenOrgid AND e.type IN :typelist and e.status in :statuslist \n " +
            "\tand e.createtime >= :starttime and e.createtime <= :endtime and e.employeeid in :tempempidlist ",nativeQuery = true)
    List<Object[]> findAllSupEmpByType(Pageable pageable, @Param("name")String name,@Param("code") String code,@Param("orgname") String orgname,@Param("childrenOrgid") List<Integer> childrenOrgid,@Param("typelist") List<Integer>typelist,
                                       @Param("statuslist") List<Integer>statuslist, @Param("starttime") Date starttime, @Param("endtime") Date enttime, @Param("tempempidlist") List<Integer>tempempidlist);
    @Query(value = "SELECT e.employeeid,e.code,e.loginname,o.name,e.status,e.createtime  FROM employee e \n" +
            "\tJOIN organization o ON e.organizationid=o.organizationid \n" +
            "\tAND IFNULL(e.code,'') LIKE :code AND e.loginname LIKE :name AND o.name LIKE :orgname AND o.organizationid IN :childrenOrgid AND e.type IN :typelist and e.status in :statuslist \n " +
            "\tand e.createtime >= :starttime and e.createtime <= :endtime and e.employeeid in :tempempidlist ",nativeQuery = true)
    List<Object[]> findAllSupEmpByType(@Param("name")String name,@Param("code") String code,@Param("orgname") String orgname,@Param("childrenOrgid") List<Integer> childrenOrgid,@Param("typelist") List<Integer>typelist,
                                       @Param("statuslist") List<Integer>statuslist, @Param("starttime") Date starttime, @Param("endtime") Date enttime, @Param("tempempidlist") List<Integer>tempempidlist);

    @Query(value = "SELECT COUNT(*) FROM employee e \n" +
            "\t JOIN organization o ON e.organizationid=o.organizationid \n" +
            "\tAND IFNULL(e.code,'') LIKE :code AND e.loginname LIKE :name AND o.name LIKE :orgname AND o.organizationid IN :childrenOrgid AND e.type IN :typelist and e.status in :statuslist \n " +
            "\tand e.createtime >= :starttime and e.createtime <= :endtime and e.employeeid in :tempempidlist ",nativeQuery = true)
    Long countAllSupEmpByType(@Param("name")String name,@Param("code") String code,@Param("orgname") String orgname,@Param("childrenOrgid") List<Integer> childrenOrgid ,@Param("typelist") List<Integer>typelist,
                              @Param("statuslist") List<Integer>statuslist, @Param("starttime") Date starttime, @Param("endtime") Date enttime, @Param("tempempidlist") List<Integer>tempempidlist);

    @Query(value = "SELECT o1.organizationid,o1.code as code,o1.name as name,c.cityname,o1.status,o2.name as parentname,o2.code as parentcode FROM organization o1 \n" +
            "LEFT JOIN city c ON o1.citycode=c.citycode \n" +
            "LEFT JOIN organization o2 ON o1.parentorgid=o2.organizationid   \n" +
            "WHERE o1.code LIKE :code AND o1.name LIKE :name AND c.cityname LIKE :city AND o2.name LIKE :parname AND o2.code LIKE :parcode AND o1.orgtype=2 and o1.status in (1,2) ",nativeQuery = true)
    List<Object[]> findInstallerOrg(Pageable pageable, @Param("code")String code,@Param("name") String name, @Param("city")String city,@Param("parname")String parname,@Param("parcode")String parcode);

    @Query(value = "SELECT COUNT(*) FROM organization o1 \n" +
            "LEFT JOIN city c ON o1.citycode=c.citycode \n" +
            "LEFT JOIN organization o2 ON o1.parentorgid=o2.organizationid   \n" +
            "WHERE o1.code LIKE :code AND o1.name LIKE :name AND c.cityname LIKE :city AND o2.name LIKE :parname AND o2.code LIKE :parcode AND o1.orgtype=2 and o1.status in (1,2) ",nativeQuery = true)
    long countInstallerOrg(@Param("code")String code, @Param("name")String name,@Param("city") String city,@Param("parname")String parname,@Param("parcode")String parcode);

    @Query(value = "SELECT o1.organizationid,o1.code as code,o1.name as name,c.cityname,o1.status,o2.name as parentname,o2.code as parentcode FROM organization o1 \n" +
            "LEFT JOIN city c ON o1.citycode=c.citycode \n" +
            "LEFT JOIN organization o2 ON o1.parentorgid=o2.organizationid   \n" +
            "WHERE o1.code LIKE :code AND o1.name LIKE :name AND c.cityname LIKE :city and o1.organizationid in :childrenOrgid AND o2.name LIKE :parname AND o2.code LIKE :parcode AND o1.orgtype=2 and o1.status in (1,2) ",nativeQuery = true)
    List<Object[]> findOwnInstallerOrg(Pageable pageable, @Param("name")String name,@Param("code") String code,@Param("city") String city,@Param("childrenOrgid") List<Integer> childrenOrgid,@Param("parname")String parname,@Param("parcode")String parcode);

    @Query(value = "SELECT COUNT(*) FROM organization o1 \n" +
            "LEFT JOIN city c ON o1.citycode=c.citycode \n" +
            "LEFT JOIN organization o2 ON o1.parentorgid=o2.organizationid   \n" +
            "WHERE o1.code LIKE :code AND o1.name LIKE :name AND c.cityname LIKE :city AND o1.organizationid IN :childrenOrgid AND o2.name LIKE :parname AND o2.code LIKE :parcode AND o1.orgtype=2 and o1.status in (1,2) ",nativeQuery = true)
    long countOwnInstallerOrg(@Param("name")String name,@Param("code") String code,@Param("city") String city,@Param("childrenOrgid") List<Integer> childrenOrgid,@Param("parname")String parname,@Param("parcode")String parcode);


    void deleteByOrganizationid(Integer id);

    List<OrganizationPO> findByParentorgidAndOrgtype(Integer organizationid, Integer orgtypeInstaller);

    OrganizationPO findByCodeAndOrganizationid(String code, Integer organizationid);

    Page<OrganizationPO> findByOrgtypeAndStatusIn(Pageable pageable, Integer orgType, int[] ints);

    List<OrganizationPO> findByOrgtypeAndStatusIn(Integer orgType, int[] ints);

    Integer countByOrgtypeAndStatusIn(Integer orgType, int[] ints);

    Object countByCodeContainingAndNameContainingAndOrgtypeAndStatusIn(String code, String name, Integer orgtype1, int[] ints);

    Page<OrganizationPO> findByCodeContainingAndNameContainingAndOrgtypeAndStatusIn(Pageable pageable, String code, String name, Integer orgtype1, int[] ints);

    Object countByCodeLikeAndNameLikeAndCitycodeInAndOrgtypeAndOrganizationidInAndStatusIn(String code, String name, List<String> list0, Integer orgtype1, List<Integer> list1, int[] ints);

    Page<OrganizationPO> findByCodeLikeAndNameLikeAndCitycodeInAndOrgtypeAndOrganizationidInAndStatusIn(String code, String name, List<String> list0, Integer orgtype1, List<Integer> list1, int[] ints, Pageable pageable);

    Object countByCodeLikeAndNameLikeAndCitycodeInAndOrgtypeAndStatusIn(String code, String name, List<String> list0, Integer orgtype1, int[] ints);

    Page<OrganizationPO> findByCodeLikeAndNameLikeAndCitycodeInAndOrgtypeAndStatusIn(String code, String name, List<String> list0, Integer orgtype1, int[] ints, Pageable pageable);

    Page<OrganizationPO> findByOrgtypeAndParentorgidAndStatusIn(Pageable pageable, Integer orgtypeInstaller, Integer organizationid, int[] ints);

    Integer countByOrgtypeAndParentorgidAndStatusIn(Integer orgtypeInstaller, Integer organizationid, int[] ints);

    List<OrganizationPO> findByParentorgidAndOrgtypeAndStatusIn(Integer organizationid, Integer orgtypeInstaller, int[] ints);

    OrganizationPO findByNameAndStatusIn(String name, int[] ints);
}
