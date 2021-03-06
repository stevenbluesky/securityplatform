package cn.com.isurpass.house.vo;

import java.util.Date;

public class UserInfoListVO {
	private Integer userid;
	private String name;
	private String phonenumber;
	private String city;
	private String suppliername;
	private Integer status;
	private String deviceid;
	private String serialnumber;
	private String codepostfix;
	private String usercode;
	private String appaccount;
	private Date createtime;
	private String monitoringstation;
	private Integer simstatus;
	private Date activationdate;

	public String getAppaccount() {
		return appaccount;
	}

	public void setAppaccount(String appaccount) {
		this.appaccount = appaccount;
	}

	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getMonitoringstation() {
		return monitoringstation;
	}

	public void setMonitoringstation(String monitoringstation) {
		this.monitoringstation = monitoringstation;
	}

	public Integer getSimstatus() {
		return simstatus;
	}

	public void setSimstatus(Integer simstatus) {
		this.simstatus = simstatus;
	}

	public Date getActivationdate() {
		return activationdate;
	}

	public void setActivationdate(Date activationdate) {
		this.activationdate = activationdate;
	}
}
