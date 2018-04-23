package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.dao.PrivilegeDAO;
import cn.com.isurpass.house.dao.RoleDAO;
import cn.com.isurpass.house.po.RolePO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.service.EmployeeService;
import cn.com.isurpass.house.service.RoleService;
import cn.com.isurpass.house.vo.EmployeeVO;
import cn.com.isurpass.house.vo.RoleChangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    EmployeeService es;
    @Autowired
    RoleDAO role;
    @Autowired
    PrivilegeDAO privilegeDAO;

    @RequestMapping("roleJsonList")
    @ResponseBody
    public Map<String, Object> roleJsonList(PageResult pr) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.ASC, "roleid");
        return roleService.roleList(pageable);
    }

    @RequestMapping("listEmployeeRole")
    public String listEmployeeRole(Integer employeeid, Model model) {
        EmployeeVO emp = es.getEmployeeInfo(employeeid);
        if (emp == null) {
            return "role/employeeList";
        }
        model.addAttribute("emp", emp);
        model.addAttribute("roleList", roleService.listEmployeeRole());
        return "role/employeeRole";
    }

    @RequestMapping("listRolePrivilege")
    public String listRolePrivilege(Integer roleid, Model model){
        RolePO rolePO = role.findByRoleid(roleid);
        if (rolePO == null) {
            return "role/roleList";
        }
        model.addAttribute("role",rolePO);
        model.addAttribute("privilegeList", privilegeDAO.findAll());
        return "role/rolePrivilege";
    }

    @RequestMapping("changeRoles")
    @ResponseBody
    public JsonResult changRoles(@RequestBody RoleChangeVO roleChangeVO) {
        roleService.changeRoles(roleChangeVO.getId(), roleChangeVO.getList());
        return new JsonResult(1, "1");
    }

    @RequestMapping("changeRolePrivilege")
    @ResponseBody
    public JsonResult changeRolePrivilege(@RequestBody RoleChangeVO roleChangeVO) {
        roleService.changePrivilege(roleChangeVO.getId(), roleChangeVO.getList());
        return new JsonResult(1, "1");
    }

    @RequestMapping("roleList")
    public String roleList() {
        return "role/roleList";
    }

    @RequestMapping("employeeList")
    public String employeeList() {
        return "role/employeeList";
    }

}
