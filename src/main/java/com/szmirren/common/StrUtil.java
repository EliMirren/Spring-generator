package com.szmirren.common;

import java.util.List;

/**
 * 字符串工具
 * 
 * @author Mirren
 *
 */
public class StrUtil {

	/**
	 * 去掉下划线并将字符串转换成帕斯卡命名规范
	 * 
	 * @param str
	 * @return
	 */
	public static String unlineToPascal(String str) {
		if (str != null) {
			if (str.indexOf("_") == -1) {
				return fristToUpCase(str);
			}
			StringBuilder result = new StringBuilder();
			String[] temp = str.split("_");
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].equals("") || temp[i].isEmpty()) {
					continue;
				}
				result.append(fristToUpCaseLaterToLoCase(temp[i]));
			}
			return result.toString();
		}

		return str;
	}

	/**
	 * 去掉下划线并将字符串转换成驼峰命名规范
	 * 
	 * @param str
	 * @return
	 */
	public static String unlineToCamel(String str) {
		if (str != null) {
			if (str.indexOf("_") == -1) {
				return fristToLoCase(str);
			}
			StringBuilder result = new StringBuilder();
			String[] temp = str.split("_");
			boolean falg = false;
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].equals("") || temp[i].isEmpty()) {
					continue;
				}
				if (falg == false) {
					falg = true;
					result.append(temp[i].toLowerCase());
				} else {
					result.append(fristToUpCaseLaterToLoCase(temp[i]));
				}
			}
			return result.toString();
		}

		return str;
	}

	/**
	 * 将字符串首字母大写其后小写
	 * 
	 * @param str
	 * @return
	 */
	public static String fristToUpCaseLaterToLoCase(String str) {
		if (str != null && str.length() > 0) {
			str = (str.substring(0, 1).toUpperCase()) + (str.substring(1).toLowerCase());
		}
		return str;
	}

	/**
	 * 将字符串首字母小写其后大写
	 * 
	 * @param str
	 * @return
	 */
	public static String fristToLoCaseLaterToUpCase(String str) {
		if (str != null && str.length() > 0) {
			str = (str.substring(0, 1).toLowerCase()) + (str.substring(1).toUpperCase());

		}
		return str;
	}

	/**
	 * 将字符串首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String fristToUpCase(String str) {
		if (str != null && str.length() > 0) {
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		return str;
	}

	/**
	 * 将字符串首字母小写
	 * 
	 * @param str
	 * @return
	 */
	public static String fristToLoCase(String str) {
		if (str != null && str.length() > 0) {
			str = str.substring(0, 1).toLowerCase() + str.substring(1);
		}
		return str;
	}

	/**
	 * 检查字符串里面是否包含指定字符,包含返回true
	 * 
	 * @param regex
	 *          指定字符
	 * @param str
	 *          字符串
	 * @return
	 */
	public static boolean indexOf(String regex, String... str) {
		if (str == null) {
			return false;
		}
		for (String temp : str) {
			if (temp.indexOf(regex) == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 如果jdk大于1.8可以直接使用String.join<br>
	 * 将字符串中间以separator连接起来<br>
	 * 示例:magre(".","1","2","3") 结果:"1.2.3"
	 * 
	 * @param separator
	 * @param str
	 * @return
	 */
	@Deprecated()
	public static String magre(String separator, String... str) {
		StringBuffer result = null;
		for (String temp : str) {
			if (result == null) {
				result = new StringBuffer(temp);
			}
			result.append("," + temp);
		}
		return result.toString();
	}

	/**
	 * 将字符串str中带有集合中rep[0]的字符串,代替为rep[1]的中的字符串
	 * 
	 * @param str
	 * @param rep
	 * @return
	 */
	public static String replace(String str, List<String[]> rep) {
		for (String[] item : rep) {
			if (item[1] == null) {
				item[1] = "";
			}
			str = str.replace(item[0], item[1]);
		}
		return str;
	}

	/**
	 * 创建字符串数组
	 * 
	 * @param str
	 * @return
	 */
	public static String[] asStrArray(String... str) {
		return str;
	}

	/**
	 * 判断字符串是否为null或者空,如果是返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为null或者空,如果是返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String... str) {
		for (int i = 0; i < str.length; i++) {
			if (str[i] == null || "".equals(str[i].trim())) {
				return true;
			}
		}
		return false;
	}

}
