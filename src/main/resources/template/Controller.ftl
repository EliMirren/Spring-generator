package ${content.controller.classPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ${content.service.classPackage}.${content.service.className};
import ${content.entity.classPackage}.${content.entity.className};

/**
 * ${content.entity.className}的路由接口服务
 * 
 * @author 
 *
 */
@RestController
public class ${content.controller.className} {

	/** ${content.entity.className}Service服务 */
	@Autowired
	private ${content.service.className} ${content.service.className?uncap_first};
	
	/**
	 * ${content.controller.item.f_find.describe!}
	 * @param value
	 * @return
	 */
	@GetMapping(value = "${content.controller.item.r_find.value}", produces = {"application/json;charset=UTF-8"})
	public String ${content.controller.item.f_find.value}(${content.entity.className} value) {
		return ${content.service.className?uncap_first}.${content.service.item.select.value}(value);
	}
	
	<#if content.entity.primaryKeyAttr??>
	/**
	 * ${content.controller.item.f_getById.describe!}
	 * @param id
	 * @return
	 */
	@GetMapping(value = "${content.controller.item.r_getById.value}", produces = {"application/json;charset=UTF-8"})
	public String ${content.controller.item.f_getById.value}(@PathVariable(name="id") ${content.entity.primaryKeyAttr.javaType} id) {
		return ${content.service.className?uncap_first}.${content.service.item.selectById.value!}(id);
	}
	</#if>
	
	/**
	 * ${content.controller.item.f_saveNotNull.describe!}
	 * @param id
	 * @return
	 */
	@PostMapping(value = "${content.controller.item.r_saveNotNull.value!}", produces = {"application/json;charset=UTF-8"})
	public String ${content.controller.item.f_saveNotNull.value!}(${content.entity.className} value) {
		return ${content.service.className?uncap_first}.${content.service.item.insertNotNull.value!}(value);
	}
	
	/**
	 * ${content.controller.item.f_updateNotNull.describe!}
	 * @param id
	 * @return
	 */
	@PutMapping(value = "${content.controller.item.r_updateNotNull.value!}", produces = {"application/json;charset=UTF-8"})
	public String ${content.controller.item.f_updateNotNull.value!}(${content.entity.className} value) {
		return ${content.service.className?uncap_first}.${content.service.item.updateNotNull.value!}(value);
	}
	
	<#if content.entity.primaryKeyAttr??>
	/**
	 * ${content.controller.item.f_deleteById.describe!}
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "${content.controller.item.r_deleteById.value!}", produces = {"application/json;charset=UTF-8"})
	public String ${content.controller.item.f_deleteById.value!}(@PathVariable(name="id") ${content.entity.primaryKeyAttr.javaType} id) {
		return ${content.service.className?uncap_first}.${content.service.item.deleteById.value!}(id);
	}
	</#if>
}
