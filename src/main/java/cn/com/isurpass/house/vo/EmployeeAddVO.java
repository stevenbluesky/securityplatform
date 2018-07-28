package cn.com.isurpass.house.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EmployeeAddVO {
    private String orgCode;
    private Integer modify;
    private Integer Organizationid;
    private String parentOrgName;
    private String loginname;
    private String password;
    private String question;
    private String answer;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date expiredate;
    private Integer status;
    private Integer type;
    private String firstname;
    private String lastname;
    private String title;
    private String ssn;
    private Integer gender;
    private String phonenumber;
    private String email;
    private String fax;

    private Integer country;
    private Integer province;
    private Integer city;
    private String detailaddress;
    private String countryname;
    private String provincename;
    private String cityname;

    private Integer employeeid;
    private Integer personid;
    private Integer addressid;
    private Integer createtime;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }

    public Integer getModify() {
        return modify;
    }

    public void setModify(Integer modify) {
        this.modify = modify;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Integer getOrganizationid() {
        return Organizationid;
    }

    public void setOrganizationid(Integer organizationid) {
        Organizationid = organizationid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public String getAnswer() {
        return answer;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress;
    }

    @Override
    public String toString() {
        return "EmployeeAddVO{" +
                "orgCode='" + orgCode + '\'' +
                ", modify=" + modify +
                ", Organizationid=" + Organizationid +
                ", parentOrgName='" + parentOrgName + '\'' +
                ", loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", code='" + code + '\'' +
                ", expiredate=" + expiredate +
                ", status=" + status +
                ", type=" + type +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", title='" + title + '\'' +
                ", ssn='" + ssn + '\'' +
                ", gender=" + gender +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", fax='" + fax + '\'' +
                ", country=" + country +
                ", province=" + province +
                ", city=" + city +
                ", detailaddress='" + detailaddress + '\'' +
                ", countryname='" + countryname + '\'' +
                ", provincename='" + provincename + '\'' +
                ", cityname='" + cityname + '\'' +
                ", employeeid=" + employeeid +
                ", personid=" + personid +
                ", addressid=" + addressid +
                ", createtime=" + createtime +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
