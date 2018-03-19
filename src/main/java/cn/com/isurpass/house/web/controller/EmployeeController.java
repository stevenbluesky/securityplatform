package cn.com.isurpass.house.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.service.EmployeeService;
import cn.com.isurpass.house.util.PageResult;
import cn.com.isurpass.house.vo.EmployeeAddVO;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService es;

	@RequestMapping("addEmployeePage")
	public String addEmployeePage() {
		return "employee/addEmployee";
	}
	
	@RequestMapping("employeeList")
	public String employeeList() {
		return "employee/employeeList";
	}
	
	@RequestMapping("employeeJsonList")
	@ResponseBody
	public Map<String, Object> employeeJsonList(PageResult pr) {
		return es.listAllEmployee(pr);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public JsonResult addEmployee(EmployeeAddVO emp) {
		try {
			es.add(emp);
		} catch (MyArgumentNullException e) {
			return new JsonResult(-1,e.getMessage());
		} catch (RuntimeException e) {
//			e.printStackTrace();
			return new JsonResult(-1,"出错啦~");
		}
		return new JsonResult(1,"success");
	}
}
