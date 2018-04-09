package com.isurpass.house;

import com.alibaba.fastjson.JSON;

import cn.com.isurpass.house.vo.LoginVO;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JaveSETest {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		/*LoginVO login = new LoginVO();
		login.setLoginname("name");
		login.setPassword("12312");
		login.setCode("123");
		login.setCaptchacode("431");
		String json = JSON.toJSONString(login);
		LoginVO xx = JSON.parseObject(json,LoginVO.class);
		System.out.println(xx.getLoginname());
		System.out.println(xx.getCode());
		System.out.println(xx.getCaptchacode());
		System.out.println(xx.getPassword());*/

		List<Object> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();

		map.put("text", "typeUserInfo");
		map.put("url", "www.dfaf");

		map1.put("text", "xixix");
		map1.put("url","www.fasdfd");
		map1.put("label", map);
		list.add(map);
		list.add(map1);
		System.out.println(list);
		Object o = JSONObject.toJSON(list);
		System.out.println(o.toString());
	}

}