package ${content.router.classPackage};

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ${content.service.classPackage!}.${content.service.className!};
import ${content.sql.classPackage!}.${content.sql.className!};

import io.vertx.core.MultiMap;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * 
 * Router类
 * 
 * @author
 *
 */
public class ${content.router.className} {
	private final Logger LOG = LogManager.getLogger(this.getClass());

	// TODO 如果你看到下面这两个变量,那么你应该把这两个变量抽到一个常量类里面才可以被重复使用
	/** 数据返回类型 */
	public final static String CONTENT_TYPE = "Content-Type";
	/** 数据返回类型 */
	public final static String JSON_UTF8 = "application/json;charset=UTF-8";

	/** Router服务 */
	private Router router;
	/** Router服务接口 */
	private ${content.service.className!} ${content.service.className?uncap_first};

	/**
	 * 实例化
	 * 
	 * @param router
	 *          Router实例
	 * @param jdbcClient
	 *          数据库客户端
	 */
	public ${content.router.className!}(Router router, JDBCClient jdbcClient) {
		super();
		this.router = router;
		this.${content.service.className?uncap_first} = ${content.service.className!}.create(new ${content.sql.className!}(), jdbcClient);
	}

	/**
	 * 启动服务
	 */
	public void startService() {
		// ${content.router.item.r_find.describe!}
		router.${content.router.item.m_find.value!}("${content.router.item.r_find.value!}").handler(this::${content.router.item.f_find.value!});
		// ${content.router.item.r_getById.describe!}
		router.${content.router.item.m_getById.value!}("${content.router.item.r_getById.value!}").handler(this::${content.router.item.f_getById.value!});
		// ${content.router.item.r_saveNotNull.describe!}
		router.${content.router.item.m_saveNotNull.value!}("${content.router.item.r_saveNotNull.value!}").handler(this::${content.router.item.f_saveNotNull.value!});
		// ${content.router.item.r_updateNotNull.describe!}
		router.${content.router.item.m_updateNotNull.value!}("${content.router.item.r_updateNotNull.value!}").handler(this::${content.router.item.f_updateNotNull.value!});
		// ${content.router.item.r_deleteById.describe!}
		router.${content.router.item.m_deleteById.value!}("${content.router.item.r_deleteById.value!}").handler(this::${content.router.item.f_deleteById.value!});
	}

	/**
	 * ${content.router.item.f_find.describe!}
	 * 
	 * @param rct
	 */
	public void ${content.router.item.f_find.value!}(RoutingContext rct) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("执行查询所有${content.entity.className!}...");
		}
		MultiMap params = rct.request().params();
		if (LOG.isDebugEnabled()) {
			LOG.debug("params:\n" + params);
		}
		${content.service.className?uncap_first}.${content.service.item.select.value!}(params, res -> {
			rct.response().putHeader(CONTENT_TYPE, JSON_UTF8).end(res.result().toBuffer());
		});
	}
	/**
	 * ${content.router.item.f_getById.describe!}
	 * 
	 * @param rct
	 */
	public void ${content.router.item.f_getById.value!}(RoutingContext rct) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("执行通过id查询${content.entity.className!}...");
		}
		MultiMap params = rct.request().params();
		if (LOG.isDebugEnabled()) {
			LOG.debug("params:\n" + params);
		}
		${content.service.className?uncap_first}.${content.service.item.selectById.value!}(params, res -> {
			rct.response().putHeader(CONTENT_TYPE, JSON_UTF8).end(res.result().toBuffer());
		});
	}
	/**
	 * ${content.router.item.f_saveNotNull.describe!}
	 * 
	 * @param rct
	 */
	public void ${content.router.item.f_saveNotNull.value!}(RoutingContext rct) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("执行将${content.entity.className!}中不为null的属性保存...");
		}
		MultiMap params = rct.request().params();
		if (LOG.isDebugEnabled()) {
			LOG.debug("params:\n" + params);
		}
		${content.service.className?uncap_first}.${content.service.item.insertNotNull.value!}(params, res -> {
			rct.response().putHeader(CONTENT_TYPE, JSON_UTF8).end(res.result().toBuffer());
		});
	}
	/**
	 * ${content.router.item.f_updateNotNull.describe!}
	 * 
	 * @param rct
	 */
	public void ${content.router.item.f_updateNotNull.value!}(RoutingContext rct) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("执行更新${content.entity.className!}类中属性不为null的数据...");
		}
		MultiMap params = rct.request().params();
		if (LOG.isDebugEnabled()) {
			LOG.debug("params:\n" + params);
		}
		${content.service.className?uncap_first}.${content.service.item.updateNotNull.value!}(params, res -> {
			rct.response().putHeader(CONTENT_TYPE, JSON_UTF8).end(res.result().toBuffer());
		});
	}
	/**
	 * ${content.router.item.f_deleteById.describe!}
	 * 
	 * @param rct
	 */
	public void ${content.router.item.f_deleteById.value!}(RoutingContext rct) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("执行通过id删除${content.entity.className!}...");
		}
		MultiMap params = rct.request().params();
		if (LOG.isDebugEnabled()) {
			LOG.debug("params:\n" + params);
		}
		${content.service.className?uncap_first}.${content.service.item.deleteById.value!}(params, res -> {
			rct.response().putHeader(CONTENT_TYPE, JSON_UTF8).end(res.result().toBuffer());
		});
	}

}
