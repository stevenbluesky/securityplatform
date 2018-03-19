package cn.com.isurpass.house.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gateway")
public class GatewayController {

	@RequestMapping("addGateway")
	public String addGateway() {
		return "gateway/addGateway";
	}
	
	@RequestMapping("gatewayList")
	public String gatewayList() {
		return "gateway/gatewayList";
	}
	
	@RequestMapping("typeGatewayInfo")
	public String typeGatewayInfo() {
		return "gateway/typeGatewayInfo";
	}
	
	@RequestMapping("gatewayDetail")
	public String gatewayDetail() {
		return "gateway/gatewayDetail";
	}
	
}
