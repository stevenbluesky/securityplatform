package com.isurpass.house;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.isurpass.house.domain.PhoneUser;

import java.text.SimpleDateFormat;
import java.util.Date;

 class JavaSETest {
	public final static int[] acsecurity = new int[]{7,38,31,84,0,0,0,0,0,0,0,3,36,85,90,14,57,92,16,23,85,63,54,82,26};
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InterruptedException {
//		phoneuser();

		String s = "[55,39,30,4,0,0,0,0,0,0,0,0,36,60,165]";
		JSONArray jsonArray = JSON.parseArray(s);
		byte[] liberay = new byte[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			liberay[i] = (byte)jsonArray.get(i);//
		}
		byte[] command = new byte[liberay.length + 1];
		for ( int i = 0 ; i < liberay.length ; i ++ )
		{
			if ( i < JavaSETest.acsecurity.length )
				command[i] = (byte)(liberay[i] ^ JavaSETest.acsecurity[i]);
			else
				command[i] = (byte)(liberay[i]);
		}

		for ( int i = 0 ; i < command.length - 1  ; i ++ )
			command[command.length -1] += command[i] ;

		for (byte str : liberay) {
			System.out.print(str);
			System.out.print(" ");
		}
		liberay[0] = (byte) (liberay[0] ^ 7);
		liberay[1] = (byte) (liberay[0] ^ 38);
		liberay[2] = (byte) (liberay[0] ^ 31);
		liberay[3] = (byte) (liberay[0] ^ 84);
		liberay[12] = (byte) (liberay[0] ^ 36);
		liberay[13] = (byte) (liberay[0] ^ 85);
		liberay[14] = (byte) (liberay[0] ^ 90);

		System.out.println();
		for (byte str : liberay) {
			System.out.print(str);
			System.out.print(" ");
		}
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