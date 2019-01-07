package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.service.EmployeeService;
import cn.com.isurpass.house.service.OrganizationService;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.ExportExcelUtil;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService es;
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
            request.getSession().setAttribute("empInfo",es.getEmployeeVOInfo(id));
        }else {
            //清除empInfo session 避免将信息带去其他页面
            request.getSession().setAttribute("empInfo", null);
        }
        return "employee/modifyEmployee";
    }
    @RequestMapping("employeeList")
    public String employeeList() {
        return "employee/employeeList";
    }
    @RequestMapping("operaterList")
    public String operaterList() {
        return "allusers/operaterList";
    }

    @RequestMapping("installerList")
    public String installerList() {
        return "allusers/installerList";
    }

    @RequestMapping("installerJsonList")
    @ResponseBody
    public Map<String, Object> installerJsonList(PageResult pr, OrgSearchVO osv, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "employeeid");
        //有搜索条件时，执行搜索代码
        if (!FormUtils.isEmpty(osv)) {
            return es.search(pageable, osv,request, Constants.INSTALLOR_TYPE);
        }
        return es.listEmployee(pageable, request,Constants.INSTALLOR_TYPE);
    }

    @RequestMapping("employeeJsonList")
    @ResponseBody
    public Map<String, Object> employeeJsonList(PageResult pr, OrgSearchVO osv, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "employeeid");
        //有搜索条件时，执行搜索代码
        if (!FormUtils.isEmpty(osv)) {
            return es.search(pageable, osv,request,-1);
        }
        return es.listEmployee(pageable, request,-1);
    }

    @RequestMapping(value = "/exportalluserdata")
    @ResponseBody
    public String exportGatewayData(OrgSearchVO osv, HttpServletRequest request, HttpServletResponse response) {
        List<AllUserInfoVO> dataset = null;
        if(FormUtils.isEmpty(osv)){
            dataset = es.listEmployee(request,-1);
        }else{
            dataset = es.search(osv,request,-1);
        }

        if(dataset==null||dataset.size()==0){
            return "No Data !";
        }
        String sheetName = "All Users Data";
        String titleName = "All Users Data";
        String fileName = "All Users Data";
        int columnNumber = 5;
        int[] columnWidth = { 30,35,60,15,30};
        String[] columnName = { "Installer Account Number", "User Name" ,"Relate to","Status","Binding Time"};
        try {
            HSSFWorkbook wb = new ExportExcelUtil().exportNoResponse(sheetName, titleName, columnNumber, columnWidth, columnName, dataset);
            if(wb==null){
                return "System Error !";
            }
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Export Successfully !";
    }
    /**
     * 发送响应流方法
     */
    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName+".xls");
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @RequestMapping("operaterJsonList")
    @ResponseBody
    public Map<String, Object> operaterJsonList(PageResult pr, OrgSearchVO osv, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "employeeid");
        if (!FormUtils.isEmpty(osv)) {
            return es.search(pageable, osv,request, Constants.OPERATOR_TYPE);
        }
        return es.listEmployee(pageable, request,Constants.OPERATOR_TYPE);
    }
    @RequestMapping("validCode")
    @ResponseBody
    public Map<String, Boolean> validCode(String code,String loginorgid, HttpServletRequest request) {
        Map<String, Boolean> map = new HashMap<>(16);
        if(StringUtils.isEmpty(loginorgid)){
            return null;
        }
        map.put("valid", es.validCode(code,Integer.parseInt(loginorgid)));
        return map;
    }
    @RequestMapping("validLoginName")
    @ResponseBody
    public Map<String, Boolean> validLoginName(String loginname,HttpServletRequest request) {
        Map<String, Boolean> map = new HashMap<>(16);
        if(StringUtils.isEmpty(loginname)){
            return null;
        }
        map.put("valid", es.validLoginName(loginname));
        return map;
    }
    @RequestMapping("add")
    @ResponseBody
    public JsonResult addEmployee(EmployeeAddVO emp, HttpServletRequest request) {
        Integer employeeid = emp.getEmployeeid();
        boolean loginname =es.findLoginname(employeeid,emp.getLoginname());
        //为真表示从员工表找到员工id和登录名相同的纪录
        if(!loginname){
            //为假，表示更改了登录名，或者更改了机构
            Boolean flag = es.validLoginName(emp.getLoginname());
            if(!flag){
                return new JsonResult(-1, "-12");
            }
        }
        try {
            //request.getSession().setAttribute("empInfo", null);
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
