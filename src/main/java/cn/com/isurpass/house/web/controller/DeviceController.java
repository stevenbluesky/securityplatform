package cn.com.isurpass.house.web.controller;

import java.util.Map;

import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.DeviceSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.service.ZwaveDeviceService;
import cn.com.isurpass.house.result.PageResult;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("device")
public class DeviceController {

	@Autowired
	ZwaveDeviceService ds;
	
	@RequestMapping("deviceJsonList")
	@ResponseBody
	public Map<String, Object> deviceJsonList(PageResult pr, DeviceSearchVO dsv, HttpServletRequest request){
		String mysort = request.getParameter("mysort");
		String mysortOrder = request.getParameter("mysortOrder");
		if(StringUtils.isEmpty(mysort)||StringUtils.isEmpty(mysortOrder)){
			Pageable pageable = PageRequest.of(pr.getPage()-1,pr.getRows(),Sort.Direction.ASC,"zwavedeviceid");
			pr.setSortOrder("asc");
			pr.setOrder("zwavedeviceid");
		}else{
			Pageable pageable = PageRequest.of(pr.getPage()-1,pr.getRows(), Sort.Direction.fromString(mysortOrder.toUpperCase()),mysort);
			pr.setSortOrder(mysortOrder.toUpperCase());
			pr.setOrder(mysort);
		}
		if (!FormUtils.isEmpty(dsv)) {//搜索
//			return ds.search(pageable, dsv,request);
			return ds.newSearchZwaveDevice(pr, dsv,request);
		}
//		return ds.listDevice(pageable,request);
        return ds.newListZwaveDevice(pr, request);
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

	@RequestMapping("toggleDeviceStatus")
	@ResponseBody
	public JsonResult toggleDeviceStatus(HttpServletRequest request,Integer zwavedeviceid,Integer toStatus){
        JsonResult jr = new JsonResult();
        try {
            String s = ds.toggleDeviceStatus(toStatus, zwavedeviceid, request);
            jr.setStatus(1);
            return jr;
        } catch (RuntimeException e) {
            e.printStackTrace();
            jr.setStatus(-1);
            jr.setMsg(e.getMessage());
            return jr;
        }
    }
}
