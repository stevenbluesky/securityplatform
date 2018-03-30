package cn.com.isurpass.house.vo;

public class TypeGatewayInfoVO {
	private String deviceid;
	private String name;
	private Integer status;
	private String model;
	private Integer battery;
	private String createtime;
	private String firmwareversion;
	
	private String cityname;
	private String citycode;
	private String customer;
	private String serviceprovider;
	private String installerorg;
	private String installer;
	private String devicename;
	
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getFirmwareversion() {
		return firmwareversion;
	}
	public void setFirmwareversion(String firmwareversion) {
		this.firmwareversion = firmwareversion;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
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
	@Override
	public String toString() {
		return "TypeGatewayInfoVO [deviceid=" + deviceid + ", name=" + name + ", status=" + status + ", model=" + model
				+ ", battery=" + battery + ", createtime=" + createtime + ", firmwareversion=" + firmwareversion
				+ ", cityname=" + cityname + ", citycode=" + citycode + ", customer=" + customer + ", serviceprovider="
				+ serviceprovider + ", installerorg=" + installerorg + ", installer=" + installer + ", devicename="
				+ devicename + "]";
	}
	
}
