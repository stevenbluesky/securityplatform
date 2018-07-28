package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.service.ZwaveDeviceService;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.DeviceSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("device")
public class DeviceController {

	@Autowired
	private ZwaveDeviceService ds;
	
	@RequestMapping("deviceJsonList")
	@ResponseBody
	public Map<String, Object> deviceJsonList(PageResult pr, DeviceSearchVO dsv, HttpServletRequest request){
		String mysort = request.getParameter("mysort");
		String mysortOrder = request.getParameter("mysortOrder");
		Pageable pageable = null;
		if(StringUtils.isEmpty(mysort)||StringUtils.isEmpty(mysortOrder)){
			pageable = PageRequest.of(pr.getPage()-1,pr.getRows(),Sort.Direction.DESC,"warningstatuses");
			pr.setSortOrder("asc");
			pr.setOrder("zwavedeviceid");
		}else{
			pageable = PageRequest.of(pr.getPage()-1,pr.getRows(), Sort.Direction.fromString(mysortOrder.toUpperCase()),mysort);
			pr.setSortOrder(mysortOrder.toUpperCase());
			pr.setOrder(mysort);
		}
		if (!FormUtils.isEmpty(dsv)) {
			return ds.newSearchZwaveDevice(pr, dsv,request,pageable);
		}
        return ds.newListZwaveDevice(pr, request,pageable);
	}
	
	@RequestMapping("deviceList")
	public String deviceList() {
		return "device/deviceList";
	}

	@ResponseBody
	@RequestMapping(value="savearea",method = RequestMethod.POST)
	public String savearea(@RequestBody String areanumber) {
		String number = "";
		String zwavedeviceid = "";
		areanumber =  areanumber.replace( ",", "");
		try{
			zwavedeviceid = areanumber.split("#")[0];
			number =areanumber.split("#")[1];
			int i = Integer.parseInt(number);
			if(i<=0||i>100||!number.equals(String.valueOf(i))){
				throw new RuntimeException();
			}
		}catch (Exception e1){
			//提示用户格式不对
			return "format";
		}

		try {
			ds.savearea(zwavedeviceid,number);
		}catch (Exception e){
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	@RequestMapping("deviceDetail")
	public String deviceDetail(Integer zwavedeviceid,Model model) {
		if(zwavedeviceid == null || zwavedeviceid == 0) {
			return "device/deviceDetail";
		}
		model.addAttribute("zwave",ds.findDeviceDetail(zwavedeviceid));
		return "device/deviceDetail";
	}

	@RequestMapping("toggleDeviceStatus")
	@ResponseBody
	public JsonResult toggleDeviceStatus(HttpServletRequest request,Integer zwavedeviceid,Integer toStatus){
        JsonResult jr = new JsonResult();
        try {
            ds.toggleDeviceStatus(toStatus, zwavedeviceid, request);
            jr.setStatus(1);
            return jr;
        } catch (RuntimeException e) {
            e.printStackTrace();
            jr.setStatus(-1);
            jr.setMsg(e.getMessage());
            return jr;
        }
    }
	@RequestMapping("dscdeviceList")
	public String dscdeviceList() {
		return "device/dscdeviceList";
	}

	@RequestMapping("adddsc")
	public String adddsc() {
		return "device/adddsc";
	}
}
