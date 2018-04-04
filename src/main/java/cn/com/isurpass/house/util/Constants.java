package cn.com.isurpass.house.util;

public class Constants {
	public static final Integer ORGTYPE_AMETA = 0; // Ameta
	public static final Integer ORGTYPE_SUPPLIER = 1; // 服务商
	public static final Integer ORGTYPE_INSTALLER = 2; // 安装商

	public static final Integer GENDER_FEMALE = 0; // 女性
	public static final Integer GENDER_MALE = 1; // 男性
	public static final Integer GENDER_LGBT = 2; // LGBT
	
	public static final Integer STATUS_UNVALID= 0; //未生效
	public static final Integer STATUS_NORMAL = 1;		//正常
	public static final Integer STATUS_SUSPENCED = 2;	//冻结
	public static final Integer STATUS_DELETED = 9;		//删除
	
	public static final Integer STATUS_ONLINE = 1; 		//在线
	public static final Integer STATUS_OFFLINE = 0 ;	//离线
	//电话卡状态切换

	public static final String ACTIVATED = "ACTIVATED";//激活
	public static final String ACTIVATION_READY = "ACTIVATION_READY";//就绪
	public static final String	 DEACTIVATED = "DEACTIVATED";//停用
	public static final String INVENTORY = "INVENTORY";//待处理
	public static final String RETIRED = "RETIRED";//退休
	public static final String Authorization = "Basic YW1ldGFjYTphNDRmNDJmMS0wMmRiLTRhY2EtYjZhMC04MWYxNTBmZGI1YmI=";//调用接口认证信息
}
