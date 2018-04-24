package cn.com.isurpass.house.shiro;

import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@Component
public class FilterChainDefinitionsService {
    @Autowired
    private ShiroPermissionFactory permissFactory;

    public void reloadFilterChains() {
        synchronized (permissFactory) {   //强制同步，控制线程安全
            AbstractShiroFilter shiroFilter = null;

            try {
                shiroFilter = (AbstractShiroFilter) permissFactory.getObject();

                PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter
                        .getFilterChainResolver();
                // 过滤管理器
                DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();
                // 清除权限配置
                manager.getFilterChains().clear();
                permissFactory.getFilterChainDefinitionMap().clear();
                // 重新设置权限
                permissFactory.setFilterChainDefinitions(ShiroPermissionFactory.definition);//传入配置中的filterchains

                Map<String, String> chains = permissFactory.getFilterChainDefinitionMap();
                //重新生成过滤链
                if (!CollectionUtils.isEmpty(chains)) {
                    chains.forEach((url, definitionChains) -> {
                        manager.createChain(url, definitionChains.trim().replace(" ", ""));
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}