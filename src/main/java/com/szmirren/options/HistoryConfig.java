package com.szmirren.options;

/**
 * 配置信息
 * 
 * @author Mirren
 *
 */
public class HistoryConfig {
	/** 配置信息的名字 */
	private String historyConfigName;
	/** 生产路径 */
	private String projectPath;
	/** 实体类的包名 */
	private String entityPackage;
	/** 实体类的类名 */
	private String entityName;
	/** service包名 */
	private String servicePackage;
	/** service类名 */
	private String serviceName;
	/** service实现类包名 */
	private String serviceImplPackage;
	/** service实现类名 */
	private String serviceImplName;
	/** Controller类包名 */
	private String controllerPackage;
	/** Controller类名 */
	private String controllerName;
	/** Dao类的包 */
	private String daoPackage;
	/** Dao类名 */
	private String daoName;
	/** Mapper类的包 */
	private String mapperPackage;
	/** Mapper名称 */
	private String mapperName;

	/** sqlAssist包名 */
	private String assistPackage;
	/** 单元测试包名 */
	private String unitTestPackage;
	/** 单元测试类名 */
	private String unitTestName;
	/** 字符编码格式 */
	private String codeFormat;

	/** 数据库配置文件 */
	private DatabaseConfig dbConfig;
	/** 实体类配置文件 */
	private EntityConfig entityConfig;
	/** Service配置文件 */
	private ServiceConfig serviceConfig;
	/** Service实现类的配置文件 */
	private ServiceImplConfig serviceImplConfig;
	/** Controller的配置文件 */
	private ControllerConfig controllerConfig;
	/** DAO的配置文件 */
	private DaoConfig daoConfig;
	/** Mapper的配置文件 */
	private MapperConfig mapperConfig;
	/** SqlAssist的配置文件 */
	private SqlAssistConfig assistConfig;
	/** 单元测试配置文件 */
	private UnitTestConfig unitTestConfig;
	/** 自定义包类的配置文件 */
	private CustomConfig customConfig;
	/** 自定义属性的配置文件 */
	private CustomPropertyConfig customPropertyConfig;

	/**
	 * 初始化
	 */
	public HistoryConfig() {
		super();
	}

	public HistoryConfig(String projectPath, String entityPackage, String entityName, String servicePackage, String serviceName,
			String serviceImplPackage, String serviceImplName, String controllerPackage, String controllerName, String daoPackage, String daoName,
			String mapperPackage, String mapperName, String assistPackage, String unitTestPackage, String unitTestName, String codeFormat) {
		super();
		this.projectPath = projectPath;
		this.entityPackage = entityPackage;
		this.entityName = entityName;
		this.servicePackage = servicePackage;
		this.serviceName = serviceName;
		this.serviceImplPackage = serviceImplPackage;
		this.serviceImplName = serviceImplName;
		this.controllerPackage = controllerPackage;
		this.controllerName = controllerName;
		this.daoPackage = daoPackage;
		this.daoName = daoName;
		this.mapperPackage = mapperPackage;
		this.mapperName = mapperName;
		this.assistPackage = assistPackage;
		this.unitTestPackage = unitTestPackage;
		this.unitTestName = unitTestName;
		this.codeFormat = codeFormat;
	}

	public MapperConfig getMapperConfig() {
		return mapperConfig;
	}

	public void setMapperConfig(MapperConfig mapperConfig) {
		this.mapperConfig = mapperConfig;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}

	public String getMapperName() {
		return mapperName;
	}

	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}

	public String getHistoryConfigName() {
		return historyConfigName;
	}

	public void setHistoryConfigName(String historyConfigName) {
		this.historyConfigName = historyConfigName;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceImplPackage() {
		return serviceImplPackage;
	}

	public void setServiceImplPackage(String serviceImplPackage) {
		this.serviceImplPackage = serviceImplPackage;
	}

	public String getServiceImplName() {
		return serviceImplName;
	}

	public void setServiceImplName(String serviceImplName) {
		this.serviceImplName = serviceImplName;
	}

	public String getControllerPackage() {
		return controllerPackage;
	}

	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getDaoName() {
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	public String getAssistPackage() {
		return assistPackage;
	}
	public void setAssistPackage(String assistPackage) {
		this.assistPackage = assistPackage;
	}
	public String getUnitTestPackage() {
		return unitTestPackage;
	}

	public void setUnitTestPackage(String unitTestPackage) {
		this.unitTestPackage = unitTestPackage;
	}

	public String getUnitTestName() {
		return unitTestName;
	}

	public void setUnitTestName(String unitTestName) {
		this.unitTestName = unitTestName;
	}

	public String getCodeFormat() {
		return codeFormat;
	}

	public void setCodeFormat(String codeFormat) {
		this.codeFormat = codeFormat;
	}

	public DatabaseConfig getDbConfig() {
		return dbConfig;
	}

	public void setDbConfig(DatabaseConfig dbConfig) {
		this.dbConfig = dbConfig;
	}

	public EntityConfig getEntityConfig() {
		return entityConfig;
	}

	public void setEntityConfig(EntityConfig entityConfig) {
		this.entityConfig = entityConfig;
	}

	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	public void setServiceConfig(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	public ServiceImplConfig getServiceImplConfig() {
		return serviceImplConfig;
	}

	public void setServiceImplConfig(ServiceImplConfig serviceImplConfig) {
		this.serviceImplConfig = serviceImplConfig;
	}

	public ControllerConfig getControllerConfig() {
		return controllerConfig;
	}

	public void setControllerConfig(ControllerConfig controllerConfig) {
		this.controllerConfig = controllerConfig;
	}

	public DaoConfig getDaoConfig() {
		return daoConfig;
	}

	public void setDaoConfig(DaoConfig daoConfig) {
		this.daoConfig = daoConfig;
	}

	public SqlAssistConfig getAssistConfig() {
		return assistConfig;
	}

	public void setAssistConfig(SqlAssistConfig assistConfig) {
		this.assistConfig = assistConfig;
	}

	public UnitTestConfig getUnitTestConfig() {
		return unitTestConfig;
	}

	public void setUnitTestConfig(UnitTestConfig unitTestConfig) {
		this.unitTestConfig = unitTestConfig;
	}

	public CustomConfig getCustomConfig() {
		return customConfig;
	}

	public void setCustomConfig(CustomConfig customConfig) {
		this.customConfig = customConfig;
	}

	public CustomPropertyConfig getCustomPropertyConfig() {
		return customPropertyConfig;
	}

	public void setCustomPropertyConfig(CustomPropertyConfig customPropertyConfig) {
		this.customPropertyConfig = customPropertyConfig;
	}

	@Override
	public String toString() {
		return "HistoryConfig [historyConfigName=" + historyConfigName + ", projectPath=" + projectPath + ", entityPackage=" + entityPackage
				+ ", entityName=" + entityName + ", servicePackage=" + servicePackage + ", serviceName=" + serviceName + ", serviceImplPackage="
				+ serviceImplPackage + ", serviceImplName=" + serviceImplName + ", controllerPackage=" + controllerPackage + ", controllerName="
				+ controllerName + ", daoPackage=" + daoPackage + ", daoName=" + daoName + ", mapperPackage=" + mapperPackage + ", mapperName="
				+ mapperName + ", assistPackage=" + assistPackage + ", unitTestPackage=" + unitTestPackage + ", unitTestName=" + unitTestName
				+ ", codeFormat=" + codeFormat + ", dbConfig=" + dbConfig + ", entityConfig=" + entityConfig + ", serviceConfig=" + serviceConfig
				+ ", serviceImplConfig=" + serviceImplConfig + ", controllerConfig=" + controllerConfig + ", daoConfig=" + daoConfig
				+ ", mapperConfig=" + mapperConfig + ", assistConfig=" + assistConfig + ", unitTestConfig=" + unitTestConfig + ", customConfig="
				+ customConfig + ", customPropertyConfig=" + customPropertyConfig + "]";
	}

}
