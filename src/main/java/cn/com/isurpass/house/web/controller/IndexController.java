package cn.com.isurpass.house.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jwzh
 */
@Controller
public class IndexController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("login")
	public String login() {
		return "login";
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
	}
}
