package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.service.EmployeeService;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.EmployeeAddVO;
import cn.com.isurpass.house.vo.OrgSearchVO;
import cn.com.isurpass.house.vo.TransferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService es;

    @RequestMapping("addEmployeePage")
    public String addEmployeePage(@RequestParam(required = false)Integer id, HttpServletRequest request) {
        if (id != null) {
//            request.setAttribute("empInfo",es.getEmployeeVOInfo(id));
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
    public Map<String, Object> employeeJsonList(PageResult pr, OrgSearchVO osv, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.ASC, "employeeid");
        if (!FormUtils.isEmpty(osv)) {//搜索
            return es.search(pageable, osv,request);
        }
        return es.listEmployee(pageable, request);
    }

    @RequestMapping("add")
    @ResponseBody
    public JsonResult addEmployee(EmployeeAddVO emp, HttpServletRequest request) {
        //noinspection Duplicates
        try {
            es.add(emp, request);
        } catch (MyArgumentNullException e) {
            e.printStackTrace();
            return new JsonResult(-1, e.getMessage());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new JsonResult(-1, "-1");
        }
        return new JsonResult(1, "1");
    }

//    @RequestMapping("toggleEmployeeStatus0")
    @ResponseBody
    public JsonResult toggleEmployeeStatus0(Integer id,Integer toStatus,HttpServletRequest request){
        try {
            es.toggleEmployeeStatus(id,toStatus, request);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new JsonResult(-1, e.getMessage());
        }
        return new JsonResult(1, "1");
    }

    @RequestMapping("toggleEmployeeStatus")
    @ResponseBody
    public JsonResult toggleEmployeeStatus(@RequestBody TransferVO tf, HttpServletRequest request) {
        String hope = tf.getHope();
        Object[] ids = tf.getIds();
        try {
            es.toggleEmployeeStatus0(hope, ids, request);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new JsonResult(-1, e.getMessage());
        }
        return new JsonResult(1, "1");
    }

}
