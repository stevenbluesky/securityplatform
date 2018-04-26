package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.EmployeeroleDAO;
import cn.com.isurpass.house.dao.PrivilegeDAO;
import cn.com.isurpass.house.dao.RoleDAO;
import cn.com.isurpass.house.dao.RolePrivilegeDAO;
import cn.com.isurpass.house.po.EmployeeRolePO;
import cn.com.isurpass.house.po.PrivilegePO;
import cn.com.isurpass.house.po.RolePO;
import cn.com.isurpass.house.po.RolePrivilegePO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.shiro.FilterChainDefinitionsService;
import cn.com.isurpass.house.shiro.MyShiroUtil;
import cn.com.isurpass.house.shiro.ShiroHelper;
import cn.com.isurpass.house.util.BeanCopyUtils;
import cn.com.isurpass.house.vo.RoleListVO;
import org.apache.shiro.SecurityUtils;
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
    EmployeeroleDAO erd;
    @Autowired
    RolePrivilegeDAO rpd;
    @Autowired
    PrivilegeDAO pd;
    @Resource
    FilterChainDefinitionsService fcds;

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
        ShiroHelper.clearAuth();
        return new JsonResult(1, "1");
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
        ShiroHelper.clearAuth();
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
            return new JsonResult(1, "1");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1, "-1");
        }
    }
}
