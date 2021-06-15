package com.demo.boot.core.aop;

import com.alibaba.fastjson.JSON;
import com.demo.boot.api.constants.ReData;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;

@Aspect
@Component
public class TimeAspect {

    // 修正Timer注解的全局唯一限定符
    @Pointcut("@annotation(com.demo.boot.api.annotation.Timer)")
    private void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标Logger
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        // 获取目标类名称
        String clazzName = joinPoint.getTarget().getClass().getName();

        // 获取类名
        String simpleClsName = joinPoint.getTarget().getClass().getSimpleName();

        // 获取目标类方法名称
        String methodName = joinPoint.getSignature().getName();

        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();

        if (parameterAnnotations != null && parameterAnnotations.length > 0) {
            for (Annotation[] parameterAnnotation : parameterAnnotations) {
                int paramIndex = ArrayUtils.indexOf(parameterAnnotations, parameterAnnotation);
                for (Annotation annotation : parameterAnnotation) {
                    if (annotation instanceof RequestBody) {
                        Object paramValue = joinPoint.getArgs()[paramIndex];
                        logger.info("{}.{}:入参:{}", simpleClsName, methodName, JSON.toJSONString(paramValue));
                    }
                    if (annotation instanceof RequestParam) {
                        Object paramValue = joinPoint.getArgs()[paramIndex];
                        logger.info("{}.{}:入参:{}", simpleClsName, methodName, paramValue);
                    }
                }
            }
        }


        long start = System.currentTimeMillis();
        logger.info( "{}: {}: start...", clazzName, methodName);

        // 调用目标方法
        Object result = joinPoint.proceed();

        try {
            if (result instanceof ReData) {
                ReData<?> response = (ReData<?>) result;
                Object data = response.getData();
                if (data != null) {
                    logger.info("{}.{}:出参:{}", simpleClsName, methodName, JSON.toJSONString(data));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;
        logger.info( "{}: {}: : end... cost time: {} ms", clazzName, methodName, time);

        return result;
    }
}
