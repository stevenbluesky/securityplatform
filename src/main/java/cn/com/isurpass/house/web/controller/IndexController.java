package cn.com.isurpass.house.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jwzh
 *
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
	
}
