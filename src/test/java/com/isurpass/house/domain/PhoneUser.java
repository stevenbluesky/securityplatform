package com.isurpass.house.domain;

public class PhoneUser{
	private int phoneuserid;
	private int usertype;
	private String countrycode;

	public int getPhoneuserid() {
		return phoneuserid;
	}

	public void setPhoneuserid(int phoneuserid) {
		this.phoneuserid = phoneuserid;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
}