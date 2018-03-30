package cn.com.isurpass.house.vo;

import java.util.Date;
import java.util.List;

import cn.com.isurpass.house.po.ZwaveDevicePO;

public class GatewayDetailVO {
	private String deviceid;//网关id
	private String name;//网关名称
	private Integer status;//网关状态，0离线，1在线
	private String model;//网关型号
	private Integer battery;//网关电量
	private Date createtime;//网关安装时间
	private String firmwareversion;//网关硬件版本
	private Integer businessstatus;//网关业务状态
	private String customer;//用户
	private String installer;//安装员
	
	private String phonecardserialnumber;//电话卡序列号
	private String phonecardmodel;//电话卡型号
	private Integer phonecardstatus;//电话卡状态
	private String rateplan;
	private String phonecardfirmwareversion;//电话卡硬件版本
	private Date activationdate ;
	private Date firstprogrammedon;
	private Date lastprogrammedon;
	private Date orderingdate;
	private Date expiredate ;
	private List<ZwaveDevicePO> device;
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getBattery() {
		return battery;
	}
	public void setBattery(Integer battery) {
		this.battery = battery;
	}
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getFirmwareversion() {
		return firmwareversion;
	}
	public void setFirmwareversion(String firmwareversion) {
		this.firmwareversion = firmwareversion;
	}
	public Integer getBusinessstatus() {
		return businessstatus;
	}
	public void setBusinessstatus(Integer businessstatus) {
		this.businessstatus = businessstatus;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getInstaller() {
		return installer;
	}
	public void setInstaller(String installer) {
		this.installer = installer;
	}
	public String getPhonecardserialnumber() {
		return phonecardserialnumber;
	}
	public void setPhonecardserialnumber(String phonecardserialnumber) {
		this.phonecardserialnumber = phonecardserialnumber;
	}
	public String getPhonecardmodel() {
		return phonecardmodel;
	}
	public void setPhonecardmodel(String phonecardmodel) {
		this.phonecardmodel = phonecardmodel;
	}
	public Integer getPhonecardstatus() {
		return phonecardstatus;
	}
	public void setPhonecardstatus(Integer phonecardstatus) {
		this.phonecardstatus = phonecardstatus;
	}
	public String getRateplan() {
		return rateplan;
	}
	public void setRateplan(String rateplan) {
		this.rateplan = rateplan;
	}
	public String getPhonecardfirmwareversion() {
		return phonecardfirmwareversion;
	}
	public void setPhonecardfirmwareversion(String phonecardfirmwareversion) {
		this.phonecardfirmwareversion = phonecardfirmwareversion;
	}
	public Date getActivationdate() {
		return activationdate;
	}
	public void setActivationdate(Date activationdate) {
		this.activationdate = activationdate;
	}
	public Date getFirstprogrammedon() {
		return firstprogrammedon;
	}
	public void setFirstprogrammedon(Date firstprogrammedon) {
		this.firstprogrammedon = firstprogrammedon;
	}
	public Date getLastprogrammedon() {
		return lastprogrammedon;
	}
	public void setLastprogrammedon(Date lastprogrammedon) {
		this.lastprogrammedon = lastprogrammedon;
	}
	public Date getOrderingdate() {
		return orderingdate;
	}
	public void setOrderingdate(Date orderingdate) {
		this.orderingdate = orderingdate;
	}
	public Date getExpiredate() {
		return expiredate;
	}
	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}
	public List<ZwaveDevicePO> getDevice() {
		return device;
	}
	public void setDevice(List<ZwaveDevicePO> device) {
		this.device = device;
	}
	
}
