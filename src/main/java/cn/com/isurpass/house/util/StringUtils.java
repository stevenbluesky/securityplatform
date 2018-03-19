package cn.com.isurpass.house.util;

public class StringUtils {

	/**
	 * 判断一个字符串是否为空(值为 null 或者长度为 0)<br>
	 * 为空时返回 false, 不为空时返回 true
	 * @return
	 */
	public static boolean checkNUll(String str) {
		if (str == null || str.length() == 0)
			return false;
		return true;
	}
}
