package ${content.sql.classPackage};

import java.util.ArrayList;
import java.util.List;

import ${content.abstractSql.classPackage!}.AbstractSQL;
import ${content.sqlAssist.classPackage!}.SqlPropertyValue;
import ${content.entity.classPackage!}.${content.entity.className!};

/**
 * ${content.entity.className!}SQL类
 * 
 * @author
 *
 */
public class ${content.sql.className} extends AbstractSQL<${content.entity.className}> {
	@Override
	protected String tableName() {
		return "${content.entity.tableName!}";
	}

	@Override
	protected String primaryId() {
		<#if !content.entity.primaryKey??>return 这里没有主键所有报错,你要么添加一个主键,要么Override AbstractSQL关于主键的方法或者不使用主键相关方法</#if>
		return "${content.entity.primaryKey!}";
	}

	@Override
	protected String columns() {
		return "<#list content.entity.attrs as item> ${item.columnName} AS ${item.field} <#if item?has_next>,</#if></#list>";
	}

	@Override
	protected List<SqlPropertyValue<?>> propertyValue(${content.entity.className!} obj) {
		List<SqlPropertyValue<?>> result = new ArrayList<>();
		<#if content.entity.otherAttrs??><#list content.entity.otherAttrs as item>
		result.add(new SqlPropertyValue<>("${item.columnName}", obj.${item.fget}()));
		</#list></#if>
		<#if content.entity.cantNullAttrs??><#list content.entity.cantNullAttrs as item>
		result.add(new SqlPropertyValue<>("${item.columnName}", obj.${item.fget}()));
		</#list></#if>
		<#if content.entity.primaryKeyAttr??>result.add(new SqlPropertyValue<>("${content.entity.primaryKeyAttr.columnName}", obj.${content.entity.primaryKeyAttr.fget}()));</#if>
		return result;
	}
}
