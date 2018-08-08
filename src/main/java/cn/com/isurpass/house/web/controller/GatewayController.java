package cn.com.isurpass.house.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.DeviceDetailVO;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.servlet.ModelAndView;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.ZwaveDevicePO;
import cn.com.isurpass.house.service.GatewayService;
import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.vo.GatewayDetailVO;
import cn.com.isurpass.house.vo.TransferVO;
import cn.com.isurpass.house.vo.TypeGatewayInfoVO;

import static com.google.zxing.client.j2se.MatrixToImageWriter.toBufferedImage;

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
		Pageable pageable = PageRequest.of(pr.getPage()-1,pr.getRows(),Sort.Direction.DESC,"gatewayid");
		EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");

		if(FormUtils.isEmpty(tgiv)){
			return gs.listNUllGateway(pageable,emp);
		}
		//return gs.findGatewayJsonList(pageable,tgiv,emp);
		return gs.listGateway(pageable,tgiv,emp);
	}
	/**
	 * 根据deviceid获取网关详细信息
	 * @param deviceid
	 * @return
	 */
	@RequestMapping("gatewayDetail")
	public String gatewayDetail(String deviceid,Model model){
		if(StringUtils.isEmpty(deviceid)){
			return null;
		}
		GatewayDetailVO gw;
		try {
			gw = gs.findByDeviceid(deviceid,null);
		} catch (Exception e) {
			return null;
		}
		model.addAttribute("gwd", gw);
//		model.addAttribute("gdd", gw.getDevice());
		return "gateway/gatewayDetail";
	}
	@ResponseBody
	@RequestMapping("gatewayDeviceDetail")
	public Map<String,Object> gatewayDeviceDetail(PageResult pr,String deviceid,Model model){
		Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.ASC, "zwavedeviceid");
		if(StringUtils.isEmpty(deviceid)){
			return null;
		}
		GatewayDetailVO gw;
		try {
			gw = gs.findByDeviceid(deviceid, pageable);
		} catch (Exception e) {
			return null;
		}
		List<DeviceDetailVO> list= gw.getDevice();
		Map<String,Object> map = new HashMap<>();
		map.put("total",gw.getTotal());
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
			return e.getMessage();
		}
		return "success";
	}

	/**
	 * 根据网关id查询二维码信息并生成二维码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getGatewayQrcode",method = RequestMethod.POST)
	public String getGatewayQrcode(@RequestBody String deviceid){
		deviceid = deviceid.replaceAll(" ","");
		String qrinfo = gs.getQrcodeInfo(deviceid);
		if ( qrinfo == null) {
			qrinfo = "";
		}
		return qrinfo;
		/*"{\"type\":\"gateway\",\"qid\":\"80XjcRQ9LkqsY3e4ocgX\"}"*/
	}
	@RequestMapping(value = "getGatewayQrcode1")
	public void getErWeiCode(HttpServletRequest request,HttpServletResponse response){
		String qrstring =request.getParameter("qrstring");
		try {
			JSONObject jsobj = JSONObject.parseObject(qrstring);
			String type = jsobj.getString("type");
			if(!type.equals("gateway")){
				qrstring = "";
			}
		}catch (Exception ee){
			qrstring = "";
		}
		if(qrstring!=null&&!"".equals(qrstring)){
			 ServletOutputStream stream=null;
			try {
				int width=400;
				int height=400;
				 stream=response.getOutputStream();
				 QRCodeWriter writer=new QRCodeWriter();
				 BitMatrix m=writer.encode(qrstring, BarcodeFormat.QR_CODE, height,width);
				 MatrixToImageWriter.writeToStream(m, "png", stream);
				} catch (Exception e) {
				   e.printStackTrace();
				}finally{
					 if(stream!=null){
						 try {
							 stream.flush();
							 stream.close();
						 } catch (IOException e) {
							 e.printStackTrace();
						 }
					 }
			}
		}
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

	@RequestMapping("queryGatewayQrcode")
	public String queryGatewayQrcodePage() {
		return "gateway/queryGatewayQrcode";
	}
}
