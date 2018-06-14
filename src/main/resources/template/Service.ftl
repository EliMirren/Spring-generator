package ${content.service.classPackage};

import ${content.serviceImpl.classPackage!}.${content.serviceImpl.className!};
import ${content.sql.classPackage!}.${content.sql.className!};

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

/**
 * ${content.router.className!}的服务接口
 * 
 * @author 
 *
 */
public interface ${content.service.className} {
	/**
	 * 获得服务的实例
	 * 
	 * @param executeSQL
	 *          SQL类
	 * @param jdbcClient
	 *          数据库客户端
	 * @return
	 */
	static ${content.service.className!} create(${content.sql.className!} executeSQL, JDBCClient jdbcClient) {
		return new ${content.serviceImpl.className!}(executeSQL, jdbcClient);
	}

	/**
	 * ${content.service.item.select.describe!}
	 * 
	 * @param params
	 *          用户请求的参数
	 * @param handler
	 *          返回的结果
	 */
	void ${content.service.item.select.value!}(MultiMap params, Handler<AsyncResult<JsonObject>> handler);
	
	/**
	 * ${content.service.item.selectById.describe!}
	 * 
	 * @param params
	 *          用户请求的参数
	 * @param handler
	 *          返回的结果
	 */
	void ${content.service.item.selectById.value!} (MultiMap params, Handler<AsyncResult<JsonObject>> handler);
	
	/**
	 *${content.service.item.insertNotNull.describe!}
	 * 
	 * @param params
	 *          用户请求的参数
	 * @param handler
	 *          返回的结果
	 */
	void ${content.service.item.insertNotNull.value!}(MultiMap params, Handler<AsyncResult<JsonObject>> handler);
	
	/**
	 * ${content.service.item.updateNotNull.describe!}
	 * 
	 * @param params
	 *          用户请求的参数
	 * @param handler
	 *          返回的结果
	 */
	void ${content.service.item.updateNotNull.value!}(MultiMap params, Handler<AsyncResult<JsonObject>> handler);
	
	/**
	 * ${content.service.item.deleteById.describe!}
	 * 
	 * @param params
	 *          用户请求的参数
	 * @param handler
	 *          返回的结果
	 */
	void ${content.service.item.deleteById.value!}(MultiMap params, Handler<AsyncResult<JsonObject>> handler);

}
