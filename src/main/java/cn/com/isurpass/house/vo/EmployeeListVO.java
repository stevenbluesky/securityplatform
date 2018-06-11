package cn.com.isurpass.house.vo;

public class EmployeeListVO {

	private Integer employeeid;
	private String name;
	private String parentOrgName;
	private String code;
	private Integer status;
	private String orgname;

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

}
