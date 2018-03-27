package cn.com.isurpass.house.vo;

import cn.com.isurpass.house.po.AddressPO;

/**
 * 这是新增服务商表单对应的实体对象.<br>
 * TODO 这个类名可能会改,因为服务商和安装商表单属性是差不多的.
 *
 * @author jwzh
 */
public class OrgAddVO {

    private Integer orgnizationid;

    private AddressPO address;
    private Integer parentorgid; // 所属安装商的名称
    private String name;// *
    private String code;// *
    private Integer country;// *
    private Integer province;// *
    private Integer city;// *
    private String detailaddress;
    private String postal;

    // 公司账务地址
    private AddressPO baddress;
    private Integer bcountry;
    private Integer bprovince;
    private Integer bcity;
    private String bdetailaddress;
    private String bpostal;

    // 服务商联系人
    private String sname;
    private String sphonenumber;
    private String sfax;
    private String semail;

    // 服务商总公司
    private AddressPO csaddress;
    private String csname;
    private Integer cscountry;
    private Integer csprovince;
    private Integer cscity;
    private String cspostal;

    // 服务商总公司联系人
    private String cspname;
    private String cspphonenumber;
    private String cspfax;
    private String cspemail;

    // 管理员
    private String loginname;// *
    private String password;// *
    private String question;
    private String answer;

    private Integer officeaddressid;
    private Integer billingaddressid;
    private Integer contactid;
    private Integer csaddressid;
    private Integer cscontactid;
    private Integer Orgtype;

    public Integer getOrgtype() {
        return Orgtype;
    }

    public void setOrgtype(Integer orgtype) {
        Orgtype = orgtype;
    }

    public Integer getOfficeaddressid() {
        return officeaddressid;
    }

    public void setOfficeaddressid(Integer officeaddressid) {
        this.officeaddressid = officeaddressid;
    }

    public Integer getBillingaddressid() {
        return billingaddressid;
    }

    public void setBillingaddressid(Integer billingaddressid) {
        this.billingaddressid = billingaddressid;
    }

    public Integer getContactid() {
        return contactid;
    }

    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }


    public Integer getCsaddressid() {
        return csaddressid;
    }

    public void setCsaddressid(Integer csaddressid) {
        this.csaddressid = csaddressid;
    }

    public Integer getCscontactid() {
        return cscontactid;
    }

    public void setCscontactid(Integer cscontactid) {
        this.cscontactid = cscontactid;
    }

    public AddressPO getBaddress() {
        return baddress;
    }

    public void setBaddress(AddressPO baddress) {
        this.baddress = baddress;
    }

    public AddressPO getCsaddress() {
        return csaddress;
    }

    public void setCsaddress(AddressPO csaddress) {
        this.csaddress = csaddress;
    }

    public AddressPO getAddress() {
        return address;
    }

    public void setAddress(AddressPO address) {
        this.address = address;
    }

    public Integer getOrgnizationid() {
        return orgnizationid;
    }

    public void setOrgnizationid(Integer orgnizationid) {
        this.orgnizationid = orgnizationid;
    }

    public Integer getParentorgid() {
        return parentorgid;
    }

    public void setParentorgid(Integer parentorgid) {
        this.parentorgid = parentorgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Integer getBcountry() {
        return bcountry;
    }

    public void setBcountry(Integer bcountry) {
        this.bcountry = bcountry;
    }

    public Integer getBprovince() {
        return bprovince;
    }

    public void setBprovince(Integer bprovince) {
        this.bprovince = bprovince;
    }

    public Integer getBcity() {
        return bcity;
    }

    public void setBcity(Integer bcity) {
        this.bcity = bcity;
    }

    public String getBdetailaddress() {
        return bdetailaddress;
    }

    public void setBdetailaddress(String bdetailaddress) {
        this.bdetailaddress = bdetailaddress;
    }

    public String getBpostal() {
        return bpostal;
    }

    public void setBpostal(String bpostal) {
        this.bpostal = bpostal;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSphonenumber() {
        return sphonenumber;
    }

    public void setSphonenumber(String sphonenumber) {
        this.sphonenumber = sphonenumber;
    }

    public String getSfax() {
        return sfax;
    }

    public void setSfax(String sfax) {
        this.sfax = sfax;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }

    public Integer getCscountry() {
        return cscountry;
    }

    public void setCscountry(Integer cscountry) {
        this.cscountry = cscountry;
    }

    public Integer getCsprovince() {
        return csprovince;
    }

    public void setCsprovince(Integer csprovince) {
        this.csprovince = csprovince;
    }

    public Integer getCscity() {
        return cscity;
    }

    public void setCscity(Integer cscity) {
        this.cscity = cscity;
    }

    public String getCspostal() {
        return cspostal;
    }

    public void setCspostal(String cspostal) {
        this.cspostal = cspostal;
    }

    public String getCspname() {
        return cspname;
    }

    public void setCspname(String cspname) {
        this.cspname = cspname;
    }

    public String getCspphonenumber() {
        return cspphonenumber;
    }

    public void setCspphonenumber(String cspphonenumber) {
        this.cspphonenumber = cspphonenumber;
    }

    public String getCspfax() {
        return cspfax;
    }

    public void setCspfax(String cspfax) {
        this.cspfax = cspfax;
    }

    public String getCspemail() {
        return cspemail;
    }

    public void setCspemail(String cspemail) {
        this.cspemail = cspemail;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "OrgAddVO{" +
                "orgnizationid=" + orgnizationid +
                ", address=" + address +
                ", parentorgid=" + parentorgid +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", country=" + country +
                ", province=" + province +
                ", city=" + city +
                ", detailaddress='" + detailaddress + '\'' +
                ", postal='" + postal + '\'' +
                ", baddress=" + baddress +
                ", bcountry=" + bcountry +
                ", bprovince=" + bprovince +
                ", bcity=" + bcity +
                ", bdetailaddress='" + bdetailaddress + '\'' +
                ", bpostal='" + bpostal + '\'' +
                ", sname='" + sname + '\'' +
                ", sphonenumber='" + sphonenumber + '\'' +
                ", sfax='" + sfax + '\'' +
                ", semail='" + semail + '\'' +
                ", csaddress=" + csaddress +
                ", csname='" + csname + '\'' +
                ", cscountry=" + cscountry +
                ", csprovince=" + csprovince +
                ", cscity=" + cscity +
                ", cspostal='" + cspostal + '\'' +
                ", cspname='" + cspname + '\'' +
                ", cspphonenumber='" + cspphonenumber + '\'' +
                ", cspfax='" + cspfax + '\'' +
                ", cspemail='" + cspemail + '\'' +
                ", loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", officeaddressid=" + officeaddressid +
                ", billingaddressid=" + billingaddressid +
                ", contactid=" + contactid +
                ", csaddressid=" + csaddressid +
                ", cscontactid=" + cscontactid +
                ", Orgtype=" + Orgtype +
                '}';
    }
}
