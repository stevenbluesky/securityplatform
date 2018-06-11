package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.service.EmployeeService;
import cn.com.isurpass.house.service.OrganizationService;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.EmployeeAddVO;
import cn.com.isurpass.house.vo.OrgSearchVO;
import cn.com.isurpass.house.vo.TransferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService es;
    @Autowired
    private OrganizationService os;
    @RequestMapping("queryEmployeeInfo")
    public String queryEmployeeInfo(HttpServletRequest request, Integer employeeid, Model model) {
        if (employeeid == null) {
            return null;
        }
        model.addAttribute("empVO", es.queryEmployeeInfo(request, employeeid));
        return "employee/employeeInfo";
    }

    @RequestMapping("addEmployeePage")
    public String addEmployeePage(@RequestParam(required = false)Integer id, HttpServletRequest request) {
        OrganizationPO loginorg = os.findOrgByLoginEmp(request);
        if (id != null) {
//            request.setAttribute("empInfo",es.getEmployeeVOInfo(id));
            request.getSession().setAttribute("empInfo",es.getEmployeeVOInfo(id));
        }else {
            //清除empInfo session
            request.getSession().setAttribute("empInfo", null);
        }
        request.getSession().setAttribute("loginorg",loginorg);
        return "employee/addEmployee";
    }
    @RequestMapping("modifyEmployeePage")
    public String modifyEmployeePage(@RequestParam(required = false)Integer id, HttpServletRequest request) {
        OrganizationPO loginorg = os.findOrgByLoginEmp(request);
        request.getSession().setAttribute("loginorg",loginorg);
        if (id != null) {
//            request.setAttribute("empInfo",es.getEmployeeVOInfo(id));
            request.getSession().setAttribute("empInfo",es.getEmployeeVOInfo(id));
        }else {
            //清除empInfo session
            request.getSession().setAttribute("empInfo", null);
        }
        return "employee/modifyEmployee";
    }
    @RequestMapping("employeeList")
    public String employeeList() {
        return "employee/employeeList";
    }

    @RequestMapping("employeeJsonList")
    @ResponseBody
    public Map<String, Object> employeeJsonList(PageResult pr, OrgSearchVO osv, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "employeeid");
        if (!FormUtils.isEmpty(osv)) {//搜索
            return es.search(pageable, osv,request);
        }
        return es.listEmployee(pageable, request);
    }
    @RequestMapping("validCode")
    @ResponseBody
    public Map<String, Boolean> validCode(String code,String loginorgid, HttpServletRequest request) {
        Map<String, Boolean> map = new HashMap<>();
        if(StringUtils.isEmpty(loginorgid)){
            return null;
        }
        map.put("valid", es.validCode(code,Integer.parseInt(loginorgid)));
        return map;
    }
    @RequestMapping("validLoginName")
    @ResponseBody
    public Map<String, Boolean> validLoginName(String loginname,HttpServletRequest request) {
        Map<String, Boolean> map = new HashMap<>();
        if(StringUtils.isEmpty(loginname)){
            return null;
        }
        map.put("valid", es.validLoginName(loginname));
        return map;
    }
    @RequestMapping("add")
    @ResponseBody
    public JsonResult addEmployee(EmployeeAddVO emp, HttpServletRequest request) {
        Integer organizationid = emp.getOrganizationid();
        Integer employeeid = emp.getEmployeeid();
        boolean loginname =es.findLoginname(employeeid,emp.getLoginname());
        //TODO 如果将某个员工改为另一个机构下一个已经存在的员工，数据库会报唯一性异常
        //为真表示从员工表找到机构id和登录名相同的纪录
        if(!loginname){
            //为假，表示更改了登录名，或者更改了机构
            Boolean flag = es.validLoginName(emp.getLoginname());
            if(!flag){
                return new JsonResult(-1, "-12");
            }
        }
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
