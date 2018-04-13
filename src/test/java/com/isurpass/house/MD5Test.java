package com.isurpass.house;

import cn.com.isurpass.house.util.Encrypt;
import cn.com.isurpass.house.util.FormUtils;

public class MD5Test {
    public static void main(String[] args) {
        System.out.println(Encrypt.encrypt("test002", "test002", "002"));
//        boolean check = Encrypt.check("test001", "test001", "002", "R+/zA+3BRTyLrziQisHK5M8qVoXj9agK");
//        System.out.println(check);
    }
}
