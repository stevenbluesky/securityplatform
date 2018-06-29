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
	public static final Integer STATUS_INVENTORY = 3;  	//待激活
	public static final Integer STATUS_DELETED = 9;		//删除
	
	public static final Integer STATUS_ONLINE = 1; 		//在线
	public static final Integer STATUS_OFFLINE = 0 ;	//离线
	//电话卡状态切换

	public static final String ACTIVATED = "ACTIVATED";//激活
	public static final String ACTIVATION_READY = "ACTIVATION_READY";//就绪 未使用该字段
	public static final String	 DEACTIVATED = "DEACTIVATED";//停用
	public static final String INVENTORY = "INVENTORY";//待激活
	public static final String RETIRED = "RETIRED";//退休 未使用该字段
	public static final String Authorization = "Basic YW1ldGFjYTphNDRmNDJmMS0wMmRiLTRhY2EtYjZhMC04MWYxNTBmZGI1YmI=";//调用接口认证信息

	public static final Integer ROLE_AMETA_ADMIN=1;
	public static final Integer ROLE_SUPPLIER_ADMIN=2;
	public static final Integer ROLE_INSTALLER_ADMILN=3;
	public static final Integer ROLE_INSTALLER=4;
	public static final Integer ROLE_AMETA_EMPLOYEE=5;
	public static final Integer ROLE_SUPPLIER_EMPLOYEE=6;
	//public static final Integer ROLE_EMPLOYEE=7;  //此用户没有权限.一般情况下不会有员工为此角色,除非新增了机构角色.
	public static final Integer ROLE_INSTALLER_EMPLOYEE=8;

	public static final String OPERATE_DEVICE_URL="app.aibasecloud.com";
	//public static final String OPERATE_DEVICE_URL="dev.isurpass.com.cn";


//	public static final Long TOKEN_EXPIRED_TIME = 7200000L;//1000*60*60*2
}
