package cn.com.isurpass.house.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.service.UserService;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.PageResult;
import cn.com.isurpass.house.vo.UserAddVO;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService us;
	
	@RequestMapping("add")
	@ResponseBody
	public JsonResult addUserInfo(UserAddVO user,HttpServletRequest request) {
		try {
			us.add(user,request);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(-1,"出错啦~");
		}
		return new JsonResult(1,"添加成功");
	}
	
	@RequestMapping("userInfoJsonList")
	@ResponseBody
	public Map<String, Object> userInfoJsonList(PageResult pr) {
		Pageable pageable = PageRequest.of(pr.getPage()-1,pr.getRows(),Sort.Direction.ASC,"organizationid");
		return us.listUserInfoByOrgtype(pageable, Constants.ORGTYPE_SUPPLIER);
	}
	
	@RequestMapping("userList")
	public String userList() {
		return "user/userList";
	}
	
	@RequestMapping("typeUserInfo")
	public String typeUserInfo() {
		return "user/typeUserInfo";
	}
}
