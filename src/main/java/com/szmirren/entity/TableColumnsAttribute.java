package com.szmirren.entity;

/**
 * 数据库列的属性
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class TableColumnsAttribute implements Comparable<TableColumnsAttribute> {

	// --------------------自定义属性--------------------------------
	/** 字段java的数据类型 */
	private String javaType;
	/**
	 * 是否允许使用 NULL值<br>
	 * true = 明确允许使用 NULL值<br>
	 * false = 可能不允许使用 NULL值(不明确是否允许使用NULL值)<br>
	 */
	private boolean nullable;

	// ----------------java.sql.DatabaseMetaData.getColumns自带的属性----------------------
	/** 列的名称 */
	private String columnName;
	/** 默认值 */
	private String columnDef;
	/** 描述列的注释 */
	private String remarks;
	/** 列的大小,对于 字符串 或 date 类型,列的大小是最大字符数,对于 numeric 和 decimal 类型,列的大小就是精度 */
	private int columnSize;
	/** 数据库数据类型名称,对于 UDT,该类型名称是完全限定的 */
	private String typeName;
	/** 小数部分的位数 */
	private int decimalDigits;
	/** 表中的列的顺序(从 1 开始) */
	private int ordinalPosition;

	/**
	 * 初始化
	 */
	public TableColumnsAttribute() {
		super();
	}

	/**
	 * 初始化
	 * 
	 * @param javaType
	 *          java类型
	 * @param columnName
	 *          列的名字
	 * @param columnDef
	 *          默认值
	 * @param remarks
	 *          备注信息
	 * @param columnSize
	 *          列的长度
	 * @param typeName
	 *          JDBC数据类型
	 * @param decimalDigits
	 *          小数点的位数
	 * @param nullable
	 *          是否允许为空值,true允许为空值,false不允许为空值(不明确是否允许使用NULL值)
	 * @param ordinalPosition
	 *          列在表中的顺序
	 */
	public TableColumnsAttribute(String javaType, String columnName, String columnDef, String remarks, int columnSize, String typeName,
			int decimalDigits, boolean nullable, int ordinalPosition) {
		super();
		this.javaType = javaType;
		this.columnName = columnName;
		this.columnDef = columnDef;
		this.remarks = remarks;
		this.columnSize = columnSize;
		this.typeName = typeName;
		this.decimalDigits = decimalDigits;
		this.nullable = nullable;
		this.ordinalPosition = ordinalPosition;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
		return "TableColumnsAttribute [javaType=" + javaType + ", columnName=" + columnName + ", columnDef=" + columnDef + ", remarks="
				+ remarks + ", columnSize=" + columnSize + ", typeName=" + typeName + ", decimalDigits=" + decimalDigits + ", nullable=" + nullable
				+ ", ordinalPosition=" + ordinalPosition + "]";
	}

	@Override
	public int compareTo(TableColumnsAttribute obj) {
		return ordinalPosition > obj.getOrdinalPosition() ? 1 : (ordinalPosition == obj.getOrdinalPosition() ? 0 : -1);
	}

}
