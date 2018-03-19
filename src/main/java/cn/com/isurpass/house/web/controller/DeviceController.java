package cn.com.isurpass.house.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("device")
public class DeviceController {

	@RequestMapping("deviceList")
	public String deviceList() {
		return "device/deviceList";
	}
	
	@RequestMapping("deviceDetail")
	public String deviceDetail() {
		return "device/deviceDetail";
	}
}
