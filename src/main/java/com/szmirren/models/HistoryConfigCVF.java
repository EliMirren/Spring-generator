package com.szmirren.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;

/**
 * 配置文件设置项
 * 
 * @author Mirren
 *
 */
public class HistoryConfigCVF {

	StringProperty name;
	HBox hbox;

	public HistoryConfigCVF() {
		super();
	}

	public HistoryConfigCVF(String name, HBox hbox) {
		super();
		this.name = new SimpleStringProperty(name);
		this.hbox = hbox;
	}

	public HistoryConfigCVF(HBox hbox) {
		super();
		this.hbox = hbox;
	}

	public HBox getHbox() {
		return hbox;
	}

	public void setHbox(HBox hbox) {
		this.hbox = hbox;
	}

	public String getName() {
		return name.get();
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

}
