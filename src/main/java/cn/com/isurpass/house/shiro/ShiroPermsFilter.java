package cn.com.isurpass.house.shiro;

import cn.com.isurpass.house.util.WebUtilsPro;
import cn.com.isurpass.house.web.controller.ControllerExceptionHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ShiroPermsFilter extends PermissionsAuthorizationFilter {

    public static Log log = LogFactory.getLog(ControllerExceptionHandler.class);

    /**
     * shiro认证roles资源失败后回调方法
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        return writeNoPermission((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, log);
    }

    static boolean writeNoPermission(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Log log) throws IOException {
        HttpServletRequest httpServletRequest = servletRequest;
        HttpServletResponse httpServletResponse = servletResponse;
        String requestedWith = httpServletRequest.getHeader("X-Requested-With");
        if (StringUtils.isNotEmpty(requestedWith) &&
                StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定格式数据
            // 输出JSON
            Map<String, Object> map = new HashMap<>();
            map.put("code", "-998");
            map.put("msg", "无权限");
            log.info("no permisson");
            WebUtilsPro.writeJson(map, httpServletResponse);
        } else {//如果是普通请求进行重定向
            log.info("no permisson");
            httpServletResponse.sendRedirect(servletRequest.getContextPath()+"/login");
        }
        return false;
    }
}