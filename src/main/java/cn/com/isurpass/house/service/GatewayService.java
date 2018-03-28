package cn.com.isurpass.house.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import cn.com.isurpass.house.dao.GatewayDAO;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.GatewayPO;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.vo.TypeGatewayInfoVO;

@Service
public class GatewayService {
	@Autowired
	private GatewayDAO gd;
	
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
	 * @param deviceid 
	 * @param installer 
	 * @param installerorg 
	 * @param serviceprovider 
	 * @param customer 
	 * @param citycode 
	 * @param cityname 
	 * @param name 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> listGateway(Pageable pageable,String name,String cityname,String citycode,String customer,String serviceprovider,String installerorg,String installer,String deviceid) {
		Map<String, Object> map = new HashMap<>();
		map.put("total", gd.count());
		Page<GatewayPO> gateList = gd.findAll(pageable);
		//TODO暂未根据搜索结果展示
		List<TypeGatewayInfoVO> list = new ArrayList<>();
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
	}
	/**
	 * 根据deviceid获取网关详细信息
	 * @param deviceid
	 * @return
	 */
	public GatewayPO findByDeviceid(String deviceid) {
		GatewayPO gw = gd.findByDeviceid(deviceid);
		return gw;
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
