package com.szmirren.options;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szmirren.models.TableAttributeKeyValueTemplate;

import javafx.collections.ObservableList;

/**
 * 自定义包类的配置文件
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class CustomConfig {
	/** 设置的tableItem */
	private List<TableAttributeKeyValueTemplate> tableItem = new ArrayList<>();
	/** 是否覆盖原文件 */
	private boolean overrideFile = true;

	/**
	 * 初始化
	 */
	public CustomConfig() {
		super();
	}

	/**
	 * 初始化
	 * 
	 * @param item
	 */
	public CustomConfig(ObservableList<TableAttributeKeyValueTemplate> item) {
		super();
		if (item != null && !item.isEmpty()) {
			tableItem.addAll(item);
		}
	}

	/**
	 * 初始化
	 * 
	 * @param item
	 * @param templateName
	 * @param overrideFile
	 */
	public CustomConfig(ObservableList<TableAttributeKeyValueTemplate> item, boolean overrideFile) {
		super();
		if (item != null && !item.isEmpty()) {
			tableItem.addAll(item);
		}
		this.overrideFile = overrideFile;
	}

	/**
	 * 初始化
	 */
	public CustomConfig(JSONObject object) {
		super();
		this.overrideFile = object.getBoolean("overrideFile");
		JSONArray array = object.getJSONArray("tableItem");
		if (array != null) {
			array.forEach(v -> {
				tableItem.add(new TableAttributeKeyValueTemplate((JSONObject) v));
			});
		}
	}

	/**
	 * 通过JSONObject 实例化一个对象
	 * 
	 * @param object
	 * @return
	 */
	public static CustomConfig fromJson(JSONObject object) {
		return new CustomConfig(object);
	}

	/**
	 * 将当前对象转换为JSONObject
	 * 
	 * @return
	 */
	public JSONObject toJson() {
		JSONObject result = new JSONObject();
		result.put("overrideFile", overrideFile);
		JSONArray array = new JSONArray();
		tableItem.forEach(v -> {
			array.add(v.toJson());
		});
		result.put("tableItem", array);
		return result;
	}

	/**
	 * 初始化默认参数
	 * 
	 * @return
	 */
	public CustomConfig initDefaultValue() {
		return this;
	}

	/**
	 * 将当期对象转换为Json字符串
	 * 
	 * @return
	 */
	public String toJsonString() {
		return toJson().toJSONString();
	}

	public List<TableAttributeKeyValueTemplate> getTableItem() {
		return tableItem;
	}

	public void setTableItem(List<TableAttributeKeyValueTemplate> tableItem) {
		this.tableItem = tableItem;
	}

	public boolean isOverrideFile() {
		return overrideFile;
	}

	public void setOverrideFile(boolean overrideFile) {
		this.overrideFile = overrideFile;
	}

	@Override
	public String toString() {
		return "CustomConfig [tableItem=" + tableItem + ",  overrideFile=" + overrideFile + "]";
	}

}
