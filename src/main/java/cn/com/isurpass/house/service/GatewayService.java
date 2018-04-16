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

import javax.servlet.http.HttpServletRequest;
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
		Map<String, Object> map = new HashMap<>();//返回的map
		List<TypeGatewayInfoVO> list = new ArrayList<>();//返回的list对象
		Integer organizationid = emp.getOrganizationid();
		OrganizationPO org = od.findByOrganizationid(organizationid);//当前用户所属机构
		if(org.getOrgtype()==Constants.ORGTYPE_SUPPLIER){//为服务商,拿本身及旗下安装商的网关
			List<Object[]> obj = gd.findInfoBySupplier(organizationid,pageable);
			count = gd.countBySupplier(organizationid);
			list = newtransfer(obj);
		}else if(org.getOrgtype()==Constants.ORGTYPE_INSTALLER){//为安装商，拿自己的网关
			List<EmployeeRolePO> elist = employeeroleDAO.findByEmployeeid(emp.getEmployeeid());
			List<Integer> emprolelist = new ArrayList<>();
			elist.forEach(single ->{emprolelist.add(single.getRoleid());});
			if(emprolelist.contains(Constants.ROLE_INSTALLER)&&emprolelist.size()==1){//只是安装员id
				List<Object[]> obj = gd.findInfoByInstaller(emp.getEmployeeid(),pageable);
				count = gd.countByInstaller(emp.getEmployeeid());
				list = newtransfer(obj);
			}else {
				List<Object[]> obj = gd.findInfoByInstallerorg(emp.getEmployeeid(),pageable);
				count = gd.countByInstallerorg(emp.getEmployeeid());
				list = newtransfer(obj);
			}
		}else{//为ameta管理员，拿所有网关
			List<Object[]> obj = gd.findInforByPage(pageable);
			count = gd.count();
			list = newtransfer(obj);
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
		List<String> orgglist = new ArrayList<>();//根据用户所属机构查找出来的网关id集合
		Integer organizationid = emp.getOrganizationid();
		OrganizationPO org = od.findByOrganizationid(organizationid);
		if(org.getOrgtype()==Constants.ORGTYPE_SUPPLIER){//为服务商,拿本身及旗下安装商的网关
			orgglist = gud.findDeviceidByOwnSupplier(organizationid,Constants.STATUS_NORMAL,Constants.ORGTYPE_SUPPLIER);
		}else if(org.getOrgtype()==Constants.ORGTYPE_INSTALLER){//为安装商，拿自己的网关
			List<EmployeeRolePO> elist = employeeroleDAO.findByEmployeeid(emp.getEmployeeid());
			List<Integer> emprolelist = new ArrayList<>();
			elist.forEach(single ->{emprolelist.add(single.getRoleid());});
			if(emprolelist.contains(Constants.ROLE_INSTALLER)&&emprolelist.size()==1){//只是安装员id
				orgglist = gud.findDeviceidByOwnInstaller(emp.getEmployeeid());
			}else {
				orgglist = gud.findDeviceidByOwnInstallerorg(org.getOrganizationid());
			}
		}else{//为ameta管理员，拿所有网关
				orgglist.add("-2");
		}
		List<String > tlist = new ArrayList<>();//标记作用，当搜索条件为空时，标记
		tlist.add("-1");
		//如果传入参数为空，则默认查网关列表的全部
		List<String> citynamedeviceidlist = StringUtils.isEmpty(tgiv.getCityname())?tlist:findIdListByCityname(tgiv.getCityname());
		List<String> citycodedeviceidlist = StringUtils.isEmpty(tgiv.getCitycode())?tlist:findIdListByCitycode(tgiv.getCitycode());
		List<String> devicenamedeviceidlist = StringUtils.isEmpty(tgiv.getName())?tlist:findIdListByDevicename(tgiv.getName());
		List<String> customerdeviceidlist = StringUtils.isEmpty(tgiv.getCustomer())?tlist:findIdListByCustomer(tgiv.getCustomer());
		List<String> serviceproviderdeviceidlist = StringUtils.isEmpty(tgiv.getServiceprovider())?tlist:findIdListByServiceprovider(tgiv.getServiceprovider());
		List<String> installerorgdeviceidlist = StringUtils.isEmpty(tgiv.getInstallerorg())?tlist:findIdListByInstallerorg(tgiv.getInstallerorg());
		List<String> installerdeviceidlist = StringUtils.isEmpty(tgiv.getInstaller())?tlist:findIdListByInstaller(tgiv.getInstaller());
		List<String> deviceiddeviceidlist = StringUtils.isEmpty(tgiv.getDeviceid())?tlist:findIdListByDeviceid(tgiv.getDeviceid());
		//当有任意一个集合为空时，返回空数据
		if(citynamedeviceidlist==null||devicenamedeviceidlist==null||serviceproviderdeviceidlist==null||installerdeviceidlist==null||citycodedeviceidlist==null||customerdeviceidlist==null||installerorgdeviceidlist==null||deviceiddeviceidlist==null){
			map.put("total", 0);
			map.put("rows", list);
			return map;
		}
		//求8个网关id集合的交集
		List<String> result = reretain(citynamedeviceidlist,devicenamedeviceidlist,serviceproviderdeviceidlist,installerdeviceidlist,citycodedeviceidlist,customerdeviceidlist,installerorgdeviceidlist,deviceiddeviceidlist);
		//当用户为ameta管理员时，不进行筛选，否则筛选出自己的网关列表
		if(orgglist.size()==1&&orgglist.get(0).equals("-2")){
		}else {
			result.retainAll(orgglist);
		}
		List<GatewayPO> gateList = gd.findByDeviceidIn(pageable,result);//从网关表中拿基本数据
		map.put("total",gd.countByDeviceidIn(result));
		//填充数据
		list =transfer(gateList);
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
		List<String> citynamedeviceidlist = gud.findDeviceidByCityname("%"+cityname+"%");
		return citynamedeviceidlist.size()==0?null:citynamedeviceidlist;
	}
	/**
	 * 根据城市代码模糊查询网关id列表
	 * @param citycode
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByCitycode(String citycode){
		List<String> citycodedeviceidlist = gud.findDeviceidByCitycode("%"+citycode+"%");
		return citycodedeviceidlist.size()==0?null:citycodedeviceidlist;
	}
	/**
	 * 根据设备名称模糊查询对应网关id列表
	 * @param devicename
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByDevicename(String devicename){
		List<String> devicenamedeviceidlist = gud.findDeviceidByDevicename("%"+devicename+"%");
		return devicenamedeviceidlist.size()==0?null:devicenamedeviceidlist;
	}
	/**
	 * 根据客户名称模糊查询其拥有的网关列表
	 * @param customer
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByCustomer(String customer){
		List<String> customerdeviceidlist = gud.findDeviceidByCustomer("%"+customer+"%");
		return customerdeviceidlist.size()==0?null:customerdeviceidlist;
	}
	/**
	 * 根据服务商名称模糊查询其拥有的网关列表
	 * @param serviceprovider
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByServiceprovider(String serviceprovider){
		List<String> serviceproviderdeviceidlist = gud.findDeviceidBySupplier("%"+serviceprovider+"%",Constants.STATUS_NORMAL,Constants.ORGTYPE_SUPPLIER);
		return serviceproviderdeviceidlist.size()==0?null:serviceproviderdeviceidlist;
	}
	/**
	 * 根据安装商名称模糊查询其拥有的网关列表
	 * @param installerorg
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByInstallerorg(String installerorg){
		List<String> installerorgdeviceidlist = gud.findDeviceidByInstallerorg("%"+installerorg+"%",Constants.ORGTYPE_INSTALLER,Constants.STATUS_NORMAL);
		return installerorgdeviceidlist.size()==0?null:installerorgdeviceidlist;
	}
	/**
	 * 根据安装员名称模糊查询其安装的网关列表
	 * @param installer
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByInstaller(String installer){//貌似不能再优化了
		List<String> installerdeviceidlist = gud.findDeviceidByInstaller("%"+installer+"%",Constants.ROLE_INSTALLER,Constants.STATUS_NORMAL);
		return installerdeviceidlist.size()==0?null:installerdeviceidlist;
	}
	/**
	 * 根据网关ID模糊查询其对应的网关列表
	 * @param deviceid
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByDeviceid(String deviceid){
		List<String> deviceiddeviceidlist = gd.findDeviceidByDeviceid("%"+deviceid+"%");
		return deviceiddeviceidlist.size()==0?null:deviceiddeviceidlist;
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
	/**
	 * 填充数据
	 * @param obj
	 * @return
	 */
	private List<TypeGatewayInfoVO> newtransfer(List<Object[]> obj){
		List<TypeGatewayInfoVO> list = new ArrayList<>();//返回的list对象
		for(Object[] o:obj){
			TypeGatewayInfoVO gateVO = new TypeGatewayInfoVO();
			gateVO.setDeviceid((String) o[0]);
			gateVO.setName((String) o[1]);
			gateVO.setStatus((Integer) o[2]);
			gateVO.setCustomer((String) o[3]);
			gateVO.setCityname((String) o[4]);
			gateVO.setServiceprovider((String) o[5]);
			gateVO.setInstallerorg((String) o[6]);
			gateVO.setInstaller((String) o[7]);
			list.add(gateVO);
		}

		return list;
	}
	/**
	 * 填充数据
	 * @param gateList
	 * @return
	 */
	private List<TypeGatewayInfoVO> transfer(List<GatewayPO> gateList){
		List<TypeGatewayInfoVO> list = new ArrayList<>();//返回的list对象
		for(GatewayPO o : gateList) {//使用for循环比lamaba表达式提高50ms响应,根据目前数据库数据少，耗时占总时间1/3左右
			TypeGatewayInfoVO gateVO = new TypeGatewayInfoVO();
			gateVO.setDeviceid(o.getDeviceid());
			List<GatewayUserPO> userlist = gud.findByDeviceid(o.getDeviceid());
			if (userlist != null && userlist.size() != 0) {//网关用户表匹配到了数据，该网关已跟用户绑定
				Integer userid = userlist.get(0).getUserid();
				UserPO upo = ud.findUserByUserid(userid);
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
		return list;
	}
	/**
	 * 传递8个搜索条件查询出来的网关id集合，进行交集处理
	 * @param list1
	 * @param list2
	 * @param list3
	 * @param list4
	 * @param list5
	 * @param list6
	 * @param list7
	 * @param list8
	 * @return
	 */
	private List<String> reretain(List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, List<String> list7, List<String> list8){
		List<Object> list = new ArrayList<>();
		if(!"-1".equals(list1.get(0))){//有搜索条件，有搜索结果
			list.add(list1);
		}
		if(!"-1".equals(list2.get(0))){
			list.add(list2);
		}
		if(!"-1".equals(list3.get(0))){
			list.add(list3);
		}
		if(!"-1".equals(list4.get(0))){
			list.add(list4);
		}
		if(!"-1".equals(list5.get(0))){
			list.add(list5);
		}if(!"-1".equals(list6.get(0))){
			list.add(list6);
		}
		if(!"-1".equals(list7.get(0))){
			list.add(list7);
		}
		if(!"-1".equals(list8.get(0))){
			list.add(list8);
		}
		if(list.size()==0) {
			return null;
		}else if(list.size()==1){
			return (List<String>) list.get(0);
		}else {
			List<String> listlist = (List<String>) list.get(0);
			for (int i = 1; i < list.size(); i++) {
				List<String> temlist = (List<String>) list.get(i);
				listlist.retainAll(temlist);
			}
			return listlist;
		}
	}

}
