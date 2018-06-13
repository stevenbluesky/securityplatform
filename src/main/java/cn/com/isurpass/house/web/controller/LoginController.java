package cn.com.isurpass.house.web.controller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.ValidateCode;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import jdk.nashorn.internal.runtime.ECMAException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.service.EmployeeService;
import cn.com.isurpass.house.service.OrganizationService;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.LoginVO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("login")
public class LoginController {

	@Autowired
	EmployeeService emps;
	@Autowired
	OrganizationService os;

	@RequestMapping("login")
	@ResponseBody
	public JsonResult pageLogin(LoginVO login, HttpServletResponse response, HttpServletRequest request) {
		String validateCode = (String) request.getSession().getAttribute("validateCode");
		String code = login.getCaptchacode();
		if(StringUtils.isEmpty(code)||!code.equalsIgnoreCase(validateCode)){
			return new JsonResult(-1, "-2");
		}
		try{
			OrganizationPO org = emps.findOrgByLoginName(login.getLoginname());
			login.setCode(org.getCode());
			login.setOrganizationid(org.getOrganizationid()+"");
			os.findOrgStatus(login.getOrganizationid());
		}catch (Exception ee){
			return new JsonResult(-1, "-3");
		}
		try {
			SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
			// 生成令牌,以便 reaml 里面进行认证
			UsernamePasswordToken token = new UsernamePasswordToken(JSON.toJSONString(login), login.getPassword());
			Subject subject = SecurityUtils.getSubject();
			// 加载 reaml 进行认证登录
			subject.login(token);
			EmployeePO emp = (EmployeePO) subject.getPrincipal();// 获取登录成功的用户对象(以前是直接去service里面查)
			request.getSession().setAttribute("emp", emp);
			OrganizationPO org = os.findOrgByLoginEmp(request);
			request.getSession().setAttribute("loginorg",org);
			//TODO 这里可以加一个当前员工角色的字段返回到前端,可以很容易的进行内容的显隐
			request.getSession().setAttribute("admin", os.isAdmin(emp.getOrganizationid()));

			return new JsonResult(1, "success");
		} catch (UnavailableSecurityManagerException e) {
			e.printStackTrace();
			return new JsonResult(-1, "-98");
 		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(-1, "-98");
		}

	}

	@RequestMapping("getOrg")
	@ResponseBody
	public List<OrganizationPO> listAllOrg() {
		return os.listAllOrg();
	}
	@RequestMapping("getOrgCode")
	@ResponseBody
	public String getOrgCode(Model model, HttpServletRequest request) {
		String loginname = request.getParameter("loginname");
		try {
			OrganizationPO codeByLoginname = os.findCodeByLoginname(loginname);
			request.getSession().setAttribute("orgbyloginname",codeByLoginname);
		}catch (Exception e){
			return "failed";
		}
		return "success";
	}
	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getCode")
	public void getCode(HttpServletRequest request, HttpServletResponse response){
		ValidateCode code = new ValidateCode(100,30,4,30,25,"validateCode");
		request.getSession();
		String validateCode = code.getCode(request, response);
	}
}
