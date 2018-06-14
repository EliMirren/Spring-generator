package com.szmirren.entity;

import com.szmirren.models.TableAttributeEntity;

/**
 * 实体类的属性
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class FieldAttribute extends TableColumnsAttribute {
	/** 是否创建该属性该属性 */
	private boolean create;
	/** 字段 */
	private String field;
	/** 字段第一个字母大写 */
	private String fieldPascal;
	/** 字段的get方法名,如果变量为boolean 类型为字段的is方法名 */
	private String fget;
	/** 字段的set方法名 */
	private String fset;
	/** 字段的获取类型的方法名,比如字符串getString */
	private String fgetType;
	/** 字段的设置类型的方法名,比如字符串setString */
	private String fsetType;

	/**
	 * 初始化
	 */
	public FieldAttribute() {
		super();
	}

	/**
	 * 初始化
	 */
	public FieldAttribute(TableAttributeEntity entity) {
		super();
		super.setJavaType(entity.getTdJavaType().getValue());
		super.setNullable(entity.isNullable());
		super.setColumnName(entity.getTdColumnName());
		super.setColumnDef(entity.getColumnDef());
		super.setRemarks(entity.getRemarks());
		super.setColumnSize(entity.getColumnSize());
		super.setTypeName(entity.getTdJdbcType());
		super.setDecimalDigits(entity.getDecimalDigits());
		super.setOrdinalPosition(entity.getOrdinalPosition());
		this.create = entity.getTdCreate();
	}

	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldPascal() {
		return fieldPascal;
	}

	public void setFieldPascal(String fieldPascal) {
		this.fieldPascal = fieldPascal;
	}

	public String getFget() {
		return fget;
	}

	public void setFget(String fget) {
		this.fget = fget;
	}

	public String getFset() {
		return fset;
	}

	public void setFset(String fset) {
		this.fset = fset;
	}

	public String getFgetType() {
		return fgetType;
	}

	public void setFgetType(String fgetType) {
		this.fgetType = fgetType;
	}

	public String getFsetType() {
		return fsetType;
	}

	public void setFsetType(String fsetType) {
		this.fsetType = fsetType;
	}

	@Override
	public String toString() {
		return super.toString() + "\nFieldAttribute [create=" + create + ", field=" + field + ", fieldPascal=" + fieldPascal + ", fget=" + fget
				+ ", fset=" + fset + ", fgetType=" + fgetType + ", fsetType=" + fsetType + "]";
	}

}
