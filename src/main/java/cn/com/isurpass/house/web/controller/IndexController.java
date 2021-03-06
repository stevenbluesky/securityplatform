package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jwzh
 */
@Controller
public class IndexController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("login2")
    public String login2() {
        return "login2";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    @ResponseBody
    @RequestMapping(value = "getMenuTree", produces = "text/json;charset=UTF-8")
    public String getMenuTree(HttpServletResponse response, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        String json = employeeService.getMenuTree(emp, request,response);
        return json;
    }
}
