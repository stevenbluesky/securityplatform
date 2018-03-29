package cn.com.isurpass.house.util;

import java.lang.reflect.Field;
import java.util.Date;

import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.vo.OrgAddVO;

public class FormUtils {

    /**
     * 如果需要验证的几个表单值都有值,返回true; 否则返回false
     *
     * @param as
     * @return
     */
    public static boolean checkOrgNull(OrgAddVO as) {
        if (FormUtils.checkNUll(as.getName()) && FormUtils.checkNUll(as.getCode()) && as.getCountry() != null
                && as.getProvince() != null && as.getCity() != null) {
            return true;
        }
        return false;
    }

    public static boolean checkLoginNull(OrgAddVO as) {
        if (FormUtils.checkNUll(as.getLoginname()) && FormUtils.checkNUll(as.getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * 判断一个对象中的属性是否为 null 或者为 "", 要注意的是,对象的属性只能为引用数据类型.
     *
     * @param obj
     * @return 为空返回true, 不为空返回false
     */
public static boolean isEmpty(Object obj) {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                System.out.println("====" + f.get(obj) + "====");
                if (f.get(obj) != null && f.get(obj).toString().replace(" ", "") != "") { // 判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
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
        // org.setOrgtype(1);//0:Ameta 1:服务商 2:安装商
        if (as.getParentorgid() == null)
            org.setOrgtype(Constants.ORGTYPE_SUPPLIER);
        else
            org.setOrgtype(Constants.ORGTYPE_INSTALLER);
    }

    /**
     * 判断一个字符串是否为空(值为 null 或者长度为 0)<br>
     * 为空时返回 false, 不为空时返回 true
     *
     * @return
     */
    public static boolean checkNUll(String string) {
        if (string == null || string.length() == 0)
            return false;
        return true;
    }

    /**
     * 通过 MD5 和盐生成加密的字符串
     *
     * @param psw
     * @return
     * @Deprecated 请使用Encrypt类中的方法
     */
    @Deprecated
    public static String encrypt(String psw) {
        String pswSalt = "/@JWZH@/";
        return MD5Utils.MD5(psw + pswSalt);
    }

}
