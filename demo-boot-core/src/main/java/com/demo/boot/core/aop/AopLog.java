//package com.demo.boot.core.aop;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Maps;
////import eu.bitwalker.useragentutils.UserAgent;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.Collections;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * <p>
// * record request msg
// * </p>
// */
//@Aspect
//@Component
//@Slf4j
//public class AopLog {
//    /**
//     * point
//     */
//    @Pointcut("execution(public * com.demo.boot.core.controller.*Controller.*(..))")
//    public void log() {}
//
//    /**
//     * around
//     *
//     * @param point point
//     * @return method process
//     * @throws Throwable
//     */
//    @Around("log()")
//    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
//
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
//
//        // print request param
//        long startTime = System.currentTimeMillis();
//        Object result = point.proceed();
//        String header = request.getHeader("User-Agent");
////        UserAgent userAgent = UserAgent.parseUserAgentString(header);
//
//        final Log l = Log.builder()
//            .threadId(Long.toString(Thread.currentThread().getId()))
//            .threadName(Thread.currentThread().getName())
//            .ip(getIp(request))
//            .url(request.getRequestURL().toString())
//            .classMethod(String.format("%s.%s", point.getSignature().getDeclaringTypeName(),
//                point.getSignature().getName()))
//            .httpMethod(request.getMethod())
//            .requestParams(getNameAndValue(point))
//            .result(result)
//            .timeCost(System.currentTimeMillis() - startTime)
//            .userAgent(header).build();
////            .browser(userAgent.getBrowser().toString())
////            .os(userAgent.getOperatingSystem().toString()).build();
//
//        log.info("Request Log Info : {}", JSON.toJSONString(l));
//
//        return result;
//    }
//
//    /**
//     *  get method name and param
//     * @param joinPoint
//     * @return
//     */
//    private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
//
//        final Signature signature = joinPoint.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        final String[] names = methodSignature.getParameterNames();
//        final Object[] args = joinPoint.getArgs();
//
//        if( names == null || names.length == 0 || args == null || args.length == 0 ) {
//            return Collections.emptyMap();
//        }
//        if (names.length != args.length) {
//            log.warn("{}The method parameter name and the number of parameter values are inconsistent", methodSignature.getName());
//            return Collections.emptyMap();
//        }
//        Map<String, Object> map = Maps.newHashMap();
//        for (int i = 0; i < names.length; i++) {
//            map.put(names[i], args[i]);
//        }
//        return map;
//    }
//
//    private static final String UNKNOWN = "unknown";
//
//    /**
//     * get ip address
//     */
//    public static String getIp(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        String comma = ",";
//        String localhost = "127.0.0.1";
//        if (ip.contains(comma)) {
//            ip = ip.split(",")[0];
//        }
//        if (localhost.equals(ip)) {
//            // 获取本机真正的ip地址
//            try {
//                ip = InetAddress.getLocalHost().getHostAddress();
//            } catch (UnknownHostException e) {
//                log.error(e.getMessage(), e);
//            }
//        }
//        return ip;
//    }
//
//    @Data
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
//    static class Log {
//        // threadId
//        private String threadId;
//        // threadName
//        private String threadName;
//        // ip
//        private String ip;
//        // url
//        private String url;
//        // httpRequest GET POST PUT DELETE PATCH
//        private String httpMethod;
//        // classMethod
//        private String classMethod;
//        // requestParams
//        private Object requestParams;
//        // result
//        private Object result;
//        // timeCost
//        private Long timeCost;
//        // system
//        private String os;
//        // browser
//        private String browser;
//        // user-agent
//        private String userAgent;
//    }
//}
