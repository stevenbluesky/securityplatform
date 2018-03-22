package cn.com.isurpass.house.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("phonecard")
public class PhonecardController {

	@RequestMapping("phonecardList")
	public String phonecardList() {
		return "phonecard/phonecardList";
	}
	
	@RequestMapping("typePhonecardInfo")
	public String typePhonecardInfo(HttpServletRequest request,HttpServletResponse resbonse) {
		request.setAttribute("msg", "zhul");
		return "phonecard/typePhonecardInfo";
	}
}
