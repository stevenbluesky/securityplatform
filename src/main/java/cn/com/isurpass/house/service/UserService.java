package cn.com.isurpass.house.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.util.Constants;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.EmployeeParentOrgIdVO;
import cn.com.isurpass.house.vo.UserAddVO;
import cn.com.isurpass.house.vo.UserInfoListVO;

@Service
public class UserService {

    @Autowired
    UserDAO ud;
    @Autowired
    CountryDAO country;
    @Autowired
    ProvinceDAO province;
    @Autowired
    CityDAO city;
    @Autowired
    AddressDAO ad;
    @Autowired
    PersonDAO pd;
    @Autowired
    OrganizationDAO od;
    @Autowired
    PhonecarduserDAO pcud;
    @Autowired
    PhonecardDAO pcard;
    @Autowired
    EmployeeService emps;
    @Autowired
    GatewayuserDAO gd;
    @Autowired
    GatewayBindingDAO gbd;
    @Autowired
    EmployeeroleDAO erd;

    @Transactional(rollbackFor = Exception.class)
    public void add(UserAddVO u, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) SecurityUtils.getSubject().getPrincipal();

        if (!FormUtils.checkNUll(u.getDeviceid()) || !FormUtils.checkNUll(u.getSerianumber())) {
            throw new RuntimeException("网关或电话卡不能为空.");
        }
        if (gd.findByDeviceid(u.getDeviceid()).size() != 0) {
            throw new RuntimeException("此网关已经绑定了用户");
        }
        if (gbd.findByDeviceidAndOrganizationid(u.getDeviceid(), emp.getOrganizationid()) == null) {//网关不存在或者此网关不属于此机构
            throw new RuntimeException("无法添加此网关!");
        }
        PhonecardPO pcardpo = pcard.findBySerialnumber(u.getSerianumber());
        if (pcardpo == null) {
            throw new RuntimeException("无法添加此网关");
        }
        if (pcud.findByPhonecardid(pcardpo.getPhonecardid()) != null) {
            throw new RuntimeException("此电话卡已经绑定了用户.");
        }

        UserPO user = new UserPO();
        user.setLoginname(u.getPhonenumber());
        user.setName(u.getFirstname() + " " + u.getLastname());
        user.setCitycode("");

        EmployeeParentOrgIdVO empp = emps.findEmpParentOrgid(emp);
        user.setOrganizationid(1);
        user.setInstallerorgid(1);
        user.setInstallerid(1);
        user.setStatus(1);
        if (empp != null) {
            user.setOrganizationid(empp.getOrganizationid());
            user.setInstallerorgid(empp.getInstallerorgid());
            user.setInstallerid(empp.getInstallerid());
        }
        user.setCodepostfix(u.getCodepostfix());
        user.setUsercode("ameta" + u.getPhonenumber());

        AddressPO address = new AddressPO();
        Integer addressid = null;
        address.setDetailaddress(u.getDetailaddress());
        if (u.getCountry() != null)
            address.setCountry(country.findByCountryid(u.getCountry()).getCountryname());
        if (u.getProvince() != null)
            address.setProvince(province.findByProvinceid(u.getProvince()).getProvincename());
        if (u.getCity() != null) {
            CityPO c = city.findByCityid(u.getCity());
            address.setCity(c.getCityname());
            user.setCitycode(c.getCitycode());
        }
        if (!FormUtils.isEmpty(address))
            addressid = ad.save(address).getAddressid();
        PersonPO person = new PersonPO();
        person.setFirstname(u.getFirstname());
        person.setLastname(u.getLastname());
        person.setName(u.getFirstname() + " " + u.getLastname());
        person.setSsn(u.getSsn());
        person.setGender(u.getGender());
        person.setPhonenumber(u.getPhonenumber());
        person.setAddressid(addressid);
        person.setEmail(u.getEmail());
        person.setFax(u.getFax());
        user.setCreatetime(new Date());

        Integer personid = pd.save(person).getPersonid();
        user.setPersonid(personid);
        UserPO userSave = ud.save(user);
//        if (u.getDeviceid() != null) {
        GatewayUserPO gateway = new GatewayUserPO();
        gateway.setDeviceid(u.getDeviceid());
        gateway.setUserid(userSave.getUserid());
        gateway.setCreatetime(new Date());
        gd.save(gateway);
//        }

//        if (pcardpo.getPhonecardid() != null) {//此方法最开始已经强制phonecardid不能为空,这里不用进行判断了.
        PhonecardUserPO pcup = new PhonecardUserPO();
        pcup.setUserid(userSave.getUserid());
        pcup.setPhonecardid(1);
        pcup.setCreatetime(new Date());
        pcud.save(pcup);
//        }

    }

    @Transactional(readOnly = true)
    public Map<String, Object> listUserInfo(Pageable pageable, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgtype = od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
        Integer count = 0;
        if (erd.findByEmployeeid(emp.getEmployeeid()).getRoleid() == 4) {
            Page<UserPO> userList = ud.findByInstallerid(emp.getEmployeeid(), pageable);
            count = ud.countByInstallerid(emp.getEmployeeid());
            return listUserInfo0(pageable, userList, count);
        }
        if (orgtype == Constants.ORGTYPE_AMETA) {
            Page<UserPO> userList = ud.findAll(pageable);
            count = (int) ud.count();
            return listUserInfo0(pageable, userList, count);
        }
        if (orgtype == Constants.ORGTYPE_INSTALLER) {
            Page<UserPO> userList = ud.findByInstallerorgid(emp.getOrganizationid(), pageable);
            count = ud.countByInstallerorgid(emp.getOrganizationid());
            return listUserInfo0(pageable, userList, count);
        }
        if (orgtype == Constants.ORGTYPE_SUPPLIER) {
            Page<UserPO> userList = ud.findByOrganizationid(emp.getOrganizationid(), pageable);
            count = ud.countByInstallerorgid(emp.getOrganizationid());
            return listUserInfo0(pageable, userList, count);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> listUserInfo0(Pageable pageable, Page<UserPO> userList, Integer count) {
        Map<String, Object> map = new HashMap<>();
        map.put("total", count);
        List<UserInfoListVO> list = new ArrayList<>();
        if (userList == null) {
            return null;
        }
        userList.forEach(u -> {
            PersonPO personPO = pd.findByPersonid(u.getPersonid());
            UserInfoListVO user = new UserInfoListVO();
            AddressPO adrs = null;
            if (personPO != null) {
                adrs = ad.findByAddressid(personPO.getAddressid());
                user.setPhonenumber(personPO.getPhonenumber());
            }
            user.setUserid(u.getUserid());
            user.setName(u.getName());
            if (adrs != null) {
                user.setCity(adrs.getCity());
            }
            if (od.findByOrganizationid(u.getOrganizationid()) != null)
                user.setSuppliername(od.findByOrganizationid(u.getOrganizationid()).getName());
            user.setStatus(u.getStatus());
            list.add(user);
            // System.out.println(list.toString()+"fff");
        });
        map.put("rows", list);
        return map;
    }

    //判断当前的是服务商还是安装商
    @Transactional(readOnly = true)
    public List<UserPO> findUser(Integer orgid) {

        //TODO 先要判断角色,然后查找相应的机构.

        List<UserPO> userList = ud.findByOrganizationid(orgid);
        return userList;
    }

    public void toggleUserStatus0(String hope, Object[] ids, HttpServletRequest request) throws MyArgumentNullException {
        if ("unsuspence".equals(hope)) {
            for (Object id : ids) {
                toggleUserStatus(Integer.valueOf(id.toString()), Constants.STATUS_NORMAL, request);
            }
        } else if ("suspence".equals(hope)) {
            for (Object id : ids) {
                toggleUserStatus(Integer.valueOf(id.toString()), Constants.STATUS_SUSPENCED, request);
            }
        }
    }

    public void toggleUserStatus(Integer userid, Integer status, HttpServletRequest request) throws MyArgumentNullException {
        if (status == null) {
            throw new RuntimeException("status不能为空");
        }
        if (!hasProvilege(userid, request)) {
            throw new MyArgumentNullException("无权操作!");
        }
        UserPO user = ud.findByUserid(userid);
        user.setStatus(status);
        ud.save(user);
    }

    private boolean hasProvilege(Integer userid, HttpServletRequest request) {
        UserPO user = ud.findByUserid(userid);
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgtype = od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
        if (orgtype == Constants.ORGTYPE_INSTALLER && emp.getOrganizationid() == user.getInstallerorgid()) {
            return true;
        }
        if (orgtype == Constants.ORGTYPE_SUPPLIER && emp.getOrganizationid() == user.getOrganizationid()) {
            return true;
        }
        if (orgtype == Constants.ORGTYPE_AMETA) {
            return true;
        }
        return false;
    }
}
