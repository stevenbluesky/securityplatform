package cn.com.isurpass.house.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.util.PageResult;

@Repository
// @SuppressWarnings("unchecked")
public class OrganizationDAO extends BaseDAO {

	public Integer add(OrganizationPO org) {
		getSession().save(org);
		return org.getOrganizationid();
	}

	/**
	 * 通过分页的形式列出指定数量的机构
	 * 
	 * @param pr
	 * @return
	 */
	public List<OrganizationPO> listOrg(PageResult pr) {
		return (List<OrganizationPO>) getSession().createCriteria(OrganizationPO.class).setFirstResult(pr.getStart())
				.setMaxResults(pr.getRows()).list();
	}

	/**
	 * 通过机构类型列出所有的机构
	 * 
	 * @param orgType
	 * @return
	 */
	public List<OrganizationPO> listOrgByType(Integer orgType) {
		return (List<OrganizationPO>) getSession().createCriteria(OrganizationPO.class)
				.add(Restrictions.eq("orgtype", orgType)).list();
	}

	/**
	 * 通过机构的类型来列出指定数量的机构(OrgType 0:Ameta 1:服务商 2:安装商)
	 * 
	 * @param orgType
	 * @return
	 */
	public List<OrganizationPO> listOrgByType(PageResult pr, Integer orgType) {
		return (List<OrganizationPO>) getSession().createCriteria(OrganizationPO.class)
				.add(Restrictions.eq("orgtype", orgType)).setFirstResult(pr.getStart()).setMaxResults(pr.getRows())
				.list();
	}

	/**
	 * 获取总机构数
	 * 
	 * @return
	 */
	public Integer getTotal() {
		Number count = (Number) getSession()
				.createSQLQuery("select count(organizationid) from organization where orgtype = 1").uniqueResult();
		if (count != null)
			return count.intValue();
		return 0;
	}

	/**
	 * 获取父机构的id
	 * 
	 * @return
	 */
	public Integer getParentOrgId(String name) {
		return (Integer) getSession().createSQLQuery("SELECT organizationid from organization where name = ?")
				.setString(0, name).uniqueResult();
	}

	public List<OrganizationPO> listAllOrg() {
		return (List<OrganizationPO>) getSession().createCriteria(OrganizationPO.class).list();
	}

	public List<OrganizationPO> listOrgSelectByType(Integer orgType) {
		return (List<OrganizationPO>)getSession().createQuery("select new OrganizationPO(organizationid,name) from OrganizationPO where orgtype=?").setInteger(0, orgType).list();
	}

	public List<OrganizationPO> listAllOrgSelect() {
		return (List<OrganizationPO>)getSession().createQuery("select new OrganizationPO(organizationid,name) from OrganizationPO").list();
	}

	public Integer getOrgType(Integer orgId) {
		return (Integer)getSession().createQuery("select orgtype from OrganizationPO where organizationid = ?").setInteger(0, orgId).uniqueResult();
	}
	
	public String getOrgNameById(Integer orgId) {
		return (String)getSession().createSQLQuery("select name from organization where organizationid = ?").setInteger(0, orgId).uniqueResult();
	}
}
