package com.demo.boot.core.aop;

import com.demo.boot.api.enums.DataSourceTypeEnum;
import com.demo.boot.core.config.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = -100)
@Slf4j
@Aspect
public class DataSourceSwitchAspect {

    @Pointcut("execution(* com.demo.boot.core.dao.source1.mapper..*.*(..))")
    private void source1Aspect() {
    }

    @Pointcut("execution(* com.demo.boot.core.dao.source2.mapper..*.*(..))")
    private void source2Aspect() {
    }


    @Before("source1Aspect()")
    public void source1() {
        DataSourceContextHolder.setDataSourceType(DataSourceTypeEnum.DATA_SOURCE1);
    }

    @Before("source2Aspect()")
    public void source2() {
        DataSourceContextHolder.setDataSourceType(DataSourceTypeEnum.DATA_SOURCE2);
    }

//    @After("source1Aspect() || source2Aspect()")
//    public void afterSwitchDataSource(){
//        DataSourceContextHolder.clearDataSourceType();
//    }
}
