package com.szmirren.options;

import com.alibaba.fastjson.JSONObject;
import com.szmirren.common.Constant;
import com.szmirren.models.TableAttributeEntity;

import javafx.collections.ObservableList;

/**
 * 实体类的配置文件
 * 
 * @author Mirren
 *
 */
public class EntityConfig {
	/** 生成模板的名字 */
	private String templateName = Constant.TEMPLATE_NAME_ENTITY;
	/** 字段使用驼峰命名 */
	private boolean fieldCamel = true;
	/** 是否覆盖原文件 */
	private boolean overrideFile = true;

	// -----------------不在保存配置范围的属性-----------------------
	/** 存储信息table里面的所有属性 */
	private ObservableList<TableAttributeEntity> tblPropertyValues;
	/** 表的别名 */
	private String tableAlias;
	/** 主键名称 */
	private String primaryKey;

	/**
	 * 实例化
	 */
	public EntityConfig() {
		super();
	}
	/**
	 * 实例化
	 * 
	 * @param obj
	 */
	public EntityConfig(JSONObject obj) {
		super();
		this.templateName = obj.getString("templateName");
		this.fieldCamel = obj.getBoolean("fieldCamel");
		this.overrideFile = obj.getBoolean("overrideFile");
	}

	/**
	 * 将对象转换为JSONObject
	 * 
	 * @return
	 */
	public JSONObject toJson() {
		JSONObject result = new JSONObject();
		result.put("templateName", templateName);
		result.put("fieldCamel", fieldCamel);
		result.put("overrideFile", overrideFile);
		return result;
	}
	/**
	 * 将当前对象转换为Json字符串
	 * 
	 * @return
	 */
	public String toJsonString() {
		return toJson().toJSONString();
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public boolean isFieldCamel() {
		return fieldCamel;
	}

	public void setFieldCamel(boolean fieldCamel) {
		this.fieldCamel = fieldCamel;
	}

	public boolean isOverrideFile() {
		return overrideFile;
	}

	public void setOverrideFile(boolean overrideFile) {
		this.overrideFile = overrideFile;
	}
	public ObservableList<TableAttributeEntity> getTblPropertyValues() {
		return tblPropertyValues;
	}
	public void setTblPropertyValues(ObservableList<TableAttributeEntity> tblPropertyValues) {
		this.tblPropertyValues = tblPropertyValues;
	}
	public String getTableAlias() {
		return tableAlias;
	}
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	@Override
	public String toString() {
		return "EntityConfig [templateName=" + templateName + ", fieldCamel=" + fieldCamel + ", overrideFile=" + overrideFile
				+ ", tblPropertyValues=" + tblPropertyValues + ", tableAlias=" + tableAlias + ", primaryKey=" + primaryKey + "]";
	}

}
