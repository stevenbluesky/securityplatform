package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class EmployeePO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeid;
	private String loginname;
	private String code;
	private String name;
	private String password;
	private Integer organizationid;
	private String question;
	private String answer;
	private Integer status; // 0:未生效 1:正常 2:冻结 9:删除
	private Integer personid;
	private Integer addressid;
	private Date expiredate;
	private Date createtime;

	
	public EmployeePO() {
		super();
	}

	public EmployeePO(String loginname, String password, String question, String answer) {
		super();
		this.loginname = loginname;
		this.password = password;
		this.question = question;
		this.answer = answer;
	}

	public EmployeePO(Integer employeeid, String loginname, String code, String name, String password,
			Integer organizationid, String question, String answer, Integer status, Integer personid, Integer addressid,
			Date expiredate, Date createtime) {
		super();
		this.employeeid = employeeid;
		this.loginname = loginname;
		this.code = code;
		this.name = name;
		this.password = password;
		this.organizationid = organizationid;
		this.question = question;
		this.answer = answer;
		this.status = status;
		this.personid = personid;
		this.addressid = addressid;
		this.expiredate = expiredate;
		this.createtime = createtime;
	}

	public Integer getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Integer organizationid) {
		this.organizationid = organizationid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPersonid() {
		return personid;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
	}

	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	public Date getExpiredate() {
		return expiredate;
	}

	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
