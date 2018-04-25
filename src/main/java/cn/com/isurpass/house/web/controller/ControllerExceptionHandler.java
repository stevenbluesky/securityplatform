package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.util.WebUtilsPro;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.CommunicationsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.result.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

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
            writeJson(map, response);
            return null;
        } else {
            return "redirect:/403";
        }
    }

    /**
     * 输出JSON
     *
     * @param response
     * @author SHANHY
     * @create 2017年4月4日
     */
    private void writeJson(Map<String,Object> map, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(JSONObject.toJSONString(map));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
