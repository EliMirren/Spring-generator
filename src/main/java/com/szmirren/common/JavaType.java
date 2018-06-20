package com.szmirren.common;

/**
 * java类型
 * 
 * @author Mirren
 *
 */
public class JavaType {

	/**
	 * 将JDBC类型转换为java数据类型
	 * 
	 * @param str
	 * @return
	 */
	public static String jdbcTypeToJavaType(String str) {
		if (str == null) {
			return "";
		}

		if (isDate(str)) {
			return "java.time.Instant";
		} else if (isInteger(str)) {
			return "Integer";
		} else if (isLong(str)) {
			return "Long";
		} else if (isDouble(str)) {
			return "Double";
		} else if (isString(str)) {
			return "String";
		} else if (isJson(str)) {
			return "JsonObject";
		} else {
			return "Object";
		}
	}

	/**
	 * 判断是否为事件
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDate(String str) {
		if (str.equalsIgnoreCase("DATE") || str.equalsIgnoreCase("DATETIME") || str.equalsIgnoreCase("TIMESTAMP")
				|| str.equalsIgnoreCase("INTERVAL") || str.equalsIgnoreCase("TIME") || str.equalsIgnoreCase("DATETIME2")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否可为String类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isString(String str) {
		if (str.equalsIgnoreCase("NCHAR") || str.equalsIgnoreCase("CHAR") || str.equalsIgnoreCase("NVARCHAR2")
				|| str.equalsIgnoreCase("VARCHAR2") || str.equalsIgnoreCase("NVARCHAR") || str.equalsIgnoreCase("VARCHAR")
				|| str.equalsIgnoreCase("DEDIUMBLOB") || str.equalsIgnoreCase("DEDIUMTEXT") || str.equalsIgnoreCase("CLOB")
				|| str.equalsIgnoreCase("NCLOB") || str.equalsIgnoreCase("BLOB") || str.equalsIgnoreCase("NBLOB") || str.equalsIgnoreCase("BFILE")
				|| str.equalsIgnoreCase("XML") || str.equalsIgnoreCase("IMAGE") || str.equalsIgnoreCase("TEXT")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断类型是否为JSON格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isJson(String str) {
		if (str.equals("JSON")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为Integer类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (str.equalsIgnoreCase("NUMBER") || str.equalsIgnoreCase("INTEGER") || str.equalsIgnoreCase("INT") || str.equalsIgnoreCase("TINYINT")
				|| str.equalsIgnoreCase("SMALLINT") || str.equalsIgnoreCase("BIT") || str.equalsIgnoreCase("Int UNSIGNED")
				|| str.equalsIgnoreCase("TINYINT UNSIGNED")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否为Long类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isLong(String str) {
		if (str.equalsIgnoreCase("Long") || str.equalsIgnoreCase("LONG UNSIGNED") || str.equalsIgnoreCase("BIGINT")
				|| str.equalsIgnoreCase("unsigned") || str.equalsIgnoreCase("BIGINT UNSIGNED")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否为Double类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		if (str.equalsIgnoreCase("BINARY_DOUBLE") || str.equalsIgnoreCase("BINARY_FLOAT") || str.equalsIgnoreCase("FLOAT")
				|| str.equalsIgnoreCase("DECIMAL") || str.equalsIgnoreCase("MONEY") || str.equalsIgnoreCase("NUMERIC")
				|| str.equalsIgnoreCase("REAL") || str.equalsIgnoreCase("DOUBLE")) {
			return true;
		} else {
			return false;
		}
	}

}
