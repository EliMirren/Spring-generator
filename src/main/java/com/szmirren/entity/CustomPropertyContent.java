package com.szmirren.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义属性的属性类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class CustomPropertyContent {
	/** 自定义属性类的配置信息 */
	private Map<String, Object> item = new HashMap<>();

	/**
	 * 初始化
	 */
	public CustomPropertyContent() {
		super();
	}

	public Map<String, Object> getItem() {
		return item;
	}

	public void setItem(Map<String, Object> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "CustomPropertyContent [item=" + item + "]";
	}

}
