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
	/** Oracle */
	static final String SQLITE = "Sqlite";

	/** java的后缀名.java */
	static final String JAVA_SUFFIX = ".java";

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
	static final String TEMPLATE_NAME_ROUTER = "Controller.ftl";
	/** Dao模板的默认名字 */
	static final String TEMPLATE_NAME_DAO = "Dao.ftl";
	/** Mapper模板的默认名字 */
	static final String TEMPLATE_NAME_MAPPER = Main.LANGUAGE.get(LanguageKey.SET_ABSTRACT_AUTOMATIC).get();
	/** Mapper模板的默认名字 */
	static final String TEMPLATE_NAME_MAPPER_SUFFIX = "Mapper.ftl";
	/** SqlAssist模板的默认名字 */
	static final String TEMPLATE_NAME_SQL_ASSIST = "SqlAssist.ftl";
	/** 单元测试模板的默认名字 */
	static final String TEMPLATE_NAME_UNIT_TEST = "UnitTest.ftl";
}
