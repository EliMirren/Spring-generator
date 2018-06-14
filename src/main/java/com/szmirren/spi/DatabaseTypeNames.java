package com.szmirren.spi;

import java.util.ArrayList;
import java.util.List;

import com.szmirren.common.Constant;

/**
 * 数据库名字
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class DatabaseTypeNames {
	/**
	 * 数据库的名称集合
	 * 
	 * @return
	 */
	public static List<String> dbTypeNames() {
		List<String> result = new ArrayList<>();
		result.add(Constant.MYSQL);
		result.add(Constant.POSTGRE_SQL);
		result.add(Constant.ORACLE);
		result.add(Constant.SQL_SERVER);
		return result;
	}
}
