package com.szmirren.common;

/**
 * 语言国际化的常量
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public interface LanguageKey {
	// =========================页面标题==============================
	/** 创建数据库连接 */
	static final String PAGE_CREATE_CONNECTION = "page.createConnection";
	/** 修改数据库连接 */
	static final String PAGE_UPDATE_CONNECTION = "page.updateConnection";
	// =========================提示语================================
	/** 生成路径不能为空 */
	static final String TIPS_PATH_CANT_EMPTY = "tips.pathCantEmpty";
	/** 首页-提示先选择表名 */
	static final String INDEX_TIPS_SELECT_TABLE_NAME = "index.selectTableTips";
	/** 首页-提示先选择表名或者全库生成 */
	static final String INDEX_TIPS_CREATE_TABLE = "index.createTableTips";
	/** 首页-配置文件的配置信息表格提示 */
	static final String HISTORY_CONFIG_TABLE_TIPS = "historyConfig.tableTips";
	// ========================通用区域===============================
	/** 通用设置按钮 */
	static final String COMMON_BTN_SET = "common.btnSet";

	// ======================首页相关区域==============================
	/** 首页的数据连接 */
	static final String INDEX_LBL_CONNECTION = "index.lblConnection";
	/** 首页的配置信息 */
	static final String INDEX_LBL_CONFIG = "index.lblConfig";
	/** 首页的使用帮助 */
	static final String INDEX_LBL_INSTRUCTIONS = "index.lblInstructions";
	/** 首页的设置 */
	static final String INDEX_LBL_SETTING = "index.lblSetting";
	/** 首页的保存配置提示 */
	static final String INDEX_SAVE_CONFIG_TIPS = "index.saveConfigTips";
	/** 首页的保存配置不合格提示 */
	static final String INDEX_SAVE_CONFIG_NOT_C_TIPS = "index.saveConfigNotCTips";

	/** 首页-项目路径文字 */
	static final String INDEX_LBL_PROJECT_PATH = "index.lblProjectPath";
	/** 首页-项目路径输入框 */
	static final String INDEX_TXT_PROJECT_PATH = "index.txtProjectPath";
	/** 首页-选择项目路径 */
	static final String INDEX_BTN_SELECT_FILE = "index.btnSelectFile";
	/** 首页-表名 */
	static final String INDEX_LBL_TABLE_NAME = "index.lblTableName";
	/** 首页-表名输入框提示 */
	static final String INDEX_TXT_TABLE_NAME = "index.txtTableName";
	/** 首页-实体类包名 */
	static final String INDEX_LBL_ENTITY_PACKAGE = "index.lblEntityPackage";
	/** 首页-Service接口包名 */
	static final String INDEX_LBL_SERVICE_PACKAGE = "index.lblServicePackage";
	/** 首页-Service实现类包名 */
	static final String INDEX_LBL_SERVICE_IMPL_PACKAGE = "index.lblServiceImplPackage";
	/** 首页-Router包名 */
	static final String INDEX_LBL_ROUTER_PACKAGE = "index.lblRouterPackage";
	/** 首页-SQL包名 */
	static final String INDEX_LBL_SQL_PACKAGE = "index.lblSqlPackage";
	/** 首页-SqlAssist包名 */
	static final String INDEX_LBL_ASSIST_PACKAGE = "index.lblAssistPackage";
	/** 首页-AbstractSQL包名 */
	static final String INDEX_LBL_ABSTRACT_SQL_PACKAGE = "index.lblAbstractSqlPackage";
	/** 首页-Mapper包名 */
	static final String INDEX_LBL_MAPPER_PACKAGE = "index.lblSqlParamsPackage";
	/** 首页-单元测试包名 */
	static final String INDEX_LBL_UNIT_TEST_PACKAGE = "index.lblUnitTestPackage";
	/** 首页-实体类类名 */
	static final String INDEX_LBL_ENTITY_NAME = "index.lblEntityName";
	/** 首页-Service接口名 */
	static final String INDEX_LBL_SERVICE_NAME = "index.lblServiceName";
	/** 首页-Service实现类名 */
	static final String INDEX_LBL_SERVICE_IMPL_NAME = "index.lblServiceImplName";
	/** 首页-Router类名 */
	static final String INDEX_LBL_ROUTER_NAME = "index.lblRouterName";
	/** 首页-SQL类名 */
	static final String INDEX_LBL_SQL_NAME = "index.lblSqlName";
	/** 首页-SqlAssist类名 */
	static final String INDEX_LBL_ASSIST_NAME = "index.lblAssistName";
	/** 首页-AbstractSql类名 */
	static final String INDEX_LBL_ABSTRACT_SQL_NAME = "index.lblAbstractSqlName";
	/** 首页-SqlAndParams类名 */
	static final String INDEX_LBL_MAPPER_NAME = "index.lblSqlParamsName";
	/** 首页-单元测试类名 */
	static final String INDEX_LBL_UNIT_TEST_NAME = "index.lblUnitTestName";

	/** 首页-正在生成提示语句 */
	static final String INDEX_RUN_CREATE_TIPS_TEXT = "index.runCreateTipsText";
	/** 首页-自定义包名与类 */
	static final String INDEX_LBL_SET_CUSTOM = "index.lblSetCustom";
	/** 首页-自定义属性 */
	static final String INDEX_LBL_SET_CUSTOM_PROPERTY = "index.lblSetCustomProperty";
	/** 首页-文件编码格式 */
	static final String INDEX_LBL_CODE_FORMAT = "index.lblCodeFormat";
	/** 首页-执行创建 */
	static final String INDEX_BTN_RUN_CREATE = "index.btnRunCreate";
	/** 首页-保存配置文件 */
	static final String INDEX_BTN_SAVE_CONFIG = "index.btnSaveConfig";
	/** 首页-数据库数右键打开连接 */
	static final String INDEX_TVMI_OPEN_CONNECT = "index.tvmiOpenConnect";
	/** 首页-数据库数右键关闭连接 */
	static final String INDEX_TVMI_CLOSE_CONNECT = "index.tvmiCloseConnect";
	/** 首页-数据库数右键修改连接 */
	static final String INDEX_TVMI_UPDATE_CONNECT = "index.tvmiUpdateConnect";
	/** 首页-数据库数右键删除连接 */
	static final String INDEX_TVMI_DELETE_CONNECT = "index.tvmiDeleteConnect";
	/** 首页-数据库数右键生成全库 */
	static final String INDEX_TVMI_CREATE_FULL_DB = "index.tvmiCreateFullDB";

	// ========================使用帮助区域===============================
	/** 使用帮助-当前版本 */
	static final String INSTRUCTION_LBL_Version = "instruction.lblVersion";
	/** 使用帮助-使用帮助 */
	static final String INSTRUCTION_LBL_INSTRUCTIONS = "instruction.lblInstructions";
	/** 使用帮助-开源地址 */
	static final String INSTRUCTION_LBL_PROJECT_PATH = "instruction.lblProjectPath";
	/** 使用帮助-模板仓库 */
	static final String INSTRUCTION_LBL_TEMPLATE_PATH = "instruction.lblTemplatePath";
	/** 使用帮助-QQ交流群 */
	static final String INSTRUCTION_LBL_TALK_GROUP_IN_QQ = "instruction.lblTalkGroupInQQ";
	/** 使用帮助-作者邮箱 */
	static final String INSTRUCTION_LBL_AUTHORS_EMAIL = "instruction.lblAuthorsEmail";

	// =======================设置区域================================
	/** 设置-语言 */
	static final String SETTING_LBL_LANGUAGE = "setting.lblLanguage";

	// ======================新建数据库连接==============================
	/** 数据库连接-连接名称 */
	static final String CONN_LBL_CONN_NAME = "conn.lblConnName";
	/** 数据库连接-连接地址 */
	static final String CONN_LBL_CONN_URL = "conn.lblConnURL";
	/** 数据库连接-端口号 */
	static final String CONN_LBL_LISTEN_PORT = "conn.lblListenPort";
	/** 数据库连接-数据库类型 */
	static final String CONN_LBL_DB_TYPE = "conn.lblDBType";
	/** 数据库连接-数据库名字 */
	static final String CONN_LBL_DB_NAME = "conn.lblDBName";
	/** 数据库连接-用户名 */
	static final String CONN_LBL_USER_NAME = "conn.lblUserName";
	/** 数据库连接-用户密码 */
	static final String CONN_LBL_USER_PWD = "conn.lblUserPwd";
	/** 数据库连接-数据库编码 */
	static final String CONN_LBL_DB_CODING = "conn.lblDBCoding";
	/** 数据库连接-连接名称 */
	static final String CONN_TXT_CONN_NAME = "conn.txtConnName";
	/** 数据库连接-连接地址 */
	static final String CONN_TXT_CONN_URL = "conn.txtConnURL";
	/** 数据库连接-端口号 */
	static final String CONN_TXT_LISTEN_PORT = "conn.txtListenPort";
	/** 数据库连接-数据库类型 */
	static final String CONN_CBO_DB_TYPE = "conn.cboDBType";
	/** 数据库连接-数据库名称 */
	static final String CONN_TXT_DB_NAME = "conn.txtDBName";
	/** 数据库连接-用户名字 */
	static final String CONN_TXT_USER_NAME = "conn.txtUserName";
	/** 数据库连接-用户密码 */
	static final String CONN_TXT_USER_PWD = "conn.txtUserPwd";
	/** 数据库连接-测试连接 */
	static final String CONN_BTN_TEST_CONN = "conn.btnTestConn";
	/** 数据库连接-保存 */
	static final String CONN_BTN_SAVE = "conn.btnSave";
	/** 数据库连接-取消 */
	static final String CONN_BTN_CANCEL = "conn.btnCancel";
	// ========================配置信息==================================
	/** 数据库连接-提示语句 */
	static final String CONFIG_LBL_TIPS = "config.lblTips";
	/** 数据库连接-配置信息文件名 */
	static final String CONFIG_TD_INFO = "config.tdInfo";
	/** 数据库连接-操作 */
	static final String CONFIG_TD_OPERATION = "config.tdOperation";
	/** 数据库连接-加载 */
	static final String CONFIG_BTN_LOAD = "config.btnLoad";
	/** 数据库连接-删除 */
	static final String CONFIG_BTN_DATELE = "config.btnDelete";
	// ========================设置==================================
	/** 设置实体类-是否创建 */
	static final String SET_ENTITY_TD_CREATE = "setEntity.tdcreate";
	/** 设置实体类-数据库列名 */
	static final String SET_ENTITY_TD_COLUMN = "setEntity.tdColumn";
	/** 设置实体类-SQL数据类型 */
	static final String SET_ENTITY_TD_SQL_TYPE = "setEntity.tdSqlType";
	/** 设置实体类-java数据类型 */
	static final String SET_ENTITY_TD_JAVA_TYPE = "setEntity.tdJavaType";
	/** 设置实体类-字段属性名 */
	static final String SET_ENTITY_TD_FIELD = "setEntity.tdField";
	/** 设置实体类-表的别名 */
	static final String SET_ENTITY_LBL_TABLE_ALIAS = "setEntity.lblTableAlias";
	/** 设置实体类-表的别名 */
	static final String SET_ENTITY_TXT_TABLE_ALIAS = "setEntity.txtTableAlias";
	/** 设置实体类-主键名称 */
	static final String SET_ENTITY_LBL_PRIMARY_KEY = "setEntity.lblPrimaryKey";
	/** 设置实体类-主键名称 */
	static final String SET_ENTITY_TXT_PRIMARY_KEY = "setEntity.txtPrimaryKey";
	/** 设置实体类- 自定属性类型 */
	static final String SET_ENTITY_LBL_KEY = "setEntity.lblKey";
	/** 设置实体类-自定属性类型 */
	static final String SET_ENTITY_TXT_KEY = "setEntity.txtKey";
	/** 设置实体类-自定属性类型名称 */
	static final String SET_ENTITY_LBL_VALUE = "setEntity.lblValue";
	/** 设置实体类-自定属性类型名称 */
	static final String SET_ENTITY_TXT_VALUE = "setEntity.txtValue";
	/** 设置实体类-字段驼峰命名 */
	static final String SET_CHK_FIELD_CAMEL = "setEntity.chkFieldCamel";
	/** 设置-表格详情 */
	static final String SET_TBL_TIPS = "set.tblTips";
	/** 设置-列详情 */
	static final String SET_TD_DESCRIBE = "set.tdDescribe";
	/** 设置-提示语句 */
	static final String SET_LBL_TIPS = "set.lblTips";
	/** 设置-保存配置 */
	static final String SET_BTN_SAVE_CONFIG = "set.btnSaveConfig";
	/** 设置-添加自定义属性 */
	static final String SET_LBL_ADD_CUSTOM_PROPERTY = "set.lblAddCustomProperty";
	/** 设置-详情 */
	static final String SET_LBL_DESCRIBE = "set.lblDescribe";
	/** 设置-添加属性key */
	static final String SET_TXT_KEY = "set.txtKey";
	/** 设置-添加属性value */
	static final String SET_TXT_VALUE = "set.txtValue";
	/** 设置-添加属性描述 */
	static final String SET_TXT_DESCRIBE = "set.txtDescribe";
	/** 设置-添加属性 */
	static final String SET_BTN_ADD_PROPERTY = "set.btnAddProperty";
	/** 设置-模板 */
	static final String SET_LBL_TEMPLATE = "set.lblTemplate";
	/** 设置-模板 */
	static final String SET_CBO_TEMPLATE = "set.cboTemplate";
	/** 设置-确定 */
	static final String SET_BTN_CONFIRM = "set.btnConfirm";
	/** 设置-取消 */
	static final String SET_BTN_CANCEL = "set.btnCancel";
	/** 设置-取消设置的提示 */
	static final String SET_BTN_CANCEL_TIPS = "set.btnCancelTips";
	/** 设置-覆盖存在的文件 */
	static final String SET_CHK_OVERRIDE_FILE = "set.chkOverrideFile";
	/** 设置-根据数据库类型自动选择 */
	static final String SET_ABSTRACT_AUTOMATIC = "set.abstractAutomatic";

	/** 设置-表格属性中删除menu */
	static final String SET_TBL_MENU_ITEM_DELETE = "set.tblMenuItemDelete";
	/** 设置-表格属性中删除提示语句 */
	static final String SET_TBL_MENU_ITEM_DELETE_CONFIRM = "set.tblMenuItemDeleteConfirm";
	/** 设置-通用包名 */
	static final String SET_COMMON_PACKAGE_NAME = "set.commonPackageName";
	/** 设置-通用类名 */
	static final String SET_COMMON_CLASS_NAME = "set.commonClassName";
	/** 设置-通用后缀名 */
	static final String SET_COMMON_SUFFIX = "set.commonSuffix";
	/** 设置-通用模板名 */
	static final String SET_COMMON_TEMPLATE_NAME = "set.commonTemplateName";
	/** 设置-文本属性包名 */
	static final String SET_LBL_PACKAGE_NAME = "set.lblPackageName";
	/** 设置-文本属性类名 */
	static final String SET_LBL_CLASS_NAME = "set.lblClassName";
	/** 设置-输入框属性包名 */
	static final String SET_TXT_PACKAGE_NAME = "set.txtPackageName";
	/** 设置-输入框属性类名 */
	static final String SET_TXT_CLASS_NAME = "set.txtClassName";
}
