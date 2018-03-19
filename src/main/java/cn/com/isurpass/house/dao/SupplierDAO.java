package cn.com.isurpass.house.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.util.PageResult;

@Repository
public class SupplierDAO extends BaseDAO {

	public Integer add(OrganizationPO org) {
		getSession().save(org);
		return org.getOrganizationid();
	}

	public List<OrganizationPO> listOrg(PageResult pr) {
		return (List<OrganizationPO>)getSession().createCriteria(OrganizationPO.class)
		    .setFirstResult(pr.getStart()).setMaxResults(pr.getRows()).list();
	}

	/**
	 * 获取总机构数
	 * @return
	 */
	public Integer getTotal() {
		Number count = (Number) getSession().createSQLQuery("select count(organizationid) from organization where orgtype = 1")
				.uniqueResult();
		if (count != null)
			return count.intValue();
		return 0;
	}
}
