package ${content.sqlAssist.classPackage};

import java.util.ArrayList;
import java.util.List;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * {@link AbstractSQL} 的查询工具类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class SqlAssist {
	// 去重
	private String distinct;
	// 自定义排序
	private String order;
	// 数据分页开始行
	private Integer startRow;
	// 每次取多少行数据
	private Integer rowSize;
	// 设置自定义返回列
	private String resultColumn;
	// 条件集
	private List<SqlWhereCondition<?>> condition = null;
	// 自定义属性
	private Object custom;
	// 是否优化分页
	private boolean optimizePage = false;
	/**
	 * 初始化
	 */
	public SqlAssist() {
		super();
	}

	/**
	 * 初始化,该构造方法用于使用SqlAssist的静态条件方法,动态添加条件
	 * 
	 * @param require
	 *          示例:查询小于使用 SqlAssist.andLt("A.ID",10)...
	 *          {@link #andLt(String, Object)}
	 */
	public SqlAssist(SqlWhereCondition<?>... require) {
		this.setConditions(require);
	}

	/**
	 * 将当前对象装换为json字符串
	 * 
	 * @return
	 */
	public String toJsonStr() {
		return toJson().toString();
	}
	/**
	 * 将当前对象装换为JsonObject
	 * 
	 * @return
	 * @since 1.0.2
	 */
	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		if (distinct != null) {
			json.put("distinct", distinct);
		}
		if (order != null) {
			json.put("order", order);
		}
		if (startRow != null) {
			json.put("startRow", startRow);
		}
		if (rowSize != null) {
			json.put("rowSize", rowSize);
		}
		if (resultColumn != null) {
			json.put("resultColumn", resultColumn);
		}
		if (custom != null) {
			json.put("custom", custom);
		}
		if (condition != null) {
			JsonArray array = new JsonArray();
			condition.forEach(va -> {
				array.add(va.toJson());
			});
			json.put("condition", array);
		}
		return json;
	}
	/**
	 * 将JsonObject对象装换为SqlAssist
	 * 
	 * @param obj
	 * @return
	 * @since 1.0.2
	 */
	public static SqlAssist fromJson(JsonObject obj) {
		if (obj == null || obj.isEmpty()) {
			return null;
		}
		SqlAssist assist = new SqlAssist();
		assist.setStartRow(obj.getInteger("startRow"));
		assist.setRowSize(obj.getInteger("rowSize"));
		assist.setDistinct(obj.getString("distinct"));
		assist.setOrders(obj.getString("order"));
		assist.setResultColumn(obj.getString("resultColumn"));
		assist.setCustom(obj.getValue("custom"));
		if (obj.getValue("condition") instanceof JsonArray) {
			List<SqlWhereCondition<?>> list = new ArrayList<>();
			obj.getJsonArray("condition").forEach(va -> {
				list.add(SqlWhereCondition.fromJson((JsonObject) va));
			});
			assist.setCondition(list);
		}
		return assist;
	}

	/**
	 * 添加单个查询条件,参数为{@ SqlWhereCondition},推荐使用Assist的静态条件方法添加条件;
	 * 
	 * @param require
	 *          示例:查询小于使用 SqlAssist.andLt("A.ID",10)
	 *          {@link #andLt(String, Object)}
	 */
	public SqlAssist setConditions(SqlWhereCondition<?> require) {
		if (this.condition == null) {
			this.condition = new ArrayList<SqlWhereCondition<?>>();
			require.setRequire(require.getRequire().substring(require.getRequire().indexOf(" ") + 1));
		}
		this.condition.add(require);
		return this;
	}

	/**
	 * 添加多个查询条件,参数为{@ SqlWhereCondition},推荐使用Assist的静态条件方法添加条件;
	 * 
	 * @param require
	 *          示例:查询小于使用 SqlAssist.andLt("A.ID",10)...
	 *          {@link #andLt(String, Object)}
	 */
	public SqlAssist setConditions(SqlWhereCondition<?>... require) {
		if (this.condition == null) {
			this.condition = new ArrayList<SqlWhereCondition<?>>();
		}
		for (int i = 0; i < require.length; i++) {
			if (i == 0 && this.condition.size() == 0) {
				require[i].setRequire(require[i].getRequire().substring(require[i].getRequire().indexOf(" ") + 1));
			}
			this.condition.add(require[i]);
		}
		return this;
	}
	/**
	 * 添加查询条件,该方法一般用于初始化,因为会将现有的条件集清空,既this.condition=conditions
	 * 
	 * @param conditions
	 * @return
	 * @since 1.0.2
	 */
	private SqlAssist setCondition(List<SqlWhereCondition<?>> conditions) {
		this.condition = conditions;
		return this;
	}

	/**
	 * 参数(列名)1 = 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andEq(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " = ? ", req);
	}

	/**
	 * 参数(列名)1 = 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orEq(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " = ? ", req);
	}

	/**
	 * 参数(列名)1 <>(不等于) 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andNeq(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " <> ? ", req);
	}

	/**
	 * 参数(列名)1 <>(不等于) 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orNeq(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " <> ? ", req);
	}

	/**
	 * 参数(列名)1 < 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andLt(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " < ? ", req);
	}

	/**
	 * 参数(列名)1 < 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orLt(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " < ? ", req);
	}

	/**
	 * 参数(列名)1 <= 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andLte(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " <= ? ", req);
	}

	/**
	 * 参数(列名)1 <= 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orLte(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " <= ? ", req);
	}

	/**
	 * 参数(列名)1 > 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andGt(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " > ? ", req);
	}

	/**
	 * 参数(列名)1 > 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orGt(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " > ? ", req);
	}

	/**
	 * 参数(列名)1 >= 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andGte(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " >= ? ", req);
	}

	/**
	 * 参数(列名)1 >= 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orGte(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " >= ? ", req);
	}

	/**
	 * 参数(列名)1 like '参数(条件)2' ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andLike(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " like ? ", req);
	}

	/**
	 * 参数(列名)1 like '参数(条件)2' ;如果表中存在相同列名,使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orLike(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " like ? ", req);
	}
	/**
	 * 自定义查询条件 :<br>
	 * 查询示例Assist.customRequire("[and/or] id in(?,?,?)",1,2,3);<br>
	 * 
	 * @param prefix
	 * @param value
	 * @return
	 */
	public static <T> SqlWhereCondition<?> customCondition(String prefix, T value) {
		return new SqlWhereCondition<Object>(prefix, value);
	}
	/**
	 * 自定义查询条件 :<br>
	 * 查询示例Assist.customRequire("[and/or] id in(?,?,?)",1,2,3);<br>
	 * 
	 * @param prefix
	 * @param value
	 * @return
	 */
	public static SqlWhereCondition<?> customCondition(String prefix, Object... value) {
		return new SqlWhereCondition<Object>(prefix, value);
	}

	/**
	 * 获得一个排序对象,将(列名)参数1 按 参数2排序(true=ASC/false=DESC)<br>
	 * ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名<br>
	 * 
	 * @param column
	 *          列名
	 * @param mode
	 *          排序类型,true=asc,false=desc
	 * @return
	 */
	public static String order(String column, boolean mode) {
		if (mode) {
			return column + " asc ";
		} else {
			return column + " desc ";
		}
	}

	/**
	 * * 设置排序,通过SqlAssist.order(列名,排序方式)<br>
	 * 示例:assist.setOrder(SqlAssist.order("id",true))//将id正序排序
	 * 
	 * @param order
	 *          {@link #order(String column, boolean mode)}
	 * @return
	 */
	public SqlAssist setOrders(String... order) {
		if (order == null || order.length == 0) {
			this.order = null;
			return this;
		}
		if (this.order == null) {
			this.order = " order by ";
		}
		StringBuffer buffer = new StringBuffer();
		for (String od : order) {
			buffer.append(od);
		}
		this.order += buffer;
		return this;
	}

	/**
	 * 设置一个编辑好的order,该方法一般用于初始化,因为会将现有的order清除,既order=参数order
	 * 
	 * @param order
	 * @return
	 * @since 1.0.2
	 */
	private SqlAssist setOrders(String order) {
		this.order = order;
		return this;
	}
	/**
	 * 获得排序
	 * 
	 * @return
	 */
	public String getOrder() {
		return order;
	}
	/**
	 * 获得是否去重
	 * 
	 * @return
	 */
	public String getDistinct() {
		return distinct;
	}

	/**
	 * 设置是否去重,true去除,false无效
	 * 
	 * @param distinct
	 */
	public SqlAssist setDistincts(boolean distinct) {
		if (distinct) {
			this.distinct = "distinct";
			return this;
		}
		return this;
	}
	/**
	 * 设置一个现有的去除方案,该方法一般用于初始化,因为会将现有的distinct清除,既distinct=传入的distinct
	 * 
	 * @param distinct
	 * @return
	 * @since 1.0.2
	 */
	private SqlAssist setDistinct(String distinct) {
		this.distinct = distinct;
		return this;
	}

	/**
	 * 获得开始分页行
	 * 
	 * @return
	 */
	public Integer getStartRow() {
		return startRow;
	}

	/**
	 * 设置从第几行开始取数据
	 * 
	 * @param startRow
	 */
	public SqlAssist setStartRow(Integer startRow) {
		this.startRow = startRow;
		return this;
	}

	/**
	 * 获得每次取多少行数据
	 * 
	 * @return
	 */
	public Integer getRowSize() {
		return rowSize;
	}

	/**
	 * 设置每次取多少很数据
	 * 
	 * @param rowSize
	 */
	public SqlAssist setRowSize(Integer rowSize) {
		this.rowSize = rowSize;
		return this;
	}

	/**
	 * 获得返回指定列
	 * 
	 * @return
	 */
	public String getResultColumn() {
		return resultColumn;
	}

	/**
	 * 设置返回指定列多个列以,逗号隔开;
	 * 
	 * @return
	 */
	public SqlAssist setResultColumn(String resultColumn) {
		this.resultColumn = resultColumn;
		return this;
	}

	/**
	 * 获得条件集
	 * 
	 * @return
	 */
	public List<SqlWhereCondition<?>> getCondition() {
		return condition;
	}

	/**
	 * 获得自定义属性
	 * 
	 * @return
	 * @since 1.0.2
	 */
	public Object getCustom() {
		return custom;
	}
	/**
	 * 设置自定义属性
	 * 
	 * @param custom
	 * @since 1.0.2
	 */
	public void setCustom(Object custom) {
		this.custom = custom;
	}

	/**
	 * 返回是否优化分页
	 * 
	 * @since 2.0.0
	 * 
	 * @return
	 */
	public boolean isOptimizePage() {
		return optimizePage;
	}
	/**
	 * 是否设置优化分页,以MySQL为例子,使用内联id进行查询
	 * 
	 * @since 2.0.0
	 * @param optimizePage,true优化,false不优化,默认false
	 * @return
	 */
	public SqlAssist setOptimizePage(boolean optimizePage) {
		this.optimizePage = optimizePage;
		return this;
	}

	@Override
	public String toString() {
		return "SqlAssist [distinct=" + distinct + ", order=" + order + ", startRow=" + startRow + ", rowSize=" + rowSize + ", resultColumn="
				+ resultColumn + ", condition=" + condition + ", custom=" + custom + ", optimizePage=" + optimizePage + "]";
	}


}
