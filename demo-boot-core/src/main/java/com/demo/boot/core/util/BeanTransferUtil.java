package com.demo.boot.core.util;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ezio
 * @since 2023/03/01 16:11:47
 */
@Slf4j
public class BeanTransferUtil {

//    public static void handleParam(PusdafilBaseVerify verify, Object obj){
//        Map<String, Object> verifyMap = transBeanToMap(verify);
//        Map<String, Object> logMap = transBeanToMap(obj);
//
//        Map<String, Object> errorParam = new HashMap<>();
//        verifyMap.forEach((k, v) ->{
//            if (v instanceof Boolean && !(Boolean) v){
//                Object value = logMap.get(k);
//                errorParam.put(k, value instanceof Date ? DateUtil.parseDateToString((Date) value, DateUtil.COMPLETE_FORMAT_STR) : value);
//            }
//        });
//        verify.setExt(JSON.toJSONString(errorParam, SerializerFeature.WriteMapNullValue, SerializerFeature.QuoteFieldNames));
//        verify.setCheckTime(new Date());
//        verify.setCreatedTime(new Date());
//        verify.setStatus(CollectionUtil.isEmpty(errorParam));
//    }

    /**
     * transBeanToMap
     * @param obj
     * @return
     */
    public static Map<String, Object> transBeanToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!"class".equals(key)) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
            log.error("transBeanToMap error: {}", e.getMessage());
        }
        return map;
    }

    /**
     * transferEntityToMap
     * @param obj
     * @return
     */
    public static Map<String, Object> transferEntityToMap(Object obj){
        Map<String, Object> map = new HashMap<>();
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
//                if (field.get(obj) instanceof Boolean){}
                map.put(field.getName(), field.get(obj));
            }
        } catch (IllegalAccessException e) {
            e.getStackTrace();
            log.error("transferEntityToMap error: {}", e.getMessage());
        }
        return map;
    }

}
