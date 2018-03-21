package cn.com.isurpass.house.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class AddressPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressid;
	private String country;
	private String province;
	private String city;
	private String detailaddress;
	private String postal;
	private String fax;
	private String phonenumber;
	
	public AddressPO() {
		super();
	}

	public AddressPO(String country, String province, String city, String detailaddress, String postal) {
		super();
		this.country = country;
		this.province = province;
		this.city = city;
		this.detailaddress = detailaddress;
		this.postal = postal;
	}


	public AddressPO(Integer addressid, String country, String province, String city, String detailaddress,
			String postal, String fax, String phonenumber) {
		super();
		this.addressid = addressid;
		this.country = country;
		this.province = province;
		this.city = city;
		this.detailaddress = detailaddress;
		this.postal = postal;
		this.fax = fax;
		this.phonenumber = phonenumber;
	}

	public Integer getAddressid() {
		return addressid;
	}


	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDetailaddress() {
		return detailaddress;
	}

	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Override
	public String toString() {
		return "AddressPO [addressid=" + addressid + ", country=" + country + ", province=" + province + ", city="
				+ city + ", detailaddress=" + detailaddress + ", postal=" + postal + ", fax=" + fax + ", phonenumber="
				+ phonenumber + "]";
	}
	
}
