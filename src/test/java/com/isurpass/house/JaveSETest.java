package com.isurpass.house;

import com.isurpass.house.domain.PhoneUser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JaveSETest {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InterruptedException {
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

		/*List<Object> list = new ArrayList<>();
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
		System.out.println(o.toString());*/
		/*
		Set<Object> set = new HashSet<>();
		set.add("sdfasdf");
		set.add("sdfsdaafdsa");
		set.add(null);
		set.remove(null);
		Iterator<Object> i = set.iterator();
		while (i.hasNext()) {
			System.out.println(i.next().toString());
		}*/
//		Random rand = new Random();
//		for (int i = 0; i < 20; i++) {
//            int i1 = rand.nextInt(6) + 1;
//            System.out.println("iRemote800500000000"+i1);
//            System.out.println(i1);
//		}
//		System.out.println(new SimpleDateFormat().format(new Date()));

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