package cn.com.isurpass.house.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class CountryPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer countryid;
	private String countryname;
	private String countryabbreviation;

	public CountryPO(Integer countryid, String countryname, String countryabbreviation) {
		super();
		this.countryid = countryid;
		this.countryname = countryname;
		this.countryabbreviation = countryabbreviation;
	}
	public CountryPO() {
		super();
	}

	public Integer getCountryid() {
		return countryid;
	}

	public void setCountryid(Integer countryid) {
		this.countryid = countryid;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getCountryabbreviation() {
		return countryabbreviation;
	}

	public void setCountryabbreviation(String countryabbreviation) {
		this.countryabbreviation = countryabbreviation;
	}
}
