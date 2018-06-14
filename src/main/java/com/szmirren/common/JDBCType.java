package com.szmirren.common;

/**
 * jdbc类型
 * 
 * @author Mirren
 *
 */
public class JDBCType {

	public static String valiJDBCType(String str) {
		/*
		 * in JDBC enum type have
		 * 
		 * ARRAY(Types.ARRAY),
		 * 
		 * BIT(Types.BIT), TINYINT(Types.TINYINT), SMALLINT(Types.SMALLINT),
		 * INTEGER(Types.INTEGER),
		 * 
		 * BIGINT(Types.BIGINT), FLOAT(Types.FLOAT), REAL(Types.REAL),
		 * DOUBLE(Types.DOUBLE), NUMERIC(Types.NUMERIC),
		 * 
		 * DECIMAL(Types.DECIMAL), CHAR(Types.CHAR), VARCHAR(Types.VARCHAR),
		 * LONGVARCHAR(Types.LONGVARCHAR), DATE(Types.DATE),
		 * 
		 * TIME(Types.TIME), TIMESTAMP(Types.TIMESTAMP), BINARY(Types.BINARY),
		 * VARBINARY(Types.VARBINARY), LONGVARBINARY(Types.LONGVARBINARY),
		 * 
		 * NULL(Types.NULL), OTHER(Types.OTHER), BLOB(Types.BLOB),
		 * CLOB(Types.CLOB), BOOLEAN(Types.BOOLEAN),
		 * 
		 * CURSOR(-10), // Oracle UNDEFINED(Integer.MIN_VALUE + 1000),
		 * NVARCHAR(Types.NVARCHAR), // JDK6 NCHAR(Types.NCHAR), // JDK6
		 * NCLOB(Types.NCLOB), // JDK6
		 * 
		 * STRUCT(Types.STRUCT), JAVA_OBJECT(Types.JAVA_OBJECT),
		 * DISTINCT(Types.DISTINCT), REF(Types.REF), DATALINK(Types.DATALINK),
		 * 
		 * ROWID(Types.ROWID), // JDK6 LONGNVARCHAR(Types.LONGNVARCHAR), // JDK6
		 * SQLXML(Types.SQLXML), // JDK6 DATETIMEOFFSET(-155); // SQL Server
		 * 2008
		 * 
		 * 
		 */
		String[] item = { "ARRAY", "BIT", "TINYINT", "SMALLINT", "INTEGER", "BIGINT", "FLOAT", "REAL", "DOUBLE",
				"NUMERIC", "DECIMAL", "CHAR", "VARCHAR", "LONGVARCHAR", "DATE", "TIME", "TIMESTAMP", "BINARY",
				"VARBINARY", "LONGVARBINARY", "NULL", "OTHER", "BLOB", "CLOB", "BOOLEAN", "CURSOR", "UNDEFINED",
				"NVARCHAR", "NCHAR", "NCLOB", "STRUCT", "JAVA_OBJECT", "DISTINCT", "REF", "DATALINK", "ROWID",
				"LONGNVARCHAR", "SQLXML", "DATETIMEOFFSET" };
		for (String tmp : item) {
			if (str.equals(tmp)) {
				return tmp;
			}
		}
		if (str.contains("NUMBER")) {
			return "DECIMAL";
		}
		if (str.contains("VARCHAR2")) {
			return "VARCHAR";
		}
		if (str.contains("NVARCHAR2")) {
			return "NVARCHAR";
		}
		if (str.contains("INT")) {
			return "INTEGER";
		}
		if (str.contains("DOUBLE")) {
			return "DOUBLE";
		}
		if (str.contains("FLOAT")) {
			return "FLOAT";
		}
		if (str.contains("LONG")) {
			return "DECIMAL";
		}
		if (str.contains("TEXT")) {
			return "VARCHAR";
		}
		if (str.indexOf("DATETIME") != -1) {
			return "DATE";
		}

		return null;
	}
}
