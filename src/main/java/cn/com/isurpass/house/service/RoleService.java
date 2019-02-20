package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.shiro.FilterChainDefinitionsService;
import cn.com.isurpass.house.shiro.MyShiroUtil;
import cn.com.isurpass.house.shiro.ShiroHelper;
import cn.com.isurpass.house.util.BeanCopyUtils;
import cn.com.isurpass.house.vo.EmployeeVO;
import cn.com.isurpass.house.vo.RoleListVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    private EmployeeDAO ed;
    @Autowired
    EmployeeroleDAO erd;
    @Autowired
    RolePrivilegeDAO rpd;
    @Autowired
    PrivilegeDAO pd;
    @Resource
    FilterChainDefinitionsService fcds;

    @Autowired
    ShiroHelper shiroHelper;

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
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

    @Transactional(rollbackFor = Exception.class)
    public JsonResult changeRoles(Integer employeeid, List<Integer> roles) {
        List<EmployeeRolePO> roleList = erd.findByEmployeeid(employeeid);
        EmployeePO employee = ed.findByEmployeeid(employeeid);
        if (roleList != null && roleList.size() > 0) {
            erd.deleteAll(roleList);
        }
        List<EmployeeRolePO> employeeRolePOS = roles.stream().map(temp -> {
            EmployeeRolePO emprole = new EmployeeRolePO();
            emprole.setRoleid(temp);
            emprole.setEmployeeid(employeeid);
            emprole.setCreatetime(new Date());
            return emprole;
        }).collect(toList());

        boolean shouldHaveInstallerCode = false;
        if(needHaveInstallerCode(roles)){
            shouldHaveInstallerCode = true;
            if(StringUtils.isNotBlank(employee.getCode())){
                employee.setType(1);
            }
        }else{
            employee.setType(0);
        }
        ed.save(employee);
        erd.saveAll(employeeRolePOS);
        shiroHelper.clearAuth();
        if(shouldHaveInstallerCode && StringUtils.isBlank(employee.getCode())){
            return new JsonResult(1,"needcode");
        }
        return new JsonResult(1, "1");
    }

    private boolean needHaveInstallerCode(List<Integer> roles) {
        for(Integer roleid : roles){
            PrivilegePO privilege = pd.findPoOfInstallerPrivilege(roleid);
            if(privilege!=null){
                return true;
            }
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    public JsonResult changePrivilege(Integer roleid, List<Integer> privileges) {
        List<RolePrivilegePO> byRoleid = rpd.findByRoleid(roleid);
/*        if (byRoleid == null || byRoleid.size() == 0) {
            return new JsonResult(-1, "-1");
        }*/
        rpd.deleteAll(byRoleid);
        List<RolePrivilegePO> collect = privileges.stream().map(temp -> {
            RolePrivilegePO rpp = new RolePrivilegePO();
            rpp.setRoleid(roleid);
            rpp.setPrivilegeid(temp);
            rpp.setCreatetime(new Date());
            return rpp;
        }).collect(toList());
        rpd.saveAll(collect);
        shiroHelper.clearAuth();
        return new JsonResult(1, "1");
    }

    @Transactional(rollbackFor = Exception.class)
    public void addRole(String rolename, List<Integer> list) {
        RolePO rolePO = new RolePO();
        rolePO.setName(rolename);
        rolePO.setOrganizationid(1);
        rolePO.setStatus(1);
        rolePO.setCreatetime(new Date());
        RolePO save = rd.save(rolePO);
        Integer roleid = save.getRoleid();
        ArrayList<RolePrivilegePO> rplist = new ArrayList<>();
        for (Integer privilege : list) {
            RolePrivilegePO rp = new RolePrivilegePO();
            rp.setPrivilegeid(privilege);
            rp.setRoleid(roleid);
            rp.setCreatetime(new Date());
            rplist.add(rp);
        }
        rpd.saveAll(rplist);
    }

    @Transactional(readOnly = true)
    public List<Integer> oldRole(Integer employeeid) {
        List<Integer> collect = erd.findByEmployeeid(employeeid).stream().map(EmployeeRolePO::getRoleid).collect(toList());
        return collect;
    }

    @Transactional(readOnly = true)
    public List<Integer> findByRoleid(Integer roleid) {
        List<Integer> collect = rpd.findByRoleid(roleid).stream().map(RolePrivilegePO::getPrivilegeid).collect(toList());
        return collect;
    }

    public JsonResult addPrivilege(String privilegecode, String privilegelabel) {
        PrivilegePO p = new PrivilegePO();
        p.setCode(privilegecode);
        p.setLabel(privilegelabel);
//        p.setParentprivilegeid(null);
        pd.save(p);
        fcds.reloadFilterChains();
        return new JsonResult(1, "1");
    }

    @Transactional(rollbackFor = Exception.class)
    public JsonResult delete(Integer roleid) {
        try {
            rd.deleteById(roleid);
//            erd.deleteByRoleid(roleid);
            rpd.deleteByRoleid(roleid);
            return new JsonResult(1, "1");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1, "-1");
        }
    }
}
