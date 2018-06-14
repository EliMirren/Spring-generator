package com.szmirren.common;

import com.szmirren.Main;

/**
 * 工具需要用到的常量词
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public interface Constant {
	// 数据库名字
	/** MySQL */
	static final String MYSQL = "MySQL";
	/** PostgreSQL */
	static final String POSTGRE_SQL = "PostgreSQL";
	/** SqlServer */
	static final String SQL_SERVER = "SqlServer";
	/** Oracle */
	static final String ORACLE = "Oracle";

	/** default */
	static final String DEFAULT = "default";
	/** language */
	static final String LANGUAGE = "language";
	/** 模板的文件夹名称 */
	static final String TEMPLATE_DIR_NAME = "template";
	/** 刷新模板文件夹 */
	static final String TEMPLATE_DIR_REFRESH = "刷新模板文件夹";
	/** 实体类模板的默认名字 */
	static final String TEMPLATE_NAME_ENTITY = "Entity.ftl";
	/** Service模板的默认名字 */
	static final String TEMPLATE_NAME_SERVICE = "Service.ftl";
	/** ServiceImpl模板的默认名字 */
	static final String TEMPLATE_NAME_SERVICE_IMPL = "ServiceImpl.ftl";
	/** Service模板的默认名字 */
	static final String TEMPLATE_NAME_ROUTER = "Router.ftl";
	/** ServiceImpl模板的默认名字 */
	static final String TEMPLATE_NAME_SQL = "SQL.ftl";
	/** SqlAssist模板的默认名字 */
	static final String TEMPLATE_NAME_SQL_ASSIST = "SqlAssist.ftl";
	/** Abstract模板的默认名字 */
	static final String TEMPLATE_NAME_ABSTRACT_SQL = Main.LANGUAGE.get(LanguageKey.SET_ABSTRACT_AUTOMATIC).get();
	/** Abstract模板的默认名字前缀 */
	static final String TEMPLATE_NAME_ABSTRACT_SQL_PREFIX = "AbstractSQL";
	/** Abstract模板的默认名字后缀 */
	static final String TEMPLATE_NAME_ABSTRACT_SQL_SUFFIX = ".ftl";
	/** SqlAndParams模板的默认名字 */
	static final String TEMPLATE_NAME_SQL_AND_PARAMS = "SqlAndParams.ftl";
	/** 单元测试模板的默认名字 */
	static final String TEMPLATE_NAME_UNIT_TEST = "UnitTest.ftl";
	/** SqlPropertyValue默认的类名 */
	static final String SQL_PROPERTY_VALUE = "SqlPropertyValue";
	/** SqlPropertyValue模板的默认名字 */
	static final String TEMPLATE_NAME_SQL_PROPERTY_VALUE = "SqlPropertyValue.ftl";
	/** SqlWhereCondition 默认的类名 */
	static final String SQL_WHERE_CONDITION = "SqlWhereCondition";
	/** SqlWhereCondition模板的默认名字 */
	static final String TEMPLATE_NAME_SQL_WHERE_CONDITION = "SqlWhereCondition.ftl";
}
