package com.szmirren.options;

import java.util.ArrayList;
import java.util.List;

import com.szmirren.common.Constant;
import com.szmirren.models.TableAttributeKeyValue;

import javafx.collections.ObservableList;

/**
 * Sql属性的配置文件
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class DaoConfig {
	/** 设置的tableItem */
	private List<TableAttributeKeyValue> tableItem = new ArrayList<>();
	/** 生成模板的名字 */
	private String templateName = Constant.TEMPLATE_NAME_DAO;
	/** 是否覆盖原文件 */
	private boolean overrideFile = true;

	/**
	 * 初始化
	 */
	public DaoConfig() {
		super();
	}

	/**
	 * 通过 ObservableList<TableAttributeKeyValue>初始化
	 * 
	 * @param tableItem
	 */
	public DaoConfig(ObservableList<TableAttributeKeyValue> item) {
		super();
		if (item != null && !item.isEmpty()) {
			tableItem.addAll(item);
		}
	}

	/**
	 * 通过 ObservableList<TableAttributeKeyValue>初始化
	 * 
	 * @param tableItem
	 */
	public DaoConfig(ObservableList<TableAttributeKeyValue> item, String templateName, boolean overrideFile) {
		super();
		if (item != null && !item.isEmpty()) {
			tableItem.addAll(item);
		}
		this.templateName = templateName;
		this.overrideFile = overrideFile;
	}

	/**
	 * 初始化默认参数
	 * 
	 * @return
	 */
	public DaoConfig initDefaultValue() {
		tableItem.add(new TableAttributeKeyValue("count", "get{C}RowCount", "查询{C}数据总行数,不需要条件时传null"));
		tableItem.add(new TableAttributeKeyValue("select", "select{C}", "查询所有{C}数据,不需要条件时传null"));
		tableItem.add(new TableAttributeKeyValue("selectById", "select{C}ById", "通过id查询{C}数据"));
		tableItem.add(new TableAttributeKeyValue("selectByObjSingle", "select{C}ObjSingle", "通过{C}对象中不为null的属性作为条件,查询出符合条件的第一个{C}数据"));
		tableItem.add(new TableAttributeKeyValue("selectByObj", "select{C}ByObj", "通过{C}对象中不为null的属性作为条件,查询出符合条件的所有{C}数据"));
		tableItem.add(new TableAttributeKeyValue("insertAll", "insert{C}", "将{C}保存到数据库中,包括null值"));
		tableItem.add(new TableAttributeKeyValue("insertNotNull", "insertNotNull{C}", "将{C}中属性不为null的数据保存到数据库中"));
		tableItem.add(new TableAttributeKeyValue("insertBatch", "insert{C}ByBatch", "将{C}保存到数据库中,包括null值"));
		tableItem.add(new TableAttributeKeyValue("deleteById", "delete{C}ById", "通过id删除{C}数据"));
		tableItem.add(new TableAttributeKeyValue("deleteByAssist", "delete{C}ByAssist", "通过Assis删除{C}数据"));
		tableItem.add(new TableAttributeKeyValue("updateAllById", "update{C}ById", "更新{C}到数据库中,包括null值,条件为对象中的主键属性"));
		tableItem.add(new TableAttributeKeyValue("updateNotNullById", "updateNotNull{C}ById", "更新{C}中属性不为null的数据到数据库中,条件为对象中的主键属性"));
		tableItem.add(new TableAttributeKeyValue("updateAllByAssist", "update{C}", "更新{C}到数据库中,包括null值,条件为Assist设置的条件"));
		tableItem.add(new TableAttributeKeyValue("updateNotNullByAssist", "updateNotNull{C}", "更新{C}中属性不为null的数据到数据库中,条件为Assist设置的条件"));
		return this;
	}

	/**
	 * 设置属性集合
	 * 
	 * @return
	 */
	public List<TableAttributeKeyValue> getTableItem() {
		return tableItem;
	}

	/**
	 * 获取属性集合
	 * 
	 * @param tableItem
	 */
	public void setTableItem(List<TableAttributeKeyValue> tableItem) {
		this.tableItem = tableItem;
	}

	/**
	 * 获得模板的名称
	 * 
	 * @return
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * 设置模板的名称
	 * 
	 * @param templateName
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * 获取是否覆盖原文件
	 * 
	 * @return
	 */
	public boolean isOverrideFile() {
		return overrideFile;
	}

	/**
	 * 设置是否覆盖原文件
	 * 
	 * @param overrideFile
	 */
	public void setOverrideFile(boolean overrideFile) {
		this.overrideFile = overrideFile;
	}

	@Override
	public String toString() {
		return "SqlConfig [tableItem=" + tableItem + ", templateName=" + templateName + ", overrideFile=" + overrideFile + "]";
	}

}
