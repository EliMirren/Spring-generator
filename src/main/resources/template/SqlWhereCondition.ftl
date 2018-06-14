package ${content.sqlAssist.classPackage!};

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * SqlAssist的条件类,require属性为列的条件,value和values为条件值
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 * @param <T>
 */
public class SqlWhereCondition<T> {
	private String require;
	private T value;
	private Object[] values;
	/**
	 * 将当前对象装换为JsonObject
	 * 
	 * @return
	 * @since 1.0.2
	 */
	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		if (require != null) {
			json.put("require", require);
		}
		if (value != null) {
			json.put("value", value);
		}
		if (values != null) {
			JsonArray array = new JsonArray();
			for (int i = 0; i < values.length; i++) {
				array.add(values[i]);
			}
			json.put("values", array);
		}
		return json;
	}

	/**
	 * 将一个JsonObject对象装换为SqlWhereCondition
	 * 
	 * @param obj
	 * @return
	 * @since 1.0.2
	 */
	public static SqlWhereCondition<?> fromJson(JsonObject obj) {
		SqlWhereCondition<Object> condition = new SqlWhereCondition<>();
		if (obj.getValue("require") instanceof String) {
			condition.setRequire(obj.getString("require"));
		}
		if (obj.getValue("value") != null) {
			condition.setValue(obj.getValue("value"));
		}
		if (obj.getValue("values") instanceof JsonArray) {
			List<Object> list = new ArrayList<>();
			obj.getJsonArray("values").forEach(va -> {
				list.add(va);
			});
			condition.setValues(list.toArray());
		}
		return condition;
	}

	public SqlWhereCondition() {
		super();
	}

	public SqlWhereCondition(String require, T value) {
		super();
		this.require = require;
		this.value = value;
	}

	public SqlWhereCondition(String require, Object... values) {
		super();
		this.require = require;
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

	@Override
	public String toString() {
		return "WhereCondition [require=" + require + ", value=" + value + ", values=" + Arrays.toString(values) + "]";
	}

}
