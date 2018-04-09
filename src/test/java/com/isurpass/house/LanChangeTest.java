package com.isurpass.house;

import org.junit.Test;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanChangeTest {
    @Test
    public void change() throws UnsupportedEncodingException {
        //Locale.US
        //Locale.getDefault()
        System.out.println(Locale.TAIWAN.getLanguage());
        ResourceBundle resourceBundle=ResourceBundle.getBundle("messages",Locale.US);
        ResourceBundle rb=ResourceBundle.getBundle("messages",Locale.SIMPLIFIED_CHINESE);
        String s = resourceBundle.getString("label.orgcode");
        String s1 = rb.getString("label.orgcode");
        //s1 = new String(s1.getBytes("ISO8859-1"), "GBK");
        System.out.println(s);
        System.out.println(s1);
    }
}
