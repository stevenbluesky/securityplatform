package com.isurpass.house;

import cn.com.isurpass.house.util.Encrypt;
import cn.com.isurpass.house.util.FormUtils;

public class MD5Test {
    public static void main(String[] args) {
        System.out.println(Encrypt.encrypt("ins1", "123", "ins2"));
    }
}
