package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gatewaybinding")
public class GatewayBindingPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getGatewaybindingid() {
		return gatewaybindingid;
	}

	public void setGatewaybindingid(Integer gatewaybindingid) {
		this.gatewaybindingid = gatewaybindingid;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public Integer getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Integer organizationid) {
		this.organizationid = organizationid;
	}

	public Integer getBindingtype() {
		return bindingtype;
	}

	public void setBindingtype(Integer bindingtype) {
		this.bindingtype = bindingtype;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	private Integer gatewaybindingid;
	private String deviceid;
	private Integer organizationid;
	private Integer bindingtype;
	private Integer status;
	private Date createtime;
}
