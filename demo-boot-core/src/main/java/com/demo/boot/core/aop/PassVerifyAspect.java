package com.demo.boot.core.aop;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.demo.boot.api.annotation.PassVerify;
import com.demo.boot.api.constants.RedisKeyConstants;
import com.demo.boot.api.enums.ErrorCodeEnum;
import com.demo.boot.api.exception.ApiCommonException;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class PassVerifyAspect {

    // 修正Timer注解的全局唯一限定符
    @Pointcut("@annotation(com.demo.boot.api.annotation.PassToken)")
    private void passVerify() {}

    @Pointcut("execution(* com.demo.boot.core.controller.*Controller.*(..))")
    private void controller(){}

    @Around("passVerify() || controller()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader("token");

        //String userUid = request.getHeader("userUid");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取目标类方法名称
        //String methodName = joinPoint.getSignature().getName();
        Method method = joinPoint.getTarget().getClass().getMethod(methodSignature.getName(),methodSignature.getParameterTypes());
        if(method.isAnnotationPresent(PassVerify.class)){
            PassVerify passToken = method.getAnnotation(PassVerify.class);
            if(passToken.query()){
                return joinPoint.proceed();
            }
        }
        if (token == null) {
            throw new ApiCommonException(ErrorCodeEnum.ERROR_CODE_100002);
        }
//        String loginName;
//        try {
//            loginName = JWT.decode(token).getAudience().get(0);
//        } catch (JWTDecodeException j) {
//            throw new ApiCommonException(ErrorCodeEnum.ERROR_CODE_100002);
//        }
//        String redisToken = RedisUtil.get(RedisKeyConstants.LOGIN_TOKEN + loginName);
//        if (StringUtils.isBlank(userUid) || !userUid.equals(loginName) || StringUtils.isBlank(redisToken) || !token.equals(redisToken)) {
//            throw new ApiCommonException(ErrorCodeEnum.ERROR_CODE_100002);
//        }
//        TblSysUser user = userService.getUser(loginName);
//        if (user == null) {
//            throw new ApiCommonException(ErrorCodeEnum.ERROR_CODE_100002);
//        }
        // 验证 token
        //JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getId())).build();
//        try {
//            jwtVerifier.verify(token);
//            RedisUtil.expire(RedisKeyConstants.LOGIN_TOKEN + loginName, 30 * 60);
//            UserObjectUtil.setCurrent(user);
//        } catch (JWTVerificationException e) {
//            throw new MiscException(ErrorCodeEnum.ERROR_CODE_00027);
//        }
        return joinPoint.proceed();
    }

}
