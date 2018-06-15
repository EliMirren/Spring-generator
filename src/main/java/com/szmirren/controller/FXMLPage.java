package com.szmirren.controller;

/**
 * 页面枚举类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public enum FXMLPage {
	/** 数据库连接页面 */
	CONNECTION("FXML/Connection.fxml"),
	/** 数据库修改页面 */
	UPDATE_CONNECTION("FXML/UpdateConnection.fxml"),
	/** 配置信息页面 */
	HISTORY_CONFIG("FXML/HistoryConfig.fxml"),
	/** 实体类属性设置页面 */
	SET_ENTITY_ATTRIBUTE("FXML/setEntityAttribute.fxml"),
	/** Service设置页面 */
	SET_ROUTER_SERVICE("FXML/SetService.fxml"),
	/** ServiceImpl设置页面 */
	SET_ROUTER_SERVICE_IMPL("FXML/SetServiceImpl.fxml"),
	/** Router设置页面 */
	SET_ROUTER("FXML/SetRouter.fxml"),
	/** SQL设置页面 */
	SET_SQL("FXML/SetSql.fxml"),
	/** SQLAssist设置页面 */
	SET_ASSIST("FXML/SetAssist.fxml"),
	/** AbstractSQL设置页面 */
	SET_ABSTRACT_SQL("FXML/SetAbstractSQL.fxml"),
	/** SQLAssist设置页面 */
	SET_MAPPER("FXML/SetMapper.fxml"),
	/** SQLAssist设置页面 */
	SET_UNIT_TEST("FXML/SetUnitTest.fxml"),
	/** SetCustomProperty设置页面 */
	SET_CUSTOM("FXML/SetCustom.fxml"),
	/** SetCustomProperty设置页面 */
	SET_CUSTOM_PROPERTY("FXML/SetCustomProperty.fxml"),
	/** 使用说明页面 */
	ABOUT("FXML/About.fxml"),
	/** 设置页面 */
	SETTING("FXML/Setting.fxml");

	private String fxml;

	FXMLPage(String fxml) {
		this.fxml = fxml;
	}

	public String getFxml() {
		return this.fxml;
	}

}
