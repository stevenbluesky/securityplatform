package cn.com.isurpass.house.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.service.EmployeeService;
import cn.com.isurpass.house.util.PageResult;
import cn.com.isurpass.house.vo.EmployeeAddVO;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService es;

    @RequestMapping("addEmployeePage")
    public String addEmployeePage(@RequestParam(required = false)Integer id, HttpServletRequest request) {
        if (id != null) {
            request.getSession().setAttribute("empInfo",es.getEmployeeVOInfo(id));
        }else {
            //清除empInfo session
            request.getSession().setAttribute("empInfo", null);
        }
        return "employee/addEmployee";
    }

    @RequestMapping("employeeList")
    public String employeeList() {
        return "employee/employeeList";
    }

    @RequestMapping("employeeJsonList")
    @ResponseBody
    public Map<String, Object> employeeJsonList(PageResult pr, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.ASC, "employeeid");
        Map<String, Object> map = es.listEmployee(pageable, request);
        return map;

    }

    @RequestMapping("add")
    @ResponseBody
    public JsonResult addEmployee(EmployeeAddVO emp, HttpServletRequest request) {
        //noinspection Duplicates
        try {
            es.add(emp, request);
        } catch (MyArgumentNullException e) {
            return new JsonResult(-1, e.getMessage());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new JsonResult(-1, "出错啦~");
        }
        return new JsonResult(1, "success");
    }
}
