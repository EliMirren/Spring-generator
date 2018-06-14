package com.szmirren.entity;

import java.util.List;

import com.szmirren.common.StrUtil;

/**
 * 实体类的上下文
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class EntityContent {
	/** 实体类的包名 */
	private String classPackage;
	/** 实体类的名字 */
	private String className;
	/** 实体类的名字,首字母小写 */
	private String classNameLower;
	/** 数据库表的名字 */
	private String tableName;
	/** 表的别名 */
	private String tableAlias;
	/** 数据库表的主键名字 */
	private String primaryKey;
	/** 数据库表的主键jdbc数据类型 */
	private String primaryKeyJdbcType;
	/** 数据库表的主键java数据类型 */
	private String primaryKeyJavaType;

	/** 实体类的属性信息 */
	private List<FieldAttribute> attrs;

	/** 主键属性 */
	private FieldAttribute primaryKeyAttr;
	/** 不能为空的属性 */
	private List<FieldAttribute> cantNullAttrs;
	/** 其他属性 */
	private List<FieldAttribute> otherAttrs;

	/**
	 * 初始化
	 */
	public EntityContent() {
		super();
	}
	/**
	 * 初始化
	 * 
	 * @param classPackage
	 *          包名
	 * @param className
	 *          类名
	 * @param tableName
	 *          表名
	 */
	public EntityContent(String classPackage, String className, String tableName) {
		super();
		this.classPackage = classPackage;
		this.className = className;
		this.classNameLower = StrUtil.fristToLoCase(className);
		this.tableName = tableName;
	}

	public String getClassPackage() {
		return classPackage;
	}
	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
		this.classNameLower = StrUtil.fristToLoCase(className);
	}
	public String getClassNameLower() {
		return classNameLower;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableAlias() {
		return tableAlias;
	}
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public List<FieldAttribute> getAttrs() {
		return attrs;
	}
	public void setAttrs(List<FieldAttribute> attrs) {
		this.attrs = attrs;
	}
	public String getPrimaryKeyJdbcType() {
		return primaryKeyJdbcType;
	}
	public void setPrimaryKeyJdbcType(String primaryKeyJdbcType) {
		this.primaryKeyJdbcType = primaryKeyJdbcType;
	}
	public String getPrimaryKeyJavaType() {
		return primaryKeyJavaType;
	}
	public void setPrimaryKeyJavaType(String primaryKeyJavaType) {
		this.primaryKeyJavaType = primaryKeyJavaType;
	}

	public FieldAttribute getPrimaryKeyAttr() {
		return primaryKeyAttr;
	}
	public void setPrimaryKeyAttr(FieldAttribute primaryKeyAttr) {
		this.primaryKeyAttr = primaryKeyAttr;
	}
	public List<FieldAttribute> getCantNullAttrs() {
		return cantNullAttrs;
	}
	public void setCantNullAttrs(List<FieldAttribute> cantNullAttrs) {
		this.cantNullAttrs = cantNullAttrs;
	}
	public List<FieldAttribute> getOtherAttrs() {
		return otherAttrs;
	}
	public void setOtherAttrs(List<FieldAttribute> otherAttrs) {
		this.otherAttrs = otherAttrs;
	}
	@Override
	public String toString() {
		return "EntityContent [classPackage=" + classPackage + ", className=" + className + ", classNameLower=" + classNameLower
				+ ", tableName=" + tableName + ", tableAlias=" + tableAlias + ", primaryKey=" + primaryKey + ", primaryKeyJdbcType="
				+ primaryKeyJdbcType + ", primaryKeyJavaType=" + primaryKeyJavaType + ", attrs=" + attrs + "]";
	}

}
