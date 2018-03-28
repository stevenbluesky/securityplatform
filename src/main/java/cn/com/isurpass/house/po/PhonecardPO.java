package cn.com.isurpass.house.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "phonecard")
public class PhonecardPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer phonecardid;
	private String serialnumber;
	private String model;
	private Integer status;
	private String rateplan;
	private String firmwareversion;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date orderingdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date activationdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expiredate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reactivationdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date firstprogrammedondate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastprogrammedondate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastsavedondate;

	public Integer getPhonecardid() {
		return phonecardid;
	}

	public void setPhonecardid(Integer phonecardid) {
		this.phonecardid = phonecardid;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	@Override
	public String toString() {
		return "PhonecardPO [phonecardid=" + phonecardid + ", serialnumber=" + serialnumber + ", model=" + model
				+ ", status=" + status + ", rateplan=" + rateplan + ", firmwareversion=" + firmwareversion
				+ ", orderingdate=" + orderingdate + ", activationdate=" + activationdate + ", expiredate=" + expiredate
				+ ", reactivationdate=" + reactivationdate + ", firstprogrammedondate=" + firstprogrammedondate
				+ ", lastprogrammedondate=" + lastprogrammedondate + ", lastsavedondate=" + lastsavedondate + "]";
	}
	
}
