package ${content.sqlAndParams.classPackage};

import java.util.List;
import java.util.ArrayList;
import io.vertx.core.json.JsonArray;

/**
 * 用于生成数据库语句时返回SQL语句与参数
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class SqlAndParams {
	private String sql;// SQL语句
	private JsonArray params;// 参数
	private List<String> batchSql;// 批量SQL语句
	private List<JsonArray> batchParams;// 批量参数
	private boolean succeeded = true;// 生成语句是否成功
	/**
	 * 创建一个新的SqlAndParams
	 */
	public SqlAndParams() {
		super();
	}
	/**
	 * 创建一个新的SqlAndParams
	 * 
	 * @param sql
	 *          SQL语句
	 */
	public SqlAndParams(String sql) {
		super();
		this.sql = sql;
	}

	/**
	 * 创建一个新的SqlAndParams
	 * 
	 * @param succeeded
	 *          是否成功,true=成功,false=失败
	 * @param sql
	 *          SQL语句或者失败语句
	 */
	public SqlAndParams(boolean succeeded, String sql) {
		super();
		this.succeeded = succeeded;
		this.sql = sql;
	}

	/**
	 * 创建一个新的SqlAndParams
	 * 
	 * @param batchSql
	 *          SQL语句集合
	 */
	public SqlAndParams(List<String> batchSql) {
		super();
		this.batchSql = batchSql;
	}
	/**
	 * 创建一个新的SqlAndParams
	 * 
	 * @param sql
	 *          SQL语句
	 * @param params
	 *          参数
	 */
	public SqlAndParams(String sql, JsonArray params) {
		super();
		this.sql = sql;
		this.params = params;
	}
	/**
	 * 创建一个新的SqlAndParams
	 * 
	 * @param sql
	 *          SQL语句
	 * @param batchParams
	 *          参数集合
	 */
	public SqlAndParams(String sql, List<JsonArray> batchParams) {
		super();
		this.sql = sql;
		this.batchParams = batchParams;
	}
	/**
	 * 创建一个新的SqlAndParams
	 * 
	 * @param batchSql
	 *          SQL语句集
	 * @param batchParams
	 *          参数集合
	 */
	public SqlAndParams(List<String> batchSql, List<JsonArray> batchParams) {
		super();
		this.batchSql = batchSql;
		this.batchParams = batchParams;
	}
	/**
	 * 获得SQL语句,如果失败时则为错误语句
	 * 
	 * @return
	 */
	public String getSql() {
		return sql;
	}
	/**
	 * 设置SQL语句,如果失败时则为错误语句
	 * 
	 * @param sql
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
	/**
	 * 获得参数
	 * 
	 * @return
	 */
	public JsonArray getParams() {
		return params;
	}
	/**
	 * 设置参数
	 */
	public void setParams(JsonArray params) {
		this.params = params;
	}
	/**
	 * 获取SQL语句集合
	 * 
	 * @return
	 */
	public List<String> getBatchSql() {
		return batchSql;
	}
	/**
	 * 设置SQL语句集合
	 * 
	 * @param batchSql
	 */
	public void setBatchSql(List<String> batchSql) {
		this.batchSql = batchSql;
	}
	/**
	 * 获取参数集合
	 * 
	 * @return
	 */
	public List<JsonArray> getBatchParams() {
		return batchParams;
	}
	/**
	 * 设置参数集合
	 * 
	 * @param batchParams
	 */
	public void setBatchParams(List<JsonArray> batchParams) {
		this.batchParams = batchParams;
	}
	/**
	 * 获取是否成功
	 * 
	 * @return
	 */
	public boolean succeeded() {
		return succeeded;
	}
	/**
	 * 设置是否成功
	 * 
	 * @param succeeded
	 */
	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}

	/**
	 * 用于将SqlAndParams添加中的sql与params添加到当前对象的Batch中,既将多个普通SqlAndParams填充成一个Batch
	 * SqlAndParams
	 * 
	 * @param sqlAndParams
	 * @return
	 */
	public SqlAndParams addSqlAndParams(SqlAndParams... sqlp) {
		if (batchSql == null) {
			batchSql = new ArrayList<>();
		}
		if (batchParams == null) {
			batchParams = new ArrayList<>();
		}
		for (SqlAndParams sp : sqlp) {
			batchSql.add(sp.getSql());
			batchParams.add(sp.getParams());
		}
		return this;
	}

	@Override
	public String toString() {
		return "SqlAndParams [sql=" + sql + ", params=" + params + ", batchSql=" + batchSql + ", batchParams=" + batchParams + ", succeeded=" + succeeded + "]";
	}

}
