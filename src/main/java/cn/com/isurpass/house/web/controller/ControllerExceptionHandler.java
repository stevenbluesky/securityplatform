package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.util.WebUtilsPro;
import com.mysql.jdbc.CommunicationsException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ControllerExceptionHandler {

    public static Log log = LogFactory.getLog(ControllerExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult runtimeExceptionHandler(Exception e) {
        e.printStackTrace();
        return new JsonResult(-1, "-1");
    }

    @ExceptionHandler(CommunicationsException.class)
    @ResponseBody
    public JsonResult communicationsException(Exception e) {
        e.printStackTrace();
        return new JsonResult(-1, "-113");
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        if (WebUtilsPro.isAjaxRequest(request)) {
            // 输出JSON
            Map<String, Object> map = new HashMap<>();
            map.put("code", "-998");
            map.put("msg", "无权限");
            WebUtilsPro.writeJson(map, response);
            log.info("no permisson");
            return null;
        } else {
            log.info("no permisson");
            return "redirect:/403";
        }
    }
}
