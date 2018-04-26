package cn.com.isurpass.house.shiro;

import cn.com.isurpass.house.web.controller.ControllerExceptionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cn.com.isurpass.house.shiro.ShiroPermsFilter.writeNoPermission;

@Component
public class ShiroRolesFilter extends RolesAuthorizationFilter {

    public static Log log = LogFactory.getLog(ControllerExceptionHandler.class);

    /**
     * shiro认证perms资源失败后回调方法
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        return writeNoPermission((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse, log);
    }
}