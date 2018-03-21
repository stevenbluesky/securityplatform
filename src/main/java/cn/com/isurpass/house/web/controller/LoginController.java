package cn.com.isurpass.house.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.isurpass.house.service.EmployeeService;

@Controller
@RequestMapping("login")
public class LoginController {

	@Autowired
	EmployeeService emps;
	
	@RequestMapping("login")
	public String pageLogin(String loginname, String password, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
			// 登录后存放进 shiro token
			UsernamePasswordToken token = new UsernamePasswordToken(loginname, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			request.getSession().setAttribute("empinfo", loginname);
		} catch (UnavailableSecurityManagerException e) {
			e.printStackTrace();
			return "/403";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "/403";
		}
		return "/index";
	}
}
