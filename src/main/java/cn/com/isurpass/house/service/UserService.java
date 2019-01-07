package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.request.HttpsUtils;
import cn.com.isurpass.house.util.*;
import cn.com.isurpass.house.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    @Autowired
    private UserDAO ud;
    @Autowired
    private CountryDAO country;
    @Autowired
    private ProvinceDAO province;
    @Autowired
    private CityDAO city;
    @Autowired
    private AddressDAO ad;
    @Autowired
    private PersonDAO pd;
    @Autowired
    private OrganizationDAO od;
    @Autowired
    private PhonecarduserDAO pcud;
    @Autowired
    private PhonecardDAO pcard;
    @Autowired
    private EmployeeService emps;
    @Autowired
    private GatewayuserDAO gd;
    @Autowired
    private GatewayBindingDAO gbd;
    @Autowired
    private EmployeeroleDAO erd;
    @Autowired
    private GatewayDAO gateway;
    @Autowired
    private OrganizationService os;
    @Autowired
    private PhonecardDAO phonecardDAO;
    @Autowired
    private EmployeeDAO empDAO;
    @Autowired
    private CodeLogDAO codelogDAO;
    @Autowired
    private SupplierCodeLogDAO supcodeDAO;
    @Autowired
    private GatewayPhonecardDAO gpDAO;
    private static Log log  = LogFactory.getLog(UserService.class);
    @Transactional(rollbackFor = Exception.class)
    public void add(UserAddVO u, HttpServletRequest request) {//531
        EmployeePO emp = (EmployeePO) SecurityUtils.getSubject().getPrincipal();
        String deviceidstring = u.getDeviceid();
        String serialnumberstring = u.getSerialnumber();
        String[] devicearray = deviceidstring.split(",");
        String[] serialnumberarr = serialnumberstring.split(",");
        String t = serialnumberstring;
        int count = 0;
        int index = 0;
        while(((index = t.indexOf(",")) != -1)){
            t = t.substring(index+1);
            count++;
        }
        String [] serialnumberarray = new String[count+1];
        System.arraycopy(serialnumberarr,0,serialnumberarray,0,serialnumberarr.length);
        if(!StringUtils.isEmpty(u.getAppaccount())&&ud.findByAppaccount(u.getAppaccount())!=null){//检查app账号是否已被别人绑定
            throw new RuntimeException("-121");
        }
        for(int i=0;i<devicearray.length;i++) {
            if (gd.findByDeviceid(devicearray[i]).size() != 0) {//网关已跟用户绑定
                throw new RuntimeException("-108");
            }
            if (gbd.findByDeviceid(devicearray[i]) == null) {//网关不存在
                throw new RuntimeException("-109");
            }
        }
        for(int i=0;i<serialnumberarray.length&&!StringUtils.isEmpty(serialnumberarray[i]);i++) {
            PhonecardPO pcardpo = pcard.findBySerialnumber(serialnumberarray[i]);
            if(!StringUtils.isEmpty(serialnumberarray[i])) {
                if (pcardpo == null) {
                    throw new RuntimeException("-111");
                }
                if (pcud.findByPhonecardid(pcardpo.getPhonecardid()) != null) {
                    throw new RuntimeException("-110");
                }
            }
        }
        for(int i = 0;i < devicearray.length-1;i++){ //循环开始元素
            for(int j = i + 1;j < devicearray.length;j++){ //循环后续所有元素
                if(devicearray[i]!=null&&!devicearray[i].equals("")&&devicearray[i].equals(devicearray[j])){
                    throw new RuntimeException("-123");
                }
            }
        }
        for(int i = 0;i < serialnumberarray.length-1;i++){ //循环开始元素
            for(int j = i + 1;j < serialnumberarray.length;j++){ //循环后续所有元素
                if(serialnumberarray[i]!=null&&!serialnumberarray[i].equals("")&&serialnumberarray[i].equals(serialnumberarray[j])){
                    throw new RuntimeException("-124");
                }
            }
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
            if(b){//名称是字母
                user.setName(u.getFirstname()+" "+ u.getLastname());
            }else{
                user.setName(u.getFirstname()+u.getLastname());
            }
        }
        EmployeeParentOrgIdVO empp = emps.findEmpParentOrgid(emp);
        user.setOrganizationid(0);
        user.setInstallerorgid(1);
        user.setInstallerid(1);
        user.setStatus(1);

        if (empp != null) {
            user.setOrganizationid(empp.getOrganizationid());
            user.setInstallerorgid(empp.getInstallerorgid());
            user.setInstallerid(empp.getInstallerid());
        }
        if(u.getOrganizationid()!=null){
            user.setOrganizationid(u.getOrganizationid());
        }
        user.setCodepostfix(u.getUsercode());
        user.setUsercode(u.getUsercode());
        user.setSupcode(u.getSupcode());
        user.setGroupid(u.getGroupid());
        user.setMonitoringstationid(u.getMonitoringstationid());
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
            if(b){
                person.setName(u.getFirstname() +" "+ u.getLastname());
            }else{
                person.setName(u.getFirstname()+u.getLastname());
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
        for(int i=0;i<devicearray.length;i++){
            GatewayUserPO gateway = new GatewayUserPO();
            gateway.setDeviceid(devicearray[i]);
            gateway.setUserid(userSave.getUserid());
            gateway.setCreatetime(new Date());
            gd.save(gateway);
            GatewayPhonecardPO gp;
            if(StringUtils.isEmpty(serialnumberarray[i])){//TODO貌似不会数组越界
                gp= new GatewayPhonecardPO(devicearray[i],null);
            }else{
                gp = new GatewayPhonecardPO(devicearray[i],serialnumberarray[i]);
            }
            gpDAO.save(gp);
        }

        for(int i=0;i<serialnumberarray.length;i++){
            PhonecardPO pcardpo = pcard.findBySerialnumber(serialnumberarray[i]);
            if(!StringUtils.isEmpty(serialnumberarray[i])){
                PhonecardUserPO pcup = new PhonecardUserPO();
                pcup.setUserid(userSave.getUserid());
                pcup.setPhonecardid(pcardpo.getPhonecardid());
                pcup.setUserid(userSave.getUserid());
                pcup.setCreatetime(new Date());
                pcud.save(pcup);
            }
        }
        OrganizationPO byOrganizationid = null;
        if (empp != null) {
            byOrganizationid = od.findByOrganizationid(empp.getInstallerorgid());
        }
        if(!StringUtils.isEmpty(u.getAppaccount())){
            try {
                HttpsUtils.SetAdvertBanner(u.getAppaccount(), byOrganizationid.getAdvertbannerid(),u.getApphometitle());
            }catch (Exception e){
                log.error("send request setadvertbanner failed");
            }
        }

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
        int pagenumber = (pageable.getPageSize())*(pageable.getPageNumber());
        int pagesize = pageable.getPageSize();
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgtype = od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
        Integer count = 0;
        List<EmployeeRolePO> elist = erd.findByEmployeeid(emp.getEmployeeid());
        List<Integer> emprolelist2 = new ArrayList<>();
        elist.forEach(single ->{emprolelist2.add(single.getRoleid());});
        List<Integer> rolelist = RemoveDuplicate.removeDuplicate(emprolelist2);
        if(rolelist!= null &&rolelist.size()==1&&rolelist.get(0)==4){
            count = ud.countUserByInstallerid(emp.getEmployeeid());
            List<Object[]> userList = ud.findUserByInstallerid(emp.getEmployeeid(),pagenumber,pagesize);
            return transferUserInfo( userList, count);
        }
        if (orgtype.equals(Constants.ORGTYPE_AMETA)) {
            List<Object[]> userList = ud.findUserWithGateway(pagenumber,pagesize);
            count = ud.countUserWithGateway();
            return transferUserInfo(userList,count);
        }
        if (orgtype.equals(Constants.ORGTYPE_INSTALLER)) {
            List<Object[]> userList =ud.findUserByInstallerorg(emp.getOrganizationid(), pagenumber,pagesize);
            count =ud.countUserByInstallerorg(emp.getOrganizationid());
            return transferUserInfo(userList, count);
        }
        if (orgtype.equals(Constants.ORGTYPE_SUPPLIER)) {
            List<Object[]> userList =ud.findUserByOrganizationorg(emp.getOrganizationid(), pagenumber,pagesize);
            count =ud.countUserByOrganizationorg(emp.getOrganizationid());
            return transferUserInfo(userList, count);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<EndUserInfoVO> listUserInfo(HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgtype = od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
        List<EmployeeRolePO> elist = erd.findByEmployeeid(emp.getEmployeeid());
        List<Integer> emprolelist2 = new ArrayList<>();
        elist.forEach(single ->{emprolelist2.add(single.getRoleid());});
        List<Integer> rolelist = RemoveDuplicate.removeDuplicate(emprolelist2);
        if(rolelist!= null &&rolelist.size()==1&&rolelist.get(0)==4){
            List<Object[]> userList = ud.findUserByInstallerid(emp.getEmployeeid());
            return transferUserInfo( userList);
        }
        if (orgtype.equals(Constants.ORGTYPE_AMETA)) {
            List<Object[]> userList = ud.findUserWithGateway();
            return transferUserInfo(userList);
        }
        if (orgtype.equals(Constants.ORGTYPE_INSTALLER)) {
            List<Object[]> userList =ud.findUserByInstallerorg(emp.getOrganizationid());
            return transferUserInfo(userList);
        }
        if (orgtype.equals(Constants.ORGTYPE_SUPPLIER)) {
            List<Object[]> userList =ud.findUserByOrganizationorg(emp.getOrganizationid());
            return transferUserInfo(userList);
        }
        return null;
    }
    public List<EndUserInfoVO> transferUserInfo(List<Object[]> userList) {
        if (userList == null) {
            return null;
        }
        List<EndUserInfoVO> listvo = new ArrayList<>();
        userList.forEach(u -> {
            EndUserInfoVO user = new EndUserInfoVO();
            CityPO citypo = city.findByCitycode((String) u[5]);
            OrganizationPO orgpo = od.findByOrganizationid((Integer) u[6]);
            user.setDeviceid((String) u[0]);
            user.setName((String) u[2]);
            user.setStatus((Integer) u[3]==1?"Normal":"Deactivate");
            user.setAppaccount((String) u[4]);
            user.setCity(citypo == null ? null : citypo.getCityname());
            user.setSuppliername(orgpo == null ? null : orgpo.getName());
            user.setUsercode((String) u[8]);
            user.setSerialnumber((String) u[9]);
            user.setCreatetime((Date)u[10]);
            listvo.add(user);
        });
        return listvo;
    }
    public Map<String, Object> transferUserInfo(List<Object[]> userList, Integer count) {
        if (userList == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        List<UserInfoListVO> listvo = new ArrayList<>();
        userList.forEach(u -> {
            UserInfoListVO user = new UserInfoListVO();
            CityPO citypo = city.findByCitycode((String) u[5]);
            OrganizationPO orgpo = od.findByOrganizationid((Integer) u[6]);
            user.setDeviceid((String) u[0]);
            user.setUserid((Integer) u[1]);
            user.setName((String) u[2]);
            user.setStatus((Integer) u[3]);
            user.setAppaccount((String) u[4]);
            user.setCity(citypo == null ? null : citypo.getCityname());
            user.setSuppliername(orgpo == null ? null : orgpo.getName());
            user.setUsercode((String) u[8]);
            user.setSerialnumber((String) u[9]);
            user.setCreatetime((Date)u[10]);
            listvo.add(user);
        });
        map.put("rows", listvo);
        map.put("total", count);
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
                Integer integer = Integer.valueOf(id.toString().replace(",", ""));
                List<GatewayUserPO> byUserid = gd.findByUserid(integer);
                List<PhonecardUserPO> byUserid1 = pcud.findByUserid(integer);
                gd.deleteByUserid(integer);
                pcud.deleteByUserid(integer);
                ud.deleteByUserid(integer);
                //删除gatewayphonecard表中所有关联数据
                if(byUserid!=null&&byUserid.size()>0){
                    for(GatewayUserPO g:byUserid){
                        gpDAO.deleteByDeviceid(g.getDeviceid());
                    }
                }
                if(byUserid1!=null&&byUserid1.size()>0){
                    for(PhonecardUserPO gu:byUserid1){
                        gpDAO.deleteBySerialnumber(pcard.findByPhonecardid(gu.getPhonecardid()).getSerialnumber());
                    }
                }
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
        int pagenumber = (pageable.getPageSize())*(pageable.getPageNumber());
        int pagesize = pageable.getPageSize();
        List<UserPO> u0 = Collections.EMPTY_LIST;
        List<UserPO> u1 = Collections.EMPTY_LIST;
        List<UserPO> u2 = Collections.EMPTY_LIST;
        List<UserPO> u3 = Collections.EMPTY_LIST;
        List<UserPO> u4 = Collections.EMPTY_LIST;
        List<UserPO> u5 = Collections.EMPTY_LIST;
        List<UserPO> u6 = Collections.EMPTY_LIST;
        List<UserPO> u7 = Collections.EMPTY_LIST;
        List<UserPO> u8 = Collections.EMPTY_LIST;
        List<UserPO> u9 = Collections.EMPTY_LIST;
        Set<List<UserPO>> set = new HashSet<>();
        if (!FormUtils.isEmpty(usv.getSearchCity())) {
            List<String> citycodelist = city.findByCitynameContaining(usv.getSearchCity()).stream().map(CityPO::getCitycode).collect(toList());
            u0 = ud.findByCitycodeIn(citycodelist);
            set.add(u0);
        }
        if (!StringUtils.isEmpty(usv.getSearchAppAccount())) {
            u1 = ud.findByAppaccountContaining(usv.getSearchAppAccount());
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
        if (!StringUtils.isEmpty(usv.getSearchName())) {
            u5 = ud.findByNameContaining(usv.getSearchName());
            set.add(u5);
        }
        if (!StringUtils.isEmpty(usv.getSearchCode())) {
            u6 = ud.findByUsercodeContaining(usv.getSearchCode());
            set.add(u6);
        }
        if (!StringUtils.isEmpty(usv.getStatus())) {
            u7 = ud.findByStatus(usv.getStatus());
            set.add(u7);
        }
        if (!StringUtils.isEmpty(usv.getStarttime())) {
            u8 = ud.findByCreatetimeAfter(usv.getStarttime());
            set.add(u8);
        }
        if (!StringUtils.isEmpty(usv.getEndtime())) {
            u9 = ud.findByCreatetimeBefore(usv.getEndtime());
            set.add(u9);
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
        List<Object[]> listpage = null;
        int total = 0;
//        List<EmployeeRolePO> emprolelist = erd.findByEmployeeid(emp.getEmployeeid());
        List<Integer> emprolelist = erd.findByEmployeeid(emp.getEmployeeid()).stream().map(EmployeeRolePO::getRoleid).collect(toList());
        List<Integer> rolelist = RemoveDuplicate.removeDuplicate(emprolelist);
        if (rolelist!= null &&rolelist.size()==1&&rolelist.get(0)==4) {
            listpage = ud.findByUserlistAndInstalleridS(ids,emp.getEmployeeid(),pagenumber,pagesize);
            total = ud.countByUserlistAndInstalleridS(ids, emp.getEmployeeid());
        } else if (orgtype.equals(Constants.ORGTYPE_AMETA)) {
            listpage = ud.findByUserlistS(ids,pagenumber,pagesize);
            total = ud.countByUserlistS(ids);
        } else if (orgtype.equals(Constants.ORGTYPE_SUPPLIER)) {
            listpage = ud.findByUserlistAndOrganizationS(ids,emp.getOrganizationid(),pagenumber,pagesize);
            total = ud.countByUserlistAndOrganizationS(ids, emp.getOrganizationid());
        } else  {
            listpage = ud.findByUserlistAndInstallerorgS(ids, emp.getOrganizationid(), pagenumber,pagesize);
            total = ud.countByUserlistAndInstallerorgS(ids, emp.getOrganizationid());
        }
        return transferUserInfo(listpage,total);
    }

    @Transactional(readOnly = true)
    public List<EndUserInfoVO> search(UserSearchVO usv, HttpServletRequest request) {
        List<UserPO> u0 = Collections.EMPTY_LIST;
        List<UserPO> u1 = Collections.EMPTY_LIST;
        List<UserPO> u2 = Collections.EMPTY_LIST;
        List<UserPO> u3 = Collections.EMPTY_LIST;
        List<UserPO> u4 = Collections.EMPTY_LIST;
        List<UserPO> u5 = Collections.EMPTY_LIST;
        List<UserPO> u6 = Collections.EMPTY_LIST;
        List<UserPO> u7 = Collections.EMPTY_LIST;
        List<UserPO> u8 = Collections.EMPTY_LIST;
        List<UserPO> u9 = Collections.EMPTY_LIST;
        Set<List<UserPO>> set = new HashSet<>();
        if (!FormUtils.isEmpty(usv.getSearchCity())) {
            List<String> citycodelist = city.findByCitynameContaining(usv.getSearchCity()).stream().map(CityPO::getCitycode).collect(toList());
            u0 = ud.findByCitycodeIn(citycodelist);
            set.add(u0);
        }
        if (!StringUtils.isEmpty(usv.getSearchAppAccount())) {
            u1 = ud.findByAppaccountContaining(usv.getSearchAppAccount());
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
        if (!StringUtils.isEmpty(usv.getSearchName())) {
            u5 = ud.findByNameContaining(usv.getSearchName());
            set.add(u5);
        }
        if (!StringUtils.isEmpty(usv.getSearchCode())) {
            u6 = ud.findByUsercodeContaining(usv.getSearchCode());
            set.add(u6);
        }
        if (!StringUtils.isEmpty(usv.getStatus())) {
            u7 = ud.findByStatus(usv.getStatus());
            set.add(u7);
        }
        if (!StringUtils.isEmpty(usv.getStarttime())) {
            u8 = ud.findByCreatetimeAfter(usv.getStarttime());
            set.add(u8);
        }
        if (!StringUtils.isEmpty(usv.getEndtime())) {
            u9 = ud.findByCreatetimeBefore(usv.getEndtime());
            set.add(u9);
        }
        set.remove(null);
        Iterator<List<UserPO>> iterator = set.iterator();
        List<UserPO> ux = iterator.next();
        while (iterator.hasNext()) {
            ux.retainAll(iterator.next());
        }
        List<Integer> ids = ux.stream().map(UserPO::getUserid).collect(toList());
        if (ids.isEmpty()) {
            return null;
        }
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgtype = od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
        List<Object[]> listpage = null;
        List<Integer> emprolelist = erd.findByEmployeeid(emp.getEmployeeid()).stream().map(EmployeeRolePO::getRoleid).collect(toList());
        List<Integer> rolelist = RemoveDuplicate.removeDuplicate(emprolelist);
        if (rolelist!= null &&rolelist.size()==1&&rolelist.get(0)==4) {
            listpage = ud.findByUserlistAndInstalleridS(ids,emp.getEmployeeid());
        } else if (orgtype.equals(Constants.ORGTYPE_AMETA)) {
            listpage = ud.findByUserlistS(ids);
        } else if (orgtype.equals(Constants.ORGTYPE_SUPPLIER)) {
            listpage = ud.findByUserlistAndOrganizationS(ids,emp.getOrganizationid());
        } else  {
            listpage = ud.findByUserlistAndInstallerorgS(ids, emp.getOrganizationid());
        }
        return transferUserInfo(listpage);
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
        userAddVO.setUsercode(byUserid.getUsercode());
        userAddVO.setCodepostfix(byUserid.getCodepostfix());
        userAddVO.setSupcode(byUserid.getSupcode());
        userAddVO.setGroupid(byUserid.getGroupid());
        OrganizationPO byOrganizationid = od.findByOrganizationid(byUserid.getOrganizationid());
        if(byOrganizationid!=null){
            userAddVO.setOrganizationid(byUserid.getOrganizationid());
            userAddVO.setServiceprovider(byOrganizationid.getName());
        }
        userAddVO.setInstallerorg(od.findByOrganizationid(byUserid.getInstallerorgid()).getName());
        userAddVO.setInstaller(empDAO.findByEmployeeid(byUserid.getInstallerid()).getLoginname());
        getUserPersonInfo(byUserid, userAddVO);
        getUserGatewayIdSIM(userid, userAddVO);
        userAddVO.setMonitoringstationid(byUserid.getMonitoringstationid());
        return userAddVO;
    }

    private void getUserGatewayIdSIM(Integer userid, UserAddVO userAddVO) {
        List<GatewayUserPO> gatewayuserlist = gd.findByUserid(userid);
        //List<PhonecardUserPO> byUserid1 = pcud.findByUserid(userid);
        if (gatewayuserlist.size()<1) {
            return ;
        }

        List<GatewayPhonecardVO> gpvolist = new ArrayList<>();
        for (GatewayUserPO g:gatewayuserlist) {
            GatewayPhonecardVO gpVO = new GatewayPhonecardVO();
            List<Object[]> obj = gpDAO.findGatewayPhonecardByDeviceid(g.getDeviceid());
            gpVO.setDeviceid((String) obj.get(0)[0]);
            gpVO.setGatewaystatus((Integer) obj.get(0)[1]);
            gpVO.setSerialnumber((String) obj.get(0)[2]);
            gpVO.setPhonecardstatus((Integer) obj.get(0)[3]);
            gpVO.setPhonecardid((Integer) obj.get(0)[4]);
            gpvolist.add(gpVO);
        }
        userAddVO.setGpVO(gpvolist);

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
        String deviceidstring = user.getDeviceid();
        String serialnumberstring = user.getSerialnumber();
        String[] devicearray = deviceidstring.split(",");
        String[] serialnumberarr = serialnumberstring.split(",");
        String t = serialnumberstring;
        int count = 0;
        int index = 0;
        while(((index = t.indexOf(",")) != -1)){
            t = t.substring(index+1);
            count++;
        }
        String [] serialnumberarray = new String[count+1];
        System.arraycopy(serialnumberarr,0,serialnumberarray,0,serialnumberarr.length);
        for(int i = 0;i < devicearray.length-1;i++){ //循环开始元素
            for(int j = i + 1;j < devicearray.length;j++){ //循环后续所有元素
                if(devicearray[i]!=null&&!devicearray[i].equals("")&&devicearray[i].equals(devicearray[j])){
                    throw new RuntimeException("-123");
                }
            }
        }
        for(int i = 0;i < serialnumberarray.length-1;i++){ //循环开始元素
            for(int j = i + 1;j < serialnumberarray.length;j++){ //循环后续所有元素
                if(serialnumberarray[i]!=null&&!serialnumberarray[i].equals("")&&serialnumberarray[i].equals(serialnumberarray[j])){
                    throw new RuntimeException("-124");
                }
            }
        }
        UserPO preUser = ud.findByUserid(user.getUserid());
        PersonPO person = pd.findByPersonid(preUser.getPersonid());
        person.setFirstname(user.getFirstname());
        person.setLastname(user.getLastname());
        person.setSsn(user.getSsn());
        person.setGender(user.getGender());
        person.setPhonenumber(user.getPhonenumber());
        person.setEmail(user.getEmail());
        person.setFax(user.getFax());
        AddressPO address = ad.findByAddressid(person.getAddressid());
        if(address==null){
            address = new AddressPO();
        }
        if (user.getDetailaddress() != null) {
            address.setDetailaddress(user.getDetailaddress());
        }
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
        if(address!=null){
            Integer addressid = ad.save(address).getAddressid();
            person.setAddressid(addressid);
        }
        pd.save(person);

        preUser.setName(user.getFirstname() + user.getLastname());
        preUser.setOrganizationid(user.getOrganizationid());
        preUser.setLoginname(user.getPhonenumber());
        String distributorcode = od.findByOrganizationid(user.getOrganizationid()).getCode();
        String installerorgcode = od.findByOrganizationid(preUser.getInstallerorgid()).getCode();
        String installercode = empDAO.findByEmployeeid(preUser.getInstallerid()).getCode();
        String newusercode = "001"+distributorcode+installerorgcode+installercode+ user.getSupcode();
        preUser.setCodepostfix(newusercode);
        preUser.setUsercode(newusercode);
        preUser.setSupcode(user.getSupcode());
        preUser.setGroupid(user.getGroupid());
        preUser.setMonitoringstationid(user.getMonitoringstationid());

        /******处理网关及电话卡*****/
        List<GatewayUserPO> gatewayuseruser = gd.findByUserid(preUser.getUserid());
        List<PhonecardUserPO> phonecarduser = pcud.findByUserid(preUser.getUserid());
        List<String> gatewaylist = new ArrayList<>();
        List<String> phonecarduserlist = new ArrayList<>();
        for(GatewayUserPO g:gatewayuseruser){
            gatewaylist.add(g.getDeviceid());
        }
        for(PhonecardUserPO p:phonecarduser){
            phonecarduserlist.add(pcard.findByPhonecardid(p.getPhonecardid()).getSerialnumber());
        }
        //关于电话卡，如果传空过来，则不进行操作，如果填了数据，则判断是否被绑定，是否存在，如果是新增，则进行绑定操作
                for(int i=0;i<serialnumberarray.length;i++){
                    if(!StringUtils.isEmpty(serialnumberarray[i])){
                        PhonecardPO bySerialnumber = pcard.findBySerialnumber(serialnumberarray[i]);
                        if(bySerialnumber==null){
                            throw new RuntimeException("-111");//该电话卡不存在
                        }else{
                            GatewayPhonecardPO gppo1 = gpDAO.findBySerialnumber(serialnumberarray[i]);//电话卡查询结果
                            GatewayPhonecardPO gppo = gpDAO.findByDeviceid(devicearray[i]);//网关查询结果
                            if(gppo!=null&&!devicearray[i].equals(gppo.getDeviceid())){
                                throw new RuntimeException("-119");
                            }
                            if(gppo!=null&&!StringUtils.isEmpty(gppo.getSerialnumber())&&!gppo.getSerialnumber().equals(serialnumberarray[i])){
                                throw new RuntimeException("-120");//该用户有电话卡未解绑
                            }
                            if(gppo1!=null&&!devicearray[i].equals(gppo1.getDeviceid())){
                                //该电话卡已被其他人绑定
                                throw new RuntimeException("-110");
                            }
                            Integer phonecardid = phonecardDAO.findBySerialnumber(serialnumberarray[i]).getPhonecardid();
                            PhonecardUserPO pu = pcud.findByPhonecardid(phonecardid);
                            if(pu==null){
                                pu = new PhonecardUserPO();
                                pu.setUserid(user.getUserid());
                                pu.setPhonecardid(phonecardDAO.findBySerialnumber(serialnumberarray[i]).getPhonecardid());
                                pu.setCreatetime(new Date());
                            }
                            pcud.save(pu);
                            GatewayPhonecardPO gp = gpDAO.findByDeviceid(devicearray[i]);
                            if(gp==null){
                                if(StringUtils.isEmpty(serialnumberarray[i])){//TODO貌似不会数组越界
                                    gp= new GatewayPhonecardPO(devicearray[i],null);
                                }else{
                                    gp = new GatewayPhonecardPO(devicearray[i],serialnumberarray[i]);
                                }
                            }
                            if(StringUtils.isEmpty(gp.getSerialnumber())&&!StringUtils.isEmpty(serialnumberarray[i])){
                                gp.setSerialnumber(serialnumberarray[i]);
                            }
                            gpDAO.save(gp);
                        }
                    }
                }
                if(devicearray.length==gatewaylist.size()){//用户原有网关数量与现传过来的数量相同
                    for(int i=0;i<gatewaylist.size();i++){
                        if(!Arrays.asList(devicearray).contains(gatewaylist.get(i))){
                            throw new RuntimeException("-119");//网关序列号在未解绑情况下被修改，不允许
                        }
                    }
                    //如果是同一批网关，则不进行修改
                }else if(devicearray.length>gatewaylist.size()){//网关增加了
                    List<String> devicelist = Arrays.asList(devicearray);
                    devicelist = devicelist.stream().filter(d -> gatewaylist.contains(d)).collect(toList());
                    if(devicelist.size()!=gatewaylist.size()){//如果不是在原有基础上添加
                        throw new RuntimeException("-119");
                    }
                    List<String> templist = Arrays.asList(devicearray);
                    templist = templist.stream().filter(d -> !gatewaylist.contains(d)).collect(toList());
                    for(int i=0;i<templist.size();i++){
                        List<GatewayUserPO> gatewayuserdevice = gd.findByDeviceid(templist.get(i));
                        if(gatewayuserdevice!=null&&gatewayuserdevice.size()>0){//该网关被别人绑定了
                            throw new RuntimeException("-108");//网关已被其他用户绑定，请填入正确网关
                        }else{
                            if(gateway.findByDeviceid(templist.get(i))==null){//网关表没有此网关，不可以绑定
                                throw new RuntimeException("-109");//网关不存在
                            }else{//执行绑定网关操作
                                GatewayUserPO gatewayUserPO = new GatewayUserPO();
                                gatewayUserPO.setUserid(user.getUserid());
                                gatewayUserPO.setDeviceid(templist.get(i));
                                gatewayUserPO.setCreatetime(new Date());
                                gd.save(gatewayUserPO);
                                GatewayPhonecardPO gp = gpDAO.findByDeviceid(devicearray[i]);
                                if(gp==null){
                                    if(StringUtils.isEmpty(serialnumberarray[i])){//TODO貌似不会数组越界
                                        gp= new GatewayPhonecardPO(devicearray[i],null);
                                    }else{
                                        gp = new GatewayPhonecardPO(devicearray[i],serialnumberarray[i]);
                                    }
                                }
                                gpDAO.save(gp);
                            }
                        }
                    }
                }else{//网关解绑了(前端限制不允许填空)
                    gatewaylist.remove(Arrays.asList(devicearray));
                    for(int i=0;i<gatewaylist.size();i++){
                        gd.deleteByDeviceid(gatewaylist.get(i));
                        GatewayPhonecardPO gp = gpDAO.findByDeviceid(gatewaylist.get(i));
                        if(gp!=null){
                            pcud.deleteByPhonecardid(pcard.findBySerialnumber(gp.getSerialnumber()).getPhonecardid());
                            gpDAO.delete(gp);
                        }
                    }
                }
        ud.save(preUser);
    }
    @Transactional(rollbackFor = Exception.class)
    public void unbundling(String userid, String serialnumber) {
        //phonecardDAO.deleteBySerialnumber(serialnumber);删除操作留到电话卡页面，此处只是解绑操作
        PhonecardPO bySerialnumber1 = pcard.findBySerialnumber(serialnumber);
        if(bySerialnumber1!=null){
            pcud.deleteByPhonecardid(bySerialnumber1.getPhonecardid());
        }
        GatewayPhonecardPO bySerialnumber = gpDAO.findBySerialnumber(serialnumber);
        bySerialnumber.setSerialnumber("");
        gpDAO.save(bySerialnumber);
    }
    @Transactional(rollbackFor = Exception.class)
    public void unbundlinggateway(String mydata) {
        gd.deleteByDeviceid(mydata);
        gpDAO.deleteByDeviceid(mydata);
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
    public String createUserCode(Integer employeeid, Integer organizationid,Integer suborgid) {
        OrganizationPO org = od.findByOrganizationid(organizationid);

        Integer parentorgid = org.getParentorgid();
        /*if(suborgid==null&&StringUtils.isEmpty(parentorgid)){
            return "-";
        }*/
        if(suborgid!=null){
            parentorgid = suborgid;
        }
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
    @Transactional(rollbackFor = Exception.class)
    public String createSupCode(Integer organizationid,Integer suborgid) {
        OrganizationPO org = od.findByOrganizationid(organizationid);
        Integer parentorgid = org.getParentorgid();
        if(suborgid==null&&StringUtils.isEmpty(parentorgid)){
            return null;
        }
        if(suborgid!=null){
            parentorgid = suborgid;
        }
        OrganizationPO parentorg = od.findByOrganizationid(parentorgid);
        String usercode = "";
        String suppliercode = parentorg.getCode();
        SupplierCodeLogPO supplierCode = supcodeDAO.findSupplierCode(suppliercode);
        if(supplierCode==null){
            usercode = "00"+"0000";
        }else{
            Integer preint = 0 ;
            Integer sufint = 0 ;
            String usercode1 = supplierCode.getUsercode();
            String precode = usercode1.substring(0,2);
            String sufcode = usercode1.substring(2);
            preint = Ten2ThirtySix.thirtysixToTen(precode);
            sufint = Integer.parseInt(sufcode,16)+1;
            if(sufint==65536){
                preint = preint +1;
                sufint = 0;
            }
            precode = Ten2ThirtySix.tenTo36(preint);
            sufcode = sufint.toHexString(sufint);
            if(precode.length()==1){
                precode = "0"+precode;
            }
            if(sufcode.length()==1){
                sufcode = "000"+sufcode;
            }else if(sufcode.length()==2){
                sufcode = "00"+sufcode;
            }else if(sufcode.length()==3){
                sufcode = "0"+sufcode;
            }
            usercode = precode.toLowerCase()+sufcode.toUpperCase();
        }
        SupplierCodeLogPO scpo = new SupplierCodeLogPO();
        scpo.setSuppliercode(suppliercode);
        scpo.setUsercode(usercode);
        supcodeDAO.save(scpo);
        return usercode;
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
            return null;
        }
    }
    @Transactional(readOnly = true)
    public String findInsName(Integer organizationid) {
        OrganizationPO org = od.findByOrganizationid(organizationid);
        return org.getName();
    }
    @Transactional(readOnly = true)
    public List<String> fillGateway(String appaccount) {
        List<GatewayPO> gatewaylist = gateway.findByAppaccount(appaccount);
        List<String> devicelist = new ArrayList<>();
        if(gatewaylist!=null&&gatewaylist.size()>0){
            for(GatewayPO s:gatewaylist){
                devicelist.add(s.getDeviceid());
            }
            return devicelist;
        }
        else return null;
    }
    @Transactional(readOnly = true)
    public boolean checkAmeta(EmployeePO emp) {
        OrganizationPO org = od.findByOrganizationid(emp.getOrganizationid());
        if(Constants.ORGTYPE_AMETA.equals(org.getOrgtype())){
            return true;
        }else{
            return false;
        }
    }
    @Transactional(readOnly = true)
    public UserPO findUserInfo(int userid) {
        return ud.findByUserid(userid);
    }

    @Transactional(readOnly = true)
    public OrganizationPO findOrgInfo(Integer organizationid) {
        return od.findByOrganizationid(organizationid);


    }
    @Transactional
    public String findGroupid(Integer organizationid) {
        return od.findByOrganizationid(organizationid).getGroupid();
    }
    @Transactional
    public String findSupplierCode(int suporgid) {
        return od.findByOrganizationid(suporgid).getCode();
    }

    @Transactional(readOnly = true)
    public String findSupOrgId(Integer organizationid) {
            OrganizationPO org = od.findByOrganizationid(organizationid);
            OrganizationPO byParentorgid = od.findByOrganizationid(org.getParentorgid());
            if(byParentorgid!=null){
                return byParentorgid.getOrganizationid()+"";
            }else{
                return null;
            }
    }
    @Transactional
    public void updatePassword(String oldpassword, String newpassword, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        OrganizationPO loginorg = (OrganizationPO) request.getSession().getAttribute("loginorg");
        //String encryptpassword = Encrypt.encrypt(emp.getLoginname(), oldpassword, loginorg.getCode());
        boolean check = Encrypt.check(emp.getLoginname(),oldpassword, loginorg.getCode(), emp.getPassword());
        if(!check){
            throw new RuntimeException("oldpassword is wrong !");
        }
        emp.setPassword(Encrypt.encrypt(emp.getLoginname(), newpassword, loginorg.getCode()));
        EmployeePO save = empDAO.save(emp);
    }
}
