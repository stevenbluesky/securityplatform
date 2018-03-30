package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.vo.GatewayDetailVO;
import cn.com.isurpass.house.vo.TypeGatewayInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

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
	/**
	 * 新增网关信息
	 * @param tgi
	 * @throws MyArgumentNullException
	 */
	@Transactional
	public void add(TypeGatewayInfoVO tgi) throws MyArgumentNullException {
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
	}
	/**
	 * 根据搜索条件获取网关分页信息列表
	 * @param pageable
	 * @param tgiv
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> listGateway(Pageable pageable, TypeGatewayInfoVO tgiv) {
		Map<String, Object> map = new HashMap<>();//返回的map
		List<TypeGatewayInfoVO> list = new ArrayList<>();//返回的list对象
		/*if(StringUtils.isEmpty(tgiv.getCityname())&&StringUtils.isEmpty(tgiv.getCitycode())&&StringUtils.isEmpty(tgiv.getName())&&StringUtils.isEmpty(tgiv.getCustomer())&&StringUtils.isEmpty(tgiv.getServiceprovider())&&StringUtils.isEmpty(tgiv.getInstallerorg())&&StringUtils.isEmpty(tgiv.getInstaller())&&StringUtils.isEmpty(tgiv.getDeviceid())){
			Page<GatewayPO> gateList = gd.findAll(pageable);
			map.put("total", gd.count());
	    	gateList.forEach(o -> {
	    		TypeGatewayInfoVO gateVO = new TypeGatewayInfoVO();
	    		gateVO.setDeviceid(o.getDeviceid());
	    		gateVO.setName(o.getName());
	    		gateVO.setStatus(o.getStatus());
	    		gateVO.setModel(o.getModel());
	    		gateVO.setBattery(o.getBattery());
	    		gateVO.setFirmwareversion(o.getFirmwareversion());
	    		list.add(gateVO);
	    	});
	    	map.put("rows", list);
			return map;
		}*/
		List<String> citynamedeviceidlist = new ArrayList<String>();
		List<String> citycodedeviceidlist = new ArrayList<String>();
		List<String> devicenamedeviceidlist = new ArrayList<String>();
		List<String> customerdeviceidlist = new ArrayList<String>();
		List<String> serviceproviderdeviceidlist = new ArrayList<String>();
		List<String> installerorgdeviceidlist = new ArrayList<String>();
		List<String> installerdeviceidlist = new ArrayList<String>();
		List<String> deviceiddeviceidlist = new ArrayList<String>();

		citynamedeviceidlist = findIdListByCityname(tgiv.getCityname());
		citycodedeviceidlist = findIdListByCitycode(tgiv.getCitycode());
		devicenamedeviceidlist = findIdListByDevicename(tgiv.getName());
		customerdeviceidlist = findIdListByCustomer(tgiv.getCustomer());
		serviceproviderdeviceidlist = findIdListByServiceprovider(tgiv.getServiceprovider());
		installerorgdeviceidlist = findIdListByInstallerorg(tgiv.getInstallerorg());
		installerdeviceidlist = findIdListByInstaller(tgiv.getInstaller());
		deviceiddeviceidlist = findIdListByDeviceid(tgiv.getDeviceid());

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
		List<GatewayPO> gateList = gd.findByDeviceidIn(pageable,citynamedeviceidlist);//从网关表中拿基本数据
		map.put("total", gateList.size());

		gateList.forEach(o -> {
			TypeGatewayInfoVO gateVO = new TypeGatewayInfoVO();
			gateVO.setDeviceid(o.getDeviceid());
			List<GatewayUserPO> userlist = gud.findByDeviceid(o.getDeviceid());//TODO
			UserPO upo= ud.findByUserid(userlist.get(0).getUserid());

			gateVO.setCityname(cd.findByCitycode(upo.getCitycode()).getCityname());
			gateVO.setInstallerorg(od.findByOrganizationid(upo.getInstallerorgid()).getName());
			gateVO.setInstaller(ed.findByEmployeeid(upo.getInstallerid()).getName());
			gateVO.setServiceprovider(od.findByOrganizationid(upo.getOrganizationid()).getName());
			gateVO.setCustomer(upo.getName());
			gateVO.setName(o.getName());
			gateVO.setStatus(o.getStatus());
			gateVO.setModel(o.getModel());
			gateVO.setBattery(o.getBattery());
			gateVO.setFirmwareversion(o.getFirmwareversion());
			list.add(gateVO);
		});
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
		if(StringUtils.isEmpty(cityname)){
			cityname = "";
		}
		List<String> citynamedeviceidlist = new ArrayList<String>();
		List<String> codelist = new ArrayList<String>();
		List<Integer> citynameuseridlist = new ArrayList<Integer>();
		List<CityPO> findByCityname = cd.findByCitynameContaining(cityname);
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
		if(StringUtils.isEmpty(citycode)){
			citycode = "";
		}
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
	/**
	 * 根据设备名称模糊查询对应网关id列表
	 * @param devicename
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByDevicename(String devicename){
		if(StringUtils.isEmpty(devicename)){
			devicename = "";
		}
		List<String> devicenamedeviceidlist = new ArrayList<String>();
		List<ZwaveDevicePO> findByNameContaining = zdd.findByNameContaining(devicename);
		if(findByNameContaining.size()==0){
			return null;
		}
		for (ZwaveDevicePO zwaveDevicePO : findByNameContaining) {
			devicenamedeviceidlist.add(zwaveDevicePO.getDeviceid());
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
		if(StringUtils.isEmpty(customer)){
			customer = "";
		}
		List<String> customerdeviceidlist = new ArrayList<String>();
		List<Integer> customeruseridlist = new ArrayList<Integer>();
		List<UserPO> userlist = ud.findByLoginnameContainingOrNameContaining(customer,customer);
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
		if(StringUtils.isEmpty(serviceprovider)){
			serviceprovider = "";
		}
		List<String> serviceproviderdeviceidlist = new ArrayList<String>();
		List<Integer> serviceprovideridlist = new ArrayList<Integer>();
		List<OrganizationPO> orglist = od.findByOrgtypeAndNameContainingOrCentralstationnameContaining(Constants.ORGTYPE_SUPPLIER,serviceprovider,serviceprovider);
		if(orglist.size()==0){
			return null;
		}
		for (OrganizationPO organizationPO : orglist) {
			serviceprovideridlist.add(organizationPO.getOrganizationid());
		}
		List<GatewayBindingPO> gatewaybindinglist = gbd.findAllByOrganizationidInAndBindingtype(serviceprovideridlist,Constants.ORGTYPE_SUPPLIER);
		if(gatewaybindinglist.size()==0){
			return null;
		}
		for (GatewayBindingPO gatewayBindingPO : gatewaybindinglist) {
			serviceproviderdeviceidlist.add(gatewayBindingPO.getDeviceid());
		}
		return serviceproviderdeviceidlist;
	}
	/**
	 * 根据安装商名称模糊查询其拥有的网关列表
	 * @param installerorg
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByInstallerorg(String installerorg){
		if(StringUtils.isEmpty(installerorg)){
			installerorg = "";
		}
		List<String> installerorgdeviceidlist = new ArrayList<String>();
		List<Integer> installerorgidlist = new ArrayList<Integer>();
		List<OrganizationPO> orglist2 = od.findByOrgtypeAndNameContainingOrCentralstationnameContaining(Constants.ORGTYPE_INSTALLER,installerorg,installerorg);
		if(orglist2.size()==0){
			return null;
		}
		for (OrganizationPO organizationPO : orglist2) {
			installerorgidlist.add(organizationPO.getOrganizationid());
		}
		List<GatewayBindingPO> gatewaybindinglist2 = gbd.findAllByOrganizationidInAndBindingtype(installerorgidlist,Constants.ORGTYPE_INSTALLER);
		if(gatewaybindinglist2.size()==0){
			return null;
		}
		for (GatewayBindingPO gatewayBindingPO : gatewaybindinglist2) {
			installerorgdeviceidlist.add(gatewayBindingPO.getDeviceid());
		}
		return installerorgdeviceidlist;
	}
	/**
	 * 根据安装员名称模糊查询其安装的网关列表
	 * @param installer
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByInstaller(String installer){
		if(StringUtils.isEmpty(installer)){
			installer = "";
		}
		List<String> installerdeviceidlist = new ArrayList<String>();
		List<Integer> employeeidlist = new ArrayList<Integer>();
		List<Integer> installeridlist = new ArrayList<Integer>();
		List<EmployeePO> emplist = ed.findByLoginnameContainingOrNameContaining(installer,installer);
		if(emplist.size()==0){
			return null;
		}
		for (EmployeePO employeePO : emplist) {
			employeeidlist.add(employeePO.getEmployeeid());
		}
		List<UserPO> userlist2 = ud.findByInstalleridIn(employeeidlist);
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
		if(StringUtils.isEmpty(deviceid)){
			deviceid = "";
		}
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
		UserPO user = ud.findByUserid(gu.get(0).getUserid());
		gate.setCustomer(user.getName());
		EmployeePO emp = ed.findByEmployeeid(user.getInstallerid());
		gate.setInstaller(emp.getName());
		//电话卡信息
		PhonecardUserPO puPO = pud.findByUserid(user.getUserid());
		PhonecardPO pc = pd.findByPhonecardid(puPO.getPhonecardid());
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
