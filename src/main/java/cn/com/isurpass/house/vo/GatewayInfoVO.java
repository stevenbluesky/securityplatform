package cn.com.isurpass.house.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class GatewayInfoVO {
	private String deviceid;
	private String name;
	private String status;
	private String cityname;
	private String serviceprovider;
	private String installerorg;
	private String installer;
	private String customer;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bindingtime;

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getServiceprovider() {
		return serviceprovider;
	}

	public void setServiceprovider(String serviceprovider) {
		this.serviceprovider = serviceprovider;
	}

	public String getInstallerorg() {
		return installerorg;
	}

	public void setInstallerorg(String installerorg) {
		this.installerorg = installerorg;
	}

	public String getInstaller() {
		return installer;
	}

	public void setInstaller(String installer) {
		this.installer = installer;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getBindingtime() {
		return bindingtime;
	}

	public void setBindingtime(Date bindingtime) {
		this.bindingtime = bindingtime;
	}
}
