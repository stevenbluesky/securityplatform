package cn.com.isurpass.house.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.isurpass.house.dao.ZwaveDeviceDAO;
import cn.com.isurpass.house.po.UserPO;
import cn.com.isurpass.house.po.ZwaveDevicePO;
import cn.com.isurpass.house.vo.ZwaveDeviceListVO;
import cn.com.isurpass.house.web.controller.DeviceDetailVO;

@Service
public class ZwaveDeviceService {

	@Autowired
	ZwaveDeviceDAO zd;
	@Autowired
	GatewayuserService gs;
	@Autowired
	UserService us;

	public Map<String, Object> listDevice(Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
//		Page<ZwaveDevicePO> zdevice = dd.findAll(pageable);
		
		//当前登录的员工的机构id
		Integer orgid = 1;
		
		//如果角色是机构,通过机构查找,如果角色是安装员,通过安装员查找
		
		// 机构查找用户,用户查找网关,网关查找设备
		//下面代码是通过机构查找
		List<UserPO> userList = us.findUser(orgid);
		List<String> deviceidlist = gs.findGatewayidListByUserList(userList);
		List<ZwaveDevicePO> zdevicelist = findZDeviceByDeviceidList(deviceidlist);
		map.put("total", zdevicelist.size());
		List<ZwaveDeviceListVO> list = new ArrayList<>();
		System.out.println(zdevicelist);
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
		return map;
	}

	/**
	 * 获取设备详情
	 * @param zwavedeviceid
	 */
	public DeviceDetailVO findDeviceDetail(Integer zwavedeviceid) {
		
		//TODO 判断当前登录的员工是否有操作此id的权限
		DeviceDetailVO dd = new DeviceDetailVO();
		ZwaveDevicePO zwave = zd.findByZwavedeviceid(zwavedeviceid);
		String deviceid = zwave.getDeviceid();
		dd.setWarningstatuses(zwave.getWarningstatuses());
		dd.setStatus(zwave.getStatus());
		dd.setDevicename(zwave.getName());
		dd.setSuppliename(gs.findOrgnameBydeviceId(deviceid));
		return dd;
	}
	
	public List<ZwaveDevicePO> findZDeviceByDeviceidList(List<String> deviceidlist) {
		return zd.findByDeviceidIn(deviceidlist);
	}
}
