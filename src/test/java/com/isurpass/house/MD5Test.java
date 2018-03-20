package com.isurpass.house;

import cn.com.isurpass.house.util.FormUtils;

public class MD5Test {
	public static void main(String[] args) {
		String psw = "zhul";
		System.out.println(FormUtils.encrypt(psw));
	}
}
