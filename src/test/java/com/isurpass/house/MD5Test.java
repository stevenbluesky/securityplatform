package com.isurpass.house;

import cn.com.isurpass.house.util.Encrypt;

public class MD5Test {
    public static void main(String[] args) {
        System.out.println(Encrypt.encrypt("test002", "test002", "002"));
//        boolean check = Encrypt.check("test001", "test001", "002", "R+/zA+3BRTyLrziQisHK5M8qVoXj9agK");
//        System.out.println(check);
        System.out.println(Encrypt.encrypt("admin", "123", "96"));//IpYC7VX7Lt0KLmJeBx3Fgrhm7S3v4OPM
        System.out.println(Encrypt.check("admin","123","10001","QcqJtrGmBvPXnXjV3lE5n/wKGJS0HCUC"));
    }
}
