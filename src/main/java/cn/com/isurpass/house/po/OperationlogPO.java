package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operationlog")
public class OperationlogPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer operationlogid;
	private Integer employeeroleid;
	private String url;
	private String requestdata;
	private String result;
	private String description;

	public Integer getOperationlogid() {
		return operationlogid;
	}

	public void setOperationlogid(Integer operationlogid) {
		this.operationlogid = operationlogid;
	}

	public Integer getEmployeeroleid() {
		return employeeroleid;
	}

	public void setEmployeeroleid(Integer employeeroleid) {
		this.employeeroleid = employeeroleid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequestdata() {
		return requestdata;
	}

	public void setRequestdata(String requestdata) {
		this.requestdata = requestdata;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	private Date createtime;

}
