package com.szmirren.entity;

/**
 * 生成文件的上下文
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class GeneratorContent {

	/** 数据库配置文件 */
	private DatabaseContent database;
	/** 实体类配置信息 */
	private EntityContent entity;
	/** 数据库表的属性 */
	private TableContent table;
	/** 实体类配置信息 */
	private ServiceContent service;
	/** 实体类配置信息 */
	private ServiceImplContent serviceImpl;
	/** 实体类配置信息 */
	private DaoContent dao;
	/** 实体类配置信息 */
	private MapperContent mapper;
	/** 实体类配置信息 */
	private ControllerContent controller;
	/** 实体类配置信息 */
	private UnitTestContent unitTest;
	/** 实体类配置信息 */
	private SqlAssistContent assist;
	/** 实体类配置信息 */
	private CustomContent custom;
	/** 实体类配置信息 */
	private CustomPropertyContent customProperty;

	public TableContent getTable() {
		return table;
	}

	public void setTable(TableContent table) {
		this.table = table;
	}

	public EntityContent getEntity() {
		return entity;
	}

	public DatabaseContent getDatabase() {
		return database;
	}

	public void setDatabase(DatabaseContent database) {
		this.database = database;
	}

	public void setEntity(EntityContent entity) {
		this.entity = entity;
	}
	public ServiceContent getService() {
		return service;
	}
	public void setService(ServiceContent service) {
		this.service = service;
	}
	public ServiceImplContent getServiceImpl() {
		return serviceImpl;
	}
	public void setServiceImpl(ServiceImplContent serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	public DaoContent getDao() {
		return dao;
	}

	public void setDao(DaoContent dao) {
		this.dao = dao;
	}

	public MapperContent getMapper() {
		return mapper;
	}

	public void setMapper(MapperContent mapper) {
		this.mapper = mapper;
	}

	public ControllerContent getController() {
		return controller;
	}

	public void setController(ControllerContent controller) {
		this.controller = controller;
	}

	public UnitTestContent getUnitTest() {
		return unitTest;
	}

	public void setUnitTest(UnitTestContent unitTest) {
		this.unitTest = unitTest;
	}

	public SqlAssistContent getAssist() {
		return assist;
	}

	public void setAssist(SqlAssistContent assist) {
		this.assist = assist;
	}

	public CustomContent getCustom() {
		return custom;
	}

	public void setCustom(CustomContent custom) {
		this.custom = custom;
	}

	public CustomPropertyContent getCustomProperty() {
		return customProperty;
	}

	public void setCustomProperty(CustomPropertyContent customProperty) {
		this.customProperty = customProperty;
	}

	@Override
	public String toString() {
		return "GeneratorContent [database=" + database + ", entity=" + entity + ", service=" + service + ", serviceImpl=" + serviceImpl
				+ ", dao=" + dao + ", mapper=" + mapper + ", controller=" + controller + ", unitTest=" + unitTest + ", assist=" + assist
				+ ", custom=" + custom + ", customProperty=" + customProperty + "]";
	}

}
