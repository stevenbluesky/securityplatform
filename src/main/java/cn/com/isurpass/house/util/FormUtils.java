package cn.com.isurpass.house.util;

import java.lang.reflect.Field;
import java.util.Date;

import ch.qos.logback.core.net.SyslogOutputStream;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.vo.OrgAddVO;

public class FormUtils {

	/**
	 * 如果需要验证的几个表单值都有值,返回true; 否则返回false
	 * @param as
	 * @return
	 */
	public static boolean checkNull(OrgAddVO as) {
		if(StringUtils.checkNUll(as.getName())&&StringUtils.checkNUll(as.getCode())
				&&StringUtils.checkNUll(as.getCountry())
				&&StringUtils.checkNUll(as.getProvince())
				&&StringUtils.checkNUll(as.getCity())
				&&StringUtils.checkNUll(as.getLoginname())
				&&StringUtils.checkNUll(as.getPassword()))
			return true;
		return false;
	}
	
	
	/**
	 * 
	 * 判断一个对象中的属性是否为 null 或者为 "", 要注意的是,对象的属性只能为引用数据类型
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj){
		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.get(obj) != null && f.get(obj) != "") { // 判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
					return false;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * 将一个实体类中的属性赋值到另一个对象中(AddPO -> OrgPO)
	 */
	public static void copyO2O(OrganizationPO org, OrgAddVO as) {
		org.setName(as.getName());
		org.setCode(as.getCode());
		org.setStatus(1);
		org.setCentralstationname(as.getCsname());
		org.setCreatetime(new Date());
//		org.setOrgtype(1);//0:Ameta 1:服务商 2:安装商
		if(as.getParentorgid() == null)
			org.setOrgtype(Constants.ORGTYPE_SUPPLIER);
		else
			org.setOrgtype(Constants.ORGTYPE_INSTALLER);
	}
}
