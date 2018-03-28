package cn.com.isurpass.house.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.PhonecardPO;
import cn.com.isurpass.house.service.PhonecardService;
import cn.com.isurpass.house.util.PageResult;
import cn.com.isurpass.house.vo.TransferVO;
import cn.com.isurpass.house.vo.TypeGatewayInfoVO;

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
	public Map<String, Object> phonecardJsonList(PageResult pr,PhonecardPO pc) {
		Pageable pageable = PageRequest.of(pr.getPage()-1,pr.getRows(),Sort.Direction.ASC,"activationdate");
		return ps.listPhonecard(pageable,pc);
	}
	/**
	 * 根据操作及id更新状态
	 * @param tf
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="update",method = RequestMethod.POST)
	public String update(@RequestBody TransferVO tf){
		String hope = tf.getHope();
		Object[] ids = tf.getIds();
		try {
			ps.updatePhonecardStatus(hope,ids);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
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
