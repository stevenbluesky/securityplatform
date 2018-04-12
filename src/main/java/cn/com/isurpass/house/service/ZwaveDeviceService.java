package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.DeviceSearchVO;
import cn.com.isurpass.house.vo.UserInfoListVO;
import cn.com.isurpass.house.vo.ZwaveDeviceListVO;
import cn.com.isurpass.house.vo.DeviceDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class ZwaveDeviceService {

    @Autowired
    ZwaveDeviceDAO zd;
    @Autowired
    GatewayuserService gs;
    @Autowired
    GatewayuserDAO gud;
    @Autowired
    UserService us;
    @Autowired
    UserDAO ud;
    @Autowired
    OrganizationDAO od;
    @Autowired
    EmployeeroleDAO erd;
    @Autowired
    CityDAO city;
    @Autowired
    EmployeeDAO ed;

    @Transactional(readOnly = true)
    public Map<String, Object> listDevice(Pageable pageable, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
//		Page<ZwaveDevicePO> zdevice = dd.findAll(pageable);

        //当前登录的员工的机构id
//		Integer orgid = 1;
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
//        od.findByParentorgidIn(emp.getOrganizationid());
        //如果角色是机构,通过机构查找,如果角色是安装员,通过安装员查找

        // 机构查找用户,用户查找网关,网关查找设备
        //下面代码是通过机构查找
        //通过机构id查找其子类所有的机构id,再查询它们的用户
//        Page<ZwaveDevicePO> zdevicelist = null;
        List<UserPO> userList = null;
        if (od.findByOrganizationid(emp.getOrganizationid()).getOrgtype() == Constants.ORGTYPE_AMETA) {
//            zdevicelist = zd.findAll(pageable);
            userList = ud.findAll();
        } else if (od.findByOrganizationid(emp.getOrganizationid()).getOrgtype() == Constants.ORGTYPE_SUPPLIER) {
//          List<UserPO> userList = us.findUser(od.findByOrganizationid(emp.getOrganizationid());
            userList = ud.findByOrganizationid(emp.getOrganizationid());
        } else {
            if (erd.findByEmployeeid(emp.getEmployeeid()).contains(Constants.ROLE_INSTALLER)) {
                //如果是安装员
                userList = ud.findByInstallerid(emp.getEmployeeid());
            } else {
                //安装商
                userList = ud.findByInstallerorgid(emp.getOrganizationid());
            }
        }
        List<String> deviceidlist = gs.findGatewayidListByUserList(userList);
        List<ZwaveDevicePO> zdevicelist0 = findZDeviceByDeviceidList(deviceidlist);
//        map.put("total", zdevicelist.getTotalElements());

        List<String> filterlist = gs.filterDevice(zdevicelist0);//只显示与用户绑定了的设备
        Page<ZwaveDevicePO> zdevicelist = findZDeviceByDeviceidList(pageable, filterlist);
        System.out.println(zdevicelist);
        if (zdevicelist == null) {
            return null;
        }
        List<ZwaveDeviceListVO> list = new ArrayList<>();
        long start = System.currentTimeMillis(); //获取开始时间
        setProperties(zdevicelist, list);
        long end = System.currentTimeMillis(); //获取结束时间
        map.put("rows", list);
        map.put("total", zd.countByDeviceidIn(filterlist));
        System.out.println("timer： " + (end - start) + "ms");
        return map;
    }

    /**
     * 获取设备详情
     *
     * @param zwavedeviceid
     */
    @Transactional(readOnly = true)
    public DeviceDetailVO findDeviceDetail(Integer zwavedeviceid) {
        //TODO 判断当前登录的员工是否有操作此id的权限

        DeviceDetailVO dd = new DeviceDetailVO();
        ZwaveDevicePO zwave = zd.findByZwavedeviceid(zwavedeviceid);
        String deviceid = zwave.getDeviceid();
        dd.setWarningstatuses(zwave.getWarningstatuses());
        dd.setStatus(zwave.getStatus());
        dd.setDevicename(zwave.getName());
        dd.setDevicetype(zwave.getDevicetype());
        dd.setSuppliename(gs.findOrgnameBydeviceId(deviceid));
        return dd;
    }

    @Transactional(readOnly = true)
    public Page<ZwaveDevicePO> findZDeviceByDeviceidList(Pageable pageable, List<String> deviceidlist) {
        return zd.findByDeviceidIn(pageable, deviceidlist);
    }

    @Transactional(readOnly = true)
    public List<ZwaveDevicePO> findZDeviceByDeviceidList(List<String> deviceidlist) {
        return zd.findByDeviceidIn(deviceidlist);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> search(Pageable pageable, DeviceSearchVO dsv, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer orgtype = od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
        List<Integer> emprolelist = erd.findByEmployeeid(emp.getEmployeeid()).stream().map(EmployeeRolePO::getRoleid).collect(toList());
        Integer emptype = null;
        if (emprolelist.contains(Constants.ROLE_INSTALLER)) {
            //登录的是安装员
            emptype = -999;
        } else {
            emptype = orgtype;
        }
        List<ZwaveDevicePO> z0 = Collections.EMPTY_LIST;
        List<ZwaveDevicePO> z1 = Collections.EMPTY_LIST;
        List<ZwaveDevicePO> z2 = Collections.EMPTY_LIST;
        List<ZwaveDevicePO> z3 = Collections.EMPTY_LIST;
        List<ZwaveDevicePO> z4 = Collections.EMPTY_LIST;
        List<ZwaveDevicePO> z5 = Collections.EMPTY_LIST;
        List<ZwaveDevicePO> z6 = Collections.EMPTY_LIST;
        List<ZwaveDevicePO> z7 = Collections.EMPTY_LIST;
        List<ZwaveDevicePO> z8 = Collections.EMPTY_LIST;
        Set<List<ZwaveDevicePO>> set = new HashSet<>();

        //进行不同角色机构的权限控制
        List<Integer> useridlist0 = Collections.EMPTY_LIST;
        List<String> deviceidlist0 = Collections.EMPTY_LIST;
        switch (emptype) {
            case 0://ameta
                break;
            case 1:
                useridlist0 = ud.findByOrganizationid(emp.getOrganizationid()).stream().map(UserPO::getUserid).collect(toList());
                deviceidlist0 = gud.findByUseridIn(useridlist0).stream().map(GatewayUserPO::getDeviceid).collect(toList());
                z8 = zd.findByDeviceidIn(deviceidlist0);
                set.add(z8);
                break;
            case 2:
                useridlist0 = ud.findByInstallerorgid(emp.getOrganizationid()).stream().map(UserPO::getUserid).collect(toList());
                deviceidlist0 = gud.findByUseridIn(useridlist0).stream().map(GatewayUserPO::getDeviceid).collect(toList());
                z8 = zd.findByDeviceidIn(deviceidlist0);
                set.add(z8);
                break;
            case -999:
                useridlist0 = ud.findByInstallerid(emp.getEmployeeid()).stream().map(UserPO::getUserid).collect(toList());
                deviceidlist0 = gud.findByUseridIn(useridlist0).stream().map(GatewayUserPO::getDeviceid).collect(toList());
                z8 = zd.findByDeviceidIn(deviceidlist0);
                set.add(z8);
                break;
        }

        if (!FormUtils.isEmpty(dsv.getSearchname())) {
            z0 = zd.findByNameContaining(dsv.getSearchname());
            set.add(z0);
        }
        if (!FormUtils.isEmpty(dsv.getSearchcityname())) {
            List<String> citylist = city.findByCitynameContaining(dsv.getSearchcityname()).stream().map(CityPO::getCitycode).collect(toList());
            List<Integer> useridlist = ud.findByCitycodeIn(citylist).stream().map(UserPO::getUserid).collect(toList());
            List<String> deviceidlist = gud.findByUseridIn(useridlist).stream().map(GatewayUserPO::getDeviceid).collect(toList());
            z1 = zd.findByDeviceidIn(deviceidlist);
            set.add(z1);
        }
        if (!FormUtils.isEmpty(dsv.getSearchcitycode())) {
            List<Integer> useridlist = ud.findByCitycodeContaining(dsv.getSearchcitycode()).stream().map(UserPO::getUserid).collect(toList());
            List<String> deviceidlist = gud.findByUseridIn(useridlist).stream().map(GatewayUserPO::getDeviceid).collect(toList());
            z2 = zd.findByDeviceidIn(deviceidlist);
            set.add(z2);
        }
        if (!FormUtils.isEmpty(dsv.getSearchcustomer())) {
            List<Integer> useridlist = ud.findByNameContaining(dsv.getSearchcustomer()).stream().map(UserPO::getUserid).collect(toList());
            List<String> deviceidlist = gud.findByUseridIn(useridlist).stream().map(GatewayUserPO::getDeviceid).collect(toList());
            z3 = zd.findByDeviceidIn(deviceidlist);
            set.add(z3);
        }
        if (!FormUtils.isEmpty(dsv.getSearchserviceprovider())) {
            List<Integer> orgidlist = od.findByNameContaining(dsv.getSearchserviceprovider()).stream().map(OrganizationPO::getOrganizationid).collect(toList());
            List<Integer> useridlist = ud.findByOrganizationidIn(orgidlist).stream().map(UserPO::getUserid).collect(toList());;
         /*   switch (emptype) {
                case 0:
                    useridlist = ud.findByOrganizationidIn(orgidlist).stream().map(UserPO::getUserid).collect(toList());
                    break;
                case 1:
                    useridlist = ud.findByOrganizationid(emp.getOrganizationid()).stream().map(UserPO::getUserid).collect(toList());
                    break;
            }*/
            List<String> deviceidlist = gud.findByUseridIn(useridlist).stream().map(GatewayUserPO::getDeviceid).collect(toList());
            z4 = zd.findByDeviceidIn(deviceidlist);
            set.add(z4);
        }
        if (!FormUtils.isEmpty(dsv.getSearchinstallerorg())) {
            List<Integer> orgidlist = od.findByNameContaining(dsv.getSearchinstallerorg()).stream().map(OrganizationPO::getOrganizationid).collect(toList());
            List<Integer> useridlist = ud.findByInstallerorgidIn(orgidlist).stream().map(UserPO::getUserid).collect(toList());
/*            List<Integer> useridlist = Collections.EMPTY_LIST;
            switch (emptype) {
                case 0:
                    useridlist = ud.findByOrganizationidIn(orgidlist).stream().map(UserPO::getUserid).collect(toList());
                    break;
                case 1:
                    useridlist = ud.findByInstallerorgidInAndOrganizationid(orgidlist, emp.getOrganizationid()).stream().map(UserPO::getUserid).collect(toList());
                    break;
                case 2:
                    useridlist = ud.findByInstallerorgid(emp.getOrganizationid()).stream().map(UserPO::getUserid).collect(toList());
                    break;
            }*/
            List<String> deviceidlist = gud.findByUseridIn(useridlist).stream().map(GatewayUserPO::getDeviceid).collect(toList());
            z5 = zd.findByDeviceidIn(deviceidlist);
            set.add(z5);
        }
        if (!FormUtils.isEmpty(dsv.getSearchinstaller())) {
            List<Integer> empidlist = ed.findByNameContaining(dsv.getSearchinstaller()).stream().map(EmployeePO::getEmployeeid).collect(toList());
            List<Integer> useridlist = ud.findByInstalleridIn(empidlist).stream().map(UserPO::getUserid).collect(toList());
           /* List<Integer> useridlist = Collections.EMPTY_LIST;
            switch (emptype) {
                case 0:
                    useridlist = ud.findByInstalleridIn(empidlist).stream().map(UserPO::getUserid).collect(toList());
                    break;
                case 1:
                    useridlist = ud.findByInstalleridInAndOrganizationidIn(empidlist, emp.getOrganizationid()).stream().map(UserPO::getUserid).collect(toList());
                    break;
                case 2:
                    useridlist = ud.findByInstallerorgidAndInstalleridIn(emp.getOrganizationid(), empidlist).stream().map(UserPO::getUserid).collect(toList());
                    break;
                case -999:
                    useridlist = ud.findByInstallerid(emp.getEmployeeid()).stream().map(UserPO::getUserid).collect(toList());
            }*/
            List<String> deviceidlist = gud.findByUseridIn(useridlist).stream().map(GatewayUserPO::getDeviceid).collect(toList());
            z6 = zd.findByDeviceidIn(deviceidlist);
            set.add(z6);
        }
        if (!FormUtils.isEmpty(dsv.getSearchgatewayid())) {
            z7 = zd.findByDeviceidContaining(dsv.getSearchgatewayid());
            set.add(z7);
        }
        set.remove(null);
        Iterator<List<ZwaveDevicePO>> iterator = set.iterator();
        List<ZwaveDevicePO> ux = iterator.next();
        while (iterator.hasNext()) {
            ux.retainAll(iterator.next());
        }
        List<Integer> ids = ux.stream().map(ZwaveDevicePO::getZwavedeviceid).collect(toList());
        Page<ZwaveDevicePO> listpage = zd.findByZwavedeviceidIn(ids, pageable);
        Map<String, Object> map = new HashMap<>();
        if (ids.isEmpty()) {
            map.put("rows", Collections.EMPTY_LIST);
            map.put("total", 0);
            return map;
        }
//        System.out.println(ux);
        List<ZwaveDeviceListVO> listVO = new ArrayList<>();
        setProperties(listpage, listVO);
        map.put("total", zd.countByZwavedeviceidIn(ids));
        map.put("rows", listVO);
        return map;
    }

    private void setProperties(Page<ZwaveDevicePO> listpage, List<ZwaveDeviceListVO> listVO) {
        listpage.forEach(zw -> {
        ZwaveDeviceListVO z = new ZwaveDeviceListVO();
        z.setZwavedeviceid(zw.getZwavedeviceid());
        z.setName(zw.getName());
        z.setDevicetype(zw.getDevicetype());
        z.setWarningstatuses(zw.getWarningstatuses());
        z.setStatus(zw.getStatus());
        z.setBattery(zw.getBattery());
        z.setUsername(gs.findUsernameByDeviceid(zw.getDeviceid()));
        z.setCity(gs.findCityBydeviceid(zw.getDeviceid()));
        z.setOrganizationname(gs.findOrgnameBydeviceId(zw.getDeviceid()));
        z.setInstallerorgname(gs.findInstallerOrgnameBydeviceId(zw.getDeviceid()));
        z.setInstallername(gs.findInstallernameBydeviceid(zw.getDeviceid()));
        listVO.add(z);
        });
    }

}
