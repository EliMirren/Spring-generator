package ${content.serviceImpl.classPackage};

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ${content.service.classPackage!}.${content.service.className!};
import ${content.sql.classPackage!}.${content.sql.className!};
import ${content.entity.classPackage!}.${content.entity.className!};

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
/**
 * ${content.router.className!}的服务接口实现类
 * 
 * @author
 */
public class ${content.serviceImpl.className} implements ${content.service.className} {
	private final Logger LOG = LogManager.getLogger(this.getClass());
	/** 数据库客户端 */
	private JDBCClient jdbcClient;
	/** ${content.entity.className!}的SQL操作类 */
	private ${content.sql.className!} ${content.sql.className?uncap_first};
	/**
	 * 实例化一个服务
	 * 
	 * @param executeSQL
	 *          ${content.entity.className!}的SQL操作类
	 * @param jdbcClient
	 *          数据库客户端
	 */
	public ${content.serviceImpl.className!}(${content.sql.className!} executeSQL, JDBCClient jdbcClient) {
		super();
		this.jdbcClient = jdbcClient;
		this.${content.sql.className?uncap_first} = executeSQL;
	}
	/**
	 * 返回格式化
	 * 
	 * @return
	 */
	public Future<JsonObject> resultFormat(int code, Object data) {
		// TODO 如果你看到该方法,正确的做法应该将返回的结果按自己的协议封装成一个工具类,比如常用的状态,并修改模板
		JsonObject json = new JsonObject();
		json.put("status", code);
		if (data != null) {
			json.put("data", data);
		} else {
			json.putNull("data");
		}
		return Future.succeededFuture(json);
	}

	@Override
	public void ${content.service.item.select.value!}(MultiMap params, Handler<AsyncResult<JsonObject>> handler) {
		jdbcClient.getConnection(conn -> {
			if (conn.succeeded()) {
				${content.sql.className?uncap_first}.selectAll(conn.result(), res -> {
					if (res.succeeded()) {
						List<JsonObject> result = res.result() == null ? new ArrayList<>() : res.result();
						if (LOG.isDebugEnabled()) {
							LOG.debug("执行查询所有${content.entity.className!}-->结果:" + result);
						}
						handler.handle(resultFormat(200, result));
					} else {
						LOG.error("执行查询所有${content.entity.className!}-->失败:", res.cause());
						handler.handle(resultFormat(412, res.cause().getMessage()));
					}
				});
			} else {
				LOG.error("执行查询所有${content.entity.className!}->获取数据库连接-->失败:", conn.cause());
				handler.handle(resultFormat(500, conn.cause().getMessage()));
			}
		});

	}
	@Override
	public void ${content.service.item.selectById.value!}(MultiMap params, Handler<AsyncResult<JsonObject>> handler) {
		String id = params.get("id");
		if (id == null || "".equals(id)) {
			// TODO 这里也可以做一个工具后用工具验证
			handler.handle(resultFormat(412, "id不能为空"));
		}
		jdbcClient.getConnection(conn -> {
			if (conn.succeeded()) {
				${content.sql.className?uncap_first}.selectById(id, conn.result(), res -> {
					if (res.succeeded()) {
						JsonObject result = res.result() == null ? new JsonObject() : res.result();
						if (LOG.isDebugEnabled()) {
							LOG.debug("执行通过id查询${content.entity.className!}-->结果:" + result);
						}
						handler.handle(resultFormat(200, result));
					} else {
						LOG.error("执行通过id查询${content.entity.className!}-->失败:", res.cause());
						handler.handle(resultFormat(412, res.cause().getMessage()));
					}
				});

			} else {
				LOG.error("执行通过id查询${content.entity.className!}->获取数据库连接-->失败:", conn.cause());
				handler.handle(resultFormat(500, conn.cause().getMessage()));
			}
		});
	}
	@Override
	public void ${content.service.item.insertNotNull.value!}(MultiMap params, Handler<AsyncResult<JsonObject>> handler) {
		try {
			${content.entity.className!} ${content.entity.classNameLower!} = new ${content.entity.className!}(params);
			<#if content.entity.cantNullAttrs?exists>
			if(<#list content.entity.cantNullAttrs as item>${content.entity.classNameLower!}.${item.fget}() == null <#if item?has_next>||</#if> </#list>){
				handler.handle(resultFormat(412, "存在空值"));
			}
			</#if>
			jdbcClient.getConnection(conn -> {
				if (conn.succeeded()) {
					${content.sql.className?uncap_first}.insertNonEmpty(${content.entity.classNameLower!}, conn.result(), res -> {
						if (res.succeeded()) {
							int result = res.result() == null ? 0 : res.result();
							if (LOG.isDebugEnabled()) {
								LOG.debug("执行将${content.entity.className!}中不为null的属性保存到数据库-->结果:" + result);
							}
							if (result > 1) {
								result = 1;
							}
							handler.handle(resultFormat(200, result));
						} else {
							LOG.error("执行将${content.entity.className!}中不为null的属性保存到数据库-->失败:", res.cause());
							handler.handle(resultFormat(412, res.cause().getMessage()));
						}
					});
				} else {
					LOG.error("执行将${content.entity.className!}中不为null的属性保存到数据库->获取数据库连接-->失败:", conn.cause());
					handler.handle(resultFormat(500, conn.cause().getMessage()));
				}
			});
		} catch (Exception e) {
			LOG.error("执行将${content.entity.className!}中不为null的属性保存到数据库-->失败:", e);
			handler.handle(resultFormat(412, e.getMessage()));
		}

	}
	@Override
	public void ${content.service.item.updateNotNull.value!}(MultiMap params, Handler<AsyncResult<JsonObject>> handler) {
		try {
			${content.entity.className!} ${content.entity.classNameLower!} = new ${content.entity.className!}(params);
			jdbcClient.getConnection(conn -> {
				if (conn.succeeded()) {
					${content.sql.className?uncap_first}.insertNonEmpty(${content.entity.classNameLower!}, conn.result(), res -> {
						if (res.succeeded()) {
							int result = res.result() == null ? 0 : res.result();
							if (LOG.isDebugEnabled()) {
								LOG.debug("执行更新${content.entity.className!}类中属性不为null的数据-->结果:" + result);
							}
							if (result > 1) {
								result = 1;
							}
							handler.handle(resultFormat(200, result));
						} else {
							LOG.error("执行更新${content.entity.className!}类中属性不为null的数据-->失败:", res.cause());
							handler.handle(resultFormat(412, res.cause().getMessage()));
						}
					});
				} else {
					LOG.error("执行更新${content.entity.className!}类中属性不为null的数据->获取数据库连接-->失败:", conn.cause());
					handler.handle(resultFormat(500, conn.cause().getMessage()));
				}
			});
		} catch (Exception e) {
			LOG.error("执行更新${content.entity.className!}类中属性不为null的数据-->失败:", e);
			handler.handle(resultFormat(412, e.getMessage()));
		}

	}
	@Override
	public void ${content.service.item.deleteById.value!}(MultiMap params, Handler<AsyncResult<JsonObject>> handler) {
		String id = params.get("id");
		if (id == null || "".equals(id)) {
			// TODO 这里也可以做一个工具后用工具验证
			handler.handle(resultFormat(412, "id不能为空"));
		}
		jdbcClient.getConnection(conn -> {
			if (conn.succeeded()) {
				${content.sql.className?uncap_first}.deleteById(id, conn.result(), res -> {
					if (res.succeeded()) {
						int result = res.result() == null ? 0 : res.result();
						if (LOG.isDebugEnabled()) {
							LOG.debug("执行通过id删除${content.entity.className!}-->结果:" + result);
						}
						if (result > 1) {
							result = 1;
						}
						handler.handle(resultFormat(200, result));
					} else {
						LOG.error("执行通过id删除${content.entity.className!}-->失败:", res.cause());
						handler.handle(resultFormat(412, res.cause().getMessage()));
					}
				});

			} else {
				LOG.error("执行通过id删除${content.entity.className!}->获取数据库连接-->失败:", conn.cause());
				handler.handle(resultFormat(500, conn.cause().getMessage()));
			}
		});

	}

}
