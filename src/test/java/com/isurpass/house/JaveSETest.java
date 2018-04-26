package com.isurpass.house;

import com.isurpass.house.domain.PhoneUser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JaveSETest {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InterruptedException {
//		phoneuser();
		Object s = 123;
		System.out.println(Integer.valueOf(String.valueOf(s)));
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