package ${content.unitTest.classPackage};

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * ${content.entity.className}的测试
 * 
 * @author 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ${content.unitTest.className} {
	@Autowired
	private MockMvc mvc;

	/**
	 * 单元测试:${content.controller.item.f_find.describe!}
	 * @throws Exception
	 */
	@Test
	public void ${content.controller.item.f_find.value!}Test() throws Exception {
		ResultActions action = mvc.perform(MockMvcRequestBuilders.get("${content.controller.item.r_find.value}")).andExpect(MockMvcResultMatchers.status().isOk());
		// TODO 当你看到这个后你应该自己修改模板编写测试内容规则
	}
	
	<#if content.entity.primaryKeyAttr??>
	/**
	 * 单元测试:${content.controller.item.f_getById.describe!}
	 * @throws Exception
	 */
	@Test
	public void ${content.controller.item.f_getById.value!}Test() throws Exception {
		ResultActions action = mvc.perform(MockMvcRequestBuilders.get("${content.controller.item.r_getById.value}")).andExpect(MockMvcResultMatchers.status().isOk());
		// TODO 当你看到这个后你应该自己修改模板编写测试内容规则
	}
	</#if>
	
	/**
	 * 单元测试:${content.controller.item.f_saveNotNull.describe!}
	 * @throws Exception
	 */
	@Test
	public void ${content.controller.item.f_saveNotNull.value!}Test() throws Exception {
		ResultActions action = mvc.perform(MockMvcRequestBuilders.post("${content.controller.item.r_saveNotNull.value}")).andExpect(MockMvcResultMatchers.status().isOk());
		// TODO 当你看到这个后你应该自己修改模板编写测试内容规则
	}
	
	/**
	 * 单元测试:${content.controller.item.f_updateNotNull.describe!}
	 * @throws Exception
	 */
	@Test
	public void ${content.controller.item.f_updateNotNull.value!}Test() throws Exception {
		ResultActions action = mvc.perform(MockMvcRequestBuilders.put("${content.controller.item.r_updateNotNull.value}")).andExpect(MockMvcResultMatchers.status().isOk());
		// TODO 当你看到这个后你应该自己修改模板编写测试内容规则
	}

	<#if content.entity.primaryKeyAttr??>
	/**
	 * 单元测试:${content.controller.item.f_deleteById.describe!}
	 * @throws Exception
	 */
	@Test
	public void ${content.controller.item.f_deleteById.value!}Test() throws Exception {
		ResultActions action = mvc.perform(MockMvcRequestBuilders.delete("${content.controller.item.r_deleteById.value}")).andExpect(MockMvcResultMatchers.status().isOk());
		// TODO 当你看到这个后你应该自己修改模板编写测试内容规则
	}
	</#if>
}