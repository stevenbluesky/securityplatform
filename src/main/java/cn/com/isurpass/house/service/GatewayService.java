package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.request.HttpsUtils;
import cn.com.isurpass.house.util.BeanCopyUtils;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.RemoveDuplicate;
import cn.com.isurpass.house.vo.*;
import com.alibaba.fastjson.JSONObject;
import com.jhlabs.vecmath.Tuple3f;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
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
	private PhonecardService phonecardService;
	@Autowired
	private OrganizationService os;
	@Autowired
	private EmployeeroleDAO employeeroleDAO;
	@Autowired
	private ZwaveSubDeviceDAO zsddao;
	@Autowired
	private GatewayPhonecardDAO gpDAO;
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
			//如果是报警中心，拿所有选了其报警服务的用户的网关和自己旗下的网关
			int monitorcount = ud.countByMonitoringstationid(organizationid);
			if(monitorcount>0){
				List<Object[]> msobj = gd.findAllGatewayByMonitoringStation(organizationid,pageable);
				count = gd.countByMonitoringStation(organizationid);
				list = newtransfer(msobj);
			}else {
				List<Object[]> obj = gd.findInfoBySupplier(organizationid, pageable);
				count = gd.countBySupplier(organizationid);
				list = newtransfer(obj);
			}
		}else if(org.getOrgtype()==Constants.ORGTYPE_INSTALLER){//为安装商，拿自己的网关
			List<EmployeeRolePO> elist = employeeroleDAO.findByEmployeeid(emp.getEmployeeid());
			List<Integer> emprolelist2 = new ArrayList<>();
			elist.forEach(single ->{emprolelist2.add(single.getRoleid());});
			List emprolelist = RemoveDuplicate.removeDuplicate(emprolelist2);
			if(emprolelist.contains(Constants.ROLE_INSTALLER)&&emprolelist.size()==1){//只是安装员id
				List<Object[]> obj = gd.findInfoByInstaller(emp.getEmployeeid(),pageable);
				count = gd.countByInstaller(emp.getEmployeeid());
				list = newtransfer(obj);
			}else {
				List<Object[]> obj = gd.findInfoByInstallerorg(organizationid, pageable);
				count = gd.countByInstallerorg(organizationid);
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
			int count =ud.countByMonitoringstationid(organizationid);
			if(count>0){//为告警中心
				orgglist = gud.findDeviceidByMonitoringStation(organizationid);
			}else {
				orgglist = gud.findDeviceidByOwnSupplier(organizationid, Constants.STATUS_NORMAL, Constants.ORGTYPE_SUPPLIER);
			}
		}else if(org.getOrgtype()==Constants.ORGTYPE_INSTALLER){//为安装商，拿自己的网关
			List<EmployeeRolePO> elist = employeeroleDAO.findByEmployeeid(emp.getEmployeeid());
			List<Integer> emprolelist2 = new ArrayList<>();
			elist.forEach(single ->{emprolelist2.add(single.getRoleid());});
			List<Integer> emprolelist = RemoveDuplicate.removeDuplicate(emprolelist2);
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
		List<String> starttimedeviceidlist = StringUtils.isEmpty(tgiv.getStarttime())?tlist:findIdListByStarttime(tgiv.getStarttime());
		List<String> endtimedeviceidlist = StringUtils.isEmpty(tgiv.getEndtime())?tlist:findIdListByEndtime(tgiv.getEndtime());
		List<String> statusdeviceidlist = StringUtils.isEmpty(tgiv.getStatus())?tlist:findIdListByStatus(tgiv.getStatus());
		//当有任意一个集合为空时，返回空数据
		if (citynamedeviceidlist == null || devicenamedeviceidlist == null || serviceproviderdeviceidlist == null || installerdeviceidlist == null
				||citycodedeviceidlist == null || customerdeviceidlist == null || installerorgdeviceidlist == null || deviceiddeviceidlist == null
				||starttimedeviceidlist == null || endtimedeviceidlist == null || statusdeviceidlist == null) {
			map.put("total", 0);
			map.put("rows", list);
			return map;
		}
		//求8个网关id集合的交集
		List<String> result = reretain(citynamedeviceidlist,devicenamedeviceidlist,serviceproviderdeviceidlist,installerdeviceidlist,citycodedeviceidlist,
				customerdeviceidlist,installerorgdeviceidlist,deviceiddeviceidlist,starttimedeviceidlist,endtimedeviceidlist,statusdeviceidlist);
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

	@Transactional(readOnly = true)
	public List<GatewayInfoVO> listNUllGatewayData(EmployeePO emp) {
		List<GatewayInfoVO> list = new ArrayList<>();//返回的list对象
		Integer organizationid = emp.getOrganizationid();
		OrganizationPO org = od.findByOrganizationid(organizationid);//当前用户所属机构
		if(org.getOrgtype()==Constants.ORGTYPE_SUPPLIER){//为服务商,拿本身及旗下安装商的网关
			//如果是报警中心，拿所有选了其报警服务的用户的网关和自己旗下的网关
			int monitorcount = ud.countByMonitoringstationid(organizationid);
			if(monitorcount>0){
				List<Object[]> msobj = gd.findAllGatewayByMonitoringStation(organizationid);
				list = newtransfer2(msobj);
			}else {
				List<Object[]> obj = gd.findInfoBySupplier(organizationid);
				list = newtransfer2(obj);
			}
		}else if(org.getOrgtype()==Constants.ORGTYPE_INSTALLER){//为安装商，拿自己的网关
			List<EmployeeRolePO> elist = employeeroleDAO.findByEmployeeid(emp.getEmployeeid());
			List<Integer> emprolelist2 = new ArrayList<>();
			elist.forEach(single ->{emprolelist2.add(single.getRoleid());});
			List emprolelist = RemoveDuplicate.removeDuplicate(emprolelist2);
			if(emprolelist.contains(Constants.ROLE_INSTALLER)&&emprolelist.size()==1){//只是安装员id
				List<Object[]> obj = gd.findInfoByInstaller(emp.getEmployeeid());
				list = newtransfer2(obj);
			}else {
				List<Object[]> obj = gd.findInfoByInstallerorg(organizationid);
				list = newtransfer2(obj);
			}
		}else{//为ameta管理员，拿所有网关
			List<Object[]> obj = gd.findAllGatewayInfo();
			list = newtransfer2(obj);
		}
		return list;
	}
    @Transactional(readOnly = true)
    public List<GatewayInfoVO> listGatewayData(TypeGatewayInfoVO tgiv, EmployeePO emp) {
        List<GatewayInfoVO> list = new ArrayList<>();//返回的list对象
        List<String> orgglist = new ArrayList<>();//根据用户所属机构查找出来的网关id集合
        Integer organizationid = emp.getOrganizationid();
        OrganizationPO org = od.findByOrganizationid(organizationid);
        if(org.getOrgtype()==Constants.ORGTYPE_SUPPLIER){//为服务商,拿本身及旗下安装商的网关
            int count =ud.countByMonitoringstationid(organizationid);
            if(count>0){//为告警中心
                orgglist = gud.findDeviceidByMonitoringStation(organizationid);
            }else {
                orgglist = gud.findDeviceidByOwnSupplier(organizationid, Constants.STATUS_NORMAL, Constants.ORGTYPE_SUPPLIER);
            }
        }else if(org.getOrgtype()==Constants.ORGTYPE_INSTALLER){//为安装商，拿自己的网关
            List<EmployeeRolePO> elist = employeeroleDAO.findByEmployeeid(emp.getEmployeeid());
            List<Integer> emprolelist2 = new ArrayList<>();
            elist.forEach(single ->{emprolelist2.add(single.getRoleid());});
            List<Integer> emprolelist = RemoveDuplicate.removeDuplicate(emprolelist2);
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
        List<String> starttimedeviceidlist = StringUtils.isEmpty(tgiv.getStarttime())?tlist:findIdListByStarttime(tgiv.getStarttime());
        List<String> endtimedeviceidlist = StringUtils.isEmpty(tgiv.getEndtime())?tlist:findIdListByEndtime(tgiv.getEndtime());
        List<String> statusdeviceidlist = StringUtils.isEmpty(tgiv.getStatus())?tlist:findIdListByStatus(tgiv.getStatus());
        //当有任意一个集合为空时，返回空数据
        if (citynamedeviceidlist == null || devicenamedeviceidlist == null || serviceproviderdeviceidlist == null || installerdeviceidlist == null
                ||citycodedeviceidlist == null || customerdeviceidlist == null || installerorgdeviceidlist == null || deviceiddeviceidlist == null
                ||starttimedeviceidlist == null || endtimedeviceidlist == null || statusdeviceidlist == null) {
            return list;
        }
        //求8个网关id集合的交集
        List<String> result = reretain(citynamedeviceidlist,devicenamedeviceidlist,serviceproviderdeviceidlist,installerdeviceidlist,citycodedeviceidlist,
                customerdeviceidlist,installerorgdeviceidlist,deviceiddeviceidlist,starttimedeviceidlist,endtimedeviceidlist,statusdeviceidlist);
        //当用户为ameta管理员时，不进行筛选，否则筛选出自己的网关列表
        if(orgglist.size()==1&&orgglist.get(0).equals("-2")){
        }else {
            result.retainAll(orgglist);
        }
        List<GatewayPO> gateList = gd.findByDeviceidIn(result);//从网关表中拿基本数据
        //填充数据
        list =transfer2(gateList);
        return list;
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
	public List<String> findIdListByInstaller(String installer){
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
	 * 根据网关绑定起始时间查询其对应的网关列表
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByStarttime(Date starttime){
		List<String> starttimedeviceidlist = gud.findDeviceidByStarttime(starttime);
		return starttimedeviceidlist.size()==0?null:starttimedeviceidlist;
	}
	/**
	 * 根据网关绑定起始时间查询其对应的网关列表
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByEndtime(Date endtime){
		List<String> endtimedeviceidlist = gud.findDeviceidByEndtime(endtime);
		return endtimedeviceidlist.size()==0?null:endtimedeviceidlist;
	}
	/**
	 * 根据网关状态查询其对应的网关列表
	 */
	@Transactional(readOnly = true)
	public List<String> findIdListByStatus(int status){
		List<String> statusdeviceidlist = gd.findDeviceidByStatus(status);
		return statusdeviceidlist.size()==0?null:statusdeviceidlist;
	}
	/**
	 * 根据deviceid获取网关详细信息
	 * @param deviceid
	 * @return
	 */
//	@Transactional(readOnly = true)
	public GatewayDetailVO findByDeviceid(String deviceid, Pageable pageable) {
		GatewayDetailVO gate = new GatewayDetailVO();
		GatewayPO gateway = gd.findByDeviceid(deviceid);
		if(gateway==null){
			throw new RuntimeException("wronggateway");
		}
		//网关信息
		gate.setName(gateway.getName());
		gate.setModel(gateway.getModel());
		gate.setDeviceid(deviceid);
		gate.setFirmwareversion(gateway.getFirmwareversion());
		gate.setStatus(gateway.getStatus());

		gate.setCreatetime(gateway.getCreatetime());
		List<GatewayUserPO> gu = gud.findByDeviceid(deviceid);
		if(gu!=null&&gu.size()!=0) {//网关用户表匹配到了数据，该网关已跟用户绑定
			UserPO user = ud.findByUserid(gu.get(0).getUserid());
			gate.setCustomer(user.getAppaccount());
			if(user.getInstallerid()!=null) {
				EmployeePO emp = ed.findByEmployeeid(user.getInstallerid());
				gate.setInstaller(emp.getLoginname());
			}
			//电话卡信息
			//PhonecardUserPO puPO = pud.findByUserid(user.getUserid()).get(0);//TODO 部分网关该处报错导致前端无数据
			GatewayPhonecardPO byDeviceid = gpDAO.findByDeviceid(deviceid);
			if(byDeviceid!=null&&!StringUtils.isEmpty(byDeviceid.getSerialnumber())) {
				PhonecardPO pc = pd.findBySerialnumber(byDeviceid.getSerialnumber());
				//PhonecardPO pc = pd.findByPhonecardid(puPO.getPhonecardid());
				if(pc!=null) {
					gate.setPhonecardid(pc.getPhonecardid());
					gate.setPhonecardserialnumber(pc.getSerialnumber());
					gate.setPhonecardmodel(pc.getModel());
					phonecardService.updatePhonecardStatusByExpiredate(pc);
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
		if (pageable != null) {
			long l = zdd.countByDeviceid(deviceid);
			Page<ZwaveDevicePO> zdlist = zdd.findByDeviceid(deviceid, pageable);
			gate.setTotal(l);
			List<ZwaveDevicePO> list = setProperties(zdlist);
			List<DeviceDetailVO> list2 = new ArrayList<>();
			for(ZwaveDevicePO z:zdlist){
				DeviceDetailVO zz = new DeviceDetailVO();
				try {
					BeanCopyUtils.copyProperties(z,zz);
					int l1 = zsddao.countByZwavedeviceid(z.getZwavedeviceid());
					if(l1>7){
						zz.setArea(1000+l1);
					}
					zz.setChannelcount(String.valueOf(l1));
				} catch (Exception e) {
					e.printStackTrace();
				}
				list2.add(zz);
			}
			gate.setDevice(list2);

		}
		return gate;
	}

	private List<ZwaveDevicePO> setProperties(Page<ZwaveDevicePO> zdlist) {
		List<ZwaveDevicePO> list = new ArrayList<>();
		zdlist.forEach(f ->{
			ZwaveDevicePO z = new ZwaveDevicePO();
			try {
				BeanCopyUtils.copyProperties(f,z);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(z);
		});
		return list;
	}

	/**
	 * 根据操作和id数组修改状态
	 * @param hope
	 * @param ids
	 */
	@Transactional
	public void updateGatewayStatus(String hope, Object[] ids) throws MyArgumentNullException {
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
		}else if("delete".equals(hope)){
			for (Object string : ids) {
				List<GatewayUserPO> byDeviceid = gud.findByDeviceid(string.toString());
				if(byDeviceid.size()>0&&byDeviceid.get(0)!=null){//网关用户表查到了记录，需要解绑
					throw new MyArgumentNullException("-897");
				}else{
					gbd.deleteByDeviceid(string.toString());
					gd.deleteByDeviceid(string.toString());
				}
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
			gateVO.setBindingtime((Date)o[8]);
			list.add(gateVO);
		}
		return list;
	}
    private List<GatewayInfoVO> newtransfer2(List<Object[]> obj){
        List<GatewayInfoVO> list = new ArrayList<>();//返回的list对象
        for(Object[] o:obj){
            GatewayInfoVO gateVO = new GatewayInfoVO();
            gateVO.setDeviceid((String) o[0]);
            gateVO.setName((String) o[1]);
            gateVO.setStatus((Integer) o[2]==1?"Online":"Offline");
            gateVO.setCustomer((String) o[3]);
            gateVO.setCityname((String) o[4]);
            gateVO.setServiceprovider((String) o[5]);
            gateVO.setInstallerorg((String) o[6]);
            gateVO.setInstaller((String) o[7]);
            gateVO.setBindingtime((Date)o[8]);
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
		for(GatewayPO o : gateList) {
			TypeGatewayInfoVO gateVO = new TypeGatewayInfoVO();
			gateVO.setDeviceid(o.getDeviceid());
			List<GatewayUserPO> userlist = gud.findByDeviceid(o.getDeviceid());
			if (userlist != null && userlist.size() != 0) {//网关用户表匹配到了数据，该网关已跟用户绑定
				Integer userid = userlist.get(0).getUserid();
				UserPO upo = ud.findUserByUserid(userid);
				if(upo.getCitycode()==null){
					//gateVO.setCityname("NONE");
				}else {
					gateVO.setCityname(cd.findByCitycode(upo.getCitycode()).getCityname());
				}
				gateVO.setInstallerorg(od.findByOrganizationid(upo.getInstallerorgid()).getName());
				if (upo.getInstallerid() != null) {
					gateVO.setInstaller(ed.findByEmployeeid(upo.getInstallerid()).getLoginname());
				}
				if(od.findByOrganizationid(upo.getOrganizationid())!=null){
					gateVO.setServiceprovider(od.findByOrganizationid(upo.getOrganizationid()).getName());
				}
				gateVO.setCustomer(upo.getAppaccount());
				gateVO.setBindingtime(gud.findByDeviceidAndUserid(o.getDeviceid(),userid).getCreatetime());
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
    private List<GatewayInfoVO> transfer2(List<GatewayPO> gateList){
        List<GatewayInfoVO> list = new ArrayList<>();//返回的list对象
        for(GatewayPO o : gateList) {
            GatewayInfoVO gateVO = new GatewayInfoVO();
            gateVO.setDeviceid(o.getDeviceid());
            List<GatewayUserPO> userlist = gud.findByDeviceid(o.getDeviceid());
            if (userlist != null && userlist.size() != 0) {//网关用户表匹配到了数据，该网关已跟用户绑定
                Integer userid = userlist.get(0).getUserid();
                UserPO upo = ud.findUserByUserid(userid);
                if(upo.getCitycode()==null){
                    //gateVO.setCityname("NONE");
                }else {
                    gateVO.setCityname(cd.findByCitycode(upo.getCitycode()).getCityname());
                }
                gateVO.setInstallerorg(od.findByOrganizationid(upo.getInstallerorgid()).getName());
                if (upo.getInstallerid() != null) {
                    gateVO.setInstaller(ed.findByEmployeeid(upo.getInstallerid()).getLoginname());
                }
                if(od.findByOrganizationid(upo.getOrganizationid())!=null){
                    gateVO.setServiceprovider(od.findByOrganizationid(upo.getOrganizationid()).getName());
                }
                gateVO.setCustomer(upo.getAppaccount());
                gateVO.setBindingtime(gud.findByDeviceidAndUserid(o.getDeviceid(),userid).getCreatetime());
            }
            gateVO.setName(o.getName());
            gateVO.setStatus(o.getStatus()==1?"Online":"Offline");

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
	private List<String> reretain(List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, List<String> list7, List<String> list8,
			List<String> list9, List<String> list10, List<String> list11){
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
		if(!"-1".equals(list9.get(0))){
			list.add(list9);
		}
		if(!"-1".equals(list10.get(0))){
			list.add(list10);
		}
		if(!"-1".equals(list11.get(0))){
			list.add(list11);
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
	@Transactional(readOnly = true)
	public Map<String, Object> findGatewayJsonList(Pageable pageable, TypeGatewayInfoVO tgiv, EmployeePO emp) {
		String deviceid = "%"+tgiv.getDeviceid()+"%";
		String cityname = "%"+tgiv.getCityname()+"%";
		String name = "%"+tgiv.getName()+"%";
		String serviceprovider = "%"+tgiv.getServiceprovider()+"%";
		String installerorg = "%"+tgiv.getInstallerorg()+"%";
		String installer = "%"+tgiv.getInstaller()+"%";
		String customer = "%"+tgiv.getCustomer()+"%";
		long total = 0;
		Map<String,Object> map = new HashMap<>();
		List<Object[]> olist = new ArrayList<>();
		List<TypeGatewayInfoVO>list = new ArrayList<>();
		Integer organizationid = emp.getOrganizationid();
		OrganizationPO loginorg = od.findByOrganizationid(organizationid);
		if(Constants.ORGTYPE_AMETA.equals(loginorg.getOrgtype())){
			olist = gd.findAllGateway(deviceid,cityname,name,serviceprovider,installerorg,installer,customer,pageable);//动态sql传递不进去//gateway表加字段维护繁琐
			total = gd.countAllGateway(deviceid,cityname,name,serviceprovider,installerorg,installer,customer);
		}

		for(Object[] o:olist){
			TypeGatewayInfoVO gatewayVO = new TypeGatewayInfoVO();
			gatewayVO.setDeviceid((String) o[0]);
			gatewayVO.setStatus((Integer) o[1]);
			gatewayVO.setName((String) o[2]);
			gatewayVO.setCityname((String) o[3]);
			gatewayVO.setServiceprovider((String) o[4]);
			gatewayVO.setInstallerorg((String) o[5]);
			gatewayVO.setInstaller((String) o[6]);
			gatewayVO.setCustomer((String) o[7]);
			list.add(gatewayVO);
		}
		map.put("rows", list);
		map.put("total",total);
		return map;
	}

	public String getQrcodeInfo(String deviceid) {
		String qrInfo = HttpsUtils.getQrInfo(deviceid);
		JSONObject jo = JSONObject.parseObject(qrInfo);
		if (jo == null || jo.isEmpty()) {
			return null;
		}
		if(jo.getInteger("resultCode")!=0){
			return null;
		}
		return jo.getString("qrstring");
	}
}
