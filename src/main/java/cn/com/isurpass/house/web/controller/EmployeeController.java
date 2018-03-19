package cn.com.isurpass.house.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.po.EmployeePO;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@RequestMapping("employeeJsonList")
	@ResponseBody
	public Map<String, Object> employeeList(Integer rows, Integer page) {
		// 查询数据库...

		Map<String, Object> map = new HashMap<>();
		map.put("total", 8);
	/*	List<EmployeePO> personList = new ArrayList<>();
		EmployeePO user1 = new EmployeePO("小明", 1, 2, 34, 4, "操作");
		EmployeePO user2 = new EmployeePO("小明", 1, 2, 34, 4, "操作");
		EmployeePO user3 = new EmployeePO("小明", 1, 2, 34, 4, "操作");
		EmployeePO user4 = new EmployeePO("小明", 1, 2, 34, 4, "操作");
		EmployeePO user5 = new EmployeePO("小明", 1, 2, 34, 4, "操作");
		EmployeePO user6 = new EmployeePO("小明", 1, 2, 34, 4, "操作");
		EmployeePO user7 = new EmployeePO("小明", 1, 2, 34, 4, "操作");
		EmployeePO user8 = new EmployeePO("小红", 5, 123, 45, 67, "操作");
		personList.add(user1);
		personList.add(user2);
		personList.add(user3);
		personList.add(user4);
		personList.add(user5);
		personList.add(user6);
		personList.add(user7);
		personList.add(user8);*/
		map.put("rows", "");
		return map;
	}


	@RequestMapping("addEmployeePage")
	public String addEmployeePage() {
		return "employee/addEmployee";
	}
	
	@RequestMapping("employeeList")
	public String employeeListPage() {
		return "employee/employeeList";
	}
	
	@RequestMapping("addEmployee")
	public String addEmployee() {
		return null;
	}
}
