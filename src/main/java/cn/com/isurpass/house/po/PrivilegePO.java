package cn.com.isurpass.house.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "privilege")
public class PrivilegePO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer privilegeid;
	private String label;
	private String code;
	private Integer parentprivilegeid;

	public Integer getPrivilegeid() {
		return privilegeid;
	}

	public void setPrivilegeid(Integer privilegeid) {
		this.privilegeid = privilegeid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getParentprivilegeid() {
		return parentprivilegeid;
	}

	public void setParentprivilegeid(Integer parentprivilegeid) {
		this.parentprivilegeid = parentprivilegeid;
	}

}
