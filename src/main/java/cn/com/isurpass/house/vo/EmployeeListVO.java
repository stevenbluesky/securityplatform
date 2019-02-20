package cn.com.isurpass.house.vo;

import java.util.Date;

public class EmployeeListVO {

	private Integer employeeid;
	private String name;
	private String parentOrgName;
	private String code;
	private Integer status;
	private String orgname;
	private String rolestr;
	private Date createtime;

	public EmployeeListVO() {
		super();
	}

	public EmployeeListVO(Integer employeeid, String name, String parentOrgName, String code, Integer status, String orgname) {
		this.employeeid = employeeid;
		this.name = name;
		this.parentOrgName = parentOrgName;
		this.code = code;
		this.status = status;
		this.orgname = orgname;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public Integer getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getRolestr() {
		return rolestr;
	}

	public void setRolestr(String rolestr) {
		this.rolestr = rolestr;
	}
}
