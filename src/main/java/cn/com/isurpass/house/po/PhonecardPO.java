package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phonecard")
public class PhonecardPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer phonecardid;
	private Integer serialnumber;
	private String simsn;
	private Integer status;
	private String rateplan;
	private String firmwareversion;
	private Date orderingdate;
	private Date activationdate;
	private Date expiredate;
	private Date reactivationdate;
	private Date firstprogrammedondate;
	private Date lastprogrammedondate;
	private Date lastsavedondate;

	public Integer getPhonecardid() {
		return phonecardid;
	}

	public void setPhonecardid(Integer phonecardid) {
		this.phonecardid = phonecardid;
	}

	public Integer getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(Integer serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getSimsn() {
		return simsn;
	}

	public void setSimsn(String simsn) {
		this.simsn = simsn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRateplan() {
		return rateplan;
	}

	public void setRateplan(String rateplan) {
		this.rateplan = rateplan;
	}

	public String getFirmwareversion() {
		return firmwareversion;
	}

	public void setFirmwareversion(String firmwareversion) {
		this.firmwareversion = firmwareversion;
	}

	public Date getOrderingdate() {
		return orderingdate;
	}

	public void setOrderingdate(Date orderingdate) {
		this.orderingdate = orderingdate;
	}

	public Date getActivationdate() {
		return activationdate;
	}

	public void setActivationdate(Date activationdate) {
		this.activationdate = activationdate;
	}

	public Date getExpiredate() {
		return expiredate;
	}

	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}

	public Date getReactivationdate() {
		return reactivationdate;
	}

	public void setReactivationdate(Date reactivationdate) {
		this.reactivationdate = reactivationdate;
	}

	public Date getFirstprogrammedondate() {
		return firstprogrammedondate;
	}

	public void setFirstprogrammedondate(Date firstprogrammedondate) {
		this.firstprogrammedondate = firstprogrammedondate;
	}

	public Date getLastprogrammedondate() {
		return lastprogrammedondate;
	}

	public void setLastprogrammedondate(Date lastprogrammedondate) {
		this.lastprogrammedondate = lastprogrammedondate;
	}

	public Date getLastsavedondate() {
		return lastsavedondate;
	}

	public void setLastsavedondate(Date lastsavedondate) {
		this.lastsavedondate = lastsavedondate;
	}

}
