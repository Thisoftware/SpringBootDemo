package com.demo.boot.core.util;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import com.demo.boot.api.enums.ErrorCodeEnum;
import com.demo.boot.api.exception.ApiCommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author kis
 * @since 2023/03/20 17:31:19
 */
@Slf4j
public class ValidateUtil {

    @SafeVarargs
    public static <T> void validateNotNull(Object obj, SFunction<T, ?>... paramNames) {
        if (paramNames == null || paramNames.length == 0) {
            log.error("--->>> --1-- validateNotNull . request param is null");
            throw new ApiCommonException(ErrorCodeEnum.PARAM_IS_EMPTY);
        }

        Map<String, Object> paramMap = JSON.parseObject(JSON.toJSONString(obj));

        List<String> columns = columnsToString(paramNames);
        for (String name : columns) {
            if (Objects.isNull(paramMap.get(name)) || StringUtils.isBlank(paramMap.get(name).toString())) {
                log.error("--->>>  --1--  validateNotNull . The {} parameter is missing or empty", name);
                throw new ApiCommonException(ErrorCodeEnum.PARAM_IS_EMPTY, name);
            }
        }
    }

    @SafeVarargs
    public static <T> List<String> columnsToString(SFunction<T, ?>... columns) {
        return Arrays.stream(columns).map(ValidateUtil::columnToString).collect(Collectors.toList());
    }

    protected static <T> String columnToString(SFunction<T, ?> column) {
        return getColumn(LambdaUtils.resolve(column));
    }

    private static String getColumn(SerializedLambda lambda) {
        return PropertyNamer.methodToProperty(lambda.getImplMethodName());
    }

    public static void validateParam(Object obj){
        try {
            for (Field field : obj.getClass().getDeclaredFields()){
                field.setAccessible(true);
                if (field.get(obj) == null){
                    throw new ApiCommonException(ErrorCodeEnum.PARAM_IS_EMPTY, field.getName());
                }
            }
        }catch (IllegalAccessException e){
            e.getStackTrace();
            log.error("transferEntityToMap error: {}", e.getMessage());
        }
    }
}
