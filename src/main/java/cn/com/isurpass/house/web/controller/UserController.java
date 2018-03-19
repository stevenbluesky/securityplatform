package cn.com.isurpass.house.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

	@RequestMapping("userList")
	public String userList() {
		return "user/userList";
	}
	
	@RequestMapping("typeUserInfo")
	public String typeUserInfo() {
		return "user/typeUserInfo";
	}
}
