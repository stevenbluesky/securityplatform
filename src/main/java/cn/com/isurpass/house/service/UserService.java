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
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
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
    @Autowired
    private PhonecardDAO phonecardDAO;
    @Autowired
    private EmployeeDAO empDAO;
    @Autowired
    private CodeLogDAO codelogDAO;
    @Transactional(rollbackFor = Exception.class)
    public void add(UserAddVO u, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) SecurityUtils.getSubject().getPrincipal();

        /*if (!FormUtils.checkNUll(u.getDeviceid()) || !FormUtils.checkNUll(u.getSerialnumber())) {
            throw new RuntimeException("-107");
        }*/
        if(ud.findByAppaccount(u.getAppaccount())!=null){//检查app账号是否已被别人绑定
            throw new RuntimeException("-121");
        }
        if (gd.findByDeviceid(u.getDeviceid()).size() != 0) {
            throw new RuntimeException("-108");
        }
        if (gbd.findByDeviceid(u.getDeviceid()) == null) {//网关不存在
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
        if(StringUtils.isEmpty(u.getAppaccount())){
            user.setAppaccount(null);
        }else{
            user.setAppaccount(u.getAppaccount());
        }
        if(u.getFirstname()!=null){
            boolean b = checkChar(u.getFirstname());
            if(b){//是英文名
                user.setName(u.getFirstname() +" "+ u.getLastname());
            }else{
                user.setName(u.getLastname()+u.getFirstname());
            }
        }
        if (FormUtils.isEmpty(u.getCity())) {
//            user.setCitycode(city.findByCityid(u.getCity()).getCitycode());
            throw new RuntimeException("-100");
        }
        EmployeeParentOrgIdVO empp = emps.findEmpParentOrgid(emp);
        user.setOrganizationid(-1);
        user.setInstallerorgid(1);
        user.setInstallerid(1);
        user.setStatus(1);
        user.setUsercode("usercode");
        if (empp != null) {
            user.setOrganizationid(empp.getOrganizationid());
            user.setInstallerorgid(empp.getInstallerorgid());
            user.setInstallerid(empp.getInstallerid());
        }
        user.setCodepostfix(u.getCodepostfix());
        //user.setUsercode("ameta" + u.getPhonenumber());

        AddressPO address = new AddressPO();
        Integer addressid = null;
        address.setDetailaddress(u.getDetailaddress());
        if (u.getCountry() != null) {
            address.setCountry(country.findByCountryid(u.getCountry()).getCountryname());
        }
        if (u.getProvince() != null) {
            address.setProvince(province.findByProvinceid(u.getProvince()).getProvincename());
        }
        if (u.getCity() != null) {
            CityPO c = city.findByCityid(u.getCity());
            address.setCity(c.getCityname());
            user.setCitycode(c.getCitycode());
        }
        if (!FormUtils.isEmpty(address)) {
            addressid = ad.save(address).getAddressid();
        }
        PersonPO person = new PersonPO();
        person.setFirstname(u.getFirstname());
        person.setLastname(u.getLastname());
        if(u.getFirstname()!=null){
            boolean b = checkChar(u.getFirstname());
            if(b){//是英文名
                person.setName(u.getFirstname() +" "+ u.getLastname());
            }else{
                person.setName(u.getLastname()+u.getFirstname());
            }
        }
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
    public static boolean checkChar(String   fstrData) {
        char   c   =   fstrData.charAt(0);
        if(((c>='a'&&c<='z')   ||   (c>='A'&&c<='Z'))) {
            return   true;
        }else{
            return   false;
        }
    }
    @Transactional(readOnly = true)
    public Map<String, Object> listUserInfo(Pageable pageable, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgtype = od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
        Integer count = 0;
        List<EmployeeRolePO> rolelist = erd.findByEmployeeid(emp.getEmployeeid());
        if(rolelist!= null &&rolelist.size()==1&&rolelist.get(0).getRoleid()==4){
            Page<UserPO> userList = ud.findByInstallerid(emp.getEmployeeid(), pageable);
            count = ud.countByInstallerid(emp.getEmployeeid());
            return listUserInfo0(pageable, userList, count);
        }
        if (orgtype.equals(Constants.ORGTYPE_AMETA)) {
            Page<UserPO> userList = ud.findAll(pageable);
            count = (int) ud.count();
            return listUserInfo0(pageable, userList, count);
        }
        if (orgtype.equals(Constants.ORGTYPE_INSTALLER)) {
            Page<UserPO> userList = ud.findByInstallerorgid(emp.getOrganizationid(), pageable);
            count = ud.countByInstallerorgid(emp.getOrganizationid());
            return listUserInfo0(pageable, userList, count);
        }
        if (orgtype.equals(Constants.ORGTYPE_SUPPLIER)) {
            Page<UserPO> userList = ud.findByOrganizationid(emp.getOrganizationid(), pageable);
            count = ud.countByOrganizationid(emp.getOrganizationid());
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
    @Transactional
    public void toggleUserStatus0(String hope, Object[] ids, HttpServletRequest request) throws MyArgumentNullException {
        if ("unsuspence".equals(hope)) {
            for (Object id : ids) {
                toggleUserStatus(Integer.valueOf(id.toString().replace( ",", "")), Constants.STATUS_NORMAL, request);
            }
        } else if ("suspence".equals(hope)) {
            for (Object id : ids) {
                toggleUserStatus(Integer.valueOf(id.toString().replace( ",", "")), Constants.STATUS_SUSPENCED, request);
            }
        }else if ("synchronous".equals(hope)) {//同步用户的服务商
            for (Object id : ids) {
                UserPO byUserid = ud.findByUserid(Integer.parseInt(id.toString().replace(",", "")));
                Integer installerorgid = byUserid.getInstallerorgid();
                OrganizationPO byOrganizationid = od.findByOrganizationid(installerorgid);
                Integer parentorgid = byOrganizationid.getParentorgid();
                if(parentorgid!=null) {
                    byUserid.setInstallerorgid(parentorgid);
                }
                ud.save(byUserid);
            }
        }else if("delete".equals(hope)){
            for (Object id : ids) {
                //执行删除用户关联关系，及删除用户单表记录操作
                gd.deleteByUserid(Integer.valueOf(id.toString().replace( ",", "")));
                pcud.deleteByUserid(Integer.valueOf(id.toString().replace( ",", "")));
                ud.deleteByUserid(Integer.valueOf(id.toString().replace( ",", "")));
            }
        }
    }
    @Transactional(rollbackFor = Exception.class)
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

    /**
     * 判断当前用户是否有权限操作此员工
     *
     * @param userid
     * @param request
     * @return
     */
    private boolean hasProvilege(Integer userid, HttpServletRequest request) {
        UserPO user = ud.findByUserid(userid);
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgtype = od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
        if (orgtype.equals(Constants.ORGTYPE_INSTALLER) && emp.getOrganizationid().equals(user.getInstallerorgid())) {
            return true;
        }
        if (orgtype.equals(Constants.ORGTYPE_SUPPLIER) && emp.getOrganizationid().equals(user.getOrganizationid())) {
            return true;
        }
        if (orgtype.equals(Constants.ORGTYPE_AMETA)) {
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> search(Pageable pageable, UserSearchVO usv, HttpServletRequest request) {
        List<UserPO> u0 = Collections.EMPTY_LIST;
        List<UserPO> u1 = Collections.EMPTY_LIST;
        List<UserPO> u2 = Collections.EMPTY_LIST;
        List<UserPO> u3 = Collections.EMPTY_LIST;
        List<UserPO> u4 = Collections.EMPTY_LIST;
        List<UserPO> u5 = Collections.EMPTY_LIST;
        List<UserPO> u6 = Collections.EMPTY_LIST;
        Set<List<UserPO>> set = new HashSet<>();
        if (!FormUtils.isEmpty(usv.getSearchCity())) {
            List<String> citycodelist = city.findByCitynameContaining(usv.getSearchCity()).stream().map(CityPO::getCitycode).collect(toList());
            u0 = ud.findByCitycodeIn(citycodelist);
            set.add(u0);
        }
        /*if (!FormUtils.isEmpty(usv.getSearchPhonenumber())) {
            List<Integer> personidlist = pd.findByPhonenumberContaining(usv.getSearchPhonenumber()).stream().map(PersonPO::getPersonid).collect(toList());
            u1 = ud.findByPersonidIn(personidlist);
            set.add(u1);
        }*/
        if (usv.getSearchAppAccount() != null && usv.getSearchAppAccount() != "") {
            u1 = ud.findByAppaccountContaining(usv.getSearchAppAccount());
            //u6 = ud.findByCodepostfixContaining(usv.getSearchCode());
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
        if (usv.getSearchCode() != null && usv.getSearchCode() != "") {
            u6 = ud.findByCodepostfixContaining(usv.getSearchCode());
            set.add(u6);
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
//        List<EmployeeRolePO> emprolelist = erd.findByEmployeeid(emp.getEmployeeid());
        List<Integer> emprolelist = erd.findByEmployeeid(emp.getEmployeeid()).stream().map(EmployeeRolePO::getRoleid).collect(toList());
        if (emprolelist.contains(Constants.ROLE_INSTALLER)) {
            //登录的是安装员
            listpage = ud.findByUseridInAndInstallerid(ids, emp.getEmployeeid(), pageable);
            map.put("total", ud.countByUseridInAndInstallerid(ids, emp.getEmployeeid()));
        } else if (orgtype.equals(Constants.ORGTYPE_AMETA)) {
            listpage = ud.findByUseridIn(ids, pageable);
            map.put("total", ud.countByUseridIn(ids));
        } else if (orgtype.equals(Constants.ORGTYPE_SUPPLIER)) {
            listpage = ud.findByUseridInAndOrganizationid(ids, emp.getOrganizationid(), pageable);
            map.put("total", ud.countByUseridInAndOrganizationid(ids, emp.getOrganizationid()));
        } else/* if (orgtype == Constants.ORGTYPE_INSTALLER) */ {
            listpage = ud.findByUseridInAndInstallerorgid(ids, emp.getOrganizationid(), pageable);
            map.put("total", ud.countByUseridInAndInstallerorgid(ids, emp.getOrganizationid()));
        }
//        System.out.println(ux);
        List<UserInfoListVO> listVO = new ArrayList<>();
        setProperties(listpage, listVO);
        map.put("rows", listVO);
        return map;
    }

    private void setProperties(Page<UserPO> list, List<UserInfoListVO> listvo) {
        list.forEach(u -> {
            UserInfoListVO user = new UserInfoListVO();
            PersonPO personPO = pd.findByPersonid(u.getPersonid());
            CityPO citypo = city.findByCitycode(u.getCitycode());
            OrganizationPO orgpo = od.findByOrganizationid(u.getOrganizationid());
            List<GatewayUserPO> byUserid = gd.findByUserid(u.getUserid());
            if(byUserid.size()>0){
                GatewayUserPO gatewayUserPO = byUserid.get(0);
                user.setDeviceid(gatewayUserPO.getDeviceid());
            }
            PhonecardUserPO phonecarduser = pcud.findByUserid(u.getUserid());

            user.setUserid(u.getUserid());
            user.setName(u.getName());
            user.setStatus(u.getStatus());
            user.setAppaccount(u.getAppaccount());
//            user.setPhonenumber(personPO == null ? null : personPO.getPhonenumber());
            user.setCity(citypo == null ? null : citypo.getCityname());
            user.setSuppliername(orgpo == null ? null : orgpo.getName());
            user.setCodepostfix(u.getCodepostfix());
            if(phonecarduser!=null) {
                PhonecardPO byPhonecardid = pcard.findByPhonecardid(phonecarduser.getPhonecardid());
                if(byPhonecardid!=null){
                    user.setSerialnumber(byPhonecardid.getSerialnumber());
                }
            }
            listvo.add(user);
        });
    }

    /**
     * 查询添加用户时所有表单信息
     */
    @Transactional(readOnly = true)
    public UserAddVO queryUserAddInfo(HttpServletRequest request, Integer userid) {
        UserPO byUserid = ud.findByUserid(userid);
        if (byUserid == null) {
            return null;
        }
        UserAddVO userAddVO = new UserAddVO();
        userAddVO.setUserid(userid);
        userAddVO.setAppaccount(byUserid.getAppaccount());
        userAddVO.setCodepostfix(byUserid.getCodepostfix());
        OrganizationPO byOrganizationid = od.findByOrganizationid(byUserid.getOrganizationid());
        if(byOrganizationid!=null){
            userAddVO.setServiceprovider(byOrganizationid.getName());
        }
        userAddVO.setInstallerorg(od.findByOrganizationid(byUserid.getInstallerorgid()).getName());
        userAddVO.setInstaller(empDAO.findByEmployeeid(byUserid.getInstallerid()).getLoginname());
        getUserPersonInfo(byUserid, userAddVO);
        getUserGatewayIdSIM(userid, userAddVO);
        return userAddVO;
    }

    private void getUserGatewayIdSIM(Integer userid, UserAddVO userAddVO) {
        List<GatewayUserPO> gatewayuserlist = gd.findByUserid(userid);
        if (gatewayuserlist.size()>0&&gatewayuserlist.get(0)!=null) {
            //目前只考虑用户只有一个网关,直接取第一个
            userAddVO.setDeviceid(gatewayuserlist.get(0).getDeviceid());
            userAddVO.setGatewaystatus(gateway.findByDeviceid(gatewayuserlist.get(0).getDeviceid()).getStatus());
        }
        PhonecardUserPO byUserid1 = pcud.findByUserid(userid);
        if (byUserid1 == null) {
            return;
        }
        PhonecardPO byPhonecardid = pcard.findByPhonecardid(byUserid1.getPhonecardid());
        if (byPhonecardid == null) {
            return;
        }
        userAddVO.setSerialnumber(byPhonecardid.getSerialnumber());
        userAddVO.setStatus(byPhonecardid.getStatus());
        userAddVO.setPhonecardid(byUserid1.getPhonecardid());
    }

    private void getUserPersonInfo(UserPO byUserid, UserAddVO userAddVO) {
        if (byUserid.getPersonid() != null) {
            PersonPO byPersonid = pd.findByPersonid(byUserid.getPersonid());
            if (byPersonid != null) {
                userAddVO.setSsn(byPersonid.getSsn());
                userAddVO.setGender(byPersonid.getGender());
                userAddVO.setEmail(byPersonid.getEmail());
                userAddVO.setFax(byPersonid.getFax());
                userAddVO.setFirstname(byPersonid.getFirstname());
                userAddVO.setLastname(byPersonid.getLastname());
                userAddVO.setPhonenumber(byPersonid.getPhonenumber());
                if (byPersonid.getAddressid() != null) {
                    AddressPO byAddressid = ad.findByAddressid(byPersonid.getAddressid());
                    if (byAddressid != null) {
                        userAddVO.setCountryname(byAddressid.getCountry());
                        userAddVO.setProvincename(byAddressid.getProvince());
                        userAddVO.setCityname(byAddressid.getCity());
                        userAddVO.setDetailaddress(byAddressid.getDetailaddress());
                    }
                }
            }
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void modify(UserAddVO user) {
        if (user.getUserid() == null || ud.findByUserid(user.getUserid()) == null) {
            throw new RuntimeException("User not Exist");
        }
        UserPO preUser = ud.findByUserid(user.getUserid());
        PersonPO person = pd.findByPersonid(preUser.getPersonid());
        AddressPO address = ad.findByAddressid(person.getAddressid());

        person.setFirstname(user.getFirstname());
        person.setLastname(user.getLastname());
        person.setSsn(user.getSsn());
        person.setGender(user.getGender());
        person.setPhonenumber(user.getPhonenumber());
        person.setEmail(user.getEmail());
        person.setFax(user.getFax());
        address.setDetailaddress(user.getDetailaddress());
        if (user.getCountry() != null) {
            address.setCountry(country.findByCountryid(user.getCountry()).getCountryname());
        }
        if (user.getProvince() != null) {
            address.setProvince(province.findByProvinceid(user.getProvince()).getProvincename());
        }
        if (user.getCity() != null) {
            CityPO c = city.findByCityid(user.getCity());
            address.setCity(c.getCityname());
            preUser.setCitycode(c.getCitycode());
        }
        ad.save(address);
        pd.save(person);

        preUser.setName(user.getFirstname() + user.getLastname());
        preUser.setLoginname(user.getPhonenumber());
        preUser.setCodepostfix(user.getCodepostfix());

        /**************************************************************************************************/
        /**判断网关
         * gd   gatewayuserdao
         * pcud phonecarddao
         * gateway gatewaydao
         * **/
        List<GatewayUserPO> gatewayuseruser = gd.findByUserid(preUser.getUserid());
        if(!StringUtils.isEmpty(user.getDeviceid())){//网关id有录入信息
            if(gatewayuseruser!=null&&gatewayuseruser.size()>0){//该用户下面还有网关
                String deviceid = gatewayuseruser.get(0).getDeviceid();
                if(deviceid.equals(user.getDeviceid())){
                    //同一个网关，网关未修改
                }else{
                    throw new RuntimeException("-119");//该用户已有网关，请先解绑再来更换网关
                }
            }else{//该用户没有网关了
                List<GatewayUserPO> gatewayuserdevice = gd.findByDeviceid(user.getDeviceid());
                if(gatewayuserdevice!=null&&gatewayuserdevice.size()>0){//该网关被别人绑定了
                    throw new RuntimeException("-108");//网关已被其他用户绑定，请填入正确网关
                }else{
                    if(gateway.findByDeviceid(user.getDeviceid())==null){//网关表没有此网关，不可以绑定
                        throw new RuntimeException("-109");//网关不存在
                    }else{//执行绑定网关操作
                        GatewayUserPO gatewayUserPO = new GatewayUserPO();
                        gatewayUserPO.setUserid(user.getUserid());
                        gatewayUserPO.setDeviceid(user.getDeviceid());
                        gatewayUserPO.setCreatetime(new Date());
                        gd.save(gatewayUserPO);
                    }
                }
            }
        }else{//网关id没有录入数据
            gd.deleteByUserid(preUser.getUserid());
        }

        /**
         * 判断电话卡
         */
        PhonecardUserPO phonecarduseruser = pcud.findByUserid(preUser.getUserid());
        if(!StringUtils.isEmpty(user.getSerialnumber())) {
            if (phonecarduseruser != null) {//该用户下有电话卡未解绑
                if (phonecardDAO.findByPhonecardid(phonecarduseruser.getPhonecardid()).getSerialnumber().equals(user.getSerialnumber())) {
                    //同一张电话卡
                } else {
                    throw new RuntimeException("-120");//该用户已有电话卡，请先解绑再来更换电话卡
                }
            } else {
                if (phonecardDAO.findBySerialnumber(user.getSerialnumber()) == null) {//电话卡未录入
                    throw new RuntimeException("-111");
                }
                PhonecardUserPO byPhonecardid = pcud.findByPhonecardid(phonecardDAO.findBySerialnumber(user.getSerialnumber()).getPhonecardid());
                if (byPhonecardid != null) {//该电话卡已被别人绑定
                    throw new RuntimeException("-110");
                }
                PhonecardUserPO pu = new PhonecardUserPO();
                pu.setUserid(user.getUserid());
                pu.setPhonecardid(phonecardDAO.findBySerialnumber(user.getSerialnumber()).getPhonecardid());
                pu.setCreatetime(new Date());
                pcud.save(pu);
            }
        }else{
            pcud.deleteByUserid(preUser.getUserid());
        }
        /****/
/*        if (gatewayuseruser != null) {
            if (user.getDeviceid() == null && gatewayuseruser != null) {
                //之前绑定了,现在没填,然后删除网关？
                List<GatewayUserPO> byUserid = gatewayuseruser;
                gd.deleteAll(byUserid);
            }
            if (!(gd.findByDeviceid(user.getDeviceid()).stream().map(GatewayUserPO::getUserid).collect(toList()).contains(preUser.getUserid()))) {
                //绑定的网关和当前输入的网关不相同, 说明在修改网关
                gd.delete(gd.findByUserid(user.getUserid()).get(0));
                if (gd.findByDeviceid(user.getDeviceid()).size() != 0) {
                    throw new RuntimeException("-108");
                }
                if (gbd.findByDeviceid(user.getDeviceid()) == null) {//网关不存在
                    throw new RuntimeException("-109");
                }
                GatewayUserPO gatewayUserPO = new GatewayUserPO();
                gatewayUserPO.setUserid(user.getUserid());
                gatewayUserPO.setDeviceid(user.getDeviceid());
                gd.save(gatewayUserPO);
            }
        }*/
/*        if (pcud.findByUserid(preUser.getUserid()) != null) {
            if (user.getSerialnumber() == null && pcud.findByUserid(preUser.getUserid()) != null) {
                PhonecardUserPO byUserid = pcud.findByUserid(preUser.getUserid());
                pcud.delete(byUserid);
            } else {
                PhonecardPO bySerialnumber = pcard.findBySerialnumber(user.getSerialnumber());
                Integer phonecardid = bySerialnumber.getPhonecardid();
                if (!(pcud.findByUserid(user.getUserid()).getPhonecardid() != phonecardid)) {
                    if (bySerialnumber == null) {
                        throw new RuntimeException("-111");
                    }
                    if (pcud.findByPhonecardid(bySerialnumber.getPhonecardid()) != null) {
                        throw new RuntimeException("-110");
                    }
                    PhonecardUserPO pu = new PhonecardUserPO();
                    pu.setUserid(user.getUserid());
                    pu.setPhonecardid(phonecardid);
                    pu.setCreatetime(new Date());
                    pcud.save(pu);
                }
            }
        }*/
        /**************************************************************************************************/

//        preUser.setUsercode("");
        ud.save(preUser);
    }
    @Transactional(rollbackFor = Exception.class)
    public void unbundling(String userid, String serialnumber) {
        //phonecardDAO.deleteBySerialnumber(serialnumber);删除操作留到电话卡页面，此处只是解绑操作
        userid = userid.replace( ",", "");
        pcud.deleteByUserid(Integer.parseInt(userid.replace( ",", "")));
    }
    @Transactional(rollbackFor = Exception.class)
    public void unbundlinggateway(String mydata) {
        gd.deleteByDeviceid(mydata);
    }
    @Transactional(readOnly = true)
    public void findPro(Integer organizationid) {
        OrganizationPO byOrganizationid = od.findByOrganizationid(organizationid);
        if(Constants.ORGTYPE_AMETA!=byOrganizationid.getOrgtype()){
            throw new RuntimeException("You have no access!");
        }
    }
    @Transactional(readOnly = true)
    public void findTypeUserPro( Integer organizationid,Integer employeeid) {
        OrganizationPO byOrganizationid = od.findByOrganizationid(organizationid);
        if(Constants.ORGTYPE_INSTALLER!=byOrganizationid.getOrgtype()){//不是安装商机构下的员工，不能打开该页面
            throw new RuntimeException("Please login as operator to create user!");
        }
        List<EmployeeRolePO> byEmployeeid = erd.findByEmployeeid(employeeid);
        byEmployeeid.get(0).getRoleid();
        List<Integer> list = new ArrayList<Integer>();
        for(EmployeeRolePO e:byEmployeeid){
            list.add(e.getRoleid());
        }
        if(StringUtils.isEmpty(empDAO.findByEmployeeid(employeeid).getCode())){
            throw new RuntimeException("Please login as operator to create user!");//需要有安装员代码
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public String createUserCode(Integer employeeid, Integer organizationid) {
        OrganizationPO org = od.findByOrganizationid(organizationid);
        Integer parentorgid = org.getParentorgid();
        //List<OrganizationPO> parentorg = od.findByParentorgid(parentorgid);
        OrganizationPO parentorg = od.findByOrganizationid(parentorgid);
        /**
         * (多伦多)(A报警中心)(安装商)(子安装商)(终端用户)
           (501)   (101)      ((10100)(101)     (55555)
         */
        String citycode = org.getCitycode();
        String Acode ="";
        if(parentorg!=null){
            Acode = parentorg.getCode();
        }else{
            Acode = "100";
        }
        String Bcode = org.getCode();
        EmployeePO emp = empDAO.findByEmployeeid(employeeid);
        String Ccode = emp.getCode();
        //生成、获取终端用户部分Code
        int Dcode;
        String id ;
        String precode = citycode+Acode+Bcode+Ccode;
        CodeLog codefromdb = codelogDAO.findCodeLog(precode);
        CodeLog codelog = new CodeLog();
        if(codefromdb==null){
            Dcode = 10001;
            id = precode;
        }else{
            Dcode = codefromdb.getLastcode() + 1;
            id = codefromdb.getId();
        }
        codelog.setId(id);
        codelog.setLastcode(Dcode);
        codelogDAO.save(codelog);
        return precode+Dcode;
    }

    @Transactional(readOnly = true)
    public boolean validCode(String appaccount) {
        UserPO user = ud.findByAppaccount(appaccount);
        if (user == null)
            return true;
        return false;
    }

    /**
     * 根据安装员的机构id拿到服务商的信息
     * @param organizationid
     * @return
     */
    @Transactional(readOnly = true)
    public String findSupName(Integer organizationid) {
        OrganizationPO org = od.findByOrganizationid(organizationid);
        OrganizationPO byParentorgid = od.findByOrganizationid(org.getParentorgid());
        if(byParentorgid!=null){
            return byParentorgid.getName();
        }else{
            return "NONE";
        }
    }
    @Transactional(readOnly = true)
    public String findInsName(Integer organizationid) {
        OrganizationPO org = od.findByOrganizationid(organizationid);
        return org.getName();
    }
    @Transactional(readOnly = true)
    public GatewayPO fillGateway(String appaccount) {
        //GatewayPO gatewayPO = gateway.findByAppaccount(appaccount);
        List<GatewayPO> gatewaylist = gateway.findByAppaccount(appaccount);
        if(gatewaylist!=null&&gatewaylist.size()>0){
            return gatewaylist.get(0);
        }
        else return null;
    }
}
