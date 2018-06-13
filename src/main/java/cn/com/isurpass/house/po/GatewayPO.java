package cn.com.isurpass.house.po;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "gateway")
public class GatewayPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gatewayid;
	private String deviceid;
	private String appaccount;
	private String name;
	private Integer status; // 0:离线 1:在线
	private String model;
	private String firmwareversion;
	private Integer battery; // 0:离线 1:在线
	private Date createtime;

	public String getAppaccount() {
		return appaccount;
	}

	public void setAppaccount(String appaccount) {
		this.appaccount = appaccount;
	}

	public Integer getGatewayid() {
		return gatewayid;
	}

	public void setGatewayid(Integer gatewayid) {
		this.gatewayid = gatewayid;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFirmwareversion() {
		return firmwareversion;
	}

	public void setFirmwareversion(String firmwareversion) {
		this.firmwareversion = firmwareversion;
	}

	public Integer getBattery() {
		return battery;
	}

	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "GatewayPO{" +
				"gatewayid=" + gatewayid +
				", deviceid='" + deviceid + '\'' +
				", appaccount='" + appaccount + '\'' +
				", name='" + name + '\'' +
				", status=" + status +
				", model='" + model + '\'' +
				", firmwareversion='" + firmwareversion + '\'' +
				", battery=" + battery +
				", createtime=" + createtime +
				'}';
	}
}
