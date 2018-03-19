package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "organization")
public class OrganizationPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer organizationid;
	private String code;
	private String citycode;
	private String name;
	private Integer parentorgid;
	private Integer orgtype;
	private Integer status;//1:正常 2:冻结 9:删除
	private Integer officeaddressid;
	private Integer billingaddressid;
	private Integer contactid;
	private String centralstationname;
	private Integer csaddressid;
	private Integer cscontactid;
	private Date createtime;

	
	public OrganizationPO() {
		super();
	}
	
	public OrganizationPO(Integer organizationid, String name) {
		super();
		this.organizationid = organizationid;
		this.name = name;
	}


	public Integer getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Integer organizationid) {
		this.organizationid = organizationid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentorgid() {
		return parentorgid;
	}

	public void setParentorgid(Integer parentorgid) {
		this.parentorgid = parentorgid;
	}

	public Integer getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(Integer orgtype) {
		this.orgtype = orgtype;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOfficeaddressid() {
		return officeaddressid;
	}

	public void setOfficeaddressid(Integer officeaddressid) {
		this.officeaddressid = officeaddressid;
	}

	public Integer getBillingaddressid() {
		return billingaddressid;
	}

	public void setBillingaddressid(Integer billingaddressid) {
		this.billingaddressid = billingaddressid;
	}

	public Integer getContactid() {
		return contactid;
	}

	public void setContactid(Integer contactid) {
		this.contactid = contactid;
	}

	public String getCentralstationname() {
		return centralstationname;
	}

	public void setCentralstationname(String centralstationname) {
		this.centralstationname = centralstationname;
	}

	public Integer getCsaddressid() {
		return csaddressid;
	}

	public void setCsaddressid(Integer csaddressid) {
		this.csaddressid = csaddressid;
	}

	public Integer getCscontactid() {
		return cscontactid;
	}

	public void setCscontactid(Integer cscontactid) {
		this.cscontactid = cscontactid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
