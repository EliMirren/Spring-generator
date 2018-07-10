package com.szmirren.models;

import com.alibaba.fastjson.JSONObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;

/**
 * Table的key与packageName属性
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class TableAttributeKeyValueTemplate {
	/** key列 */
	private StringProperty key = new SimpleStringProperty();
	/** 包名 */
	private StringProperty packageName = new SimpleStringProperty();
	/** 类名 */
	private StringProperty className = new SimpleStringProperty();
	/** 后缀名 */
	private StringProperty suffix = new SimpleStringProperty(".java");
	/** 模板 */
	private ComboBox<String> template;
	/** 模板的值 */
	private String templateValue;

	/**
	 * 初始化
	 */
	public TableAttributeKeyValueTemplate() {
		super();
	}

	/**
	 * 初始化
	 * 
	 * @param key
	 *          key列的值
	 * @param packageName
	 *          包名
	 * @param className
	 *          类名
	 * @param suffix
	 *          后缀名
	 * @param templateValue
	 *          模板的值
	 */
	public TableAttributeKeyValueTemplate(String key, String packageName, String className, String suffix, String templateValue) {
		super();
		this.key.setValue(key);
		this.packageName.setValue(packageName);
		this.className.setValue(className);
		this.suffix.setValue(suffix);
		this.template = new ComboBox<>();
		this.template.setValue(templateValue);
		this.templateValue = templateValue;
	}

	/**
	 * 初始化
	 */
	public TableAttributeKeyValueTemplate(JSONObject object) {
		super();
		setKey(object.getString("key"));
		setPackageName(object.getString("packageName"));
		setClassName(object.getString("className"));
		setSuffix(object.getString("suffix"));
		setTemplateValue(object.getString("templateValue"));
	}

	/**
	 * 将JSONObject对象转换为当前对象
	 * 
	 * @param object
	 * @return
	 */
	public static TableAttributeKeyValueTemplate fromJson(JSONObject object) {
		return new TableAttributeKeyValueTemplate(object);
	}

	/**
	 * 将当期对象转换为JSONObject
	 * 
	 * @return
	 */
	public JSONObject toJson() {
		JSONObject result = new JSONObject();
		result.put("key", key.getValue());
		result.put("packageName", packageName.getValue());
		result.put("className", className.getValue());
		result.put("suffix", suffix.getValue());
		result.put("templateValue", getTemplateValue());
		return result;
	}

	/**
	 * 初始化
	 * 
	 * @param key
	 *          key列的值
	 * @param packageName
	 *          packageName列的值
	 * @param className
	 *          当前列的描述
	 * @param template
	 *          模板的ComboBox
	 */
	public TableAttributeKeyValueTemplate(String key, String packageName, String className, String suffix, ComboBox<String> template) {
		super();
		this.key.setValue(key);
		this.packageName.setValue(packageName);
		this.className.setValue(className);
		this.suffix.setValue(suffix);
		this.template = template;
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

	public String getPackageName() {
		return packageName.getValue();
	}

	public void setPackageName(String packageName) {
		this.packageName.setValue(packageName);
	}

	public void setPackageName(StringProperty packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className.getValue();
	}

	public void setClassName(String className) {
		this.className.setValue(className);
	}

	public void setClassName(StringProperty className) {
		this.className = className;
	}

	public String getTemplateValue() {
		if (template != null) {
			return template.getValue();
		}
		if (templateValue != null) {
			return templateValue;
		}
		return null;
	}

	public void setTemplateValue(String templateValue) {
		this.templateValue = templateValue;
		if (this.template == null) {
			this.template = new ComboBox<>();
			this.template.setValue(templateValue);
		}
	}

	public ComboBox<String> getTemplate() {
		return template;
	}

	public String getSuffix() {
		return suffix.getValue();
	}

	public void setSuffix(String suffix) {
		this.suffix.setValue(suffix);
	}
	public void setSuffix(StringProperty suffix) {
		this.suffix = suffix;
	}

	public void setTemplate(String template) {
		this.template.setValue(template);
	}

	public void setTemplate(ComboBox<String> template) {
		this.template = template;
	}

	@Override
	public String toString() {
		return "TableAttributeKeyValueTemplate [key=" + key + ", packageName=" + packageName + ", className=" + className + ", suffix=" + suffix
				+ ", template=" + template + ", templateValue=" + templateValue + "]";
	}

}
