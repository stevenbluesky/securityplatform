package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.EmployeeParentOrgIdVO;
import cn.com.isurpass.house.vo.UserAddVO;
import cn.com.isurpass.house.vo.UserInfoListVO;
import cn.com.isurpass.house.vo.UserSearchVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.util.stream.Collectors.toList;

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
    @Autowired
    GatewayDAO gateway;
    @Autowired
    OrganizationService os;

    @Transactional(rollbackFor = Exception.class)
    public void add(UserAddVO u, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) SecurityUtils.getSubject().getPrincipal();

        if (!FormUtils.checkNUll(u.getDeviceid()) || !FormUtils.checkNUll(u.getSerialnumber())) {
            throw new RuntimeException("-107");
        }
        if (gd.findByDeviceid(u.getDeviceid()).size() != 0) {
            throw new RuntimeException("-108");
        }
        if (gbd.findByDeviceidAndOrganizationid(u.getDeviceid(), emp.getOrganizationid()) == null) {//网关不存在或者此网关不属于此机构
            throw new RuntimeException("-109");
        }
        PhonecardPO pcardpo = pcard.findBySerialnumber(u.getSerialnumber());
        if (pcardpo == null) {
            throw new RuntimeException("-111");
        }
        if (pcud.findByPhonecardid(pcardpo.getPhonecardid()) != null) {
            throw new RuntimeException("-110");
        }

        UserPO user = new UserPO();
        user.setLoginname(u.getPhonenumber());
        user.setName(u.getFirstname() + u.getLastname());
        if (!FormUtils.isEmpty(u.getCity())) {
            user.setCitycode(city.findByCityid(u.getCity()).getCitycode());
        }
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
        person.setName(u.getFirstname() + u.getLastname());
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
        pcup.setPhonecardid(pcardpo.getPhonecardid());
        pcup.setUserid(userSave.getUserid());
        pcup.setCreatetime(new Date());
        pcud.save(pcup);
//        }
    }

    @Transactional(readOnly = true)
    public Map<String, Object> listUserInfo(Pageable pageable, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgtype = od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
        Integer count = 0;
        if (erd.findByEmployeeid(emp.getEmployeeid()) != null && erd.findByEmployeeid(emp.getEmployeeid()).stream().map(EmployeeRolePO::getRoleid).anyMatch(id -> id == 4)) {
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
        setProperties(userList, list);
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
            throw new RuntimeException("-101");
        }
        if (!hasProvilege(userid, request)) {
            throw new MyArgumentNullException("-99");
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

    @Transactional(readOnly = true)
    public Map<String, Object> search(Pageable pageable, UserSearchVO usv, HttpServletRequest request) {
        /*Specification<UserPO> specification = new Specification<UserPO>() {
            @Override
            public Predicate toPredicate(Root<UserPO> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                Predicate predicate = null;
                Expression<String> namePath = null;

                List<String> citycodelist = null;
                List<Integer> personidlist = null;
                List<Integer> useridlist = null;
                List<Integer> useridlist0 = null;
                List<Integer> orgidlist = null;
                if (!FormUtils.isEmpty(usv.getSearchCity())) {
                    citycodelist = city.findByCitynameContaining(usv.getSearchCity()).stream().map(CityPO::getCitycode).collect(toList());
                }
                if (!FormUtils.isEmpty(usv.getSearchPhonenumber())) {
                    personidlist = pd.findByPhonenumberContaining(usv.getSearchPhonenumber()).stream().map(PersonPO::getPersonid).collect(toList());
                }
                if (!FormUtils.isEmpty(usv.getSearchGatewayid())) {
                    List<String> gatewayidlist = gateway.findByDeviceidContaining(usv.getSearchGatewayid()).stream().map(GatewayPO::getDeviceid).collect(toList());
                    useridlist = gd.findByDeviceidIn(gatewayidlist).stream().map(GatewayUserPO::getUserid).collect(toList());
                }
                if (!FormUtils.isEmpty(usv.getSearchSerialnumber())) {
                    List<Integer> phonecardidlist = pcard.findBySerialnumberContaining(usv.getSearchSerialnumber()).stream().map(PhonecardPO::getPhonecardid).collect(toList());
                    useridlist0 = pcud.findByPhonecardidIn(phonecardidlist).stream().map(PhonecardUserPO::getUserid).collect(toList());
                }
                if (!FormUtils.isEmpty(usv.getSearchDealername())) {
                    orgidlist = od.findByOrgtypeAndNameContaining(Constants.ORGTYPE_SUPPLIER, usv.getSearchName()).stream().map(OrganizationPO::getOrganizationid).collect(toList());
                }
                if (citycodelist.size() == 0 || personidlist.size() == 0 || useridlist.size() == 0 || useridlist0.size() == 0 || orgidlist.size() == 0) {
                    return null;
                } else {
                    if (usv.getSearchName() != null && usv.getSearchName() != "") {
                        namePath = root.get("name");
                        predicates.add(builder.like(namePath, "%" + usv.getSearchName() + "%"));
                    }
                    if (citycodelist != null) {
                        namePath = root.get("citycode");
                        predicates.add(namePath.in(citycodelist));
                    }
                    if (personidlist != null) {
                        namePath = root.get("personid");
//                        predicate = builder.in(namePath.in(personidlist));
                        predicates.add(namePath.in(personidlist));
                    }
                    if (useridlist != null) {
                        namePath = root.get("userid");
                        predicates.add(namePath.in(useridlist));
                    }
                    if (useridlist0 != null) {
                        namePath = root.get("userid");
//                        predicate = builder.in(namePath.in(useridlist0));
                        predicates.add(namePath.in(useridlist0));
                    }
                    if (orgidlist != null) {
                        namePath = root.get("organizationid");
                        predicates.add(namePath.in(orgidlist));
                    }
                    //如果角色是安装员的话,返回user中安装员id和他一样的
                    EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
                    List<EmployeeRolePO> erpolist = erd.findByEmployeeid(emp.getEmployeeid());
                    if (erpolist == null) {
                        return null;
                    }
                    if (erpolist.contains(Constants.ROLE_AMETA_ADMI)) {

                    } else if (erpolist.contains(Constants.ROLE_SUPPLIER_ADMIN)) {
                        namePath = root.get("organizationid");
                        predicates.add(namePath.in(emp.getOrganizationid()));
                    } else if (erpolist.contains(Constants.ROLE_INSTALLER_ADMILN)) {
                        namePath = root.get("installerorgid");
                        predicates.add(namePath.in(emp.getOrganizationid()));
                    } else if (erpolist.contains(Constants.ROLE_INSTALLER)) {
                        namePath = root.get("intallerid");
                        predicates.add(namePath.in(emp.getEmployeeid()));
                    }
                    query.where(predicates.toArray(new Predicate[predicates.size()]));
                    return query.getRestriction();
                }
            }
        };
//        System.out.println(ud.findAll(specification));
        Map<String, Object> map = new HashMap<>();
        if (specification == null) {
            map.put("rows", Collections.EMPTY_LIST);
            map.put("total", 0);
            return map;
        }
        Page<UserPO> list = ud.findAll(specification, pageable);
        System.out.println(list);
        List<UserInfoListVO> listvo = new ArrayList<>();
        setProperties(list, listvo);
        map.put("rows", listvo);
        map.put("total", ud.count(specification));
        return map;*/
        List<UserPO> u0 = Collections.EMPTY_LIST;
        List<UserPO> u1 = Collections.EMPTY_LIST;
        List<UserPO> u2 = Collections.EMPTY_LIST;
        List<UserPO> u3 = Collections.EMPTY_LIST;
        List<UserPO> u4 = Collections.EMPTY_LIST;
        List<UserPO> u5 = Collections.EMPTY_LIST;
        Set<List<UserPO>> set = new HashSet<>();
        if (!FormUtils.isEmpty(usv.getSearchCity())) {
            List<String> citycodelist = city.findByCitynameContaining(usv.getSearchCity()).stream().map(CityPO::getCitycode).collect(toList());
            u0 = ud.findByCitycodeIn(citycodelist);
            set.add(u0);
        }
        if (!FormUtils.isEmpty(usv.getSearchPhonenumber())) {
            List<Integer> personidlist = pd.findByPhonenumberContaining(usv.getSearchPhonenumber()).stream().map(PersonPO::getPersonid).collect(toList());
            u1 = ud.findByPersonidIn(personidlist);
            set.add(u1);
        }
        if (!FormUtils.isEmpty(usv.getSearchGatewayid())) {
            List<String> gatewayidlist = gateway.findByDeviceidContaining(usv.getSearchGatewayid()).stream().map(GatewayPO::getDeviceid).collect(toList());
            List<Integer> useridlist = gd.findByDeviceidIn(gatewayidlist).stream().map(GatewayUserPO::getUserid).collect(toList());
            u2 = ud.findByUseridIn(useridlist);
            set.add(u2);
        }
        if (!FormUtils.isEmpty(usv.getSearchSerialnumber())) {
            List<Integer> phonecardidlist = pcard.findBySerialnumberContaining(usv.getSearchSerialnumber()).stream().map(PhonecardPO::getPhonecardid).collect(toList());
            List<Integer> useridlist0 = pcud.findByPhonecardidIn(phonecardidlist).stream().map(PhonecardUserPO::getUserid).collect(toList());
            u3 = ud.findByUseridIn(useridlist0);
            set.add(u3);
        }
        if (!FormUtils.isEmpty(usv.getSearchDealername())) {
            List<Integer> orgidlist = od.findByNameContaining(usv.getSearchDealername()).stream().map(OrganizationPO::getOrganizationid).collect(toList());
            u4 = ud.findByOrganizationidIn(orgidlist);
            set.add(u4);
        }
        if (usv.getSearchName() != null && usv.getSearchName() != "") {
            u5 = ud.findByNameContaining(usv.getSearchName());
            set.add(u5);
        }

        set.remove(null);
        Iterator<List<UserPO>> iterator = set.iterator();
        List<UserPO> ux = iterator.next();
        while (iterator.hasNext()) {
            ux.retainAll(iterator.next());
        }
        List<Integer> ids = ux.stream().map(UserPO::getUserid).collect(toList());
        Map<String, Object> map = new HashMap<>();
        if (ids.isEmpty()) {
            map.put("rows", Collections.EMPTY_LIST);
            map.put("total", 0);
            return map;
        }
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgtype = od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
        Page<UserPO> listpage = null;
        List<EmployeeRolePO> emprolelist = erd.findByEmployeeid(emp.getEmployeeid());
        if (emprolelist.contains(Constants.ROLE_INSTALLER)) {
            //登录的是安装员
            listpage = ud.findByUseridInAndInstallerid(ids, emp.getEmployeeid(), pageable);
            map.put("total", ud.countByUseridInAndInstallerid(ids, emp.getEmployeeid()));
        } else if (orgtype == Constants.ORGTYPE_AMETA) {
            listpage = ud.findByUseridIn(ids, pageable);
            map.put("total", ud.countByUseridIn(ids));
        } else if (orgtype == Constants.ORGTYPE_SUPPLIER) {
            listpage = ud.findByUseridInAndOrganizationid(ids, emp.getOrganizationid(), pageable);
            map.put("total", ud.countByUseridInAndOrganizationid(ids, emp.getOrganizationid()));
        } else/* if (orgtype == Constants.ORGTYPE_INSTALLER) */{
            listpage = ud.findByUseridInAndInstallerorgid(ids, emp.getOrganizationid(), pageable);
            map.put("total", ud.countByUseridInAndInstallerorgid(ids,emp.getOrganizationid()));
        }
//        System.out.println(ux);
        List<UserInfoListVO> listVO = new ArrayList<>();
        setProperties(listpage, listVO);
        map.put("rows", listVO);
        return map;
    }

    private void setProperties(Page<UserPO> list, List<UserInfoListVO> listvo) {
        list.forEach(u -> {
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
            listvo.add(user);
        });
    }
}
