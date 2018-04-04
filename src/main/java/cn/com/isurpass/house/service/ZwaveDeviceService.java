package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.dao.UserDAO;
import cn.com.isurpass.house.dao.ZwaveDeviceDAO;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.UserPO;
import cn.com.isurpass.house.po.ZwaveDevicePO;
import cn.com.isurpass.house.vo.ZwaveDeviceListVO;
import cn.com.isurpass.house.vo.DeviceDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZwaveDeviceService {

    @Autowired
    ZwaveDeviceDAO zd;
    @Autowired
    GatewayuserService gs;
    @Autowired
    UserService us;
    @Autowired
    UserDAO ud;
    @Autowired
    OrganizationDAO od;

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
        if (od.findByOrganizationid(emp.getOrganizationid()).getOrgtype() == 0) {
//            zdevicelist = zd.findAll(pageable);
            userList = ud.findAll();
        } else if (od.findByOrganizationid(emp.getOrganizationid()).getOrgtype() == 1) {
//          List<UserPO> userList = us.findUser(od.findByOrganizationid(emp.getOrganizationid());
            userList = ud.findByOrganizationid(emp.getOrganizationid());
        } else {
            userList = ud.findByInstallerorgid(emp.getOrganizationid());
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
        long start=System.currentTimeMillis(); //获取开始时间
        List<ZwaveDeviceListVO> list = new ArrayList<>();
        zdevicelist.forEach(zw -> {
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
            list.add(z);
        });
        map.put("rows", list);
        map.put("total",zd.countByDeviceidIn(filterlist));
        long end=System.currentTimeMillis(); //获取结束时间
        System.out.println("timer： "+(end-start)+"ms");
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
}
