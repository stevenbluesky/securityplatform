package cn.com.isurpass.house.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.isurpass.house.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.isurpass.house.dao.AddressDAO;
import cn.com.isurpass.house.dao.CityDAO;
import cn.com.isurpass.house.dao.CountryDAO;
import cn.com.isurpass.house.dao.EmployeeDAO;
import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.dao.PersonDAO;
import cn.com.isurpass.house.dao.ProvinceDAO;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.AddressPO;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.po.PersonPO;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.Encrypt;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.EmployeeAddVO;
import cn.com.isurpass.house.vo.EmployeeListVO;
import cn.com.isurpass.house.vo.EmployeeParentOrgIdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDAO ed;
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
            throw new MyArgumentNullException("必填字段不能为空!");
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
            throw new MyArgumentNullException("用户名已存在!");
        }
        if ((od.findByOrganizationid(emp.getOrganizationid()).getOrgtype() == Constants.ORGTYPE_INSTALLER)
                && !FormUtils.checkNUll(emp.getCode()))// 是安装员且code为空时
            throw new MyArgumentNullException("安装员必须要有员工代码!");
//        System.out.println(FormUtils.checkNUll(emp.getCode()) + "==================");
        if (empInfo == null && FormUtils.checkNUll(emp.getCode()) && !ed.findByOrganizationidAndCodeAndStatusNot(emp.getOrganizationid(), emp.getCode(),
                Constants.STATUS_DELETED).isEmpty())
            throw new MyArgumentNullException("员工代码不能重复.!");

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
        Encrypt svr = new Encrypt();
//        empPO.setPassword(FormUtils.encrypt(emp.getPassword()));// 加密
        empPO.setPassword(svr.encrypt(emp.getLoginname(), emp.getPassword(), emp.getCode()));
        empPO.setQuestion(emp.getQuestion());
//        empPO.setAnswer(FormUtils.encrypt(emp.getAnswer()));
        empPO.setAnswer(svr.encrypt(emp.getLoginname(), emp.getAnswer(), emp.getCode()));
        empPO.setStatus(emp.getStatus());
        empPO.setExpiredate(new Date());
        empPO.setOrganizationid(emp.getOrganizationid());
        empPO.setName(emp.getLastname() + " " + emp.getFirstname());
//        System.out.println(emp);
        Integer addressid = null;
        if (!FormUtils.isEmpty(addressPO)) {
            addressid = ad.save(addressPO).getAddressid();
            empPO.setAddressid(addressid);
        }
        if (FormUtils.checkNUll(emp.getLastname()) || FormUtils.checkNUll(emp.getFirstname()) || FormUtils.checkNUll(emp.getSsn())
                || FormUtils.checkNUll(emp.getSsn()) || emp.getGender() != null || FormUtils.checkNUll(emp.getTitle())) {
            PersonPO personPO = new PersonPO(emp.getLastname() + " " + emp.getFirstname(), emp.getSsn(), emp.getGender(), emp.getTitle(), emp.getFirstname(), emp.getLastname(),
                    emp.getPhonenumber(), emp.getEmail(), emp.getFax());
            personPO.setAddressid(addressid);
            if (empInfo != null) {
                personPO.setPersonid(empInfo.getPersonid());
            }
            empPO.setPersonid(pd.save(personPO).getPersonid());
        }
        ed.save(empPO);
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
            for (int i = 0;!empList.isEmpty() && i < empList.size(); i++) {
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
     * @param employeeid
     * @param status
     */
    public void toggleEmployeeStatus(Integer employeeid,Integer status,HttpServletRequest request) {
        if (status == null) {
            throw new RuntimeException("status不能为空");
        }
        if (!hasProvilege(employeeid, request)) {
            throw new RuntimeException("无权操作!");
        }
        EmployeePO emp = ed.findByEmployeeid(employeeid);
        emp.setStatus(status);
        ed.save(emp);
    }

    public void toggleEmployeeStatus0(String hope, Object[] ids,HttpServletRequest request) {
        if ("unsuspence".equals(hope)) {
            for (Object id : ids) {
                toggleEmployeeStatus(Integer.valueOf(id.toString()), Constants.STATUS_NORMAL, request);
            }
        } else if("suspence".equals(hope)){
            for (Object id : ids) {
                toggleEmployeeStatus(Integer.valueOf(id.toString()),Constants.STATUS_SUSPENCED,request);
            }
        }
    }

    /**
     * 通过要操作的员工id和request判断当前登录的员工是否有权限操作此员工
     * @param employeeid
     * @param request
     * @return
     */
    @Transactional(readOnly = true)
    public boolean hasProvilege(Integer employeeid,HttpServletRequest request) {
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
}
