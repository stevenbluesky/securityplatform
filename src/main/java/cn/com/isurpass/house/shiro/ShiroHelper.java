package cn.com.isurpass.house.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShiroHelper {

    @Autowired
    MyShiroUtil myShiroRealm;

    public  void clearAuth() {
       /* RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroUtil myShiroUtil = (MyShiroUtil) securityManager.getRealms().iterator().next();
        myShiroUtil.clearAuthz();*/
//        cacheManager.getCache("authorizationCache").clear();
        myShiroRealm.clearAllCachedAuthorizationInfo();
    }

}
