package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employeerole")
public class EmployeeRolePO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeroleid;
	private Integer employeeid;
	private Integer roleid;
	private Date createtime;

	public Integer getEmployeeroleid() {
		return employeeroleid;
	}

	public void setEmployeeroleid(Integer employeeroleid) {
		this.employeeroleid = employeeroleid;
	}

	public Integer getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
