package cn.com.isurpass.house.web.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.GatewayPO;
import cn.com.isurpass.house.po.ZwaveDevicePO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.service.GatewayService;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.PageResult;
import cn.com.isurpass.house.vo.GatewayDetailVO;
import cn.com.isurpass.house.vo.TransferVO;
import cn.com.isurpass.house.vo.TypeGatewayInfoVO;

@Controller
@RequestMapping("/gateway")
public class GatewayController {
	@Autowired
	private GatewayService gs;
	
	/**
	 * 录入网关信息
	 * @param tgi
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="add",method = RequestMethod.GET)
	public ModelAndView add(TypeGatewayInfoVO tgi,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("gateway/typeGatewayInfo");
		mv.addObject("deviceid", tgi.getDeviceid());
        mv.addObject("model", tgi.getModel());
        mv.addObject("firmwareversion", tgi.getFirmwareversion());
		try {
			gs.add(tgi,request);
	        mv.addObject("msg", "4"); 
	        mv.addObject("msgdeviceid",tgi.getDeviceid());
		} catch (MyArgumentNullException e) {
			mv.addObject("msg", e.getMessage());  	
			return mv;
		} catch (RuntimeException e) {
			e.printStackTrace();
			mv.addObject("msg", "3");  	
			return mv;
		}
		return mv;
	}

	/**
	 * 获取网关分页信息
	 * @param pr
	 * @return
	 * http://localhost:8080/house/gateway/gatewayJsonList?name=&cityname=&citycode=&customer=&serviceprovider=&installerorg=&installer=&rows=10&page=2&_=1522138095864
	 */
	@RequestMapping("gatewayJsonList")
	@ResponseBody
	public Map<String, Object> gatewayJsonList(PageResult pr,TypeGatewayInfoVO tgiv,HttpServletRequest request) {
		Pageable pageable = PageRequest.of(pr.getPage()-1,pr.getRows(),Sort.Direction.ASC,"deviceid");
		EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
		if(FormUtils.isEmpty(tgiv)){
			return gs.listNUllGateway(pageable,emp);
		}
		return gs.listGateway(pageable,tgiv,emp);
	}
	
	/*@RequestMapping("gatewayDeviceJsonList")
	@ResponseBody
	public Map<String, Object> gatewayDeviceJsonList(PageResult pr,TypeGatewayInfoVO tgiv) {
		Pageable pageable = PageRequest.of(pr.getPage()-1,pr.getRows(),Sort.Direction.ASC,"deviceid");
		return gs.listGateway(pageable,tgiv);
	}*/
	/**
	 * 根据deviceid获取网关详细信息
	 * @param deviceid
	 * @return
	 */
	//@ResponseBody
	@RequestMapping("gatewayDetail")
	public String gatewayDetail(String deviceid,Model model) {
		if(StringUtils.isEmpty(deviceid)){
			return null;
		}
		GatewayDetailVO gw = gs.findByDeviceid(deviceid);
		model.addAttribute("gwd", gw);
		//model.addAttribute("gdd", gw.getDevice());
		return "gateway/gatewayDetail";
	}
	@ResponseBody
	@RequestMapping("gatewayDeviceDetail")
	public Map<String,Object> gatewayDeviceDetail(PageResult pr,String deviceid,Model model) {
		if(StringUtils.isEmpty(deviceid)){
			return null;
		}
		GatewayDetailVO gw = gs.findByDeviceid(deviceid);
		List<ZwaveDevicePO> list= gw.getDevice();
		Map<String,Object> map = new HashMap<>();
		map.put("total",list.size());
		map.put("rows",list);
		return map;
	}
	/**
	 * 根据操作及id执行更新 
	 * @param tf
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="update",method = RequestMethod.POST)
	public String update(@RequestBody TransferVO tf){
		String hope = tf.getHope();
		Object[] ids = tf.getIds();
		try {
			gs.updateGatewayStatus(hope,ids);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	//页面跳转
	@RequestMapping("gatewayList")
	public String gatewayList() {
		return "gateway/gatewayList";
	}
	
	@RequestMapping("typeGatewayInfo")
	public String typeGatewayInfo() {
		return "gateway/typeGatewayInfo";
	}
	
	
	@RequestMapping("addGateway")
	public String addGateway() {
		return "gateway/addGateway";
	}
}
