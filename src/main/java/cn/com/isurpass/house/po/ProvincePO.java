package cn.com.isurpass.house.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "province")
public class ProvincePO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer provinceid;
	private Integer countryid;
	private String provincename;
	private String provinceabbreviation;

	public Integer getCountryid() {
		return countryid;
	}

	public void setCountryid(Integer countryid) {
		this.countryid = countryid;
	}

	public ProvincePO(Integer provinceid, String provincename, String provinceabbreviation) {
		super();
		this.provinceid = provinceid;
		this.provincename = provincename;
		this.provinceabbreviation = provinceabbreviation;
	}

	public ProvincePO() {
		super();
	}

	public Integer getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getProvinceabbreviation() {
		return provinceabbreviation;
	}

	public void setProvinceabbreviation(String provinceabbreviation) {
		this.provinceabbreviation = provinceabbreviation;
	}
}
