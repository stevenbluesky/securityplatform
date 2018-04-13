package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service("gatewayuser")
public class GatewayuserService {

    @Autowired
    UserDAO ud;
    @Autowired
    GatewayuserDAO gd;
    @Autowired
    CityDAO cd;
    @Autowired
    OrganizationDAO od;
    @Autowired
    EmployeeDAO ed;

    /**
     * 通过网关id获取 使用网关的用户姓名
     *
     * @param deviceid
     * @return
     */
    @Transactional(readOnly = true)
    public String findUsernameByDeviceid(String deviceid) {
        UserPO up = findUserBydeviceid(deviceid);
        return up == null ? null : up.getName();
    }

    /**
     * 通过网关id获取 使用网关的用户
     *
     * @param deviceid
     * @return
     */
    @Transactional(readOnly = true)
    public UserPO findUserBydeviceid(String deviceid) {
        List<GatewayUserPO> gulist = gd.findByDeviceid(deviceid);
        if (gulist == null || gulist.isEmpty()) {
            return null;
        }
        GatewayUserPO gu = gulist.get(0);
        if (gu == null) {
            return null;
        }
        Integer userid = gu.getUserid();
        UserPO user = ud.findByUserid(userid);
        return user;
    }

    /**
     * 通过网关id 获取用户所在的城市名
     *
     * @param deviceid
     * @return
     */
    @Transactional(readOnly = true)
    public String findCityBydeviceid(String deviceid) {
        UserPO up = findUserBydeviceid(deviceid);
        return up == null ? null : cd.findByCitycode(up.getCitycode()).getCityname();
    }

    /**
     * 通过device获取服务商对象
     *
     * @param deviceid
     * @return
     */
    @Transactional(readOnly = true)
    public OrganizationPO findOrgBydeviceid(String deviceid) {
        UserPO up = findUserBydeviceid(deviceid);
        return up == null ? null : od.findByOrganizationid(up.getOrganizationid());
    }

    @Transactional(readOnly = true)
    public OrganizationPO findInstallOrgBydeviceid(String deviceid) {
        UserPO up = findUserBydeviceid(deviceid);
        return up == null ? null : od.findByOrganizationid(up.getOrganizationid());
    }

    @Transactional(readOnly = true)
    public EmployeePO findInstallerByDeviceid(String deviceid) {
        UserPO up = findUserBydeviceid(deviceid);
        return up == null ? null : ed.findByEmployeeid(up.getInstallerid());
    }
    @Transactional(readOnly = true)
    public String findInstallernameBydeviceid(String deviceid) {
        EmployeePO ep = findInstallerByDeviceid(deviceid);
        return ep == null ? null : ep.getName();
    }

    /**
     * 通过device获取服务商名称
     *
     * @param deviceid
     * @return
     */
    @Transactional(readOnly = true)
    public String findOrgnameBydeviceId(String deviceid) {
        OrganizationPO org = findOrgBydeviceid(deviceid);
        return org == null ? null : org.getName();
    }

    @Transactional(readOnly = true)
    public String findInstallerOrgnameBydeviceId(String deviceid) {
        OrganizationPO org = findInstallOrgBydeviceid(deviceid);
        return org == null ? null : org.getName();
    }

    /**
     * 通过UserPOList获取相应的deviceid
     *
     * @param list
     * @return
     */
    @Transactional(readOnly = true)
    public List<String> findGatewayidListByUserList(List<UserPO> list) {
      /*  List<Integer> l = new ArrayList<>();
        List<String> gid = new ArrayList<>();
        list.forEach(li -> l.add(li.getUserid()));
        List<GatewayUserPO> gupo = gd.findByUseridIn(l);
        gupo.forEach(g -> gid.add(g.getDeviceid()));*/
        List<Integer> userids = list.stream().map(UserPO::getUserid).collect(toList());
        List<String> gid = gd.findByUseridIn(userids).stream().map(GatewayUserPO::getDeviceid).collect(toList());
        return gid;
    }

    /**
     * 过滤zdevicelist,只显示与用户绑定了的zdevicelist
     *
     * @param zdevicelist
     */
    @Transactional(readOnly = true)
    public List<String> filterDevice(List<ZwaveDevicePO> zdevicelist) {
   /*     List<String> list0 = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        zdevicelist.forEach(z -> list1.add(z.getDeviceid()));
        List<GatewayUserPO> list = gd.findByDeviceidIn(list1);
        if (!list.isEmpty()) {
            list.forEach(l -> list0.add(l.getDeviceid()));
        }
*/
        List<String> deviceids = zdevicelist.stream().map(ZwaveDevicePO::getDeviceid).collect(toList());
        List<String> list0 = gd.findByDeviceidIn(deviceids).stream().map(GatewayUserPO::getDeviceid).collect(toList());
        return list0;
    }
}
