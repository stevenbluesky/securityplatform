package cn.com.isurpass.house.service;

import java.util.*;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.vo.GatewayDetailVO;
import cn.com.isurpass.house.vo.TypeGatewayInfoVO;

import javax.servlet.http.HttpServletRequest;

@Service
public class GatewayService {
	@Autowired
	private GatewayDAO gd;
	@Autowired
	private CityDAO cd;
	@Autowired
	private UserDAO ud;
	@Autowired
	private GatewayuserDAO gud;
	@Autowired
	private ZwaveDeviceDAO zdd;
	@Autowired
	private OrganizationDAO od;
	@Autowired
	private GatewayBindingDAO gbd;
	@Autowired
	private EmployeeDAO ed;
	@Autowired
	private PhonecarduserDAO pud;
	@Autowired
	private PhonecardDAO pd;
	@Autowired
	private OrganizationService os;
	@Autowired
	private EmployeeroleDAO employeeroleDAO;
	/**
	 * 新增网关信息
	 * @param tgi
	 * @throws MyArgumentNullException
	 */
	@Transactional(rollbackFor = Exception.class)
	public void add(TypeGatewayInfoVO tgi,HttpServletRequest request) throws MyArgumentNullException {
		EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
		if (StringUtils.isEmpty(tgi.getDeviceid())|| StringUtils.isEmpty(tgi.getModel()) || StringUtils.isEmpty(tgi.getFirmwareversion())){
			throw new MyArgumentNullException("1");
		}
		GatewayPO gw = gd.findByDeviceid(tgi.getDeviceid());
		if(gw!=null){
			throw new MyArgumentNullException("2");
		}
		GatewayPO gwPO = new GatewayPO();
		// ID => name
		gwPO.setCreatetime(new Date());
		gwPO.setDeviceid(tgi.getDeviceid());
		gwPO.setFirmwareversion(tgi.getFirmwareversion());
		gwPO.setModel(tgi.getModel());
		gwPO.setStatus(1);//TODO 暂时默认新增即视为在线状态
		gd.save(gwPO);
		//添加网关时，根据用户所属机构进行机构网关绑定
		GatewayBindingPO gbp = new GatewayBindingPO();
		gbp.setCreatetime(new Date());
		gbp.setDeviceid(tgi.getDeviceid());
		gbp.setBindingtype(os.getOrgtypeByReqeust(request));
		gbp.setOrganizationid(emp.getOrganizationid());
		gbp.setStatus(1);
		gbd.save(gbp);
	}

	/**
	 * 没有搜索条件时显示的网关
	 * @param pageable
	 * @param emp
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> listNUllGateway(Pageable pageable, EmployeePO emp) {
		Long count = 0L;
		List<GatewayPO> gateList = new ArrayList<>();//网关集合
		Map<String, Object> map = new HashMap<>();//返回的map
		List<TypeGatewayInfoVO> list = new ArrayList<>();//返回的list对象
		List<String> orgglist = new ArrayList<>();//网关id集合

		Integer organizationid = emp.getOrganizationid();
		OrganizationPO org = od.findByOrganizationid(organizationid);//当前用户所属机构
		if("1".equals(org.getOrgtype()+"")){//为服务商,拿本身及旗下安装商的网关
			List<OrganizationPO> pid = od.findByParentorgid(organizationid);//拿到旗下安装商
			List<Integer> oolist = new ArrayList<>();//机构id集合
			Set<Integer> useridset = new TreeSet<>();//用户id集合
			if(pid.size()!=0&&pid!=null) {
				for (OrganizationPO o : pid) {
					oolist.add(o.getOrganizationid());
				}
			}
			List<UserPO> byOrganizationid = ud.findByOrganizationid(organizationid);//拿到本服务商下用户
			List<UserPO> byInstallerorgidIn = ud.findByInstallerorgidIn(oolist);//拿到旗下安装商用户
			byOrganizationid.forEach(single ->{useridset.add(single.getUserid());});
			byInstallerorgidIn.forEach(single ->{useridset.add(single.getUserid());});
			List<GatewayUserPO> byUseridIn = gud.findByUseridIn(useridset);
			byUseridIn.forEach(single ->{orgglist.add(single.getDeviceid());});
			gateList = gd.findByDeviceidIn(orgglist,pageable);
			count = gd.countByDeviceidIn(orgglist);
		}else if("2".equals(org.getOrgtype()+"")){//为安装商，拿自己的网关
			List<EmployeeRolePO> elist = employeeroleDAO.findByEmployeeid(emp.getEmployeeid());
			List<Integer> emprolelist = new ArrayList<>();
			List<Integer> useridlist = new ArrayList<>();
			elist.forEach(single ->{emprolelist.add(single.getRoleid());});
			if(emprolelist.contains(Constants.ROLE_INSTALLER)&&emprolelist.size()==1){//只是安装员id
				List<UserPO> byInstallerid = ud.findByInstallerid(emp.getEmployeeid());
				byInstallerid.forEach(single ->{useridlist.add(single.getUserid());});
			}else {
				//List<GatewayBindingPO> olist = gbd.findByOrganizationid(organizationid);//网关绑定表只能跟ameta绑定，暂认为无用
				List<UserPO> byInstallerorgid = ud.findByInstallerorgid(organizationid);
				byInstallerorgid.forEach(single ->{useridlist.add(single.getUserid());});
			}
			List<GatewayUserPO> byUseridIn = gud.findByUseridIn(useridlist);
			byUseridIn.forEach(single ->{orgglist.add(single.getDeviceid());});
			gateList = gd.findByDeviceidIn(orgglist,pageable);
			count = gd.countByDeviceidIn(orgglist);
		}else{//为ameta管理员，拿所有网关
			Page<GatewayPO> all = gd.findAll(pageable);
			count = gd.count();
			gateList = all.getContent();
		}
		for(GatewayPO o : gateList) {//使用for循环比lamaba表达式提高50ms响应,根据目前数据库数据少，耗时占总时间1/3左右
			TypeGatewayInfoVO gateVO = new TypeGatewayInfoVO();
			gateVO.setDeviceid(o.getDeviceid());
			List<GatewayUserPO> userlist = gud.findByDeviceid(o.getDeviceid());//TODO
			if (userlist != null && userlist.size() != 0) {//网关用户表匹配到了数据，该网关已跟用户绑定
				UserPO upo = ud.findByUserid(userlist.get(0).getUserid());
				gateVO.setCityname(cd.findByCitycode(upo.getCitycode()).getCityname());
				gateVO.setInstallerorg(od.findByOrganizationid(upo.getInstallerorgid()).getName());
				if (upo.getInstallerid() != null) {
					gateVO.setInstaller(ed.findByEmployeeid(upo.getInstallerid()).getLoginname());
				}
				gateVO.setServiceprovider(od.findByOrganizationid(upo.getOrganizationid()).getName());
				gateVO.setCustomer(upo.getLoginname());
			}
			gateVO.setName(o.getName());
			gateVO.setStatus(o.getStatus());
			gateVO.setModel(o.getModel());
			gateVO.setBattery(o.getBattery());
			gateVO.setFirmwareversion(o.getFirmwareversion());
			list.add(gateVO);
		}
		map.put("total",count);
		map.put("rows",list);
		return map;
	}
	/**
	 * 根据搜索条件获取网关分页信息列表
	 * @param pageable
	 * @param tgiv
	 * @param emp
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> listGateway(Pageable pageable, TypeGatewayInfoVO tgiv, EmployeePO emp) {
		Map<String, Object> map = new HashMap<>();//返回的map
		List<TypeGatewayInfoVO> list = new ArrayList<>();//返回的list对象
		Iterable<GatewayPO> geted = gd.findAll();//拿到网关列表所有记录
		List<String> gdlist = new ArrayList<>();
		geted.forEach(single ->{gdlist.add(single.getDeviceid());});//网关表所有记录的id集合
		List<Integer> orglist = new ArrayList<>();
		List<String> orgglist = new ArrayList<>();
		Integer organizationid = emp.getOrganizationid();
		OrganizationPO org = od.findByOrganizationid(organizationid);
		if("1".equals(org.getOrgtype()+"")){//为服务商,拿本身及旗下安装商的网关
			List<OrganizationPO> pid = od.findByParentorgid(organizationid);//拿到旗下安装商
			List<Integer> oolist = new ArrayList<>();//机构id集合
			Set<Integer> useridset = new TreeSet<>();//用户id集合
			if(pid.size()!=0&&pid!=null) {
				for (OrganizationPO o : pid) {
					oolist.add(o.getOrganizationid());
				}
			}
			List<UserPO> byOrganizationid = ud.findByOrganizationid(organizationid);//拿到本服务商下用户
			List<UserPO> byInstallerorgidIn = ud.findByInstallerorgidIn(oolist);//拿到旗下安装商用户
			byOrganizationid.forEach(single ->{useridset.add(single.getUserid());});
			byInstallerorgidIn.forEach(single ->{useridset.add(single.getUserid());});
			List<GatewayUserPO> byUseridIn = gud.findByUseridIn(useridset);
			byUseridIn.forEach(single ->{orgglist.add(single.getDeviceid());});
		}else if("2".equals(org.getOrgtype()+"")){//为安装商，拿自己的网关
			List<EmployeeRolePO> elist = employeeroleDAO.findByEmployeeid(emp.getEmployeeid());
			List<Integer> emprolelist = new ArrayList<>();
			List<Integer> useridlist = new ArrayList<>();
			elist.forEach(single ->{emprolelist.add(single.getRoleid());});
			if(emprolelist.contains(Constants.ROLE_INSTALLER)&&emprolelist.size()==1){//只是安装员id
				List<UserPO> byInstallerid = ud.findByInstallerid(emp.getEmployeeid());
				byInstallerid.forEach(single ->{useridlist.add(single.getUserid());});
			}else {
				//List<GatewayBindingPO> olist = gbd.findByOrganizationid(organizationid);//网关绑定表只能跟ameta绑定，暂认为无用
				List<UserPO> byInstallerorgid = ud.findByInstallerorgid(organizationid);
				byInstallerorgid.forEach(single ->{useridlist.add(single.getUserid());});
			}
			List<GatewayUserPO> byUseridIn = gud.findByUseridIn(useridlist);
			byUseridIn.forEach(single ->{orgglist.add(single.getDeviceid());});
		}else{//为ameta管理员，拿所有网关
			for(String s : gdlist){
				orgglist.add(s);
			}
		}
		//如果传入参数为空，则默认查网关列表的全部
		List<String> citynamedeviceidlist = StringUtils.isEmpty(tgiv.getCityname())?gdlist:findIdListByCityname(tgiv.getCityname());
		List<String> citycodedeviceidlist = StringUtils.isEmpty(tgiv.getCitycode())?gdlist:findIdListByCitycode(tgiv.getCitycode());
		List<String> devicenamedeviceidlist = StringUtils.isEmpty(tgiv.getName())?gdlist:findIdListByDevicename(tgiv.getName());
		List<String> customerdeviceidlist = StringUtils.isEmpty(tgiv.getCustomer())?gdlist:findIdListByCustomer(tgiv.getCustomer());
		List<String> serviceproviderdeviceidlist = StringUtils.isEmpty(tgiv.getServiceprovider())?gdlist:findIdListByServiceprovider(tgiv.getServiceprovider());
		List<String> installerorgdeviceidlist = StringUtils.isEmpty(tgiv.getInstallerorg())?gdlist:findIdListByInstallerorg(tgiv.getInstallerorg());
		List<String> installerdeviceidlist = StringUtils.isEmpty(tgiv.getInstaller())?gdlist:findIdListByInstaller(tgiv.getInstaller());
		List<String> deviceiddeviceidlist = StringUtils.isEmpty(tgiv.getDeviceid())?gdlist:findIdListByDeviceid(tgiv.getDeviceid());
		//处理各获取id列表方法的返回值
		if(citynamedeviceidlist==null||devicenamedeviceidlist==null||serviceproviderdeviceidlist==null||installerdeviceidlist==null||citycodedeviceidlist==null||customerdeviceidlist==null||installerorgdeviceidlist==null||deviceiddeviceidlist==null){
			map.put("total", 0);
			map.put("rows", list);
			return map;
		}
		//取8个搜索条件的id交集
		citynamedeviceidlist.retainAll(citycodedeviceidlist);
		devicenamedeviceidlist.retainAll(customerdeviceidlist);
		serviceproviderdeviceidlist.retainAll(installerorgdeviceidlist);
		installerdeviceidlist.retainAll(deviceiddeviceidlist);
		citynamedeviceidlist.retainAll(devicenamedeviceidlist);
		serviceproviderdeviceidlist.retainAll(installerdeviceidlist);
		citynamedeviceidlist.retainAll(serviceproviderdeviceidlist);
		citynamedeviceidlist.retainAll(orgglist);
		List<GatewayPO> gateList = gd.findByDeviceidIn(pageable,citynamedeviceidlist);//从网关表中拿基本数据
		map.put("total",gd.countByDeviceidIn(citynamedeviceidlist));
		for(GatewayPO o : gateList) {//使用for循环比lamaba表达式提高50ms响应,根据目前数据库数据少，耗时占总时间1/3左右
			TypeGatewayInfoVO gateVO = new TypeGatewayInfoVO();
			gateVO.setDeviceid(o.getDeviceid());
			List<GatewayUserPO> userlist = gud.findByDeviceid(o.getDeviceid());//TODO
			if (userlist != null && userlist.size() != 0) {//网关用户表匹配到了数据，该网关已跟用户绑定
				UserPO upo = ud.findByUserid(userlist.get(0).getUserid());
				gateVO.setCityname(cd.findByCitycode(upo.getCitycode()).getCityname());
				gateVO.setInstallerorg(od.findByOrganizationid(upo.getInstallerorgid()).getName());
				if (upo.getInstallerid() != null) {
					gateVO.setInstaller(ed.findByEmployeeid(upo.getInstallerid()).getLoginname());
				}
				gateVO.setServiceprovider(od.findByOrganizationid(upo.getOrganizationid()).getName());
				gateVO.setCustomer(upo.getLoginname());
			}
			gateVO.setName(o.getName());
			gateVO.setStatus(o.getStatus());
			gateVO.setModel(o.getModel());
			gateVO.setBattery(o.getBattery());
			gateVO.setFirmwareversion(o.getFirmwareversion());
			list.add(gateVO);
		}
		map.put("rows", list);
		return map;
	}
	/**
	 * 根据城市名称模糊查询网关id列表
	 * @param cityname
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByCityname(String cityname){
		List<String> citynamedeviceidlist = new ArrayList<String>();
		List<String> codelist = new ArrayList<String>();
		List<Integer> citynameuseridlist = new ArrayList<Integer>();
		Long start = System.currentTimeMillis();
		List<CityPO> findByCityname = cd.findByCitynameContaining(cityname);
		Long stop = System.currentTimeMillis();
		System.out.println((stop-start)/1000+"秒");
		if(findByCityname.size()==0){
			return null;
		}
		for (CityPO cityPO : findByCityname) {
			codelist.add(cityPO.getCitycode());
		}
		if(codelist.size()==0){
			return null;
		}
		for (String cl : codelist) {
			List<UserPO> findByCitycode = ud.findByCitycode(cl);
			for (UserPO userPO : findByCitycode) {
				citynameuseridlist.add(userPO.getUserid());
			}
		}
		if(citynameuseridlist.size()==0){
			return null;
		}
		List<GatewayUserPO> findByUseridIn = gud.findByUseridIn(citynameuseridlist);
		for (GatewayUserPO gatewayUserPO : findByUseridIn) {
			citynamedeviceidlist.add(gatewayUserPO.getDeviceid());
		}
		return citynamedeviceidlist;
	}
	/**
	 * 根据城市代码模糊查询网关id列表
	 * @param citycode
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByCitycode(String citycode){
		List<String> citycodedeviceidlist = new ArrayList<String>();
		List<Integer> citycodeuseridlist = new ArrayList<Integer>();
		List<UserPO> findByCitycodeContaining = ud.findByCitycodeContaining(citycode);
		if(findByCitycodeContaining.size()==0){
			return null;
		}
		for (UserPO userPO : findByCitycodeContaining) {
			citycodeuseridlist.add(userPO.getUserid());
		}
		List<GatewayUserPO> findByUseridIn2 = gud.findByUseridIn(citycodeuseridlist);
		if(findByUseridIn2.size()==0){
			return null;
		}
		for (GatewayUserPO gatewayUserPO : findByUseridIn2) {
			citycodedeviceidlist.add(gatewayUserPO.getDeviceid());
		}
		return citycodedeviceidlist;
	}
	//TODO
	/**
	 * 根据设备名称模糊查询对应网关id列表
	 * @param devicename
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByDevicename(String devicename){
		List<String> devicenamedeviceidlist = new ArrayList<String>();
		List<ZwaveDevicePO> byNameContaining = zdd.findByNameContaining(devicename);
		if(byNameContaining.size()==0){
			return null;
		}
		for (ZwaveDevicePO gatewayPO : byNameContaining) {
			devicenamedeviceidlist.add(gatewayPO.getDeviceid());
		}
		return devicenamedeviceidlist;
	}
	/**
	 * 根据客户名称模糊查询其拥有的网关列表
	 * @param customer
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByCustomer(String customer){
		List<String> customerdeviceidlist = new ArrayList<String>();
		List<Integer> customeruseridlist = new ArrayList<Integer>();
		List<UserPO> userlist = ud.findByLoginnameContaining(customer);
		if(userlist.size()==0){
			return null;
		}
		for (UserPO userPO : userlist) {
			customeruseridlist.add(userPO.getUserid());
		}
		List<GatewayUserPO> findByUseridIn3 = gud.findByUseridIn(customeruseridlist);
		if(findByUseridIn3.size()==0){
			return null;
		}
		for (GatewayUserPO gatewayUserPO : findByUseridIn3) {
			customerdeviceidlist.add(gatewayUserPO.getDeviceid());
		}
		return customerdeviceidlist;
	}
	/**
	 * 根据服务商名称模糊查询其拥有的网关列表
	 * @param serviceprovider
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByServiceprovider(String serviceprovider){
		List<String> serviceproviderdeviceidlist = new ArrayList<String>();
		List<Integer> serviceprovideridlist = new ArrayList<Integer>();
		List<Integer> useridlist = new ArrayList<Integer>();
		List<OrganizationPO> orglist = od.findByOrgtypeAndNameContaining(Constants.ORGTYPE_SUPPLIER,serviceprovider);
		if(orglist.size()==0){
			return null;
		}
		for (OrganizationPO organizationPO : orglist) {
			serviceprovideridlist.add(organizationPO.getOrganizationid());//拿到服务商id集合
		}
		List<UserPO> byOrganizationidIn = ud.findByOrganizationidIn(serviceprovideridlist);//用户集合
		if(byOrganizationidIn==null||byOrganizationidIn.size()==0){
			return null;
		}
		byOrganizationidIn.forEach(s->{useridlist.add(s.getUserid());});
		List<GatewayUserPO> byUseridIn = gud.findByUseridIn(useridlist);
		if(byUseridIn==null||byUseridIn.size()==0){
			return null;
		}
		byUseridIn.forEach(s->{serviceproviderdeviceidlist.add(s.getDeviceid());});
		return serviceproviderdeviceidlist;
	}
	/**
	 * 根据安装商名称模糊查询其拥有的网关列表
	 * @param installerorg
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByInstallerorg(String installerorg){
		List<String> installerorgdeviceidlist = new ArrayList<String>();
		List<Integer> installerorgidlist = new ArrayList<Integer>();
		List<Integer> useridlist = new ArrayList<Integer>();
		List<OrganizationPO> orglist2 = od.findByOrgtypeAndNameContaining(Constants.ORGTYPE_INSTALLER,installerorg);
		if(orglist2.size()==0){
			return null;
		}
		for (OrganizationPO organizationPO : orglist2) {
			installerorgidlist.add(organizationPO.getOrganizationid());//拿到安装商id集合
		}
		List<UserPO> byInstallerorgidIn = ud.findByInstallerorgidIn(installerorgidlist);//用户集合
		if(byInstallerorgidIn==null||byInstallerorgidIn.size()==0){
			return null;
		}
		byInstallerorgidIn.forEach(s->{useridlist.add(s.getUserid());});
		List<GatewayUserPO> byUseridIn = gud.findByUseridIn(useridlist);
		if(byUseridIn==null||byUseridIn.size()==0){
			return null;
		}
		byUseridIn.forEach(s->{installerorgdeviceidlist.add(s.getDeviceid());});
		return installerorgdeviceidlist;
	}
	/**
	 * 根据安装员名称模糊查询其安装的网关列表
	 * @param installer
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByInstaller(String installer){
		List<String> installerdeviceidlist = new ArrayList<String>();
		List<Integer> employeeidlist = new ArrayList<Integer>();
		List<Integer> installeridlist = new ArrayList<Integer>();
		List<EmployeePO> emplist = ed.findByLoginnameContaining(installer);
		if(emplist.size()==0){
			return null;
		}
		for (EmployeePO employeePO : emplist) {
			employeeidlist.add(employeePO.getEmployeeid());//拿到安装员id集合
		}
		List<UserPO> userlist2 = ud.findByInstalleridIn(employeeidlist);//拿到用户集合
		if(userlist2.size()==0){
			return null;
		}
		for (UserPO userPO : userlist2) {
			installeridlist.add(userPO.getUserid());
		}
		List<GatewayUserPO> findByUseridIn4 = gud.findByUseridIn(installeridlist);
		if(findByUseridIn4.size()==0){
			return null;
		}
		for (GatewayUserPO gatewayUserPO : findByUseridIn4) {
			installerdeviceidlist.add(gatewayUserPO.getDeviceid());
		}
		return installerdeviceidlist;
	}
	/**
	 * 根据网关ID模糊查询其对应的网关列表
	 * @param deviceid
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByDeviceid(String deviceid){
		List<String> deviceiddeviceidlist = new ArrayList<String>();
		List<GatewayPO> gatewaylist = gd.findByDeviceidContaining(deviceid);
		if(gatewaylist.size()==0){
			return null;
		}
		for (GatewayPO gatewayPO : gatewaylist) {
			deviceiddeviceidlist.add(gatewayPO.getDeviceid());
		}
		return deviceiddeviceidlist;
	}
	/**
	 * 根据deviceid获取网关详细信息
	 * @param deviceid
	 * @return
	 */
	@Transactional(readOnly = true)
	public GatewayDetailVO findByDeviceid(String deviceid) {
		GatewayDetailVO gate = new GatewayDetailVO();
		GatewayPO gateway = gd.findByDeviceid(deviceid);
		//网关信息
		gate.setName(gateway.getName());
		gate.setModel(gateway.getModel());
		gate.setDeviceid(deviceid);
		gate.setFirmwareversion(gateway.getFirmwareversion());
		gate.setStatus(gateway.getStatus());
		//TODO 业务状态未知
		gate.setCreatetime(gateway.getCreatetime());
		List<GatewayUserPO> gu = gud.findByDeviceid(deviceid);
		if(gu!=null&&gu.size()!=0) {//网关用户表匹配到了数据，该网关已跟用户绑定
			UserPO user = ud.findByUserid(gu.get(0).getUserid());
			gate.setCustomer(user.getLoginname());
			if(user.getInstallerid()!=null) {
				EmployeePO emp = ed.findByEmployeeid(user.getInstallerid());
				gate.setInstaller(emp.getLoginname());
			}
			//电话卡信息
			PhonecardUserPO puPO = pud.findByUserid(user.getUserid());
			if(puPO!=null) {
				PhonecardPO pc = pd.findByPhonecardid(puPO.getPhonecardid());
				if(pc!=null) {
					gate.setPhonecardserialnumber(pc.getSerialnumber());
					gate.setPhonecardmodel(pc.getModel());
					gate.setPhonecardstatus(pc.getStatus());
					gate.setRateplan(pc.getRateplan());
					gate.setPhonecardfirmwareversion(pc.getFirmwareversion());
					gate.setActivationdate(pc.getActivationdate());
					gate.setFirstprogrammedon(pc.getFirstprogrammedondate());
					gate.setLastprogrammedon(pc.getLastprogrammedondate());
					gate.setOrderingdate(pc.getOrderingdate());
					gate.setExpiredate(pc.getExpiredate());
				}
			}
		}
		List<ZwaveDevicePO> zdlist = zdd.findByDeviceid(deviceid);
		gate.setDevice(zdlist);
		return gate;
	}
	/**
	 * 根据操作和id数组修改状态
	 * @param hope
	 * @param ids
	 */
	@Transactional
	public void updateGatewayStatus(String hope, Object[] ids) {
		if("start".equals(hope)){
			for (Object string : ids) {
				GatewayPO gatewaypo = gd.findByDeviceid(string);
				gatewaypo.setStatus(Constants.STATUS_ONLINE);
				gd.save(gatewaypo);
			}
		}else if("stop".equals(hope)){
			for (Object string : ids) {
				GatewayPO gatewaypo = gd.findByDeviceid(string);
				gatewaypo.setStatus(Constants.STATUS_OFFLINE);
				gd.save(gatewaypo);
			}
		}
	}


}
