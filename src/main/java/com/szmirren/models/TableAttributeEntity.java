package com.szmirren.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;

/**
 * Table的key与packageName属性
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class TableAttributeEntity implements Comparable<TableAttributeEntity> {
	// ---------------------------表格需要的属性---------------------------------
	/** 是否创建 */
	private BooleanProperty tdCreate = new SimpleBooleanProperty(true);
	/** 列名 */
	private StringProperty tdColumnName = new SimpleStringProperty();
	/** jdbc数据类型 */
	private StringProperty tdJdbcType = new SimpleStringProperty();
	/** java数据类型 */
	private ComboBox<String> tdJavaType = new ComboBox<String>();
	/** 属性名称 */
	private StringProperty tdField = new SimpleStringProperty();

	// --------------------自定义属性--------------------------------
	/**
	 * 是否允许使用 NULL值<br>
	 * true = 明确允许使用 NULL值<br>
	 * false = 可能不允许使用 NULL值(不明确是否允许使用NULL值)<br>
	 */
	private boolean nullable;

	// ----------------java.sql.DatabaseMetaData.getColumns自带的属性----------------------
	/** 默认值 */
	private String columnDef;
	/** 描述列的注释 */
	private String remarks;
	/** 列的大小,对于 字符串 或 date 类型,列的大小是最大字符数,对于 numeric 和 decimal 类型,列的大小就是精度 */
	private int columnSize;
	/** 小数部分的位数 */
	private int decimalDigits;
	/** 表中的列的顺序(从 1 开始) */
	private int ordinalPosition;
	/**
	 * 初始化
	 */
	public TableAttributeEntity() {
		super();
		initComboBox();
	}
	/**
	 * 初始化
	 * 
	 * @param javaType
	 *          数据类型
	 * @param fieldName
	 *          字段名称
	 */
	public TableAttributeEntity(String javaType, String fieldName) {
		super();
		initComboBox();
		this.tdJavaType.setValue(javaType);
		this.tdField.setValue(fieldName);
	}
	public void initComboBox() {
		tdJavaType.setEditable(true);
		tdJavaType.getItems().addAll("int", "double", "char", "long", "java.util.Date", "java.sql.Date", "java.time.LocalDate",
				"java.time.LocalTime", "java.time.LocalDateTime", "java.util.List<E>", "java.util.Set<E>", "java.util.Map<K, V>", "JsonObject",
				"String", "Character", "Double", "Integer", "Long", "Object");
	}

	public BooleanProperty tdCreateProperty() {
		return tdCreate;
	}
	public boolean getTdCreate() {
		return tdCreate.get();
	}
	public void setTdCreate(boolean create) {
		this.tdCreate.set(create);
	}

	public String getTdColumnName() {
		return tdColumnName.getValue();
	}

	public void setTdColumnName(String tdColumnName) {
		this.tdColumnName.setValue(tdColumnName);
	}

	public void setTdColumnName(StringProperty tdColumnName) {
		this.tdColumnName = tdColumnName;
	}

	public String getTdJdbcType() {
		return tdJdbcType.getValue();
	}

	public void setTdJdbcType(String tdJdbcType) {
		this.tdJdbcType.setValue(tdJdbcType);
	}

	public void setTdJdbcType(StringProperty tdJdbcType) {
		this.tdJdbcType = tdJdbcType;
	}

	public ComboBox<String> getTdJavaType() {
		return tdJavaType;
	}

	public void setTdJavaType(String tdJavaType) {
		this.tdJavaType.setValue(tdJavaType);
	}

	public void setTdJavaType(ComboBox<String> tdJavaType) {
		this.tdJavaType = tdJavaType;
	}

	public String getTdField() {
		return tdField.getValue();
	}

	public void setTdField(String tdField) {
		this.tdField.setValue(tdField);
	}

	public void setTdField(StringProperty tdField) {
		this.tdField = tdField;
	}

	public String getColumnDef() {
		return columnDef;
	}

	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public int getDecimalDigits() {
		return decimalDigits;
	}

	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public int getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(int ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	@Override
	public String toString() {
		return "TableAttributeEntity [tdCreate=" + tdCreate + ", tdColumnName=" + tdColumnName + ", tdJdbcType=" + tdJdbcType + ", tdJavaType="
				+ tdJavaType + ", tdField=" + tdField + ", nullable=" + nullable + ", columnDef=" + columnDef + ", remarks=" + remarks
				+ ", columnSize=" + columnSize + ", decimalDigits=" + decimalDigits + ", ordinalPosition=" + ordinalPosition + "]";
	}
	@Override
	public int compareTo(TableAttributeEntity obj) {
		return ordinalPosition > obj.getOrdinalPosition() ? 1 : (ordinalPosition == obj.getOrdinalPosition() ? 0 : -1);
	}

}
