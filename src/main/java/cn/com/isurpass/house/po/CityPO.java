package cn.com.isurpass.house.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class CityPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cityid;
	private Integer provinceid;
	private String cityname;
	private String citycode;
	private String cityabbreviation;

	public CityPO() {
		super();
	}

	public CityPO(Integer cityid, String cityname, String citycode, String cityabbreviation) {
		super();
		this.cityid = cityid;
		this.cityname = cityname;
		this.citycode = citycode;
		this.cityabbreviation = cityabbreviation;
	}

	public CityPO(String cityname, String citycode, String cityabbreviation) {
		super();
		this.cityname = cityname;
		this.citycode = citycode;
		this.cityabbreviation = cityabbreviation;
	}

	public Integer getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCityabbreviation() {
		return cityabbreviation;
	}

	public void setCityabbreviation(String cityabbreviation) {
		this.cityabbreviation = cityabbreviation;
	}

}
