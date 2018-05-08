package com.isurpass.house;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.isurpass.house.domain.PhoneUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class JavaSETest {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InterruptedException {
	}

	private static void phoneuser() {
		PhoneUser user = new PhoneUser();
		user.setCountrycode("86");
		user.setUsertype(1);
		if (!("86".equals(user.getCountrycode()) && user.getUsertype() == 0)) {
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}

}