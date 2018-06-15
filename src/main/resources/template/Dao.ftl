package ${content.dao.classPackage};
import java.util.List;

import ${content.assist.classPackage}.${content.assist.className};
import ${content.entity.classPackage}.${content.entity.className};

import org.apache.ibatis.annotations.Param;
/**
 * ${content.entity.className}的Dao接口
 * 
 * @author 
 *
 */
public interface ${content.dao.className} {

	/**
	 * 获得${content.entity.className}数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	long ${content.dao.item.count.value!}(Assist assist);
	
	/**
	 * 获得${content.entity.className}数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	List<${content.entity.className}> ${content.dao.item.select.value!}(Assist assist);
	<#if content.entity.primaryKeyAttr??>
	/**
	 * 通过${content.entity.className}的id获得${content.entity.className}对象
	 * 
	 * @param id
	 * @return
	 */
	${content.entity.className} ${content.dao.item.selectById.value!}(${content.entity.primaryKeyAttr.javaType} id);
	</#if>
	
	/**
	 * 获得一个${content.entity.className}对象,以参数${content.entity.className}对象中不为空的属性作为条件进行查询,返回符合条件的第一条
	 * 
	 * @param obj
	 * @return
	 */
	${content.entity.className} ${content.dao.item.selectByObjSingle.value!}(${content.entity.className} obj);
	
	/**
	 * 获得一个${content.entity.className}对象,以参数${content.entity.className}对象中不为空的属性作为条件进行查询
	 * 
	 * @param obj
	 * @return
	 */
	List<${content.entity.className}> ${content.dao.item.selectByObj.value!}(${content.entity.className} obj);

	/**
	 * 插入${content.entity.className}到数据库,包括null值
	 * 
	 * @param value
	 * @return
	 */
	int ${content.dao.item.insertAll.value!}(${content.entity.className} value);
	
	/**
	 * 插入${content.entity.className}中属性值不为null的数据到数据库
	 * 
	 * @param value
	 * @return
	 */
	int ${content.dao.item.insertNotNull.value!}(${content.entity.className} value);
	
	/**
	 * 批量插入${content.entity.className}到数据库,包括null值
	 * 
	 * @param value
	 * @return
	 */
	int ${content.dao.item.insertBatch.value!}(List<${content.entity.className}> value);
	<#if content.entity.primaryKeyAttr??>
	/**
	 * 通过${content.entity.className}的id删除${content.entity.className}
	 * 
	 * @param id
	 * @return
	 */
	int ${content.dao.item.deleteById.value!}(${content.entity.primaryKeyAttr.javaType} id);
	</#if>
	
	/**
	 * 通过辅助工具Assist的条件删除${content.entity.className}
	 * 
	 * @param assist
	 * @return
	 */
	int ${content.dao.item.deleteByAssist.value!}(Assist assist);
	
	<#if content.entity.primaryKeyAttr??>
	/**
	 * 通过${content.entity.className}的id更新${content.entity.className}中的数据,包括null值
	 * 
	 * @param enti
	 * @return
	 */
	int ${content.dao.item.updateAllById.value!}(${content.entity.className} enti);
	
	/**
	 * 通过${content.entity.className}的id更新${content.entity.className}中属性不为null的数据
	 * 
	 * @param enti
	 * @return
	 */
	int ${content.dao.item.updateNotNullById.value!}(${content.entity.className} enti);
	</#if>
	
	/**
	 * 通过辅助工具Assist的条件更新${content.entity.className}中的数据,包括null值
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int ${content.dao.item.updateAllByAssist.value!}(@Param("enti") ${content.entity.className} value, @Param("assist") Assist assist);
	
	/**
	 * 通过辅助工具Assist的条件更新${content.entity.className}中属性不为null的数据
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int ${content.dao.item.updateNotNullByAssist.value!}(@Param("enti") ${content.entity.className} value, @Param("assist") Assist assist);
}