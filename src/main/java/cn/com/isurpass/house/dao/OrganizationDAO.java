package cn.com.isurpass.house.dao;

import java.util.List;
import java.util.Set;

import cn.com.isurpass.house.po.EmployeePO;
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

    OrganizationPO findByName(String name);

    List<OrganizationPO> findAll();

    @Query(value = "select new OrganizationPO(organizationid,name) from OrganizationPO o where o.orgtype = :orgtype")
    List<OrganizationPO> findAllOrgSelect(@Param("orgtype") Integer orgtype);

    @Query("select new OrganizationPO(organizationid,name) from OrganizationPO")
    List<OrganizationPO> findAllOrgSelect();

    OrganizationPO findByOrganizationid(Integer organizationid);

    Page<OrganizationPO> findByParentorgidIn(Pageable pageable, Integer id);

    OrganizationPO findByCode(String code);

    List<OrganizationPO> findByParentorgid(Integer id);

    Page<OrganizationPO> findByOrgtypeAndParentorgidIn(Pageable pageable, Integer orgtypeInstaller, Set<Integer> idlist);

    List<OrganizationPO> findByCodeLike(String s);

    Page<OrganizationPO> findByCitycode(String citycode, Pageable pageable);

    Page<OrganizationPO> findByNameLikeAndCitycodeLike(Pageable pageable, String s, String s1);

    Page<OrganizationPO> findByNameLikeAndCitycodeIn(String name, List<String> list0, Pageable pageable);

    Page<OrganizationPO> findByNameLikeAndCitycodeInAndOrgtype(String name, List<String> list0, Integer orgtype, Pageable pageable);

    List<OrganizationPO> findByOrgtypeAndNameContainingOrCentralstationnameContaining(Integer orgtypeSupplier,
                                                                                      String serviceprovider, String serviceprovider2);

    Integer countByStatus(int i);

    Integer countByStatusAndContactid(int i, int i1);

    Integer countByOrgtype(Integer orgType);

    Page<OrganizationPO> findByOrgtypeAndParentorgid(Pageable pageable, Integer orgtypeInstaller, Integer organizationid);

    Integer countByOrgtypeAndParentorgid(Integer orgtypeInstaller,Integer parentorgid);

    Integer countByNameLikeAndCitycodeLike(String name, String citycode);

    Integer countByNameLikeAndCitycodeInAndOrgtype(String name, List<String> list0, Integer orgtype);

    List<OrganizationPO> findByNameLikeAndCitycodeLike(String s, String s1);
}
