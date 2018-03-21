package cn.com.isurpass.house.shiro;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * 这是一个 ShiroFilterFactoryBean 的包装类，ShiroFilterFactoryBean 会调用
 * setFilterChainDefinitions 方法加载配置文件里面的权限列表, 通过重写 setFilterChainDefinitions
 * 方法，实现从数据库里面动态加载权限。
 * </p>
 * <p>
 * 要注意的是，当我们对数据库里面的权限表进行修改时，我们必须重新加载配置文件！
 * setFilterChainDefinitions()
 * </p>
 * 
 * 
 * @author jwzh
 *
 */
public class ShiroFilterFactoryBeanWrapper extends ShiroFilterFactoryBean {

	/** 配置中的过滤链 */
	public static String filterChainDefinitions;
	
	@Override
	public void setFilterChainDefinitions(String definitions) {
		ShiroFilterFactoryBeanWrapper.filterChainDefinitions = definitions;
//		super.setFilterChainDefinitions(definitions);
		// 数据库动态获取权限
	/*	List<PermissionUrl> permissions = pud.findAll();
		for (PermissionUrl po : permissions) {
			// 字符串拼接权限 definitions类型=>StringBuiled
			definitions = definitions + po.getPermissionurl() + " = " + "perms[" + po.getPermissionname() + "]"+System.getProperty("line.separator");
		}*/
		System.out.println(definitions);
		
		// 从配置文件加载权限配置
		Ini ini = new Ini();
		ini.load(definitions);
		Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		// 加入权限集合
		setFilterChainDefinitionMap(section);
	}

}
