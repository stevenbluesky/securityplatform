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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.service.EmployeeService;
import cn.com.isurpass.house.service.OrganizationService;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.LoginVO;

@Controller
@RequestMapping("login")
public class LoginController {

	@Autowired
	EmployeeService emps;
	@Autowired
	OrganizationService os;

	@RequestMapping("login")
	@ResponseBody
	public JsonResult pageLogin(LoginVO login, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
			// 生成令牌,以便 reaml 里面进行认证
			UsernamePasswordToken token = new UsernamePasswordToken(JSON.toJSONString(login), FormUtils.encrypt(login.getPassword()));
			Subject subject = SecurityUtils.getSubject();
			// 加载 reaml 进行认证登录
			subject.login(token);
			EmployeePO emp = (EmployeePO) subject.getPrincipal();// 获取登录成功的用户对象(以前是直接去service里面查)
			request.getSession().setAttribute("emp", emp);
			//TODO 这里可以加一个当前员工角色的字段返回到前端,可以很容易的进行内容的显隐
			request.getSession().setAttribute("admin", os.isAdmin(emp.getOrganizationid()));
			return new JsonResult(1, "success");
		} catch (UnavailableSecurityManagerException e) {
			e.printStackTrace();
			return new JsonResult(-1, "登录失败");
 		} catch (AuthenticationException e) {
			e.printStackTrace();
			return new JsonResult(-1, "登录失败");
		}

	}
}
