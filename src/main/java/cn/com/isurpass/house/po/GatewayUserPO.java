package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "gatewayuser")
public class GatewayUserPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gatewayuserbindingid;
	private String deviceid;
	private Integer userid;
	private Date createtime;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getGatewayuserbindingid() {
		return gatewayuserbindingid;
	}

	public void setGatewayuserbindingid(Integer gatewayuserbindingid) {
		this.gatewayuserbindingid = gatewayuserbindingid;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
}
