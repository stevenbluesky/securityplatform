package cn.com.isurpass.house.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.isurpass.house.dao.CityDAO;
import cn.com.isurpass.house.dao.EmployeeDAO;
import cn.com.isurpass.house.dao.GatewayuserDAO;
import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.dao.UserDAO;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.GatewayUserPO;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.po.UserPO;

@Service
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
	public String findUsernameByDeviceid(String deviceid) {
		return findUserBydeviceid(deviceid).getName();
	}

	/**
	 * 通过网关id获取 使用网关的用户
	 * 
	 * @param deviceid
	 * @return
	 */
	public UserPO findUserBydeviceid(String deviceid) {
		GatewayUserPO gu = gd.findByDeviceid(deviceid);
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
	public String findCityBydeviceid(String deviceid) {
		String code = findUserBydeviceid(deviceid).getCitycode();
		return cd.findByCitycode(code).getCityname();
	}

	/**
	 * 通过device获取服务商对象
	 * @param deviceid
	 * @return
	 */
	public OrganizationPO findOrgBydeviceid(String deviceid) {
		Integer organizationid = findUserBydeviceid(deviceid).getOrganizationid();
		return od.findByOrganizationid(organizationid);
	}
	public OrganizationPO findInstallOrgBydeviceid(String deviceid) {
		Integer organizationid = findUserBydeviceid(deviceid).getInstallerorgid();
		return od.findByOrganizationid(organizationid);
	}
	public EmployeePO findInstallerByDeviceid(String deviceid) {
		Integer installerid = findUserBydeviceid(deviceid).getInstallerid();
		return ed.findByEmployeeid(installerid);
	}
	
	public String findInstallernameBydeviceid(String deviceid) {
		return findInstallerByDeviceid(deviceid).getName();
	}

	/**
	 * 通过device获取服务商名称
	 * @param deviceid
	 * @return
	 */
	public String findOrgnameBydeviceId(String deviceid) {
		return findOrgBydeviceid(deviceid).getName();
	}
	public String findInstallerOrgnameBydeviceId(String deviceid) {
		return findInstallOrgBydeviceid(deviceid).getName();
	}

	/**
	 * 通过UserPOList获取相应的deviceid
	 * 
	 * @param list
	 * @return
	 */
	public List<String> findGatewayidListByUserList(List<UserPO> list) {
		List<Integer> l = new ArrayList<>();
		List<String> gid = new ArrayList<>();
		list.forEach(li -> l.add(li.getUserid()));
		List<GatewayUserPO> gupo = gd.findByUseridIn(l);
		gupo.forEach(g -> gid.add(g.getDeviceid()));
		return gid;
	}
}
