package ${content.entity.classPackage};

import com.alibaba.fastjson.JSONObject;

/**
 * ${content.entity.tableName}实体类
 * 
 * @author 
 *
 */
public class ${content.entity.className} {
	<#list content.entity.attrs as item> 
	/**${item.remarks!}*/
	private ${item.javaType} ${item.field}; 
	</#list>
	/**
	 * 实例化
	 */
	public ${content.entity.className!}() {
		super();
	}
	/**
	 * 实例化
	 * 
	 * @param obj
	 */
	public ${content.entity.className!}(JSONObject obj) {
		this();
		<#list content.entity.attrs as item> 
		<#if item.javaType  == "String">
		if (obj.get("${item.field}") instanceof String) {
			this.${item.fset}((String) obj.get("${item.field}"));
		}
		<#elseif item.javaType  ==  "Integer" || item.javaType  ==  "int">
		if (obj.get("${item.field}") instanceof Number) {
			this.${item.fset}(((Number) obj.get("${item.field}")).intValue());
		}
		<#elseif item.javaType  ==  "Long" || item.javaType  ==  "long">
		if (obj.get("${item.field}") instanceof Number) {
			this.${item.fset}(((Number) obj.get("${item.field}")).longValue());
		}
		<#elseif item.javaType  ==  "Double" || item.javaType  ==  "double">
		if (obj.get("${item.field}") instanceof Number) {
			this.${item.fset}(((Number) obj.get("${item.field}")).doubleValue());
		}
		<#elseif item.javaType  ==  "JsonObject">
		if (obj.get("${item.field}") instanceof JsonObject) {
			this.${item.fset}((JsonObject) obj.get("${item.field}"));
		}
		<#else>
		this.${item.fset}(obj.get("${item.field}"));
		</#if>
		</#list>
	}
	
	/**
	 * 将当前对象转换为JsonObject
	 * 
	 * @return
	 */
	public JSONObject toJson() {
		JSONObject result = new JSONObject();
		<#list content.entity.attrs as item> 
		<#if item.javaType == "int" || item.javaType == "double" || item.javaType == "char" || item.javaType == "long"  || item.javaType == "boolean" >
		result.put("${item.field}",this.${item.fget}());
		<#else>
		if (this.${item.fget}() != null) {
			result.put("${item.field}",this.${item.fget}());
		}
		</#if>
		</#list>
		return result;
	}
	
	<#list content.entity.attrs as item> 
	
	/**
	 * 获取${item.field}
	 * 
	 * @return
	 */
	public ${item.javaType} ${item.fget}() {
		return ${item.field};
	}

	/**
	 * 设置${item.field}
	 * 
	 * @param ${item.field}
	 */
	public void ${item.fset}(${item.javaType} ${item.field}) {
		this.${item.field} = ${item.field};
	}
	</#list>

	@Override
	public String toString() {
		return "${content.entity.className!} [<#list content.entity.attrs as item>${item.field}=" + ${item.field} + " <#if item?has_next>,</#if> </#list>]";
	
	}
	
	
}
