package cn.com.isurpass.house.web.controller;

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
	public String typePhonecardInfo() {
		return "phonecard/typePhonecardInfo";
	}
}
