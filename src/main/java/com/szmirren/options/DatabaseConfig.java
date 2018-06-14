package com.szmirren.options;

/**
 * 数据库连接配置文件
 * 
 * @author Mirren
 *
 */
public class DatabaseConfig {

	private String connName;
	private String connURL;
	private String listenPort;
	private String dbName;
	private String userName;
	private String userPwd;
	private String dbType;
	private String encoding;

	public String getConnName() {
		return connName;
	}

	public void setConnName(String connName) {
		this.connName = connName;
	}

	public String getConnURL() {
		return connURL;
	}

	public void setConnURL(String connURL) {
		this.connURL = connURL;
	}

	public String getListenPort() {
		return listenPort;
	}

	public void setListenPort(String listenPort) {
		this.listenPort = listenPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public DatabaseConfig() {
		super();
	}

	public DatabaseConfig(String connName, String connURL, String listenPort, String dbName, String userName, String userPwd, String dbType, String encoding) {
		super();
		this.connName = connName;
		this.connURL = connURL;
		this.listenPort = listenPort;
		this.dbName = dbName;
		this.userName = userName;
		this.userPwd = userPwd;
		this.dbType = dbType;
		this.encoding = encoding;
	}

	@Override
	public String toString() {
		return "DatabaseConfig [connName=" + connName + ", connURL=" + connURL + ", listenPort=" + listenPort + ", dbName=" + dbName + ", userName=" + userName + ", userPwd=" + userPwd + ", dbType="
				+ dbType + ", encoding=" + encoding + "]";
	}

}
