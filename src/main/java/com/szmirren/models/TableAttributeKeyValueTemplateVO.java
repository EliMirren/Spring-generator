package com.szmirren.models;

/**
 * Table的key与packageName属性
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class TableAttributeKeyValueTemplateVO {
	/** key列 */
	private String key;
	/** 包名 */
	private String packageName;
	/** 类名 */
	private String className;
	/** 模板的值 */
	private String templateValue;

	/**
	 * 初始化
	 */
	public TableAttributeKeyValueTemplateVO() {
		super();
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
	 * @param templateValue
	 *          模板的值
	 */
	public TableAttributeKeyValueTemplateVO(String key, String packageName, String className, String templateValue) {
		super();
		this.key = key;
		this.packageName = packageName;
		this.className = className;
		this.templateValue = templateValue;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTemplateValue() {
		return templateValue;
	}

	public void setTemplateValue(String templateValue) {
		this.templateValue = templateValue;
	}

	@Override
	public String toString() {
		return "TableAttributeKeyValueTemplateClass [key=" + key + ", packageName=" + packageName + ", className=" + className
				+ ", templateValue=" + templateValue + "]";
	}

}
