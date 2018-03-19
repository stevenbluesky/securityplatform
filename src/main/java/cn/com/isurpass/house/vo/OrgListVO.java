package cn.com.isurpass.house.vo;

/**
 * 服务商,安装商 列表
 * 
 * @author jwzh
 *
 */
public class OrgListVO {

	private Integer organizationid;
	private String name;
	private String code;
	private String city;
	private Integer status;

	public Integer getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Integer organizationid) {
		this.organizationid = organizationid;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
