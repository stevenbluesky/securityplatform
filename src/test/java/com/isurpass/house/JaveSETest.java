package com.isurpass.house;

import java.lang.reflect.Field;

public class JaveSETest {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Person p1 = new Person();
		Person p2 = new Person("fff", null);
		System.out.println(isEmpty(p1));
		System.out.println(isEmpty(p2));

	}

	private static boolean isEmpty(Object obj) throws IllegalArgumentException, IllegalAccessException {
		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if (f.get(obj) != null && f.get(obj) != "") { // 判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
				return false;
			}
		}
		return true;
	}
}

class Person {
	private String name;
	private Integer age;

	public Person() {
		super();
	}

	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
