package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zwavedevice")
public class ZwaveDevicePO{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer zwavedeviceid;
	private String deviceid;
	private String name;
	private String devicetype;
	private Integer battery;
	private Integer status; // 0:离线 1:在线
	private String statuses; // 0:离线 1:在线
	private String warningstatuses;
	private Date createtime;

	public Integer getZwavedeviceid() {
		return zwavedeviceid;
	}

	public void setZwavedeviceid(Integer zwavedeviceid) {
		this.zwavedeviceid = zwavedeviceid;
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

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public Integer getBattery() {
		return battery;
	}

	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatuses() {
		return statuses;
	}

	public void setStatuses(String statuses) {
		this.statuses = statuses;
	}

	public String getWarningstatuses() {
		return warningstatuses;
	}

	public void setWarningstatuses(String warningstatuses) {
		this.warningstatuses = warningstatuses;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
