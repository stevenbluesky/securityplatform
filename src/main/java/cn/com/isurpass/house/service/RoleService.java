package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.EmployeeroleDAO;
import cn.com.isurpass.house.dao.RoleDAO;
import cn.com.isurpass.house.dao.RolePrivilegeDAO;
import cn.com.isurpass.house.po.EmployeeRolePO;
import cn.com.isurpass.house.po.RolePO;
import cn.com.isurpass.house.po.RolePrivilegePO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.util.BeanCopyUtils;
import cn.com.isurpass.house.vo.RoleListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class RoleService {

    @Autowired
    EmployeeService es;
    @Autowired
    RoleDAO rd;
    @Autowired
    EmployeeroleDAO erd;
    @Autowired
    RolePrivilegeDAO rpd;

    public Map<String, Object> roleList(Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        Page<RolePO> list = rd.findAll(pageable);
        List<RoleListVO> roleList = new ArrayList<>();
        seProperties(list, roleList);
        map.put("total", (int) rd.count());
        map.put("rows", roleList);
        return map;
    }

    private void seProperties(Page<RolePO> list, List<RoleListVO> roleList) {
        list.forEach(o -> {
            RoleListVO roleListVO = new RoleListVO();
            roleListVO.setName(o.getName());
            roleListVO.setRoleid(o.getRoleid());
            roleListVO.setStatus(o.getStatus());
            roleList.add(roleListVO);
            // System.out.println(list.toString()+"fff");
        });
    }

    public List<RoleListVO> listEmployeeRole() {
        List<RolePO> list = rd.findAll();
        if (list == null || list.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        List<RoleListVO> roleList = new ArrayList<>();
        list.forEach(o -> {
            RoleListVO role = new RoleListVO();
            try {
                BeanCopyUtils.copyProperties(o, role);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            roleList.add(role);
        });
        return roleList;
    }

    public JsonResult changeRoles(Integer employeeid, List<Integer> roles) {
        List<EmployeeRolePO> roleList = erd.findByEmployeeid(employeeid);
        if (roleList == null || roleList.size() == 0) {
            return new JsonResult(-1, "-1");
        }
        erd.deleteAll(roleList);
        List<EmployeeRolePO> employeeRolePOS = roles.stream().map(temp -> {
            EmployeeRolePO emprole = new EmployeeRolePO();
            emprole.setRoleid(temp);
            emprole.setEmployeeid(employeeid);
            emprole.setCreatetime(new Date());
            return emprole;
        }).collect(toList());
        erd.saveAll(employeeRolePOS);
        return new JsonResult(1, "1");
    }

    public JsonResult changePrivilege(Integer roleid, List<Integer> privileges) {
        List<RolePrivilegePO> byRoleid = rpd.findByRoleid(roleid);
        if (byRoleid == null || byRoleid.size() == 0) {
            return new JsonResult(-1, "-1");
        }
        rpd.deleteAll(byRoleid);
        List<RolePrivilegePO> collect = privileges.stream().map(temp -> {
            RolePrivilegePO rpp = new RolePrivilegePO();
            rpp.setRoleid(roleid);
            rpp.setPrivilegeid(temp);
            rpp.setCreatetime(new Date());
            return rpp;
        }).collect(toList());
        rpd.saveAll(collect);
        return new JsonResult(1, "1");
    }
}
