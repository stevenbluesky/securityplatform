package cn.com.isurpass.house.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zwavesubdevice")
public class ZwaveSubDevicePO {

	@Id
	private Integer zwavesubdeviceid;
	private Integer zwavedeviceid;
	private Integer channelid;
	private String name;
	private String subdevicetype;

	public Integer getZwavesubdeviceid() {
		return zwavesubdeviceid;
	}

	public void setZwavesubdeviceid(Integer zwavesubdeviceid) {
		this.zwavesubdeviceid = zwavesubdeviceid;
	}

	public Integer getZwavedeviceid() {
		return zwavedeviceid;
	}

	public void setZwavedeviceid(Integer zwavedeviceid) {
		this.zwavedeviceid = zwavedeviceid;
	}

	public Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubdevicetype() {
		return subdevicetype;
	}

	public void setSubdevicetype(String subdevicetype) {
		this.subdevicetype = subdevicetype;
	}
}
