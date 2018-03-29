package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	private String loginname;
	private String name;
	private String citycode;
	private Integer organizationid;
	private Integer installerorgid;
	private Integer installerid;
	private String codepostfix;
	private String usercode;
	private Integer personid;
	private Integer status;
	private Date createtime;

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public Integer getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Integer organizationid) {
		this.organizationid = organizationid;
	}

	public Integer getInstallerorgid() {
		return installerorgid;
	}

	public void setInstallerorgid(Integer installerorgid) {
		this.installerorgid = installerorgid;
	}

	public Integer getInstallerid() {
		return installerid;
	}

	public void setInstallerid(Integer installerid) {
		this.installerid = installerid;
	}

	public String getCodepostfix() {
		return codepostfix;
	}

	public void setCodepostfix(String codepostfix) {
		this.codepostfix = codepostfix;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public Integer getPersonid() {
		return personid;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}



}
