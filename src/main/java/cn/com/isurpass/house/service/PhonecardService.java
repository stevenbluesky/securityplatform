package cn.com.isurpass.house.service;

import java.text.SimpleDateFormat;
import java.util.*;
import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.util.PhoneCardInterfaceCallUtils;
import cn.com.isurpass.house.util.RemoveDuplicate;
import cn.com.isurpass.house.vo.SimCardSearchVO;
import cn.com.isurpass.house.vo.SimCardVO;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.util.Constants;
import javax.servlet.http.HttpServletRequest;

@Service
public class PhonecardService {
	@Autowired
	private PhonecardDAO pd;
	@Autowired
	private OrganizationDAO organizationDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private PhonecarduserDAO phonecarduserDAO;
	@Autowired
	private OrganizationService os;
	@Autowired
	private EmployeeroleDAO erd;
	/**
	 * 新增网关信息
	 * @param pc
	 * @throws MyArgumentNullException
	 */
	@Transactional(rollbackFor = Exception.class)
	public void add(PhonecardPO pc) throws MyArgumentNullException {
		if (StringUtils.isEmpty(pc.getSerialnumber())){
			throw new MyArgumentNullException("1");
		}
		PhonecardPO pp = pd.findBySerialnumber(pc.getSerialnumber());
		if(pp!=null){
			throw new MyArgumentNullException("2");
		}
		PhonecardPO pcPO = new PhonecardPO();
		// ID => name
		if(StringUtils.isEmpty(pc.getModel())){
			pc.setModel("");
		}
		if(StringUtils.isEmpty(pc.getRateplan())){
			pc.setRateplan("");
		}
		pcPO.setRateplan(pc.getRateplan());
		pcPO.setSerialnumber(pc.getSerialnumber());
		pcPO.setFirmwareversion(pc.getFirmwareversion());
		pcPO.setModel(pc.getModel());
		pcPO.setStatus(Constants.STATUS_INVENTORY);
		pcPO.setActivationdate(pc.getActivationdate());
		pcPO.setOrderingdate(pc.getOrderingdate());
		pcPO.setExpiredate(pc.getExpiredate());
		pcPO.setFirstprogrammedondate(pc.getFirstprogrammedondate());
		pcPO.setLastprogrammedondate(pc.getLastprogrammedondate());

		pd.save(pcPO);
	}
	/**
	 * 根据搜索条件进行分页查询
	 * @param pageable
	 * @param pc
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> listPhonecard(Pageable pageable, SimCardSearchVO pc, HttpServletRequest request) {
		EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
		List<EmployeeRolePO> elist = erd.findByEmployeeid(emp.getEmployeeid());
		List<Integer> emprolelist2 = new ArrayList<>();
		elist.forEach(single ->{emprolelist2.add(single.getRoleid());});
		List<Integer> rolelist = RemoveDuplicate.removeDuplicate(emprolelist2);
		OrganizationPO byOrganizationid = organizationDAO.findByOrganizationid(emp.getOrganizationid());
		Integer orgtype = byOrganizationid.getOrgtype();
		Map<String, Object> map = new HashMap<>();
		Integer status = pc.getStatus();
		List<Integer> statuslist = new ArrayList<Integer>();
		if(Integer.valueOf(2).equals(status)){//查询正常激活记录1
			statuslist.add(1);
		}else if(Integer.valueOf(3).equals(status)){//查询冻结记录 2
			statuslist.add(2);
		}else if(Integer.valueOf(4).equals(status)){//查询未激活记录 3
			statuslist.add(3);
		}else{//查询全部记录（暂不包括已删除记录）
			statuslist.add(1);
			statuslist.add(2);
			statuslist.add(3);
		}
		String rateplan ="";
		String serialnumber ="";
		if(pc.getRateplan()!=null){
			rateplan = "%"+pc.getRateplan()+"%";
		}
		if(pc.getSerialnumber()!=null){
			serialnumber = "%"+pc.getSerialnumber()+"%";
		}
		//有激活时间的搜索
		if(pc.getStarttime()!=null||pc.getEndtime()!=null){
            if (pc.getStarttime() == null) {
                Calendar curr = Calendar.getInstance();
                curr.setTime(new Date());
                curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) - 200);
                pc.setStarttime(curr.getTime());
            }
            if (pc.getEndtime() == null) {
                Calendar curr = Calendar.getInstance();
                curr.setTime(new Date());
                curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 200);
                pc.setEndtime(curr.getTime());
            }
        }
		List<Object[]> resultlist = new ArrayList<>();
		long count = 0;
		List<Integer> list = new ArrayList<>();
		List<PhonecardPO> relist = new ArrayList<>();
		List<Integer> childrenOrgid = os.findChildrenOrgid(emp.getOrganizationid(), list);
		childrenOrgid.add(emp.getOrganizationid());
		if (os.isAdmin(emp.getOrganizationid())) {
            if(pc.getStarttime()==null&&pc.getEndtime()==null){
                resultlist = pd.findByAmeta(statuslist,serialnumber,rateplan,pageable);
                count = pd.countByStatusInAndSerialnumberLikeAndRateplanLikeAndTime(statuslist,serialnumber,rateplan);
            }else {
                resultlist = pd.findByAmeta(statuslist, serialnumber, rateplan, pc.getStarttime(), pc.getEndtime(), pageable);
                count = pd.countByStatusInAndSerialnumberLikeAndRateplanLikeAndTime(statuslist, serialnumber, rateplan, pc.getStarttime(), pc.getEndtime());
            }
		}else if(rolelist.size()==1&&rolelist.get(0)==4){//只有一个安装员的角色，就只拿他安装过的用户
            if(pc.getStarttime()==null&&pc.getEndtime()==null){
                resultlist = pd.findByInstaller(statuslist, serialnumber, rateplan, emp.getEmployeeid(), pageable);
                count = pd.countByInstaller(statuslist, serialnumber, rateplan, emp.getEmployeeid());
            }else {
                resultlist = pd.findByInstaller(statuslist, serialnumber, rateplan, emp.getEmployeeid(),pc.getStarttime(), pc.getEndtime(), pageable);
                count = pd.countByInstaller(statuslist, serialnumber, rateplan, emp.getEmployeeid(),pc.getStarttime(), pc.getEndtime());
            }
		}else if(orgtype==Constants.ORGTYPE_INSTALLER){
            if(pc.getStarttime()==null&&pc.getEndtime()==null){
                resultlist = pd.findByInstallerOrg(statuslist, serialnumber, rateplan, childrenOrgid, pageable);
                count = pd.countByInstallerOrg(statuslist, serialnumber, rateplan, childrenOrgid);
            }else {
                resultlist = pd.findByInstallerOrg(statuslist, serialnumber, rateplan, childrenOrgid,pc.getStarttime(), pc.getEndtime(), pageable);
                count = pd.countByInstallerOrg(statuslist, serialnumber, rateplan, childrenOrgid,pc.getStarttime(), pc.getEndtime());
            }
		}else if(orgtype==Constants.ORGTYPE_SUPPLIER){
            if(pc.getStarttime()==null&&pc.getEndtime()==null){
                resultlist = pd.findBySupplier(statuslist, serialnumber, rateplan, childrenOrgid, pageable);
                count = pd.countBySupplier(statuslist, serialnumber, rateplan, childrenOrgid);
            }else {
                resultlist = pd.findBySupplier(statuslist, serialnumber, rateplan, childrenOrgid,pc.getStarttime(), pc.getEndtime(), pageable);
                count = pd.countBySupplier(statuslist, serialnumber, rateplan, childrenOrgid,pc.getStarttime(), pc.getEndtime());
            }
		}
		for(Object[] o:resultlist){
			PhonecardPO p = new PhonecardPO();
			p.setPhonecardid((Integer)o[0]);
			p.setSerialnumber((String)o[1]);
			p.setStatus((Integer)o[2]);
			p.setModel((String)o[3]);
			p.setFirmwareversion((String)o[4]);
			p.setRateplan((String)o[5]);
			p.setActivationdate((Date) o[6]);
			relist.add(p);
		}
		map.put("total",count);
		map.put("rows", relist);
		return map;
	}


	@Transactional(readOnly = true)
	public List<SimCardVO> listPhonecard(SimCardSearchVO pc, HttpServletRequest request) {
		EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
		List<EmployeeRolePO> elist = erd.findByEmployeeid(emp.getEmployeeid());
		List<Integer> emprolelist2 = new ArrayList<>();
		elist.forEach(single ->{emprolelist2.add(single.getRoleid());});
		List<Integer> rolelist = RemoveDuplicate.removeDuplicate(emprolelist2);
		OrganizationPO byOrganizationid = organizationDAO.findByOrganizationid(emp.getOrganizationid());
		Integer orgtype = byOrganizationid.getOrgtype();
		Integer status = pc.getStatus();
		List<Integer> statuslist = new ArrayList<Integer>();
		if(Integer.valueOf(2).equals(status)){//查询正常激活记录1
			statuslist.add(1);
		}else if(Integer.valueOf(3).equals(status)){//查询冻结记录 2
			statuslist.add(2);
		}else if(Integer.valueOf(4).equals(status)){//查询未激活记录 3
			statuslist.add(3);
		}else{//查询全部记录（暂不包括已删除记录）
			statuslist.add(1);
			statuslist.add(2);
			statuslist.add(3);
		}
		String rateplan ="";
		String serialnumber ="";
		if(pc.getRateplan()!=null){
			rateplan = "%"+pc.getRateplan()+"%";
		}
		if(pc.getSerialnumber()!=null){
			serialnumber = "%"+pc.getSerialnumber()+"%";
		}
        //有激活时间的搜索
        if(pc.getStarttime()!=null||pc.getEndtime()!=null){
            if (pc.getStarttime() == null) {
                Calendar curr = Calendar.getInstance();
                curr.setTime(new Date());
                curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) - 200);
                pc.setStarttime(curr.getTime());
            }
            if (pc.getEndtime() == null) {
                Calendar curr = Calendar.getInstance();
                curr.setTime(new Date());
                curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 200);
                pc.setEndtime(curr.getTime());
            }
        }
		List<Object[]> resultlist = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		List<SimCardVO> relist = new ArrayList<>();
		List<Integer> childrenOrgid = os.findChildrenOrgid(emp.getOrganizationid(), list);
		childrenOrgid.add(emp.getOrganizationid());
		if (os.isAdmin(emp.getOrganizationid())) {
            if(pc.getStarttime()==null&&pc.getEndtime()==null){
                resultlist = pd.findByAmeta(statuslist,serialnumber,rateplan);
            }else{
                resultlist = pd.findByAmeta(statuslist,serialnumber,rateplan,pc.getStarttime(), pc.getEndtime());
            }
		}else if(rolelist.size()==1&&rolelist.get(0)==4){//只有一个安装员的角色，就只拿他安装过的用户
            if(pc.getStarttime()==null&&pc.getEndtime()==null){
                resultlist = pd.findByInstaller(statuslist,serialnumber,rateplan,emp.getEmployeeid());
            }else{
                resultlist = pd.findByInstaller(statuslist,serialnumber,rateplan,emp.getEmployeeid(),pc.getStarttime(), pc.getEndtime());
            }
		}else if(orgtype==Constants.ORGTYPE_INSTALLER){
            if(pc.getStarttime()==null&&pc.getEndtime()==null){
                resultlist = pd.findByInstallerOrg(statuslist,serialnumber,rateplan,childrenOrgid);
            }else{
                resultlist = pd.findByInstallerOrg(statuslist,serialnumber,rateplan,childrenOrgid,pc.getStarttime(), pc.getEndtime());
            }
		}else if(orgtype==Constants.ORGTYPE_SUPPLIER){
            if(pc.getStarttime()==null&&pc.getEndtime()==null){
                resultlist = pd.findBySupplier(statuslist,serialnumber,rateplan,childrenOrgid);
            }else{
                resultlist = pd.findBySupplier(statuslist,serialnumber,rateplan,childrenOrgid,pc.getStarttime(), pc.getEndtime());
            }
		}
		for(Object[] o:resultlist){
			SimCardVO s = new SimCardVO();
			s.setSerialnumber((String)o[1]);
			switch ((Integer)o[2]){
				case 1 :
					s.setStatus("ACTIVATED");
					break;
				case 2 :
					s.setStatus("DEACTIVATED");
					break;
				case 3 :
					s.setStatus("INVENTORY");
					break;
				case 0 :
					s.setStatus("Ready");
					break;
				default:
					s.setStatus("Ready");
					break;
			}
			s.setModel((String)o[3]);
			s.setFirmwareversion((String)o[4]);
			s.setRateplan((String)o[5]);
			s.setActivationdate((Date)o[6]);
			relist.add(s);
		}

		return relist;
	}

	@Transactional(rollbackFor = Exception.class)
	public void updatePhonecardStatus(String hope, Object [] ids, EmployeePO emp,String confirmdelete) throws Exception {
		OrganizationPO org = organizationDAO.findByOrganizationid(emp.getOrganizationid());
		if(!"0".equals(org.getOrgtype()+"")){//如果该员工不是ameta员工，则需要判断其希望修改的电话卡id是否是属于他的公司
			List<UserPO> userlist = userDAO.findByOrganizationid(emp.getOrganizationid());
			List<UserPO> userlist1 = userDAO.findByInstallerorgid(emp.getOrganizationid());
			List<Integer> useridlist = new ArrayList<>();//用户id集合
			List<Integer> pclist = new ArrayList<>();//电话卡id集合
			userlist.forEach(s->{useridlist.add(s.getUserid());});
			userlist1.forEach(s->{useridlist.add(s.getUserid());});
			List<PhonecardUserPO> pulist = phonecarduserDAO.findByUseridIn(useridlist);
			pulist.forEach(s->{pclist.add(s.getPhonecardid());});
			for(Object o : ids){
                PhonecardPO phonecardpo = pd.findByPhonecardid(o);
				if(!pclist.contains(phonecardpo.getSerialnumber())){
					//throw new MyArgumentNullException("You have not access to modify the SIM status!");
				}
			}
		}
		for (Object string : ids) {
			string = Integer.valueOf(String.valueOf(string).replace( ",", ""));
			PhonecardPO phonecardpo = pd.findByPhonecardid(string);
			if("start".equals(hope)){
				updatePhonecardStatusNormal(phonecardpo);
			}else if("freeze".equals(hope)){
				updatePhonecardStatusSuspenced(phonecardpo);
			}else if("delete".equals(hope)){
				if(confirmdelete==null || "".equals(confirmdelete)||"null".equals(confirmdelete)){
					throw new MyArgumentNullException("-899");//请传入字符
				}
				PhonecardUserPO phonecarduser = phonecarduserDAO.findByPhonecardid((Integer) string);
				if(phonecarduser!=null){
					throw new MyArgumentNullException("-898");//请先解绑
				}
				updatePhonecardStatusDeleted(phonecardpo,confirmdelete);
			}else if("synchronous".equals(hope)) {
				synchronousPhonecardStatus(phonecardpo);
			}

		}
	}

	@RequiresPermissions("label.Activated")
	private void updatePhonecardStatusNormal(PhonecardPO phonecardpo) throws Exception {
		PhoneCardInterfaceCallUtils.updateStatus(phonecardpo.getSerialnumber(), Constants.ACTIVATED);
		phonecardpo.setStatus(Constants.STATUS_NORMAL);
		pd.save(phonecardpo);
	}

	private void updatePhonecardStatusSuspenced(PhonecardPO phonecardpo) throws Exception {
		PhoneCardInterfaceCallUtils.updateStatus(phonecardpo.getSerialnumber(),Constants.INVENTORY);
		phonecardpo.setStatus(Constants.STATUS_SUSPENCED);
		pd.save(phonecardpo);
	}

	private void updatePhonecardStatusDeleted(PhonecardPO phonecardpo,String confirmdelete) throws Exception
	{
		if("yes".equalsIgnoreCase(confirmdelete)){
			PhoneCardInterfaceCallUtils.updateStatus(phonecardpo.getSerialnumber(),Constants.INVENTORY);
			//phonecardpo.setStatus(Constants.STATUS_DELETED);
			pd.deleteByPhonecardid(phonecardpo.getPhonecardid());
		}else if("no".equalsIgnoreCase(confirmdelete)){
			//phonecardpo.setStatus(Constants.STATUS_DELETED);
			pd.deleteByPhonecardid(phonecardpo.getPhonecardid());
		}else{
			throw new MyArgumentNullException("-900");
		}
	}
	private void synchronousPhonecardStatus(PhonecardPO phonecardpo) throws Exception {
		String cardInfo = PhoneCardInterfaceCallUtils.interfaceCallGet(phonecardpo.getSerialnumber());
		JSONObject jsStr = JSONObject.parseObject(cardInfo);
		String status = (String)jsStr.get("status");
		if("activated".equalsIgnoreCase(status)){
			phonecardpo.setStatus(Constants.STATUS_NORMAL);
		}else if("activation_ready".equalsIgnoreCase(status)||"inventory".equalsIgnoreCase(status)){
			phonecardpo.setStatus(Constants.STATUS_SUSPENCED);
		}else if("deactivated".equalsIgnoreCase(status)||"retired".equalsIgnoreCase(status)){
			phonecardpo.setStatus(Constants.STATUS_DELETED);
		}
		phonecardpo.setRateplan((String)jsStr.get("ratePlan"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateActivated = (String) jsStr.get("dateActivated");
		if(!StringUtils.isEmpty(dateActivated)){
			dateActivated.substring(0,dateActivated.indexOf("."));
			Date activationdate = sdf.parse(dateActivated);
			phonecardpo.setActivationdate(new Date(activationdate.getTime() - (long) 4 * 60 * 60 * 1000));
		}
		String dateAdded = (String)jsStr.get("dateAdded");
		dateAdded.substring(0,dateAdded.indexOf("."));
		Date firstprogrammedondate = sdf.parse(dateAdded);
		phonecardpo.setFirstprogrammedondate(new Date(firstprogrammedondate.getTime() - (long) 4 * 60 * 60 * 1000));

		String dateUpdated = (String)jsStr.get("dateUpdated");
		dateUpdated.substring(0,dateUpdated.indexOf("."));
		Date lastprogrammedondate = sdf.parse(dateUpdated);
		phonecardpo.setLastprogrammedondate(new Date(lastprogrammedondate.getTime() - (long) 4 * 60 * 60 * 1000));

		pd.save(phonecardpo);
	}
	/**
	 * 在获取电话卡信息时,通过此方法来同步status和exiredate之间的关系
	 */
	public void updatePhonecardStatusByExpiredate(PhonecardPO phonecard){
		if (phonecard == null || phonecard.getExpiredate() == null) {
			return ;
		}
		if (phonecard.getStatus() == Constants.STATUS_NORMAL && new Date().after(phonecard.getExpiredate())) {//当电话卡产状态为正常且当前时间超过失效日期时,
			phonecard.setStatus(Constants.STATUS_SUSPENCED);
			pd.save(phonecard);
		}
	}

    public PhonecardPO findByPhonecardid(String phonecardid) {
		return pd.findByPhonecardid(Integer.parseInt(phonecardid.replace( ",", "")));
    }
}
