package cn.com.isurpass.house.vo;

public class LoginVO {

	private String loginname;
	private String password;
	private String code;
	private String captchacode;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCaptchacode() {
		return captchacode;
	}

	public void setCaptchacode(String captchacode) {
		this.captchacode = captchacode;
	}

}
