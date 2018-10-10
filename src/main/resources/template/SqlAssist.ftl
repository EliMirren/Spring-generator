package ${content.assist.classPackage};

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Spring-generator-MyBatis的查询帮助类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class Assist {
	/** 去重 */
	private String distinct;
	/** 自定义排序 */
	private String order;
	/** 数据分页开始行 */
	private Integer startRow;
	/** 每次取多少行数据 */
	private Integer rowSize;
	/** 设置自定义返回列 */
	private String resultColumn;
	/** 条件集 */
	private List<WhereRequire<?>> require = null;
	/** 自定义值,该值可以在自定义Mapper需要用到Assist跟自定义的值时使用 */
	private Object customValue;

	/***
	 * 条件类,require属性为列的条件,value为条件值,values为多个条件值,suffix为结尾
	 * 
	 * @author <a href="http://szmirren.com">Mirren</a>
	 *
	 * @param <T>
	 */
	public class WhereRequire<T> {
		/** 查询语句前缀 */
		private String require;
		/** 单个查询条件值 */
		private T value;
		/** 多个查询条件值 */
		private Object[] values;
		/** 查询语句的后缀 */
		private String suffix;
		/** 自定义查询条件 */
		private WhereRequire<T>[] customRequire;

		public WhereRequire(String require, T value) {
			super();
			this.require = require;
			this.value = value;
		}

		public WhereRequire(String require, T value, String suffix) {
			super();
			this.require = require;
			this.value = value;
			this.suffix = suffix;
		}

		public WhereRequire(String require, String suffix, Object... values) {
			super();
			this.require = require;
			this.suffix = suffix;
			this.values = values;
		}

		public String getRequire() {
			return require;
		}

		public void setRequire(String require) {
			this.require = require;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public Object[] getValues() {
			return values;
		}

		public void setValues(Object[] values) {
			this.values = values;
		}

		public String getSuffix() {
			return suffix;
		}

		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		public WhereRequire<T>[] getCustomRequire() {
			return customRequire;
		}

		public void setCustomRequire(WhereRequire<T>[] customRequire) {
			this.customRequire = customRequire;
		}

		@Override
		public String toString() {
			return "WhereRequire [require=" + require + ", value=" + value + ", values=" + Arrays.toString(values) + ", suffix=" + suffix
					+ ", customRequire=" + Arrays.toString(customRequire) + "]";
		}

	}

	/**
	 * 排序类用于排序column为列名,mode为排序方式,true=asc,false=desc
	 * 
	 * @author <a href="http://szmirren.com">Mirren</a>
	 *
	 */
	public class WhereOrder {
		private String column;
		private boolean mode;

		public WhereOrder(String column, boolean mode) {
			super();
			this.column = column;
			this.mode = mode;

		}

		public String getColumn() {
			return column;
		}

		public void setColumn(String column) {
			this.column = column;
		}

		public boolean isMode() {
			return mode;
		}

		public void setMode(boolean mode) {
			this.mode = mode;
		}

		@Override
		public String toString() {
			return "WhereOrder [column=" + column + ", mode=" + mode + "]";
		}

	}

	/**
	 * 添加查询条件,参数为Assist的内部类whereRequire,推荐使用Assist的静态条件方法添加条件;
	 * 
	 * @param require
	 *          示例:setRequire(Assist.whereRequire("id",10))
	 */
	public void setRequire(WhereRequire<?> require) {
		if (this.require == null) {
			this.require = new ArrayList<Assist.WhereRequire<?>>();
		}
		this.require.add(require);
	}
	/**
	 * 添加查询条件,参数为Assist的内部类whereRequire,推荐使用Assist的静态条件方法添加条件;
	 * 
	 * @param require
	 *          示例:setRequire(Assist.whereRequire("id",10),Assist.whereRequire("id",10)...)
	 */
	public void setRequires(WhereRequire<?>... require) {
		if (this.require == null) {
			this.require = new ArrayList<Assist.WhereRequire<?>>();
		}
		for (int i = 0; i < require.length; i++) {
			this.require.add(require[i]);
		}
	}

	/**
	 * 添加查询条件:列名 = xxx<br>
	 * 参数1(列名) = 参数2(条件值)<br>
	 * 结果: and 列名 = 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist andEq(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("and " + column + " = ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 = xxx<br>
	 * 参数1(列名) = 参数2(条件值)<br>
	 * 结果: or 列名 = 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist orEq(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("or " + column + " = ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 不等于 xxx<br>
	 * 参数1(列名) <> 参数2(条件值)<br>
	 * 结果: and 列名 <> 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist andNeq(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("and " + column + " <> ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 不等于 xxx<br>
	 * 参数1(列名) <> 参数2(条件值)<br>
	 * 结果: or 列名 <> 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist orNeq(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("or " + column + " <> ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 < xxx <br>
	 * 参数1(列名) < 参数2(条件值)<br>
	 * 结果: and 列名 < 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist andLt(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("and " + column + "< ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 < xxx <br>
	 * 参数1(列名) < 参数2(条件值)<br>
	 * 结果: or 列名 < 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist orLt(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("or " + column + " < ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 <= xxx <br>
	 * 参数1(列名) <= 参数2(条件值)<br>
	 * 结果: and 列名 <= 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist andLte(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("and " + column + " <= ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 < xxx <br>
	 * 参数1(列名) <= 参数2(条件值)<br>
	 * 结果: or 列名 <= 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist orLte(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("or " + column + " <= ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 > xxx <br>
	 * 参数1(列名) > 参数2(条件值)<br>
	 * 结果: and 列名 > 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist andGt(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("and " + column + " > ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 > xxx <br>
	 * 参数1(列名) > 参数2(条件值)<br>
	 * 结果: or 列名 > 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist orGt(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("or " + column + " > ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 >= xxx <br>
	 * 参数1(列名) >= 参数2(条件值)<br>
	 * 结果: and 列名 >= 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist andGte(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("and " + column + " >= ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 >= xxx <br>
	 * 参数1(列名) >= 参数2(条件值)<br>
	 * 结果: or 列名 >= 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist orGte(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("or " + column + " >= ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 like 条件值 ,通配符的话需要添加到value中,比如%ABC<br>
	 * 参数1(列名) like 参数2(条件值)<br>
	 * 结果: and 列名 like 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist andLike(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("and " + column + " like ", value));
		return this;
	}

	/**
	 * 添加查询条件:列名 like 条件值 ,通配符的话需要添加到value中,比如%ABC<br>
	 * 参数1(列名) like 参数2(条件值)<br>
	 * 结果: or 列名 like 条件值
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist orLike(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("or " + column + " like ", value));
		return this;
	}
	/**
	 * 添加查询条件:列名 in 条件值 <br>
	 * 参数1(列名) in 参数2(条件值)<br>
	 * 结果: and 列名 in (条件值)
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist andIn(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("and " + column + " in (", value, " ) "));
		return this;
	}

	/**
	 * 添加查询条件:列名 in 条件值 <br>
	 * 参数1(列名) in 参数2(条件值)<br>
	 * 结果: or 列名 in (条件值)
	 * 
	 * @param column
	 *          列名
	 * @param value
	 *          条件值
	 * @return
	 */
	public <T> Assist orIn(String column, T value) {
		setRequires(new Assist().new WhereRequire<T>("or " + column + " in (", value, " ) "));
		return this;
	}
	/**
	 * 添加查询条件:列名 in 多个条件值 ,如果List可以调用list.toArray()<br>
	 * 参数1(列名) in 参数2(条件值,添加值...)<br>
	 * 结果: and 列名 in (条件值,添加值...)
	 * 
	 * @param column
	 *          列名
	 * @param values
	 *          条件值
	 * @return
	 */
	public <T> Assist andIn(String column, Object... values) {
		setRequires(new Assist().new WhereRequire<T>("and " + column + " in (", " ) ", values));
		return this;
	}

	/**
	 * 添加查询条件:列名 in 多个条件值 ,如果List可以调用list.toArray()<br>
	 * 参数1(列名) in 参数2(条件值,添加值...)<br>
	 * 结果: or 列名 in (条件值,添加值...)
	 * 
	 * @param column
	 *          列名
	 * @param values
	 *          条件值
	 * @return
	 */
	public <T> Assist orIn(String column, Object... values) {
		setRequires(new Assist().new WhereRequire<T>("or " + column + " in (", " ) ", values));
		return this;
	}

	/**
	 * 添加括号条件(xx=xx or xx=xx)<br>
	 * 第一个参数列名不需要加上逻辑运算符,第二个开始需要加上逻辑运算符号,如果List可以调用list.toArray()<br>
	 * 简单调用示例: <br>
	 * assist.orParenthesis(<br>
	 * --Assist.whereRequire("列名 = ", 条件值),<br>
	 * --Assist.whereRequire("or 列名 = ", 条件值)<br>
	 * );<br>
	 * 结果:select * from xxx where x=x or (列名 = 值 or 列名 = 值)<br>
	 * <br>
	 * 嵌套调用示例: <br>
	 * assist.orParenthesis(<br>
	 * --Assist.whereRequire("列名 >= ", 条件值),<br>
	 * --Assist.whereRequire("or (列名 = ", 条件值),<br>
	 * --Assist.whereRequire("and 列名 = ", 条件值,")")<br>
	 * );<br>
	 * 结果:select * from xxx where x=x or (列名 >= 值 or (列名 = 值 and 列名 = 值))<br>
	 * 
	 * @param customRequire
	 *          使用Assist的静态方法Assist.whereRequire获取值
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public <T> Assist orParenthesis(WhereRequire... customRequire) {
		WhereRequire<T> cr = new Assist().new WhereRequire<>("or (", " ) ");
		cr.setCustomRequire(customRequire);
		setRequires(cr);
		return this;
	}
	
	/**
	 * 添加括号条件(xx=xx or xx=xx)<br>
	 * 第一个参数列名不需要加上逻辑运算符,第二个开始需要加上逻辑运算符号,如果List可以调用list.toArray()<br>
	 * 简单调用示例: <br>
	 * assist.andParenthesis(<br>
	 * --Assist.whereRequire("列名 = ", 条件值),<br>
	 * --Assist.whereRequire("or 列名 = ", 条件值)<br>
	 * );<br>
	 * 结果:select * from xxx where x=x and (列名 = 值 or 列名 = 值)<br>
	 * <br>
	 * 嵌套调用示例: <br>
	 * assist.andParenthesis(<br>
	 * --Assist.whereRequire("列名 >= ", 条件值),<br>
	 * --Assist.whereRequire("or (列名 = ", 条件值),<br>
	 * --Assist.whereRequire("and 列名 = ", 条件值,")")<br>
	 * );<br>
	 * 结果:select * from xxx where x=x and (列名 >= 值 or (列名 = 值 and 列名 = 值))<br>
	 * 
	 * @param customRequire
	 *          使用Assist的静态方法Assist.whereRequire获取值
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public <T> Assist andParenthesis(WhereRequire... customRequire) {
		WhereRequire<T> cr = new Assist().new WhereRequire<>("and (", " ) ");
		cr.setCustomRequire(customRequire);
		setRequires(cr);
		return this;
	}
	
	/**
	 * 自定义查询条件 :<br>
	 * 参数1=自定义开头语句<br>
	 * 参数2=条件值<br>
	 * 参数3=自定义结尾语句<br>
	 * 如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名<br>
	 * 示例子查询:<br>
	 * 参数1= 列名 in (select 返回列名 from 表名 where 列名 = <br>
	 * 参数2= 123456<br>
	 * 参数3= ) <br>
	 * 假设有一张user表,里面有id列结果为:<br>
	 * select * from user where id in (select id from user where id=123456)<br>
	 * <b>需要特别注意的是,当where中不止一个条件的时候需要加上and或者or,根据自己的情况而定</b>
	 * 
	 * @param prefix
	 * @param value
	 * @param suffix
	 * @return
	 */
	public <T> Assist customRequire(String prefix, T value, String suffix) {
		setRequires(new Assist().new WhereRequire<T>(prefix, value, suffix));
		return this;
	}
	/**
	 * 获得一个查询条件
	 * 
	 * 示例:Assist.require(" and id = ",1);
	 * 
	 * @param condition
	 *          条件的SQL语句比如 and id =
	 * @param value
	 *          条件的值
	 * @return
	 */
	public static <T> WhereRequire<T> whereRequire(String condition, T value) {
		return new Assist().new WhereRequire<T>(condition, value);
	}

	/**
	 * 获得一个查询条件
	 * 
	 * 示例:Assist.require(" and id in (",1,")");
	 * 
	 * @param condition
	 *          条件的SQL语句比如 and id =
	 * @param value
	 *          条件的值
	 * @param suffix
	 *          后缀
	 * @return
	 */
	public static <T> WhereRequire<T> whereRequire(String condition, T value, String suffix) {
		return new Assist().new WhereRequire<T>(condition, value, suffix);
	}
	/**
	 * 获得一个查询条件
	 * 
	 * 示例:Assist.require(" and id in (",")",1,2,3,4);
	 * 
	 * @param condition
	 *          条件的SQL语句比如 and id =
	 * @param suffix
	 *          后缀
	 * @param values
	 *          条件的值
	 * @return
	 */
	public static <T> WhereRequire<T> whereRequire(String condition, String suffix, Object... values) {
		return new Assist().new WhereRequire<T>(condition, suffix, values);
	}

	/**
	 * 获得一个排序对象,将(列名)参数1 按
	 * 参数2排序(true=ASC/false=DESC),该方法配置setOrder使用,也就是说排序使用setOrder方法参数为该静态方法<br>
	 * ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名<br>
	 * 
	 * @param column
	 *          列名
	 * @param mode
	 *          排序类型,true=asc,false=desc
	 * @return
	 */
	public static WhereOrder order(String column, boolean mode) {
		return new Assist().new WhereOrder(column, mode);
	}

	/**
	 * 设置排序,通过Assist.order(列名,排序方式)<br>
	 * 示例:assist.setOrder(Assist.order("id",true))//将id正序排序
	 * 
	 * @param column
	 * @param mode
	 */
	public Assist setOrder(WhereOrder... order) {
		if (order == null || order.length == 0) {
			this.order = null;
			return this;
		}
		if (order.length == 1) {
			if (order[0].isMode()) {
				this.order = "order By " + order[0].getColumn() + " asc";
			} else {
				this.order = "order By " + order[0].getColumn() + " desc";
			}
			return this;
		}
		StringBuffer sql = new StringBuffer("order By ");
		for (int i = 0; i < order.length; i++) {
			if (i == 0) {
				if (order[i].isMode()) {
					sql.append(order[i].getColumn() + " asc");
				} else {
					sql.append(order[i].getColumn() + " desc");
				}
			} else {
				if (order[i].isMode()) {
					sql.append(", " + order[i].getColumn() + " asc");
				} else {
					sql.append(", " + order[i].getColumn() + " desc");
				}
			}
		}
		this.order = sql.toString();
		return this;
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
	 * 设置是否去重
	 * 
	 * @param distinct
	 */
	public Assist setDistinct(boolean distinct) {
		if (distinct) {
			this.distinct = "distinct";
		}
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
	public Assist setStartRow(Integer startRow) {
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
	public Assist setRowSize(Integer rowSize) {
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
	 * 设置返回指定列多个列以,逗号隔开;需要特别注意的是返回列需要起别名,别名以mapper里面的resultMap的column为准;
	 * 一般是类名加上属性的顺序号,
	 * 
	 * @return
	 */
	public Assist setResultColumn(String resultColumn) {
		this.resultColumn = resultColumn;
		return this;
	}
	/**
	 * 获得自定义的属性值
	 * 
	 * @return
	 */
	public Object getCustomValue() {
		return customValue;
	}
	/**
	 * 设置自定义的属性值
	 * 
	 * @param customValue
	 */
	public Assist setCustomValue(Object customValue) {
		this.customValue = customValue;
		return this;
	}

	/**
	 * 获得条件集
	 * 
	 * @return
	 */
	public List<WhereRequire<?>> getRequire() {
		return require;
	}
	/**
	 * 初始化
	 */
	public Assist() {
		super();
	}

	/**
	 * 初始化,该构造方法用于使用Assist的静态条件方法,动态添加条件
	 * 
	 * @param require
	 *          示例:Assist.lt("id",10)...
	 */
	public Assist(WhereRequire<?>... require) {
		super();
		if (this.require == null) {
			this.require = new ArrayList<Assist.WhereRequire<?>>();
		}
		for (int i = 0; i < require.length; i++) {
			this.require.add(require[i]);
		}
	}

	@Override
	public String toString() {
		return "Assist [distinct=" + distinct + ", order=" + order + ", startRow=" + startRow + ", rowSize=" + rowSize + ", resultColumn="
				+ resultColumn + ", require=" + require + ", customValue=" + customValue + "]";
	}

}
