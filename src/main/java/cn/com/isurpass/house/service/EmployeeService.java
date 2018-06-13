package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.Encrypt;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
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
    private EmployeeroleDAO erd;
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
        if (!FormUtils.checkNUll(emp.getLoginname()) || !FormUtils.checkNUll(emp.getPassword())) {
            throw new MyArgumentNullException("-100");
        }
        EmployeePO emp1 = (EmployeePO) request.getSession().getAttribute("emp");
        //修改员工时才会存在的员工session
        EmployeeAddVO empInfo = (EmployeeAddVO) request.getSession().getAttribute("empInfo");
        if (emp.getOrganizationid() == null) { //如果没有传入机构id
            if (empInfo != null) {
                //修改
                emp.setOrganizationid(empInfo.getOrganizationid());
            } else {
                //说明这是安装商在进行添加
                emp.setOrganizationid(emp1.getOrganizationid());
            }
        }
        if (empInfo == null && isRegister(emp.getOrganizationid(), emp.getLoginname())) {
            //被注册了
            throw new MyArgumentNullException("-102");
        }

        if (empInfo == null && FormUtils.checkNUll(emp.getCode()) && !ed.findByOrganizationidAndCodeAndStatusNot(emp.getOrganizationid(), emp.getCode(), Constants.STATUS_DELETED).isEmpty()) {
            throw new MyArgumentNullException("-104");
        }

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
        if(StringUtils.isEmpty(emp.getCode())){
            empPO.setCode(null);
        }else{
            empPO.setCode(emp.getCode());
        }
        String code = od.findByOrganizationid(emp.getOrganizationid()).getCode();
        empPO.setPassword(Encrypt.encrypt(emp.getLoginname(), emp.getPassword(), code));
        //TODO 该处是修改密码
        empPO.setQuestion(emp.getQuestion());
        empPO.setAnswer(Encrypt.encrypt(emp.getLoginname(), emp.getAnswer(), code));
        if (emp.getStatus()==null) {
            emp.setStatus(Constants.STATUS_NORMAL);
        }
        empPO.setStatus(emp.getStatus());
        empPO.setExpiredate(emp.getExpiredate());
        empPO.setOrganizationid(emp.getOrganizationid());
        empPO.setName(emp.getLastname() + emp.getFirstname());
        Integer addressid = null;
        if (!FormUtils.isEmpty(addressPO)) {
            if(addressPO.getCountry()==null||addressPO.getProvince()==null||addressPO.getCity()==null){
                throw  new MyArgumentNullException("-122");
            }
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

            //添加员工所属的机构的类型
            Integer orgtype = od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
            //默认角色是员工
            erp.setRoleid(5);
            if (orgtype.equals(Constants.ORGTYPE_AMETA)) {
                erp.setRoleid(Constants.ROLE_AMETA_EMPLOYEE);
            } else if (orgtype.equals(Constants.ORGTYPE_SUPPLIER)) {
                erp.setRoleid(Constants.ROLE_SUPPLIER_EMPLOYEE);
            } else {
                // code为空时，新增的是安装商普通员工
                if (StringUtils.isEmpty(emp.getCode())) {
                    erp.setRoleid(Constants.ROLE_INSTALLER_EMPLOYEE);
                }else{
                    //新增的是安装员
                    erp.setRoleid(Constants.ROLE_INSTALLER);
                }
            }
            erp.setEmployeeid(save.getEmployeeid());
            erp.setCreatetime(new Date());
            erd.save(erp);
        }else{
            Integer orgtype = od.findByOrganizationid(empInfo.getOrganizationid()).getOrgtype();
            List<EmployeeRolePO> byEmployeeid = employeeroleDAO.findByEmployeeid(empInfo.getEmployeeid());
            EmployeeRolePO erp = new EmployeeRolePO();
            erp.setEmployeeid(empInfo.getEmployeeid());
            erp.setCreatetime(new Date());
            if (orgtype.equals(Constants.ORGTYPE_INSTALLER)) {
                // code为空时，新增的是安装商普通员工
                if (StringUtils.isEmpty(emp.getCode())) {
                    erp.setRoleid(Constants.ROLE_INSTALLER_EMPLOYEE);
                }else{
                    //新增的是安装员
                    erp.setRoleid(Constants.ROLE_INSTALLER);
                }
                byEmployeeid.add(erp);
                erd.saveAll(byEmployeeid);
            }
        }
    }

//    @Transactional(readOnly = true)
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
//    @Transactional(readOnly = true)
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
        emp.setName(e.getLoginname());
        emp.setEmployeeid(e.getEmployeeid());
        emp.setCode(e.getCode());
        updateEmployeeStatusByExpiredate(e);
        emp.setStatus(e.getStatus());
        if (od.findByOrganizationid(e.getOrganizationid()) != null) {
            emp.setParentOrgName(od.findByOrganizationid(e.getOrganizationid()).getName());
        }
        list.add(emp);
    }

    @Transactional(readOnly = true)
    public EmployeePO findByLoginname(String loginname) {
        return ed.findByLoginname(loginname);
    }

    @Transactional(readOnly = true)
    public EmployeePO login(String loginname, String password, String organizationid,String code) {
        OrganizationPO org = null;
        if ((org = od.findByOrganizationid(Integer.parseInt(organizationid))) != null) {
            initStatusByExpireTime(org.getOrganizationid(), loginname);
            List<EmployeePO> empList = ed.findByOrganizationidAndLoginnameAndStatus(org.getOrganizationid(), loginname, Constants.STATUS_NORMAL);
            for (int i = 0; !empList.isEmpty() && i < empList.size(); i++) {
                if (Encrypt.check(loginname, password, code, empList.get(i).getPassword())) {
                    return empList.get(i);
                }
            }
//            return ed.findByLoginnameAndPasswordAndOrganizationid(loginname, password, org.getOrganizationid());
        } else {
            return null;
        }
        return null;
    }

    private void initStatusByExpireTime(Integer organizationid, String loginname) {
        List<EmployeePO> list = ed.findByOrganizationidAndLoginname(organizationid, loginname);
        if (list == null || list.size() == 0) {
            return;
        }
        updateEmployeeStatusByExpiredate(list.get(0));
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
        empp.setInstallerid(emp.getEmployeeid());//当前录入员工为安装员
        OrganizationPO org0 = od.findByOrganizationid(emp.getOrganizationid());
        if (org0 != null) {
            empp.setInstallerorgid(org0.getOrganizationid());//录入员工的机构为安装商
            if (org0.getOrgtype() == Constants.ORGTYPE_INSTALLER)//如果机构为安装商，则其父机构为服务商，
            {
                if(org0.getParentorgid()==null){
                    empp.setOrganizationid(0);
                }else{
                    empp.setOrganizationid(org0.getParentorgid());
                }
            } else {
                // 说明Org0是一个服务商
                empp.setOrganizationid(org0.getOrganizationid());
            }
        }

        return empp;
    }

    /**
     * 返回一个 employeeAddVO对象的所有信息
     *
     * @param id
     * @return
     */
//    @Transactional(readOnly = true)
    public EmployeeAddVO getEmployeeVOInfo(Integer id) {
        EmployeeAddVO emp = new EmployeeAddVO();
        EmployeePO empPO = ed.findByEmployeeid(id);
        emp.setLoginname(empPO.getLoginname());
        emp.setPassword(empPO.getPassword());
        emp.setQuestion(empPO.getQuestion());
        emp.setCode(empPO.getCode());
        emp.setExpiredate(empPO.getExpiredate());
        updateEmployeeStatusByExpiredate(empPO);
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
                toggleEmployeeStatus(Integer.valueOf(id.toString().replace( ",", "")), Constants.STATUS_NORMAL, request);
            }
        } else if ("suspence".equals(hope)) {
            for (Object id : ids) {
                toggleEmployeeStatus(Integer.valueOf(id.toString().replace( ",", "")), Constants.STATUS_SUSPENCED, request);
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
        EmployeePO loginemp = (EmployeePO) request.getSession().getAttribute("emp");
        EmployeePO emp = ed.findByEmployeeid(employeeid);
        if (emp == null) {
            return false;
        }
        //如果登录的用户有权限操作此用户所在的机构,此也有权限操作这个用户
        return os.hasPermissionOperateOrg(loginemp.getEmployeeid(), emp.getOrganizationid());
    }

//    @Transactional(readOnly = true)
    public Map<String, Object> search(Pageable pageable, OrgSearchVO search, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgid = emp.getOrganizationid();
        Integer orgtype = od.findByOrganizationid(orgid).getOrgtype();
        long total = 0;
        String name = "";
        String code = "";
        String orgname = "";
        Map<String, Object> map = new HashMap<>();
        List<Object[]> olist = new ArrayList<>();
        List<EmployeeListVO> list = new ArrayList<>();
        if (search.getSearchname() != null) {
            name = "%" + search.getSearchname() + "%";
        }
        if (search.getSearchcode() != null) {
            code = "%" + search.getSearchcode() + "%";
        }
        if (search.getSearchorgname() != null) {
            orgname = "%" + search.getSearchorgname() + "%";
        }
        if(Constants.ORGTYPE_AMETA.equals(orgtype)){
            olist = od.findAllEmp(pageable,name,code,orgname);
            total = od.countAllEmp(name,code,orgname);
        }else{
            List<Integer> list1 = new ArrayList<>();
            List<Integer> childrenOrgid = os.findChildrenOrgid(orgid, list1);
            olist = od.findAllSupEmp(pageable,name,code,orgname,childrenOrgid);
            total = od.countAllSupEmp(name,code,orgname,childrenOrgid);
        }
        for(Object[] o:olist){
            EmployeeListVO empVO = new EmployeeListVO();
            empVO.setEmployeeid((Integer)o[0]);
            empVO.setCode((String)o[1]);
            empVO.setName((String)o[2]);
            empVO.setParentOrgName((String)o[3]);
            empVO.setStatus((Integer)o[4]);
            list.add(empVO);
        }
        map.put("rows", list);
        map.put("total",total);
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

    /**
     * 根据登录员工的角色、权限拿到对应的菜单
     *
     * @param emp
     * @param request
     * @return
     */
    @Transactional(readOnly = true)
    public String getMenuTree(EmployeePO emp, HttpServletRequest request) {
        ResourceBundle resourceBundle;
        String language = request.getLocale().getLanguage();
        if ("zh".equals(language)) {
            resourceBundle = ResourceBundle.getBundle("messages", Locale.SIMPLIFIED_CHINESE);
            //resourceBundle =ResourceBundle.getBundle("messages",Locale.US);
        } else {
            resourceBundle = ResourceBundle.getBundle("messages", Locale.US);
        }
        List<RolePrivilegePO> roleprivilegelist = new ArrayList<>();//角色权限列表
        List<Integer> privilegeid = new ArrayList<>();//权限列表
        OrganizationPO org = od.findByOrganizationid(emp.getOrganizationid());//根据员工的机构id获取所属机构
        List<EmployeeRolePO> emprolelist = employeeroleDAO.findByEmployeeid(emp.getEmployeeid());//根据员工id获取员工的所有角色
        emprolelist.forEach(e -> roleprivilegelist.addAll(rolePrivilegeDAO.findByRoleid(e.getRoleid())));//遍历员工角色列表，获取角色权限列表
        roleprivilegelist.forEach(e -> privilegeid.add(e.getPrivilegeid()));//根据员工的角色权限列表得到员工的权限id列表
        List<PrivilegePO> privilegelist = privilegeDAO.findByPrivilegeidIn(privilegeid);//根据角色权限id列表去拿到权限权限列表
        List<Integer> idlist = new ArrayList<>();//权限列表id集合
        for (PrivilegePO e : privilegelist) {
            idlist.add(e.getPrivilegeid());
        }
        List<Object> parlist = new ArrayList<>();
        for (PrivilegePO p : privilegelist) {
            Map<String, Object> parmap = new LinkedHashMap<>();
            if ("0".equals(p.getParentprivilegeid() + "") || "-1".equals(p.getParentprivilegeid() + "")) {//为父节点, 为 -1 时,此节点不能点击
                String text = resourceBundle.getString(p.getCode());
                String href = p.getLabel();
                parmap.put("text", text);
                parmap.put("href", href);
                if ("-1".equals(p.getParentprivilegeid() + "")) {
                    parmap.put("selectable", false);
                }
                Map<String, String> m = new HashMap<>();
                m.put("expanded", "true");
                parmap.put("state", m);
                List<PrivilegePO> sonlist = privilegeDAO.findByParentprivilegeid(p.getPrivilegeid());
                if (sonlist.size() > 0) {
                    List<Object> pplist = new ArrayList<>();
                    for (PrivilegePO pp : sonlist) {
                        if (idlist.contains(pp.getPrivilegeid())) {
                            Map<String, Object> sonmap = new HashMap<>();
                            String tt = resourceBundle.getString(pp.getCode());
                            String hh = pp.getLabel();
                            sonmap.put("text", tt);
                            sonmap.put("href", hh);
                            pplist.add(sonmap);
                        }
                    }
                    JSONArray jj = JSONArray.fromObject(pplist);
                    parmap.put("nodes", jj);
                } else {
                    parmap.put("nodes", new ArrayList<>());
                }
            } else if (p.getParentprivilegeid() != null && !idlist.contains(p.getParentprivilegeid())) {
                String text = resourceBundle.getString(p.getCode());
                String href = p.getLabel();
                parmap.put("text", text);
                parmap.put("href", href);
                Map<String, String> m = new HashMap<>();
                m.put("expanded", "true");
                parmap.put("state", m);
                parmap.put("nodes", new ArrayList<>());
            }
            if (parmap.size() > 0) {
                parlist.add(parmap);
            }
        }
        JSONArray json = JSONArray.fromObject(parlist);
        return String.valueOf(json);
    }

    public RequestExpendVO getEmployeeInfo(HttpServletRequest request) {
        RequestExpendVO req = new RequestExpendVO();
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        req.setEmployeerole(erd.findByEmployeeid(emp.getEmployeeid()).stream().map(EmployeeRolePO::getRoleid).collect(toList()));
        req.setEmployeeid(emp.getEmployeeid());
        req.setOrgid(emp.getOrganizationid());
        req.setOrgtype(od.findByOrganizationid(emp.getOrganizationid()).getOrgtype());
        return req;
    }

    public EmployeeVO getEmployeeInfo(Integer employeeid) {
        EmployeePO employeePO = ed.findByEmployeeid(employeeid);
        if (employeePO == null) {
            return null;
        }
        EmployeeVO employee = new EmployeeVO();
        employee.setEmployeeid(employeePO.getEmployeeid());
        employee.setName(employeePO.getLoginname());
        return employee;
    }

    /**
     * 通过角色信息判断此用户是否是ameta管理员
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public boolean isAmetaAdmin(Integer id) {
        List<EmployeeRolePO> list = erd.findByEmployeeid(id);
        if (list != null && list.size() != 0) {
            for (EmployeeRolePO emp : list) {
                if (emp.getRoleid().equals(Constants.ROLE_AMETA_ADMIN)||emp.getRoleid().equals(Constants.ROLE_AMETA_EMPLOYEE)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*@Transactional(readOnly = true)
    public EmployeePO findInstallerInfo(Integer orgid) {
        Integer admin = ed.findAdmin(orgid,3);
        if (admin == null) {
            return null;
        }
        return ed.findByEmployeeid(admin);
    }

    @Transactional(readOnly = true)
    public EmployeePO findSuppllierAdmin(Integer orgid){
        Integer admin = ed.findAdmin(orgid,2);
        if (admin == null) {
            return null;
        }
        return ed.findByEmployeeid(admin);
    }*/

    /**
     * 在获取员工信息时,通过此方法来同步status和exiredate之间的关系
     */
    private void updateEmployeeStatusByExpiredate(EmployeePO emp){
        if (emp == null || emp.getExpiredate() == null) {
            return ;
        }
        //当用户状态为正常且当前时间超过失效日期时,
        if (emp.getStatus().equals(Constants.STATUS_NORMAL) && new Date().after(emp.getExpiredate())) {
            emp.setStatus(Constants.STATUS_SUSPENCED);
            ed.save(emp);
        }
    }

    public EmployeeAddVO queryEmployeeInfo(HttpServletRequest request, Integer employeeid) {
        /*if (!hasProvilege(employeeid, request)) {
            throw new RuntimeException("-998");//TODO 判断权限写的业务逻辑太混乱了，暂时不判断
        }*/
        EmployeeAddVO employeeAddVO = new EmployeeAddVO();
        EmployeePO byEmployeeid = ed.findByEmployeeid(employeeid);
        if (byEmployeeid == null) {
            return null;
        }
        OrganizationPO byOrganizationid = od.findByOrganizationid(byEmployeeid.getOrganizationid());
        if (byOrganizationid != null) {
            employeeAddVO.setOrgCode(byOrganizationid.getCode());
            employeeAddVO.setParentOrgName(byOrganizationid.getName());
        }
        employeeAddVO.setLoginname(byEmployeeid.getLoginname());
        employeeAddVO.setCode(byEmployeeid.getCode());
        employeeAddVO.setQuestion(byEmployeeid.getQuestion());
        employeeAddVO.setCode(byEmployeeid.getCode());
        employeeAddVO.setExpiredate(byEmployeeid.getExpiredate());
        employeeAddVO.setStatus(byEmployeeid.getStatus());
        employeeAddVO.setFirstname(byEmployeeid.getName());
        PersonPO byPersonid = pd.findByPersonid(byEmployeeid.getPersonid());
        AddressPO byAddressid = ad.findByAddressid(byEmployeeid.getAddressid());
        if (byPersonid != null) {
            employeeAddVO.setTitle(byPersonid.getTitle());
            employeeAddVO.setGender(byPersonid.getGender());
            employeeAddVO.setEmail(byPersonid.getEmail());
            employeeAddVO.setFax(byPersonid.getFax());
            employeeAddVO.setSsn(byPersonid.getSsn());
            employeeAddVO.setPhonenumber(byPersonid.getPhonenumber());
        }
        if (byAddressid != null) {
            employeeAddVO.setCountryname(byAddressid.getCountry());
            employeeAddVO.setProvincename(byAddressid.getProvince());
            employeeAddVO.setCityname(byAddressid.getCity());
            employeeAddVO.setDetailaddress(byAddressid.getDetailaddress());
        }

        return employeeAddVO;
    }
    @Transactional(readOnly = true)
    public Boolean validCode(String code, Integer organizationid) {
        EmployeePO emp = ed.findByCodeAndOrganizationid(code,organizationid);
        if (emp == null)
            return true;
        return false;
    }
    @Transactional
    public OrganizationPO findOrgByLoginName(String loginname) {
        EmployeePO emp = ed.findByLoginname(loginname);
        OrganizationPO org = od.findByOrganizationid(emp.getOrganizationid());
        return org;
    }
    @Transactional
    public Boolean validLoginName(String loginname) {
        EmployeePO byLoginname = ed.findByLoginname(loginname);
        if(byLoginname==null){
            return true;
        }else{
            return false;
        }
    }

    public boolean findLoginname(Integer employeeid, String loginname) {
        EmployeePO emp = ed.findByLoginname(loginname);
        if(emp!=null&&emp.getEmployeeid()-employeeid==0){
            return true;
        }else{
            return false;
        }
    }
}
