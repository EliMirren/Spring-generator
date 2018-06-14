package ${content.unitTest.classPackage};

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ${content.entity.classPackage}.${content.entity.className};

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

/**
 * ${content.router.className!}的测试
 * 
 * @author 
 *
 */
@RunWith(VertxUnitRunner.class)
public class ${content.unitTest.className} {
	private Vertx vertx;
	private Integer port;
	@Before
	public void setUp(TestContext context) throws IOException {
		vertx = Vertx.vertx();
		ServerSocket socket = new ServerSocket(0);
		port = socket.getLocalPort();
		socket.close();
		// 启动Verticle的配置文件
		JsonObject config = new JsonObject();
		DeploymentOptions options = new DeploymentOptions().setConfig(config);
		// TODO 这里修改成启动你的服务,你可以定一个规则后修改模板
		vertx.deployVerticle(YourVerticle.class.getName(), options, context.asyncAssertSuccess());
	}
	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}
	@Test
	public void ${content.router.item.f_find.value!}Test(TestContext context) {
		final Async async = context.async();
		// TODO 填充测试值与结果
		vertx.createHttpClient().getNow(port, "localhost", "${content.router.item.r_find.value!}", response -> {
			context.assertTrue(response.statusCode() == 200);
			async.complete();
		});
	}
	@Test
	public void ${content.router.item.f_getById.value!}Test(TestContext context) {
		final Async async = context.async();
		// TODO 填充测试值与结果
		vertx.createHttpClient().getNow(port, "localhost", "${content.router.item.r_getById.value!}", response -> {
			context.assertTrue(response.statusCode() == 200);
			async.complete();
		});
	}
	@Test
	public void ${content.router.item.f_saveNotNull.value!}Test(TestContext context) {
		final Async async = context.async();
		${content.entity.className} ${content.entity.className?uncap_first} = new ${content.entity.className}();// TODO 添加自己添加或者修改模板定制
		vertx.createHttpClient().post(port, "localhost", "${content.router.item.r_saveNotNull.value!}", response -> {
			context.assertTrue(response.statusCode() == 200);// TODO 添加自己设定可以在模板修改
			async.complete();
		}).end(${content.entity.className?uncap_first}.toJson().toBuffer());
	}
	@Test
	public void ${content.router.item.f_updateNotNull.value!}Test(TestContext context) {
		final Async async = context.async();
		${content.entity.className} ${content.entity.className?uncap_first} = new ${content.entity.className}();// TODO 添加自己添加或者修改模板定制
		vertx.createHttpClient().put(port, "localhost", "${content.router.item.r_updateNotNull.value!}", response -> {
			context.assertTrue(response.statusCode() == 200);// TODO 添加自己设定可以在模板修改
			async.complete();
		}).end(${content.entity.className?uncap_first}.toJson().toBuffer());
	}
	@Test
	public void ${content.router.item.f_deleteById.value!}Test(TestContext context) {
		final Async async = context.async();
		// TODO 填充测试值与结果
		vertx.createHttpClient().delete(port, "localhost", "${content.router.item.r_deleteById.value!}", response -> {
			context.assertTrue(response.statusCode() == 200);
			async.complete();
		}).end();
	}

}
