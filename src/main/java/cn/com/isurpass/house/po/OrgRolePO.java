package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orgrole")
public class OrgRolePO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orgprivilegeid;
	private Integer privilegeid;
	private Integer status;

	public Integer getOrgprivilegeid() {
		return orgprivilegeid;
	}

	public void setOrgprivilegeid(Integer orgprivilegeid) {
		this.orgprivilegeid = orgprivilegeid;
	}

	public Integer getPrivilegeid() {
		return privilegeid;
	}

	public void setPrivilegeid(Integer privilegeid) {
		this.privilegeid = privilegeid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
