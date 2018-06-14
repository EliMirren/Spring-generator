package com.szmirren.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.szmirren.options.ControllerConfig;
import com.szmirren.options.CustomConfig;
import com.szmirren.options.CustomPropertyConfig;
import com.szmirren.options.DaoConfig;
import com.szmirren.options.DatabaseConfig;
import com.szmirren.options.EntityConfig;
import com.szmirren.options.HistoryConfig;
import com.szmirren.options.MapperConfig;
import com.szmirren.options.ServiceConfig;
import com.szmirren.options.ServiceImplConfig;
import com.szmirren.options.SqlAssistConfig;
import com.szmirren.options.UnitTestConfig;

/**
 * 配置文件工具
 * 
 * @author Mirren
 *
 */
public class ConfigUtil {
	private static final String CONFIG_DIR = "config";
	private static final String CONFIG_FILE = "/ConfigDB.db";
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String DB_URL = "jdbc:sqlite:./config/ConfigDB.db";

	/**
	 * 获得数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL);
		return conn;
	}

	/**
	 * 查看是否存在配置文件数据库,如果不存在则创建
	 * 
	 * @throws IOException
	 */
	public static void existsConfigDB() throws IOException {
		File path = new File(CONFIG_DIR);

		if (!path.exists()) {
			path.mkdir();
		}
		File file = new File(CONFIG_DIR + CONFIG_FILE);
		if (!file.exists()) {
			InputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/ConfigDB.db");
				fos = new FileOutputStream(CONFIG_DIR + CONFIG_FILE);
				byte[] buffer = new byte[1024];
				int byteread = 0;
				while ((byteread = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, byteread);
				}
			} finally {
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
			}

		}
	}

	/**
	 * 获得所有配置的表明
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> getConfigTables() throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select name from sqlite_master where type='table' order by name";
			ResultSet resultSet = stat.executeQuery(sql);
			List<String> result = new ArrayList<>();
			while (resultSet.next()) {
				result.add(resultSet.getString("name"));
			}
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 保存数据库连接
	 * 
	 * @param connName
	 * @param dbConfig
	 * @throws Exception
	 */
	public static void saveDatabaseConfig(String connName, DatabaseConfig dbConfig) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			ResultSet rs1 = stat.executeQuery(String.format("select * from DBSet where name ='%s'", connName));
			if (rs1.next()) {
				throw new RuntimeException("该连接名称已经存在!请使用其它名字...");
			}
			String jsonStr = JSON.toJSONString(dbConfig);
			String sql = String.format("insert into DBSet values('%s', '%s')", connName, jsonStr);
			stat.executeUpdate(sql);
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 更新数据库连接
	 * 
	 * @param dbConfig
	 * @throws Exception
	 */
	public static void updateDatabaseConfig(DatabaseConfig dbConfig) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = JSON.toJSONString(dbConfig);
			String sql = String.format("update DBSet set value='%s' where name='%s'", jsonStr, dbConfig.getConnName());
			stat.executeUpdate(sql);
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 获得数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<DatabaseConfig> getDatabaseConfig() throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from DBSet");
			List<DatabaseConfig> result = new ArrayList<DatabaseConfig>();
			while (rs.next()) {
				String name = rs.getString("name");
				String value = rs.getString("value");
				DatabaseConfig databaseConfig = JSON.parseObject(value, DatabaseConfig.class);
				databaseConfig.setConnName(name);
				result.add(databaseConfig);
			}

			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 删除数据库连接
	 * 
	 * @param name
	 * @throws Exception
	 */
	public static void deleteDatabaseConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("delete from DBSet where name='%s'", name);
			stat.executeUpdate(sql);
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 保存配置文件信息
	 * 
	 * @param Config
	 * @throws Exception
	 */
	public static void saveHistoryConfig(HistoryConfig config) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = JSON.toJSONString(config);
			String sql = String.format("replace into HistoryConfig values('%s', '%s')", config.getHistoryConfigName(), jsonStr);
			stat.executeUpdate(sql);
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 获得配置文件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<HistoryConfig> getHistoryConfigs() throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from HistoryConfig ";
			rs = stat.executeQuery(sql);
			List<HistoryConfig> configs = new ArrayList<>();
			while (rs.next()) {
				String value = rs.getString("value");
				configs.add(JSON.parseObject(value, HistoryConfig.class));
			}
			return configs;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 通过名字找到配置信息
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static HistoryConfig getHistoryConfigByName(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("select * from HistoryConfig where name ='%s'", name);
			rs = stat.executeQuery(sql);
			HistoryConfig config = null;
			while (rs.next()) {
				String value = rs.getString("value");
				config = JSON.parseObject(value, HistoryConfig.class);
			}
			return config;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 删除配置文件信息
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static int deleteHistoryConfigByName(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("DELETE FROM HistoryConfig where name='%s'", name);
			return stat.executeUpdate(sql);
		} finally {
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 保存实体类配置文件信息
	 * 
	 * @param Config
	 * @throws Exception
	 */
	public static int saveEntityConfig(EntityConfig config, String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = config.toJsonString();
			String sql = String.format("replace into ClassConfig(name,value) values('%s', '%s')", name, jsonStr);
			int result = stat.executeUpdate(sql);
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 获得实体类配置文件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static EntityConfig getEntityConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("select * from ClassConfig where name='%s'", name);
			ResultSet resultSet = stat.executeQuery(sql);
			while (resultSet.next()) {
				JSONObject object = JSON.parseObject(resultSet.getString("value"), JSONObject.class);
				EntityConfig result = new EntityConfig(object);
				return result;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}

	/**
	 * 保存Service配置文件信息
	 * 
	 * @param Config
	 * @throws Exception
	 */
	public static int saveServiceConfig(ServiceConfig config, String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = JSON.toJSONString(config);
			String sql = String.format("replace into ServiceConfig(name,value) values('%s', '%s')", name, jsonStr);
			int result = stat.executeUpdate(sql);
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 获得Service配置文件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ServiceConfig getServiceConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("select * from ServiceConfig where name='%s'", name);
			ResultSet resultSet = stat.executeQuery(sql);
			while (resultSet.next()) {
				ServiceConfig result = JSON.parseObject(resultSet.getString("value"), ServiceConfig.class);
				return result;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}

	/**
	 * 获得ServiceImpl配置文件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ServiceImplConfig getServiceImplConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("select * from ServiceImplConfig where name='%s'", name);
			ResultSet resultSet = stat.executeQuery(sql);
			while (resultSet.next()) {
				ServiceImplConfig result = JSON.parseObject(resultSet.getString("value"), ServiceImplConfig.class);
				return result;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}

	/**
	 * 保存ServiceImpl配置文件信息
	 * 
	 * @param Config
	 * @throws Exception
	 */
	public static int saveServiceImplConfig(ServiceImplConfig config, String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = JSON.toJSONString(config);
			String sql = String.format("replace into ServiceImplConfig(name,value) values('%s', '%s')", name, jsonStr);
			int result = stat.executeUpdate(sql);
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 获得Router配置文件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ControllerConfig getRouterConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("select * from RouterConfig where name='%s'", name);
			ResultSet resultSet = stat.executeQuery(sql);
			while (resultSet.next()) {
				ControllerConfig result = JSON.parseObject(resultSet.getString("value"), ControllerConfig.class);
				return result;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}

	/**
	 * 保存Router配置文件信息
	 * 
	 * @param Config
	 * @throws Exception
	 */
	public static int saveRouterConfig(ControllerConfig config, String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = JSON.toJSONString(config);
			String sql = String.format("replace into RouterConfig(name,value) values('%s', '%s')", name, jsonStr);
			int result = stat.executeUpdate(sql);
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 保存SQL配置文件信息
	 * 
	 * @param Config
	 * @throws Exception
	 */
	public static int saveSQLConfig(DaoConfig config, String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = JSON.toJSONString(config);
			String sql = String.format("replace into SqlConfig(name,value) values('%s', '%s')", name, jsonStr);
			int result = stat.executeUpdate(sql);
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 获得SQL配置文件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static DaoConfig getSQLConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("select * from SQLConfig where name='%s'", name);
			ResultSet resultSet = stat.executeQuery(sql);
			while (resultSet.next()) {
				DaoConfig result = JSON.parseObject(resultSet.getString("value"), DaoConfig.class);
				return result;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}

	/**
	 * 保存SqlAssist配置文件信息
	 * 
	 * @param Config
	 * @throws Exception
	 */
	public static int saveSqlAssistConfig(SqlAssistConfig config, String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = JSON.toJSONString(config);
			String sql = String.format("replace into SqlAssistConfig (name,value) values('%s', '%s')", name, jsonStr);
			int result = stat.executeUpdate(sql);
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 获得SqlAssist配置文件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static SqlAssistConfig getSqlAssistConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("select * from SqlAssistConfig where name='%s'", name);
			ResultSet resultSet = stat.executeQuery(sql);
			while (resultSet.next()) {
				SqlAssistConfig result = JSON.parseObject(resultSet.getString("value"), SqlAssistConfig.class);
				return result;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}

	/**
	 * 保存SqlAndParams配置文件信息
	 * 
	 * @param Config
	 * @throws Exception
	 */
	public static int saveSqlAndParamsConfig(MapperConfig config, String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = JSON.toJSONString(config);
			String sql = String.format("replace into SqlAndParamsConfig (name,value) values('%s', '%s')", name, jsonStr);
			int result = stat.executeUpdate(sql);
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 获得SqlAndParams配置文件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static MapperConfig getSqlAndParamsConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("select * from SqlAndParamsConfig where name='%s'", name);
			ResultSet resultSet = stat.executeQuery(sql);
			while (resultSet.next()) {
				MapperConfig result = JSON.parseObject(resultSet.getString("value"), MapperConfig.class);
				return result;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}

	/**
	 * 保存单元测试配置文件信息
	 * 
	 * @param Config
	 * @throws Exception
	 */
	public static int saveUnitTestConfig(UnitTestConfig config, String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = JSON.toJSONString(config);
			String sql = String.format("replace into UnitTestConfig (name,value) values('%s', '%s')", name, jsonStr);
			int result = stat.executeUpdate(sql);
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 获得单元测试配置文件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static UnitTestConfig getUnitTestConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("select * from UnitTestConfig where name='%s'", name);
			ResultSet resultSet = stat.executeQuery(sql);
			while (resultSet.next()) {
				UnitTestConfig result = JSON.parseObject(resultSet.getString("value"), UnitTestConfig.class);
				return result;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}

	/**
	 * 保存Custom配置文件信息
	 * 
	 * @param Config
	 * @throws Exception
	 */
	public static int saveCustomConfig(CustomConfig config, String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = config.toJsonString();
			String sql = String.format("replace into CustomConfig (name,value) values('%s', '%s')", name, jsonStr);
			int result = stat.executeUpdate(sql);
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 获得Custom配置文件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static CustomConfig getCustomConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("select * from CustomConfig where name='%s'", name);
			ResultSet resultSet = stat.executeQuery(sql);
			while (resultSet.next()) {
				JSONObject object = JSON.parseObject(resultSet.getString("value"), JSONObject.class);
				CustomConfig result = new CustomConfig(object);
				return result;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}

	/**
	 * 保存CustomProperty配置文件信息
	 * 
	 * @param Config
	 * @throws Exception
	 */
	public static int saveCustomPropertyConfig(CustomPropertyConfig config, String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String jsonStr = JSON.toJSONString(config);
			String sql = String.format("replace into CustomPropertyConfig (name,value) values('%s', '%s')", name, jsonStr);
			int result = stat.executeUpdate(sql);
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 获得SqlAndParams配置文件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static CustomPropertyConfig getCustomPropertyConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = String.format("select * from CustomPropertyConfig where name='%s'", name);
			ResultSet resultSet = stat.executeQuery(sql);
			while (resultSet.next()) {
				CustomPropertyConfig result = JSON.parseObject(resultSet.getString("value"), CustomPropertyConfig.class);
				return result;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}
}
