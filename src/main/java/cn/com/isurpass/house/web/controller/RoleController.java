package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.dao.PrivilegeDAO;
import cn.com.isurpass.house.dao.RoleDAO;
import cn.com.isurpass.house.dao.RolePrivilegeDAO;
import cn.com.isurpass.house.po.PrivilegePO;
import cn.com.isurpass.house.po.RolePO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.service.EmployeeService;
import cn.com.isurpass.house.service.RoleService;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.EmployeeVO;
import cn.com.isurpass.house.vo.RoleChangeVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import static cn.com.isurpass.house.util.CodeConstants.PRIVILEGE_EXISIT;

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
    public String listEmployeeRole(Integer employeeid, Integer addNew, Model model) {
        EmployeeVO emp = es.getEmployeeInfo(employeeid);
        if (emp == null && !Integer.valueOf(1).equals(addNew)) {
            return "role/employeeList";
        }
        model.addAttribute("cemp", emp);
        model.addAttribute("roleList", roleService.listEmployeeRole());
        model.addAttribute("oldRole", roleService.oldRole(employeeid));
        return "role/employeeRole";
    }

    @RequestMapping("listRolePrivilege")
    public String listRolePrivilege(Integer roleid, Integer addNew, Model model) {
        RolePO rolePO = role.findByRoleid(roleid);
        if (rolePO == null && !Integer.valueOf(1).equals(addNew)) {
            return "role/roleList";
        }
        model.addAttribute("role", rolePO);
        model.addAttribute("privilegeList", privilegeDAO.findAll());
        model.addAttribute("oldPrivilege", roleService.findByRoleid(roleid));
        return "role/rolePrivilege";
    }

    @RequestMapping("modifyEmployeeRole")
    @ResponseBody
    public JsonResult changRoles(@RequestBody RoleChangeVO roleChangeVO) {
       /* if (roleChangeVO.isNew()) {
            roleService.addRole(rolename, roleChangeVO.getList());
            return new JsonResult(1, "1");
        }*/
        roleService.changeRoles(roleChangeVO.getId(), roleChangeVO.getList());
        return new JsonResult(1, "1");
    }

    @RequestMapping("addRole")
    @ResponseBody
    public JsonResult addRole(@RequestBody RoleChangeVO roleChangeVO) {
        if (!roleChangeVO.isNew()) {
            roleService.changeRoles(roleChangeVO.getId(), roleChangeVO.getList());
            return new JsonResult(1, "1");
        }
        if (roleChangeVO.getRolename() == null || "".equals(roleChangeVO.getRolename())) {
            return new JsonResult(1, "-100");
        }
        roleService.addRole(roleChangeVO.getRolename(), roleChangeVO.getList());
        return new JsonResult(1, "1");
    }

    @RequestMapping("changeRolePrivilege")
    @ResponseBody
    public JsonResult changeRolePrivilege(@RequestBody RoleChangeVO roleChangeVO) {
        if (roleChangeVO.isNew()) {
            assert (roleChangeVO.getRolename() != null && !roleChangeVO.getRolename().isEmpty());
            roleService.addRole(roleChangeVO.getRolename(), roleChangeVO.getList());
            return new JsonResult(1, "1");
        }
        roleService.changePrivilege(roleChangeVO.getId(), roleChangeVO.getList());
        return new JsonResult(1, "1");
    }

    @RequiresPermissions("%jwzh%")//用来控制此路径不可访问
    @RequestMapping("addPrivilege")
    @ResponseBody
    public JsonResult addPrivilege(String privilegecode, String privilegelabel){
        if (!FormUtils.checkNUll(privilegecode) || !FormUtils.checkNUll(privilegelabel)){
            //有为空时
            return new JsonResult(-1, "-100");
        }
        List<PrivilegePO> list = privilegeDAO.findByCodeAndLabel(privilegecode, privilegelabel);
        if (list != null && list.size() != 0) {
            return new JsonResult(-1, PRIVILEGE_EXISIT.toString());
        }
        JsonResult jr = roleService.addPrivilege(privilegecode, privilegelabel);
        return jr;
    }

    @RequestMapping("deleteRole")
    @ResponseBody
    public JsonResult deleteRole(Integer roleid){
        if (roleid == null || roleid == 0) {
            return new JsonResult(-1, "-100");
        }
            return roleService.delete(roleid);
    }

    @RequestMapping("roleList")
    public String roleList() {
        return "role/roleList";
    }

//    @RequestMapping("employeeList")
    public String employeeList() {
        return "role/employeeList";
    }

    @RequestMapping("addPrivilegePage")
    public String addPrivilegePage() {
        return "role/addPrivilege";
    }
}
