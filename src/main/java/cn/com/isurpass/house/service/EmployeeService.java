package cn.com.isurpass.house.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.util.Encrypt;

import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.EmployeeAddVO;
import cn.com.isurpass.house.vo.EmployeeListVO;
import cn.com.isurpass.house.vo.EmployeeParentOrgIdVO;
import cn.com.isurpass.house.vo.OrgSearchVO;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;


@Service
public class EmployeeService {
    //TODO
    @Autowired
    EmployeeDAO ed;
    @Autowired
    private EmployeeroleDAO employeeroleDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private RolePrivilegeDAO rolePrivilegeDAO;
    @Autowired
    private PrivilegeDAO privilegeDAO;
    @Autowired
    AddressDAO ad;
    @Autowired
    CountryDAO country;
    @Autowired
    ProvinceDAO province;
    @Autowired
    CityDAO city;
    @Autowired
    PersonDAO pd;
    @Autowired
    PersonService ps;
    @Autowired
    OrganizationDAO od;
    @Autowired
    OrganizationService os;
    @Autowired
    AddressService as;
    @Autowired
    EmployeeroleDAO erd;
    @Autowired
    RoleDAO rd;
    @Autowired
    RolePrivilegeDAO rpd;
    @Autowired
    PrivilegeDAO privilegedao;

    public void changeStatus(Integer empid, Integer status) {
        EmployeePO emp = ed.findByEmployeeid(empid);
        switch (status) {
            case 0:
                emp.setStatus(Constants.STATUS_UNVALID);
                break;
            case 1:
                emp.setStatus(Constants.STATUS_NORMAL);
                break;
            case 2:
                emp.setStatus(Constants.STATUS_SUSPENCED);
                break;
            case 9:
                emp.setStatus(Constants.STATUS_DELETED);
                break;
            default:
                emp.setStatus(Constants.STATUS_NORMAL);
                break;
        }
        ed.save(emp);
    }

    /*
     * 判断一个员工登录用户名在其机构下是否已经被注册,如果被注册返回true
     * */
    @Transactional(readOnly = true)
    public boolean isRegister(Integer orgid, String loginname) {
        List<EmployeePO> list = ed.findByOrganizationidAndLoginname(orgid, loginname);
        if (!list.isEmpty()) {
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(EmployeeAddVO emp, HttpServletRequest request) throws MyArgumentNullException {
        if (!FormUtils.checkNUll(emp.getLoginname()) || !FormUtils.checkNUll(emp.getPassword()))
            throw new MyArgumentNullException("-100");
        EmployeePO emp1 = (EmployeePO) request.getSession().getAttribute("emp");//当前登录用户
        EmployeeAddVO empInfo = (EmployeeAddVO) request.getSession().getAttribute("empInfo");//修改员工时才会存在的员工session
        if (emp.getOrganizationid() == null) {//如果没有传入机构id
            if (empInfo != null) {//修改
                emp.setOrganizationid(empInfo.getOrganizationid());
            } else {//说明这是安装商在进行添加
                emp.setOrganizationid(emp1.getOrganizationid());
            }
        }
        if (empInfo == null && isRegister(emp.getOrganizationid(), emp.getLoginname())) {//被注册了
            throw new MyArgumentNullException("-102");
        }
        if ((od.findByOrganizationid(emp.getOrganizationid()).getOrgtype() == Constants.ORGTYPE_INSTALLER)
                && !FormUtils.checkNUll(emp.getCode()))// 是安装员且code为空时
            throw new MyArgumentNullException("-103");
//        System.out.println(FormUtils.checkNUll(emp.getCode()) + "==================");
        if (empInfo == null && FormUtils.checkNUll(emp.getCode()) && !ed.findByOrganizationidAndCodeAndStatusNot(emp.getOrganizationid(), emp.getCode(),
                Constants.STATUS_DELETED).isEmpty())
            throw new MyArgumentNullException("-104");

        EmployeePO empPO = new EmployeePO();

        // ID => name
        String countryname = null;
        String provincename = null;
        String cityname = null;
        if (emp.getCountry() != null) {
            countryname = country.findByCountryid(emp.getCountry()).getCountryname();
        }
        if (emp.getProvince() != null) {
            provincename = province.findByProvinceid(emp.getProvince()).getProvincename();
        }
        if (emp.getCity() != null) {
            cityname = city.findByCityid(emp.getCity()).getCityname();
        }

        AddressPO addressPO = new AddressPO(countryname, provincename, cityname, emp.getDetailaddress(), null);
        empPO.setCreatetime(new Date());
        if (empInfo != null) {
            empPO.setCreatetime(ed.findByEmployeeid(empInfo.getEmployeeid()).getCreatetime());
            addressPO.setAddressid(empInfo.getAddressid());
            empPO.setEmployeeid(empInfo.getEmployeeid());
        }
        empPO.setLoginname(emp.getLoginname());
        empPO.setCode(emp.getCode());
//        Encrypt svr = new Encrypt();
//        empPO.setPassword(FormUtils.encrypt(emp.getPassword()));// 加密
        String code = od.findByOrganizationid(emp.getOrganizationid()).getCode();
        empPO.setPassword(Encrypt.encrypt(emp.getLoginname(), emp.getPassword(), code));
        empPO.setQuestion(emp.getQuestion());
//        empPO.setAnswer(FormUtils.encrypt(emp.getAnswer()));
        empPO.setAnswer(Encrypt.encrypt(emp.getLoginname(), emp.getAnswer(), emp.getCode()));
        empPO.setStatus(emp.getStatus());
        empPO.setExpiredate(new Date());
        empPO.setOrganizationid(emp.getOrganizationid());
        empPO.setName(emp.getLastname() + emp.getFirstname());
//        System.out.println(emp);
        Integer addressid = null;
        if (!FormUtils.isEmpty(addressPO)) {
            addressid = ad.save(addressPO).getAddressid();
            empPO.setAddressid(addressid);
        }
        if (FormUtils.checkNUll(emp.getLastname()) || FormUtils.checkNUll(emp.getFirstname()) || FormUtils.checkNUll(emp.getSsn())
                || FormUtils.checkNUll(emp.getSsn()) || emp.getGender() != null || FormUtils.checkNUll(emp.getTitle())) {
            PersonPO personPO = new PersonPO(emp.getLastname() + emp.getFirstname(), emp.getSsn(), emp.getGender(), emp.getTitle(), emp.getFirstname(), emp.getLastname(),
                    emp.getPhonenumber(), emp.getEmail(), emp.getFax());
            personPO.setAddressid(addressid);
            if (empInfo != null) {
                personPO.setPersonid(empInfo.getPersonid());
            }
            empPO.setPersonid(pd.save(personPO).getPersonid());
        }
        EmployeePO save = ed.save(empPO);
        if (empInfo == null) {
            EmployeeRolePO erp = new EmployeeRolePO();
            erp.setRoleid(5);//默认角色是员工
            if ((od.findByOrganizationid(emp.getOrganizationid()).getOrgtype() == Constants.ORGTYPE_INSTALLER)){// 是安装员且code为空时
                erp.setRoleid(4);
            }
            erp.setEmployeeid(save.getEmployeeid());
            erp.setCreatetime(new Date());
            erd.save(erp);
        }
    }

    @Transactional(readOnly = true)
    public Map<String, Object> listEmployee(Pageable pageable, HttpServletRequest request) {
        // 角色不要在里面判断,可以在方法上加上权限注解.(如,管理员才可以访问)

        // 只显示对应的服务商所具有权限的安装商,如果是ameta,则可以看见所有的
        // 首先判断当前登录的员工角色,
        // 如果角色是员工,over.
        // 如果是安装商,再通过员工的机构id查询所有属于它的员工,
        // 如果是服务商,则查询它的所有的安装商,通过服务商,安装商id list查询其所有的员工
        // 如果是ameta,直接查询所有的员工

        // 另一种办法,首先判断当前登录的员工角色,如果角色是员工,over.
        // 通过当前员工的机构id,查询此机构是否具有父机构,如果有,查询其父机构id.这样遍历取此机构所有
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");

        List<Integer> list0 = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (os.isAdmin(emp.getOrganizationid())) {//如果是ameta返回所有的员工信息
            return listAllEmployee(pageable);
        } else {
            List<Integer> list = os.findChildrenOrgid(emp.getOrganizationid(), list0);
            list.add(emp.getOrganizationid());
//            System.out.println(list+"fdsf");
            Page<EmployeePO> empList = ed.findByOrganizationidIn(pageable, list);
            map.put("total", ed.countByOrganizationidIn(list));
//            map.put("total", empList.getTotalElements());
            List<EmployeeListVO> listt = new ArrayList<>();
            empList.forEach(e -> {
                forEachEmp(listt, e);
            });
            map.put("rows", listt);
            return map;
        }
    }

    // @RequiresPermissions("employeeList")
    @Transactional(readOnly = true)
    public Map<String, Object> listAllEmployee(Pageable pageable) {
        // System.out.println(em.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("total", ed.count());
        List<EmployeeListVO> list = new ArrayList<>();
        Page<EmployeePO> empList = ed.findAll(pageable);
        empList.forEach((EmployeePO e) -> {
            forEachEmp(list, e);
        });
        map.put("rows", list);
        return map;
    }

    private void forEachEmp(List<EmployeeListVO> list, EmployeePO e) {
        EmployeeListVO emp = new EmployeeListVO();
        emp.setName(e.getName());
        emp.setEmployeeid(e.getEmployeeid());
        emp.setCode(e.getCode());
        emp.setStatus(e.getStatus());
        if (od.findByOrganizationid(e.getOrganizationid()) != null)
            emp.setParentOrgName(od.findByOrganizationid(e.getOrganizationid()).getName());
        list.add(emp);
    }

    @Transactional(readOnly = true)
    public EmployeePO findByLoginname(String loginname) {
        return ed.findByLoginname(loginname);
    }

    /**
     * 不要使用此方法,ameta登录时也请传入code来进行密码校验
     *
     * @param loginname
     * @param password
     * @return
     */
    @Deprecated
    public EmployeePO login(String loginname, String password) {
        return ed.findByLoginnameAndPassword(loginname, password);
    }

    @Transactional(readOnly = true)
    public EmployeePO login(String loginname, String password, String code0) {
        OrganizationPO org = null;
        // System.out.println(od.findByCode(code0));
        if ((org = od.findByCode(code0)) != null) {
            List<EmployeePO> empList = ed.findByOrganizationidAndLoginname(org.getOrganizationid(), loginname);
            for (int i = 0; !empList.isEmpty() && i < empList.size(); i++) {
                if (Encrypt.check(loginname, password, code0, empList.get(i).getPassword())) {
                    return empList.get(i);
                }
            }
//            return ed.findByLoginnameAndPasswordAndOrganizationid(loginname, password, org.getOrganizationid());
        } else {
            return null;
        }
        return null;
    }

    /**
     * 对EmployeeParentOrgIdVO进行查找,填充
     *
     * @param emp
     * @return
     */
    @Transactional(readOnly = true)
    public EmployeeParentOrgIdVO findEmpParentOrgid(EmployeePO emp) {
        EmployeeParentOrgIdVO empp = new EmployeeParentOrgIdVO();
        empp.setInstallerid(emp.getEmployeeid());
        OrganizationPO org0 = od.findByOrganizationid(emp.getOrganizationid());
        if (org0 != null) {
            empp.setInstallerorgid(org0.getOrganizationid());
            if (org0.getParentorgid() != null)// parentid的对象不为空,说明org0是一个安装商,emp是安装商员工
                empp.setOrganizationid(org0.getParentorgid());
            else// 说明Org0是一个服务商
                empp.setOrganizationid(org0.getOrganizationid());
        }
        return empp;
    }

    /**
     * 返回一个 employeeAddVO对象的所有信息
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public EmployeeAddVO getEmployeeVOInfo(Integer id) {
        EmployeeAddVO emp = new EmployeeAddVO();
        EmployeePO empPO = ed.findByEmployeeid(id);
        emp.setLoginname(empPO.getLoginname());
        emp.setPassword(empPO.getPassword());
        emp.setQuestion(empPO.getQuestion());
        emp.setCode(empPO.getCode());
        emp.setExpiredate(empPO.getExpiredate());
        emp.setStatus(empPO.getStatus());
        ps.findPeronInfo(empPO.getPersonid(), emp);
        as.findAddressInfo(empPO.getAddressid(), emp);
//        OrganizationPO orgPO = od.findByOrganizationid(empPO.getOrganizationid());
        emp.setOrganizationid(empPO.getOrganizationid());
        emp.setEmployeeid(empPO.getEmployeeid());
        return emp;
    }

    /**
     * 改变用户的状态
     *
     * @param employeeid
     * @param status
     */
    public void toggleEmployeeStatus(Integer employeeid, Integer status, HttpServletRequest request) {
        if (status == null) {
            throw new RuntimeException("-101");
        }
        if (!hasProvilege(employeeid, request)) {
            throw new RuntimeException("-99");
        }
        EmployeePO emp = ed.findByEmployeeid(employeeid);
        emp.setStatus(status);
        ed.save(emp);
    }

    public void toggleEmployeeStatus0(String hope, Object[] ids, HttpServletRequest request) {
        if ("unsuspence".equals(hope)) {
            for (Object id : ids) {
                toggleEmployeeStatus(Integer.valueOf(id.toString()), Constants.STATUS_NORMAL, request);
            }
        } else if ("suspence".equals(hope)) {
            for (Object id : ids) {
                toggleEmployeeStatus(Integer.valueOf(id.toString()), Constants.STATUS_SUSPENCED, request);
            }
        }
    }

    /**
     * 通过要操作的员工id和request判断当前登录的员工是否有权限操作此员工
     *
     * @param employeeid
     * @param request
     * @return
     */
    @Transactional(readOnly = true)
    public boolean hasProvilege(Integer employeeid, HttpServletRequest request) {
     /*   EmployeePO emp = ed.findByEmployeeid(employeeid);
        OrganizationPO org = od.findByOrganizationid(emp.getOrganizationid());//要操作的员工的机构

        EmployeePO emp1 = (EmployeePO) request.getSession().getAttribute("emp");
        OrganizationPO org1 = od.findByOrganizationid(emp1.getOrganizationid());

        if (org1.getOrgtype() == Constants.ORGTYPE_AMETA) {//如果登录的员工机构是ameta,直接操作
            return true;

        } else if(org1.getOrgtype() == Constants.ORGTYPE_SUPPLIER){//员工的机构id必须是他的安装商或者服务商
            if (org1.getOrganizationid() == org.getOrganizationid() || org1.getOrganizationid() == org.getParentorgid()) {
                return true;
            } else if (org1.getOrgtype() == Constants.ORGTYPE_INSTALLER && org.getOrganizationid() == org1.getOrganizationid()) {
                return true;
            }
        }
        return false;*/
        EmployeePO emp = ed.findByEmployeeid(employeeid);
        return os.hasProvilege(emp.getOrganizationid(), request);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> search(Pageable pageable, OrgSearchVO search, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgid = emp.getOrganizationid();
        Integer orgtype = od.findByOrganizationid(orgid).getOrgtype();

        String name = "";
        String city1 = "";
        String citycode = "";

        Map<String, Object> map = new HashMap<>();
        Page<EmployeePO> empList = null;
        if (search.getSearchname() != null) {
            name = "%" + search.getSearchname() + "%";
        }
        if (search.getSearchcity() != null) {
            city1 = "%" + search.getSearchcity() + "%";
        }
        if (search.getSearchcitycode() != null) {
            citycode = "%" + search.getSearchcitycode() + "%";
        }

        List<CityPO> cityPO = city.findByCitycodeLikeAndCitynameLike(citycode, city1);
        List<String> citynamelist = cityPO.stream().map(CityPO::getCityname).collect(toList());
        List<AddressPO> citylist = ad.findByCityIn(citynamelist);
        List<Integer> addressidlist = citylist.stream().map(AddressPO::getAddressid).collect(toList());
        if (search.getSearchname() == null || search.getSearchname() == "") {
            if (Constants.ORGTYPE_AMETA.equals(orgtype)) {
                empList = ed.findByAddressidIn(pageable, addressidlist);
                map.put("total", ed.countByAddressidIn(addressidlist));
            } else {
                List<Integer> list1 = new ArrayList<>();
                List<Integer> childrenOrgid = os.findChildrenOrgid(orgid, list1);
                childrenOrgid.add(orgid);
                empList = ed.findByAddressidInAndOrganizationidIn(pageable, addressidlist,childrenOrgid);
                map.put("total", ed.countByAddressidIn(addressidlist));
            }
        } else{
            //当通过名称来搜索时,首先判断城市和城市代码有没有传值:
            //如果有传值,先通过城市和城市代码查找出一个addressid集合,再通过集合和名称进行查找
            //如果没有传值,直接通过名称查找.
            List<Integer> list = new ArrayList<>();
            list.add(orgid);
            List<Integer> ids = os.findChildrenOrgid(emp.getOrganizationid(), list);
            if (FormUtils.isEmpty(search.getSearchcitycode()) && FormUtils.isEmpty(search.getSearchcity())) {
                if (orgtype == Constants.ORGTYPE_AMETA) {
                    empList = ed.findByNameLike(pageable, name);
                    map.put("total", ed.countByNameLike(name));
                } else if (orgtype == Constants.ORGTYPE_SUPPLIER) {
                    empList = ed.findByOrganizationidInAndNameLike(pageable, ids, name);
                    map.put("total", ed.countByOrganizationidInAndNameLike(ids, name));
                } else if (orgtype == Constants.ORGTYPE_INSTALLER) {//当前登录的是安装商
                    empList = ed.findByNameLikeAndOrganizationid(pageable, name, orgid);
                    map.put("total", ed.countByNameLikeAndOrganizationid(name, orgid));
                }
            }else {
                if (orgtype == Constants.ORGTYPE_AMETA) {
                    empList = ed.findByNameLikeAndAddressidIn(pageable, name, addressidlist);
                    map.put("total", ed.countByNameLikeAndAddressidIn(name,ids));
                } else if (orgtype == Constants.ORGTYPE_SUPPLIER) {
                    empList = ed.findByOrganizationidInAndNameLikeAndAddressidIn(pageable, ids, name, addressidlist);
                    map.put("total", ed.countByOrganizationidInAndNameLikeAndAddressidIn(ids, name,addressidlist));
                } else if (orgtype == Constants.ORGTYPE_INSTALLER) {//当前登录的是安装商
                    empList = ed.findByNameLikeAndOrganizationidAndAddressidIn(pageable, name, orgid,addressidlist);
                    map.put("total", ed.countByNameLikeAndOrganizationidAndAddressidIn(name, orgid,addressidlist));
                }
            }
        }
        if (empList == null || empList.getTotalElements() == 0) {
            map.put("total", 0);
            map.put("rows", Collections.EMPTY_LIST);
            return map;
        }
        List<EmployeeListVO> list = new ArrayList<>();
        empList.forEach(e -> forEachEmp(list, e));
        map.put("rows", list);
        return map;
    }


    /**
     * 获取用户的角色集合(返回角色名称)
     *
     * @param employddid
     * @return
     */

    @Transactional(readOnly = true)
    public Set getEmployeeRolesNameSet(Integer employddid) {
        Set<String> set = new HashSet();
        List<EmployeeRolePO> emprolelist = erd.findByEmployeeid(employddid);
        if (emprolelist != null && !emprolelist.isEmpty()) {
            emprolelist.forEach(emprole -> {
                        RolePO role = rd.findByRoleid(emprole.getRoleid());
                        if (role != null) {
                            set.add(role.getName());
                        }
                    }
            );
        }
        return set;
    }

    @Transactional(readOnly = true)
    public Set<String> getEmployeePermissionsName(Integer employeeid) {
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        List<EmployeeRolePO> emprolelist = erd.findByEmployeeid(employeeid);
        if (emprolelist != null && !emprolelist.isEmpty()) {
            emprolelist.forEach(emprole -> {
                List<RolePrivilegePO> rplist = rpd.findByRoleid(emprole.getRoleid());
                if (rplist != null) {
                    List<Integer> privilegeidlist = rplist.stream().map(RolePrivilegePO::getPrivilegeid).collect(toList());
                    List<PrivilegePO> privilegelist = privilegedao.findByPrivilegeidIn(privilegeidlist);
                    if (privilegelist != null && privilegelist.size() != 0) {
                        set.addAll(privilegelist.stream().map(PrivilegePO::getCode).collect(toSet()));
                    }
                }
            });
            return set;
        }
        return null;
    }
	
	 @Transactional(readOnly = true)
    public String getMenuTree(EmployeePO emp, HttpServletRequest request) {
        ResourceBundle resourceBundle ;
        String language = request.getLocale().getLanguage();
         if ("zh".equals(language)){
            resourceBundle =ResourceBundle.getBundle("messages",Locale.SIMPLIFIED_CHINESE);
            //resourceBundle =ResourceBundle.getBundle("messages",Locale.US);
         }else{
             resourceBundle =ResourceBundle.getBundle("messages",Locale.US);
         }
         List<RolePrivilegePO> roleprivilegelist = new ArrayList<>();//角色权限列表
         List<Integer> privilegeid = new ArrayList<>();//权限列表
         OrganizationPO org = od.findByOrganizationid(emp.getOrganizationid());//根据员工的机构id获取所属机构
         List<EmployeeRolePO> emprolelist = employeeroleDAO.findByEmployeeid(emp.getEmployeeid());//根据员工id获取员工的所有角色
         emprolelist.forEach(e -> roleprivilegelist.addAll(rolePrivilegeDAO.findByRoleid(e.getRoleid())));//遍历员工角色列表，获取角色权限列表
         roleprivilegelist.forEach(e -> privilegeid.add(e.getPrivilegeid()));//根据员工的角色权限列表得到员工的权限id列表
         List<PrivilegePO> privilegelist = privilegeDAO.findByPrivilegeidIn(privilegeid);//根据角色权限id列表去拿到权限权限列表
         List<Integer> idlist = new ArrayList<>();//权限列表id集合
         for(PrivilegePO e : privilegelist){
             idlist.add(e.getPrivilegeid());
         }
         List<Object> parlist = new ArrayList<>();
         for(PrivilegePO p : privilegelist){
             Map<String,Object> parmap = new LinkedHashMap<>();
             if ("0".equals(p.getParentprivilegeid()+"")){//为父节点
                 String text = resourceBundle.getString(p.getCode());
                 String href = p.getLabel();
                 parmap.put("text",text);
                 parmap.put("href",href);
                 List<PrivilegePO> sonlist = privilegeDAO.findByParentprivilegeid(p.getPrivilegeid());
                 if(sonlist.size()>0) {
                     List<Object> pplist = new ArrayList<>();
                     for (PrivilegePO pp : sonlist) {
                         if(idlist.contains(pp.getPrivilegeid())) {
                             Map<String, Object> sonmap = new HashMap<>();
                             String tt = resourceBundle.getString(pp.getCode());
                             String hh = pp.getLabel();
                             sonmap.put("text", tt);
                             sonmap.put("href", hh);
                             pplist.add(sonmap);
                         }
                     }
                     JSONArray jj = JSONArray.fromObject(pplist);
                     parmap.put("nodes",jj);
                 }
             }else if(p.getParentprivilegeid()!=null&&!idlist.contains(p.getParentprivilegeid())){
                 String text = resourceBundle.getString(p.getCode());
                 String href = p.getLabel();
                 parmap.put("text",text);
                 parmap.put("href",href);
             }
             if(parmap.size()>0) {
                 parlist.add(parmap);
             }
         }
        JSONArray json = JSONArray.fromObject(parlist);
        return String.valueOf(json);
    }
}
