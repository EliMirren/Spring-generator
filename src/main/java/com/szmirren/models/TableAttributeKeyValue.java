package com.szmirren.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Table的key与value属性
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class TableAttributeKeyValue {
	/** key列 */
	private StringProperty key = new SimpleStringProperty();
	/** value列 */
	private StringProperty value = new SimpleStringProperty();
	/** 描述 */
	private StringProperty describe = new SimpleStringProperty();

	/**
	 * 初始化
	 */
	public TableAttributeKeyValue() {
		super();
	}

	/**
	 * 初始化
	 * 
	 * @param key
	 *          key列的值
	 * @param value
	 *          value列的值
	 * @param describe
	 *          当前列的描述
	 */
	public TableAttributeKeyValue(String key, String value, String describe) {
		super();
		this.key.setValue(key);
		this.value.setValue(value);
		this.describe.setValue(describe);
	}

	public String getKey() {
		return key.getValue();
	}

	public void setKey(String key) {
		this.key.setValue(key);
	}

	public void setKey(StringProperty key) {
		this.key = key;
	}

	public String getValue() {
		return value.getValue();
	}

	public void setValue(String value) {
		this.value.setValue(value);
	}

	public void setValue(StringProperty value) {
		this.value = value;
	}

	public String getDescribe() {
		return describe.getValue();
	}

	public void setDescribe(String describe) {
		this.describe.setValue(describe);
	}

	public void setDescribe(StringProperty describe) {
		this.describe = describe;
	}

	@Override
	public String toString() {
		return "TableAttributeKeyValue [key=" + key + ", value=" + value + ", describe=" + describe + "]";
	}

}
