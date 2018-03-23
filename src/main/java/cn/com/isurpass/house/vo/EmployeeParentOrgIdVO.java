package cn.com.isurpass.house.vo;

/**
 * 这个对象是用来存储安装商与其父机构的id的实体类
 * @author jwzh
 *
 */
public class EmployeeParentOrgIdVO {

	private Integer installerorgid; //安装商id
	private Integer installerid; //安装员id
	private Integer organizationid; //服务商id
	public Integer getInstallerorgid() {
		return installerorgid;
	}
	public void setInstallerorgid(Integer installerorgid) {
		this.installerorgid = installerorgid;
	}
	public Integer getInstallerid() {
		return installerid;
	}
	public void setInstallerid(Integer installerid) {
		this.installerid = installerid;
	}
	public Integer getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(Integer organizationid) {
		this.organizationid = organizationid;
	}
	
}
