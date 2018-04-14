package cn.com.isurpass.house.vo;

public class ZwaveDeviceListVO {

	private Integer zwavedeviceid;
	private String name;
	private String devicetype;
	private String warningstatuses;
	private Integer status;
	private Integer battery;
	private String city;
	private String organizationname;
	private String installerorgname;
	private String installername;
	private String username;

	public Integer getZwavedeviceid() {
		return zwavedeviceid;
	}

	public void setZwavedeviceid(Integer zwavedeviceid) {
			this.zwavedeviceid = zwavedeviceid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getWarningstatuses() {
		return warningstatuses;
	}

	public void setWarningstatuses(String warningstatuses) {
		this.warningstatuses = warningstatuses;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBattery() {
		return battery;
	}

	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}

	public String getInstallerorgname() {
		return installerorgname;
	}

	public void setInstallerorgname(String installerorgname) {
		this.installerorgname = installerorgname;
	}

	public String getInstallername() {
		return installername;
	}

	public void setInstallername(String installername) {
		this.installername = installername;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "ZwaveDeviceListVO{" +
				"zwavedeviceid=" + zwavedeviceid +
				", name='" + name + '\'' +
				", devicetype='" + devicetype + '\'' +
				", warningstatuses='" + warningstatuses + '\'' +
				", status=" + status +
				", battery=" + battery +
				", city='" + city + '\'' +
				", organizationname='" + organizationname + '\'' +
				", installerorgname='" + installerorgname + '\'' +
				", installername='" + installername + '\'' +
				", username='" + username + '\'' +
				'}';
	}
}
