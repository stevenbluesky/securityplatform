package cn.com.isurpass.house.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;

public class ShiroHelper {
    public static void clearAuth() {
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroUtil myShiroUtil = (MyShiroUtil) securityManager.getRealms().iterator().next();
        myShiroUtil.clearAuthz();
    }
}
