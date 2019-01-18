package ${content.service.classPackage};
import java.util.List;

import ${content.serviceImpl.classPackage}.${content.serviceImpl.className};
import ${content.assist.classPackage}.${content.assist.className};
import ${content.entity.classPackage}.${content.entity.className};

/**
 * ${content.entity.className}的服务接口
 * 
 * @author 
 *
 */
public interface ${content.service.className} {
	/**
	 * 获得${content.entity.className}数据集,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @return
	 */
	String ${content.service.item.select.value!}(${content.entity.className} value);
	
	<#if content.entity.primaryKeyAttr??>
	/**
	 * 通过${content.entity.className}的id获得${content.entity.className}对象
	 * 
	 * @param id
	 * @return
	 */
	String ${content.service.item.selectById.value!}(${content.entity.primaryKeyAttr.javaType} id);
	<#else>
	// TODO 你的表中没有找到主键属性,你可以修改模板使用Assist来作为条件值做一些操作,比如用Assist来做删除与修改
	</#if>
	
	/**
	 * 将${content.entity.className}中属性值不为null的数据到数据库
	 * 
	 * @param value
	 * @return
	 */
	String ${content.service.item.insertNotNull.value!}(${content.entity.className} value);
	
	<#if content.entity.primaryKeyAttr??>
	/**
	 * 通过${content.entity.className}的id更新${content.entity.className}中属性不为null的数据
	 * 
	 * @param enti
	 * @return
	 */
	String ${content.service.item.updateNotNull.value!}(${content.entity.className} enti);
	
	/**
	 * 通过${content.entity.className}的id删除${content.entity.className}
	 * 
	 * @param id
	 * @return
	 */
	String ${content.service.item.deleteById.value!}(${content.entity.primaryKeyAttr.javaType} id);
	</#if>
}
