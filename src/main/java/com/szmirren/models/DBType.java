package com.szmirren.models;

/**
 * 数据库类型
 * 
 * @author Mirren
 *
 */
public enum DBType {
	Oracle("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s"),
	OracleServiceName("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s/%s"),
	MySQL("com.mysql.cj.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=%s&serverTimezone=UTC"), 
	SqlServer("com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://%s:%s;databaseName=%s"), 
	PostgreSQL("org.postgresql.Driver","jdbc:postgresql://%s:%s/%s");
	private final String driverClass;
	private final String connectionUrlPattern;

	DBType(String driverClass, String connectionUrlPattern) {
		this.driverClass = driverClass;
		this.connectionUrlPattern = connectionUrlPattern;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public String getConnectionUrlPattern() {
		return connectionUrlPattern;
	}

}