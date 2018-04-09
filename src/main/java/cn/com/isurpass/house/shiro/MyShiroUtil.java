package cn.com.isurpass.house.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.service.EmployeeService;
import cn.com.isurpass.house.vo.LoginVO;

/**
 * 权限认证 reaml
 *
 * @author jwzh
 */
public class MyShiroUtil extends AuthorizingRealm {

    @Autowired
    private EmployeeService es;

    public void clearAuthz() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    /*
     * 授权获得用户权限信息的方法 获得用户的 角色 及 权限 信息
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        EmployeePO emp = (EmployeePO) principalCollection.fromRealm(getName()).iterator().next();
        System.out.println(emp);
//		EmployeePO emp = es.findByLoginname(loginName);
//        System.out.println("abcdefg");
//		System.out.println(loginName);
        // User user = us.getUser(username, password);
        // Set<Role> list = us.getRoles();
        if (emp != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			 info.setRoles(es.getEmployeeRolesNameSet(emp.getEmployeeid()));
			 info.addStringPermissions(es.getEmployeePermissionsName(emp.getEmployeeid()));
//			 List<Role> roleList = us.getRolesList(user.getId());
//			List roleList = null;
//			if (roleList.get(0) == null)// 当user_role里面没有此用户的记录时，返回空
//				return null;

           /* for (Role role : roleList) {
                info.addStringPermissions(us.getPermissionsName(role.getId())); //
                System.out.println("123" + role.getRolename());
            }*/

            return info;
        }
        return null;
    }

    /*
     * 获取认证信息，验证当前的用户合法性，，，，就是登录认证 通过判断
     * AuthenticationToken传过来的用户信息与数据库里面的进行对比，如果失败就throw对应的异常，如果成功就返回一个封闭了用户信息的实例（
     * SimpleAuthenticationInfo类）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token0) {
        UsernamePasswordToken token = (UsernamePasswordToken) token0;//取得token
        LoginVO login = JSON.parseObject(token.getUsername(), LoginVO.class);
        EmployeePO emp = es.login(login.getLoginname(), String.valueOf(token.getPassword()), login.getCode());
        if (emp != null) {
            return new SimpleAuthenticationInfo(emp, String.valueOf(token.getPassword()), getName());
        }
        return null;
    }

}