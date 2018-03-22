package com.isurpass.house;

import com.alibaba.fastjson.JSON;

import cn.com.isurpass.house.vo.LoginVO;

public class JaveSETest {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		LoginVO login = new LoginVO();
		login.setLoginname("name");
		login.setPassword("12312");
		login.setCode("123");
		login.setCaptchacode("431");
		String json = JSON.toJSONString(login);
		LoginVO xx = JSON.parseObject(json,LoginVO.class);
		System.out.println(xx.getLoginname());
		System.out.println(xx.getCode());
		System.out.println(xx.getCaptchacode());
		System.out.println(xx.getPassword());
	}
}