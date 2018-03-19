package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roleprivilege")
public class RolePrivilegePO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleprivilegeid;
	private Integer roleid;
	private Integer privilegeid;

	public Integer getRoleprivilegeid() {
		return roleprivilegeid;
	}

	public void setRoleprivilegeid(Integer roleprivilegeid) {
		this.roleprivilegeid = roleprivilegeid;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getPrivilegeid() {
		return privilegeid;
	}

	public void setPrivilegeid(Integer privilegeid) {
		this.privilegeid = privilegeid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	private Date createtime;

}
