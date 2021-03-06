package cn.com.isurpass.house.web.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.util.ExportExcelUtil;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.servlet.ModelAndView;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.PhonecardPO;
import cn.com.isurpass.house.service.PhonecardService;
import cn.com.isurpass.house.result.PageResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("phonecard")
public class PhonecardController {
	@Autowired
	private PhonecardService ps;
	@ResponseBody
	@RequestMapping(value="add",method = RequestMethod.POST)
	public ModelAndView add(PhonecardPO pc) {
		ModelAndView mv = new ModelAndView("phonecard/typePhonecardInfo");
		mv.addObject("serialnumber", pc.getSerialnumber());
        mv.addObject("model", pc.getModel());
        mv.addObject("firmwareversion", pc.getFirmwareversion());
        mv.addObject("rateplan", pc.getRateplan());
        mv.addObject("activationdate", pc.getActivationdate());
        mv.addObject("firstprogrammedondate", pc.getFirstprogrammedondate());
        mv.addObject("lastprogrammedondate", pc.getLastprogrammedondate());
        mv.addObject("lastsavedondate", pc.getLastsavedondate());
        mv.addObject("orderingdate", pc.getOrderingdate());
        mv.addObject("expiredate",pc.getExpiredate());
        try {
			ps.add(pc);
	        mv.addObject("msg", "6"); 
	        mv.addObject("msgserialnumber",pc.getSerialnumber());
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
	 * 获取电话卡分页信息
	 * @param pr
	 * @return
	 */
	@RequestMapping("phonecardJsonList")
	@ResponseBody
	public Map<String, Object> phonecardJsonList(PageResult pr,SimCardSearchVO pc,HttpServletRequest request) {
		Pageable pageable = PageRequest.of(pr.getPage()-1,pr.getRows(),Sort.Direction.DESC,"phonecardid");
		return ps.listPhonecard(pageable,pc,request);
	}

	@RequestMapping(value = "/exportsimcarddata")
	@ResponseBody
	public String exportSimCardData(SimCardSearchVO pc, HttpServletRequest request, HttpServletResponse response) {
		EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
		List<SimCardVO> dataset = ps.listPhonecard(pc,request);

		if(dataset==null||dataset.size()==0){
			return "No Data !";
		}
		String sheetName = "SIM Card Data";
		String titleName = "SIM Card Data";
		String fileName = "SIM Card Data";
		int columnNumber = 6;
		int[] columnWidth = { 35,20,20, 35,40,30};
		String[] columnName = {"SIM Card Number" ,"Status","Model","Firmware Version","Rate Plan","Activation Date"};
		try {
			HSSFWorkbook wb = new ExportExcelUtil().exportNoResponse(sheetName, titleName, columnNumber, columnWidth, columnName, dataset);
			if(wb==null){
				return "System Error !";
			}
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Export Successfully !";
	}
	/**
	 * 发送响应流方法
	 */
	private void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName+".xls");
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@RequestMapping("phonecardDetail")
	public String gatewayDetail(String phonecardid,Model model) {
		if(StringUtils.isEmpty(phonecardid)){
			return null;
		}
		PhonecardPO pp = ps.findByPhonecardid(phonecardid);
		model.addAttribute("pnd", pp);
		//model.addAttribute("gdd", gw.getDevice());
		return "phonecard/phonecardDetail";
	}
	/**
	 * 根据操作及id更新状态
	 * @param tf
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="update",method = RequestMethod.POST)
	public JsonResult update(@RequestBody TransferVO tf, HttpServletRequest request){
		EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
		String hope = tf.getHope();
		Object[] ids = tf.getIds();
		String confirmdelete = tf.getConfirmdelete();
		try {
			ps.updatePhonecardStatus(hope, ids,emp,confirmdelete);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(-1,e.getMessage().toString());
		}
		return new JsonResult(1,"1");
	}
	
	@RequestMapping("phonecardList")
	public String phonecardList() {
		return "phonecard/phonecardList";
	}
	
	@RequestMapping("typePhonecardInfo")
	public String typePhonecardInfo() {
		return "phonecard/typePhonecardInfo";
	}
}
