package com.admin.luntan.util;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Invocation;
import org.apache.logging.log4j.LogManager;

import java.util.Collection;

/**
 * MyBatis的SQL监控插件拦截器
 * <p>可监控慢sql，大集合<p>
 * @author BBF
 */
public class MyBatisSQLMonitorPlugin {
  protected static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MyBatisSQLMonitorPlugin.class);

  /**
   * MyBatis的SQL监控插件拦截器
   * @param invocation 调用方法
   * @param showSql 是否显示SQL
   * @param slower 慢SQL监控阀值
   * @param maxCount 大集合监控阀值
   * @return Object
   * @throws Throwable 异常
   */
  public static Object intercept(Invocation invocation, boolean showSql, int slower, int maxCount) throws
      Throwable {
    MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
    //请求参数
    Object parameter = null;
    if (1 < invocation.getArgs().length) {
      parameter = invocation.getArgs()[1];
    }
    BoundSql boundSql = mappedStatement.getBoundSql(parameter);
    String sql = boundSql.getSql();
    //执行的sql所在的mapper文件
    String resource = mappedStatement.getResource();
    //执行sql的dao文件的包名+方法名
    String daoMethod = mappedStatement.getId();
    //去除sql文中的换行
    sql = sql.replace("\n", "").replaceAll("\\s+", " ");
    if (showSql) {
      LOGGER.info("[SQLMonitorPlugin]SQL监控：{}|{}，执行SQL：{}，参数：{}", resource, daoMethod,
          sql, JsonUtil.toJson(parameter));
    }
    try {
      long start = System.currentTimeMillis();
      Object e = invocation.proceed();
      long timeTicket = System.currentTimeMillis() - start;
      if (slower < timeTicket) {
        LOGGER.warn("[SQLMonitorInterceptor]SQL监控[慢SQL（{}/{}ms）]：{}|{}，SQL：{}，参数：{}",
            timeTicket, slower, resource, daoMethod, sql, JsonUtil.toJson(parameter));
      }
      if (e instanceof Collection) {
        int ct = ((Collection<?>) e).size();
        if (maxCount < ct) {
          LOGGER.warn("[SQLMonitorInterceptor]SQL监控[大集合（{}/{}）]：{}|{}，SQL：{}，参数：{}",
              ct, maxCount, resource, daoMethod, sql, JsonUtil.toJson(parameter));
        }
      }
      return e;
    } catch (Exception e) {
      LOGGER.error("[SQLMonitorPlugin]SQL监控[执行出错]：{}|{}，SQL：{}，参数：{}", resource, daoMethod,
          sql, JsonUtil.toJson(parameter));
      throw e;
    }
  }
}
