package com.demo.boot.core.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.*;

@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class SqlLogInterceptor  implements Interceptor{

    private static final Logger log = LoggerFactory.getLogger(SqlLogInterceptor.class);
    boolean sqlconcatSwitchOn;
    int iSqlThreshold;

    public SqlLogInterceptor() {
    }

    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnVal = null;

        Object var5;
        try {
            returnVal = invocation.proceed();
            var5 = returnVal;
        } finally {
            try {
                this.logSql(invocation, startTime, returnVal);
            } catch (Exception var12) {
                log.error("error:" + var12.toString());
            }

        }

        return var5;
    }

    private void logSql(Invocation invocation, long startTime, Object returnVal) {
        long endTime = System.currentTimeMillis();
        long sqlCost = endTime - startTime;
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement)args[0];
        Object parameter = args[1];
        BoundSql boundSql = ms.getBoundSql(parameter);
        String sql = "";
        if (this.sqlconcatSwitchOn && sqlCost >= (long)this.iSqlThreshold) {
            sql = boundSql.getSql();
            sql = this.beautifySql(sql);
            Object parameterObject = boundSql.getParameterObject();
            if (sql == null || sql.length() == 0) {
                return;
            }

            sql = this.sqlReplace(ms, boundSql, sql, parameterObject);
        }

        String sqlId = ms.getId();
        int returnRows;
        String strSqlCost;
        if (sqlId.split("\\.").length > 2) {
            returnRows = sqlId.lastIndexOf(".");
            String methodName = sqlId.substring(returnRows + 1);
            strSqlCost = sqlId.substring(0, returnRows);
            String clsName = strSqlCost.substring(strSqlCost.lastIndexOf(".") + 1);
            sqlId = clsName + "." + methodName;
        }

        returnRows = 0;
        if (null != returnVal) {
            if (returnVal instanceof ArrayList) {
                List<?> returnList = (ArrayList)returnVal;
                returnRows = returnList.size();
            } else if (returnVal instanceof Integer) {
                returnRows = (Integer)returnVal;
            }
        }

        Map<String, Object> map = new HashMap<>();
        strSqlCost = sqlCost + " ms";
        map.put("sqlruntime", strSqlCost);
        map.put("sqlmethod", sqlId);
        map.put("sqltext", sql);
        map.put("sqlresultcount", returnRows);
        log.debug(JSON.toJSONString(map));
    }

    private String sqlReplace(MappedStatement ms, BoundSql boundSql, String sql, Object parameterObject) {
        Configuration configuration = ms.getConfiguration();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings == null) {
            return sql;
        } else {
            for(int i = 0; i < parameterMappings.size(); ++i) {
                ParameterMapping parameterMapping = (ParameterMapping)parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    String propertyName = parameterMapping.getProperty();
                    Object value;
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }

                    String propertyValue = null;
                    if (null != value) {
                        if (value instanceof Date) {
                            propertyValue = (new Timestamp(((Date)value).getTime())).toString();
                        } else {
                            propertyValue = value.toString();
                        }

                        Class javaType = parameterMapping.getJavaType();
                        if (javaType.isAssignableFrom(String.class) || javaType.isAssignableFrom(Date.class) || javaType.isAssignableFrom(Timestamp.class)) {
                            propertyValue = "\"" + propertyValue + "\"";
                        }
                    }

                    sql = sql.replaceFirst("\\?", propertyValue);
                }
            }

            return sql;
        }
    }

    private String beautifySql(String sql) {
        for(sql = sql.replace("\n", "").replace("\t", " ").replace("  ", " ").replace("( ", "(").replace(" )", ")").replace(" ,", ","); sql.contains("  "); sql = sql.replace("  ", " ")) {
        }

        return sql;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        String sqlconcat = properties.getProperty("sqlConcat");
        String sqlThreshold = properties.getProperty("sqlThreshold");
        if (sqlconcat == null || sqlconcat.length() == 0) {
            this.sqlconcatSwitchOn = false;
        }

        if ("true".equals(sqlconcat.toLowerCase())) {
            this.sqlconcatSwitchOn = true;
        } else {
            this.sqlconcatSwitchOn = false;
        }

        this.iSqlThreshold = Integer.valueOf(sqlThreshold);
    }
}
