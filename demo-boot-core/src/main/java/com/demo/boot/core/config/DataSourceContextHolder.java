package com.demo.boot.core.config;

import com.demo.boot.api.enums.DataSourceTypeEnum;

public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dataSourceTypeEnum
     */
    public static void setDataSourceType(DataSourceTypeEnum dataSourceTypeEnum){
        contextHolder.set(dataSourceTypeEnum.getCode());
    }

    /**
     * 获取当前数据源
     * @return
     */
    public static String getDataSourceType(){
        return contextHolder.get();
    }

    /**
     * 清除上下文数据源
     */
    public static void clearDataSourceType(){
        contextHolder.remove();
    }
}
