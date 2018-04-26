package cn.com.isurpass.house.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.dao.PhonecarduserDAO;
import cn.com.isurpass.house.dao.UserDAO;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.util.PhoneCardInterfaceCallUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.isurpass.house.dao.PhonecardDAO;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.vo.TypeGatewayInfoVO;
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
	/**
	 * 新增网关信息
	 * @param pc
	 * @throws MyArgumentNullException
	 */
	@Transactional(rollbackFor = Exception.class)
	public void add(PhonecardPO pc) throws MyArgumentNullException {
		if (StringUtils.isEmpty(pc.getSerialnumber())|| StringUtils.isEmpty(pc.getModel()) || StringUtils.isEmpty(pc.getRateplan())){
			throw new MyArgumentNullException("1");
		}
		PhonecardPO pp = pd.findBySerialnumber(pc.getSerialnumber());
		if(pp!=null){
			throw new MyArgumentNullException("2");
		}
		PhonecardPO pcPO = new PhonecardPO();
		// ID => name
		pcPO.setRateplan(pc.getRateplan());
		pcPO.setSerialnumber(pc.getSerialnumber());
		pcPO.setFirmwareversion(pc.getFirmwareversion());
		pcPO.setModel(pc.getModel());
		pcPO.setStatus(1);//TODO 暂时默认新增即视为在线状态
		pcPO.setActivationdate(pc.getActivationdate());
		pcPO.setOrderingdate(pc.getOrderingdate());
		pcPO.setExpiredate(pc.getExpiredate());
		pcPO.setFirstprogrammedondate(pc.getFirstprogrammedondate());
		pcPO.setLastprogrammedondate(pc.getLastprogrammedondate());

		System.out.println(pcPO);
		pd.save(pcPO);
	}
	/**
	 * 根据搜索条件进行分页查询
	 * @param pageable
	 * @param pc
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> listPhonecard(Pageable pageable, PhonecardPO pc) {
		Map<String, Object> map = new HashMap<>();
		//map.put("total", pd.count());
		//SELECT * FROM  phonecard WHERE serialnumber LIKE '%f%' AND rateplan LIKE '%f%' AND STATUS=1  ORDER BY activationdate ASC LIMIT 0,9;
		Integer status = pc.getStatus();
		List<Integer> statuslist = new ArrayList<Integer>();
		if(Integer.valueOf(2).equals(status)){//查询正常记录1
			statuslist.add(1);
		}else if(Integer.valueOf(3).equals(status)){//查询冻结记录 2
			statuslist.add(2);
		}else{//查询全部记录（暂不包括已删除记录）
			statuslist.add(1);
			statuslist.add(2);
		}
		Page<PhonecardPO> gateList = pd.findByStatusInAndSerialnumberContainingAndRateplanContaining(statuslist,pc.getSerialnumber(),pc.getRateplan(),pageable);
		long count = pd.countByStatusInAndSerialnumberContainingAndRateplanContaining(statuslist,pc.getSerialnumber(),pc.getRateplan());
		map.put("total",count);
		List<PhonecardPO> list = new ArrayList<>();
		gateList.forEach(o -> {
			list.add(o);
		});
		map.put("rows", list);
		return map;
	}

	@Transactional(rollbackFor = Exception.class)
	public void updatePhonecardStatus(String hope, Object [] ids, EmployeePO emp) throws Exception {
		OrganizationPO org = organizationDAO.findByOrganizationid(emp.getOrganizationid());
		if(!"0".equals(org.getOrgtype()+"")){//如果该员工不是ameta员工，则需要判断其希望修改的电话卡id是否是属于他的公司
			List<UserPO> userlist = userDAO.findByOrganizationid(emp.getOrganizationid());
			List<Integer> useridlist = new ArrayList<>();//用户id集合
			List<Integer> pclist = new ArrayList<>();//电话卡id集合
			userlist.forEach(s->{useridlist.add(s.getUserid());});
			List<PhonecardUserPO> pulist = phonecarduserDAO.findByUseridIn(useridlist);
			pulist.forEach(s->{pclist.add(s.getPhonecardid());});
			for(Object o : ids){
                PhonecardPO phonecardpo = pd.findByPhonecardid(o);
				if(!pclist.contains(phonecardpo.getSerialnumber())){
					throw new MyArgumentNullException("You have not access to modify the SIM status!");
				}
			}
		}
		for (Object string : ids) {
			string = Integer.valueOf(String.valueOf(string));
			PhonecardPO phonecardpo = pd.findByPhonecardid(string);
			if("start".equals(hope)){
				updatePhonecardStatusNormal(phonecardpo);
			}else if("freeze".equals(hope)){
				updatePhonecardStatusSuspenced(phonecardpo);
			}else if("delete".equals(hope)){
				updatePhonecardStatusDeleted(phonecardpo);
			}
			pd.save(phonecardpo);
		}
	}

	@RequiresPermissions("label.Activated")
	private void updatePhonecardStatusNormal(PhonecardPO phonecardpo) throws Exception {
		PhoneCardInterfaceCallUtils.updateStatus(phonecardpo.getSerialnumber(), Constants.ACTIVATED);
		phonecardpo.setStatus(Constants.STATUS_NORMAL);
	}

	private void updatePhonecardStatusSuspenced(PhonecardPO phonecardpo) throws Exception {
		PhoneCardInterfaceCallUtils.updateStatus(phonecardpo.getSerialnumber(),Constants.INVENTORY);
		phonecardpo.setStatus(Constants.STATUS_SUSPENCED);
	}

	private void updatePhonecardStatusDeleted(PhonecardPO phonecardpo) throws Exception {
		PhoneCardInterfaceCallUtils.updateStatus(phonecardpo.getSerialnumber(),Constants.DEACTIVATED);
		phonecardpo.setStatus(Constants.STATUS_DELETED);
	}
	
}
