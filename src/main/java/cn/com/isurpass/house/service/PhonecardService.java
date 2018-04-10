package cn.com.isurpass.house.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.isurpass.house.dao.PhonecarduserDAO;
import cn.com.isurpass.house.util.PhoneCardInterfaceCallUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.isurpass.house.dao.PhonecardDAO;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.GatewayPO;
import cn.com.isurpass.house.po.PhonecardPO;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.vo.TypeGatewayInfoVO;
@Service
public class PhonecardService {
	@Autowired
	private PhonecardDAO pd;
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
		map.put("total", pd.count());
		//SELECT * FROM  phonecard WHERE serialnumber LIKE '%f%' AND rateplan LIKE '%f%' AND STATUS=1  ORDER BY activationdate ASC LIMIT 0,9;
		Integer status = pc.getStatus();
		List<Integer> statuslist = new ArrayList<Integer>();
		if(status==2){//查询正常记录1
			statuslist.add(1);
		}else if(status==3){//查询冻结记录 2
			statuslist.add(2);
		}else{//查询全部记录（暂不包括已删除记录）
			statuslist.add(1);
			statuslist.add(2);
		}
		Page<PhonecardPO> gateList = pd.findByStatusInAndSerialnumberContainingAndRateplanContaining(statuslist,pc.getSerialnumber(),pc.getRateplan(),pageable);
		List<PhonecardPO> list = new ArrayList<>();
		gateList.forEach(o -> {
			list.add(o);
		});
		map.put("rows", list);
		return map;
	}
	@Transactional(rollbackFor = Exception.class)
	public String  updatePhonecardStatus(String hope, Object [] ids) throws Exception {
		String s = "";
		for (Object string : ids) {
			PhonecardPO phonecardpo = pd.findByPhonecardid(string);
			if("start".equals(hope)){
				s = PhoneCardInterfaceCallUtils.updateStatus(phonecardpo.getSerialnumber(), Constants.ACTIVATED);
				phonecardpo.setStatus(Constants.STATUS_NORMAL);
			}else if("freeze".equals(hope)){
				s = PhoneCardInterfaceCallUtils.updateStatus(phonecardpo.getSerialnumber(),Constants.INVENTORY);
				phonecardpo.setStatus(Constants.STATUS_SUSPENCED);
			}else if("delete".equals(hope)){
				s = PhoneCardInterfaceCallUtils.updateStatus(phonecardpo.getSerialnumber(),Constants.DEACTIVATED);
				phonecardpo.setStatus(Constants.STATUS_DELETED);
			}
			pd.save(phonecardpo);
		}
		return s;
	}
	
}
