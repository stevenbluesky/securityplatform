package cn.com.isurpass.house.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class PersonPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer personid;
	private String name;
	private String ssn;
	private Integer gender; // 0:女 1:男 2:LGBT
	private String title;
	private String firstname;
	private String lastname;
	private String phonenumber;
	private String email;
	private String fax;
	private Integer addressid;

	public PersonPO() {
		super();
	}

	public PersonPO(String name, String phonenumber, String email, String fax) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
		this.email = email;
		this.fax = fax;
	}

	public PersonPO(Integer personid, String name, String ssn, Integer gender, String title, String firstname,
			String lastname, String phonenumber, String email, String fax, Integer addressid) {
		super();
		this.personid = personid;
		this.name = name;
		this.ssn = ssn;
		this.gender = gender;
		this.title = title;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
		this.email = email;
		this.fax = fax;
		this.addressid = addressid;
	}

	public PersonPO(String name, String ssn, Integer gender, String title, String firstname, String lastname, String phonenumber, String email, String fax) {
		this.name= name;
		this.ssn = ssn;
		this.gender = gender;
		this.title =title;
		this.firstname =firstname;
		this.lastname = lastname;
		this.phonenumber =	phonenumber;
		this.email	=email;
		this.fax =	 fax;
	}

	public Integer getPersonid() {
		return personid;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

}
