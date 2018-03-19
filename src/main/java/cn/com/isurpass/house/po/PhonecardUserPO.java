package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "phonecarduser")
public class PhonecardUserPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer phonecarduserbingingid;
	private Integer phonecardid;
	private Integer userid;
	private Date createtime;

	public Integer getPhonecarduserbingingid() {
		return phonecarduserbingingid;
	}

	public void setPhonecarduserbingingid(Integer phonecarduserbingingid) {
		this.phonecarduserbingingid = phonecarduserbingingid;
	}

	public Integer getPhonecardid() {
		return phonecardid;
	}

	public void setPhonecardid(Integer phonecardid) {
		this.phonecardid = phonecardid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
