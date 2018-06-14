package com.szmirren.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * CustomContent的实体类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class CustomContent {
	/** CustomContent类的配置文件 */
	private Map<String, Object> item = new HashMap<>();

	/**
	 * 初始化
	 */
	public CustomContent() {
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
		return "CustomContent [item=" + item + "]";
	}

}
