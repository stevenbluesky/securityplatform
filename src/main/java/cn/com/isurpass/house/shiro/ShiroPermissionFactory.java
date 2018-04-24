package cn.com.isurpass.house.shiro;

import cn.com.isurpass.house.dao.PrivilegeDAO;
import cn.com.isurpass.house.po.PrivilegePO;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by Administrator on 2017/8/14 0014.
 */
public class ShiroPermissionFactory extends ShiroFilterFactoryBean {
    /**记录配置中的过滤链*/
    public static String definition = "";
    @Autowired
    private PrivilegeDAO privilegeDAO;

    /**
     * 初始化设置过滤链
     */
    @Override
    public void setFilterChainDefinitions(String definitions) {
//        String token =  manageUserService.getAdminToken(0);

        //可从数据库读取后，添加至过滤链，参考此处已注释的代码
        definition = definitions;//记录配置的静态过滤链
//        List<Permission> permissions = permissService.findAll();

        List<PrivilegePO> rolesPermissions = privilegeDAO.findAll();
        Set<String> urls = new LinkedHashSet<>();
        for (PrivilegePO rolesPermission : rolesPermissions) {
            urls.add(rolesPermission.getLabel());
        }

        Map<String,String> otherChains = new HashMap<>();
//        for (String url : urls) {
        for (int i = 0; i <urls.size(); i++) {
            StringBuilder roleOrFilters = new StringBuilder();
            /*for (int i = 0; i < rolesPermissions.size(); i++) {
                if (Objects.equals(url, rolesPermissions.get(i).getLabel())) {
                    if (i == 0) {
                        roleOrFilters.append(rolesPermissions.get(i).getCode());
                    }else{
                        roleOrFilters.append(",").append(rolesPermissions.get(i).getCode());
                    }
                }
            }*/
            roleOrFilters.append(rolesPermissions.get(i).getCode());
            String rolesStr = roleOrFilters.toString();
            if (!"".equals(rolesStr)) {
                otherChains.put(rolesPermissions.get(i).getLabel(), "perms["+rolesStr+"]"); //  /discover/newstag  authc,roles[user,admin]
            }
        }
        System.out.println(otherChains.toString());
        //加载配置默认的过滤链
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        //加上数据库中过滤链
        section.putAll(otherChains);
        section.put("/**", "anon");
        setFilterChainDefinitionMap(section);
    }
}