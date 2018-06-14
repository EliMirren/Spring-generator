package com.szmirren.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.szmirren.entity.ControllerContent;
import com.szmirren.entity.CustomContent;
import com.szmirren.entity.CustomPropertyContent;
import com.szmirren.entity.DaoContent;
import com.szmirren.entity.DatabaseContent;
import com.szmirren.entity.EntityContent;
import com.szmirren.entity.FieldAttribute;
import com.szmirren.entity.MapperContent;
import com.szmirren.entity.ServiceContent;
import com.szmirren.entity.ServiceImplContent;
import com.szmirren.entity.SqlAssistContent;
import com.szmirren.entity.UnitTestContent;
import com.szmirren.models.TableAttributeEntity;
import com.szmirren.models.TableAttributeKeyValue;
import com.szmirren.models.TableAttributeKeyValueTemplate;
import com.szmirren.models.TableAttributeKeyValueTemplateVO;
import com.szmirren.options.ControllerConfig;
import com.szmirren.options.CustomConfig;
import com.szmirren.options.CustomPropertyConfig;
import com.szmirren.options.DaoConfig;
import com.szmirren.options.DatabaseConfig;
import com.szmirren.options.EntityConfig;
import com.szmirren.options.MapperConfig;
import com.szmirren.options.ServiceConfig;
import com.szmirren.options.ServiceImplConfig;
import com.szmirren.options.SqlAssistConfig;
import com.szmirren.options.UnitTestConfig;

/**
 * 转换器
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class ConverterUtil {
	/**
	 * 将实体类属性信息转换为模板类需要用的上下文属性
	 * 
	 * @param ec
	 *          实体类信息
	 * @param content
	 *          装换目标类
	 */
	public static void databaseConfigToContent(DatabaseConfig dc, DatabaseContent content) {
		content.setDisplayName(dc.getConnName());
		content.setHost(dc.getConnURL());
		content.setPort(dc.getListenPort());
		content.setUserName(dc.getUserName());
		content.setUserPwd(dc.getUserPwd());
		content.setDbName(dc.getDbName());
		content.setDbType(dc.getDbType());
		content.setEncoding(dc.getEncoding());
	}
	/**
	 * 将实体类属性信息转换为模板类需要用的上下文属性
	 * 
	 * @param ec
	 *          实体类信息
	 * @param content
	 *          装换目标类
	 */
	public static void entityConfigToContent(EntityConfig ec, EntityContent content) {
		if (!StrUtil.isNullOrEmpty(ec.getTableAlias())) {
			content.setTableAlias(ec.getTableAlias());
		}
		if (!StrUtil.isNullOrEmpty(ec.getPrimaryKey())) {
			content.setPrimaryKey(ec.getPrimaryKey());
		}
		if (ec.getTblPropertyValues() != null) {
			ArrayList<FieldAttribute> list = new ArrayList<>();
			ArrayList<FieldAttribute> cantNullAttrs = null;
			ArrayList<FieldAttribute> otherAttrs = null;

			for (TableAttributeEntity c : ec.getTblPropertyValues()) {
				if (!c.getTdCreate()) {
					continue;
				}
				FieldAttribute attr = new FieldAttribute(c);
				attr.setField(c.getTdField());
				// 列名首字母大写
				String upField = StrUtil.fristToUpCase(c.getTdField());
				attr.setFieldPascal(upField);
				// java数据类型
				String javaType = attr.getJavaType();
				if ("boolean".equalsIgnoreCase(javaType)) {
					attr.setFget("is" + upField);
				} else {
					attr.setFget("get" + upField);
				}
				attr.setFset("set" + upField);
				attr.setFgetType("get" + javaType);
				attr.setFsetType("set" + javaType);
				// 添加属性到所有属性
				list.add(attr);
				// 设置主键的类型与主键的属性列
				if (ec.getPrimaryKey() != null && ec.getPrimaryKey().equals(c.getTdColumnName())) {
					content.setPrimaryKeyJdbcType(c.getTdJdbcType());
					content.setPrimaryKeyJavaType(c.getTdJavaType().getValue());
					content.setPrimaryKeyAttr(attr);
				}
				// 添加不能为空的属性属性列与其他属性
				if (!attr.isNullable() && StrUtil.isNullOrEmpty(attr.getColumnDef())) {
					if (cantNullAttrs == null) {
						cantNullAttrs = new ArrayList<>();
					}
					cantNullAttrs.add(attr);
				} else {
					if (otherAttrs == null) {
						otherAttrs = new ArrayList<>();
					}
					otherAttrs.add(attr);
				}
			}
			content.setAttrs(list);
			content.setCantNullAttrs(cantNullAttrs);
			content.setOtherAttrs(otherAttrs);
		}
	}
	/**
	 * 将Service转换为content
	 * 
	 * @param sc
	 *          service配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void serviceConfigToContent(ServiceConfig sc, ServiceContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.fristToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将ServiceImpl转换为content
	 * 
	 * @param sc
	 *          ServiceImpl配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void serviceImplConfigToContent(ServiceImplConfig sc, ServiceImplContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.fristToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}

	/**
	 * 将SQL转换为content
	 * 
	 * @param sc
	 *          Sql配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void SqlConfigToContent(DaoConfig sc, DaoContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.fristToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将Router转换为content
	 * 
	 * @param sc
	 *          Router配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void routerConfigToContent(ControllerConfig sc, ControllerContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.fristToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将单元测试转换为content
	 * 
	 * @param sc
	 *          单元测试配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void unitTestConfigToContent(UnitTestConfig sc, UnitTestContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.fristToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将Assist转换为content
	 * 
	 * @param sc
	 *          Assist配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void sqlAssistConfigToContent(SqlAssistConfig sc, SqlAssistContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.fristToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将MapperConfig转换为content
	 * 
	 * @param sc
	 *          SqlAndParamsConfig配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void mapperConfigToContent(MapperConfig sc, MapperContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.fristToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}

	/**
	 * 将custom包类转换为content
	 * 
	 * @param sc
	 *          custom包类配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void customConfigToContent(CustomConfig sc, CustomContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.fristToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValueTemplate c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String cpackage = c.getPackageName().replace("{C}", className).replace("{c}", loCase);
				String name = c.getClassName().replace("{C}", className).replace("{c}", loCase);
				String templateValue = c.getTemplateValue();
				map.put(c.getKey(), new TableAttributeKeyValueTemplateVO(key, cpackage, name, templateValue));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将custom属性转换为content
	 * 
	 * @param sc
	 *          custom属性配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void customPropertyConfigToContent(CustomPropertyConfig sc, CustomPropertyContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.fristToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}

}
