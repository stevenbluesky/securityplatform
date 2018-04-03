package cn.com.isurpass.house.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.digest.PooledStringDigester;

public class Encrypt {

    private static Log log = LogFactory.getLog(Encrypt.class);
    private static PooledStringDigester digester = new PooledStringDigester();

    static {
        digester.setPoolSize(8);
        digester.setIterations(1059);
    }

    public static String encrypt(String username, String password, String code) {
        String pw = username + password + code;
        return digester.digest(pw);
    }

    public static boolean check(String loginname, String password, String code, String enpassword) {
        try {
            return digester.matches(loginname + password + code, enpassword);
        } catch (Throwable t) {
            log.warn(t.getMessage());
            return false;
        }
    }

    public static void main(String arg[]) {
//        String name = "admin";
//        String pw = "123";
//        String code = "002";
//
//        long s = System.currentTimeMillis();
//        Encrypt svr = new Encrypt();
//        String str = encrypt(name, pw, code);
//        System.out.println(System.currentTimeMillis() - s);
//        System.out.println(str);
//
//        s = System.currentTimeMillis();
////        boolean b = digester.matches(name + pw + code , str);
//        boolean b = check(name, pw, code, str);
//        System.out.println(System.currentTimeMillis() - s);
//        System.out.println(b);
        String name = "test001";
        String pw = "test001";
        String code = "002";
//        System.out.println(encrypt(name,pw,code));
        System.out.println(Encrypt.check(name,pw,code,"b6WLvEXigldas6v2H6TinxZBFwMD65iq"));
    }
}
