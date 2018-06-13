package cn.com.isurpass.house.vo;

public class LoginVO {

	private String loginname;
	private String password;
	private String organizationid;
	private String captchacode;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}

	public String getCaptchacode() {
		return captchacode;
	}

	public void setCaptchacode(String captchacode) {
		this.captchacode = captchacode;
	}

}
