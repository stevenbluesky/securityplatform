package cn.com.isurpass.house.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.service.ZwaveDeviceService;
import cn.com.isurpass.house.util.PageResult;

@Controller
@RequestMapping("device")
public class DeviceController {

	@Autowired
	ZwaveDeviceService ds;
	
	@RequestMapping("deviceJsonList")
	@ResponseBody
	public Map<String, Object> deviceJsonList(PageResult pr){
		Pageable pageable = PageRequest.of(pr.getPage()-1,pr.getRows(),Sort.Direction.ASC,"zwavedeviceid");
		return ds.listDevice(pageable);
	} 
	
	@RequestMapping("deviceList")
	public String deviceList() {
		return "device/deviceList";
	}
	
	@RequestMapping("deviceDetail")
	public String deviceDetail(Integer zwavedeviceid,Model model) {
		if(zwavedeviceid == null || zwavedeviceid == 0)
			return "device/deviceDetail";
		model.addAttribute("zwave",ds.findDeviceDetail(zwavedeviceid));
		return "device/deviceDetail";
	}
}
