//package com.demo.boot.core.interceptor;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by wyl on 2017/8/17.
// */
//public class MyInterceptor implements HandlerInterceptor{
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
//            throws Exception {
//        System.out.println("MyInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
//
//        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
//                           ModelAndView modelAndView) throws Exception {
//        System.out.println("MyInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
//                                Exception e) throws Exception {
//        System.out.println("MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
//    }
//}


//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.demo.boot.api.annotation.PassVerify;
//import com.demo.boot.api.enums.ErrorCodeEnum;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
//public class AuthenticationInterceptor implements HandlerInterceptor {
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
//        // 从 http 请求头中取出 token
//        String token = httpServletRequest.getHeader("token");
//        // 从 http 请求头中取出 userUid
//        String userUid = httpServletRequest.getHeader("userUid");
//        // 如果不是映射到方法直接通过
//        if (!(object instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) object;
//        Method method = handlerMethod.getMethod();
//        //检查是否有passVerify注释，有则跳过认证
//        if (method.isAnnotationPresent(PassVerify.class)) {
//            PassVerify passVerify = method.getAnnotation(PassVerify.class);
//            if (passVerify.query()) {
//                return true;
//            }
//        }
//        // 执行认证
//        if (token == null) {
//            throw new MiscException(ErrorCodeEnum.ERROR_CODE_00027);
//        }
//        // 获取 token 中的 user id
//        String loginName;
//        try {
//            loginName = JWT.decode(token).getAudience().get(0);
//        } catch (JWTDecodeException j) {
//            throw new MiscException(ErrorCodeEnum.ERROR_CODE_00027);
//        }
//
//        String redisToken = RedisUtil.get(RedisKeyConstants.LOGIN_TOKEN + loginName);
//        if (userUid == null || !userUid.equals(loginName) || StringUtils.isEmpty(redisToken) || !token.equals(redisToken)) {
//            throw new MiscException(ErrorCodeEnum.ERROR_CODE_00027);
//        }
//        TblSysUser user = userService.getUser(loginName);
//        if (user == null) {
//            throw new MiscException(ErrorCodeEnum.ERROR_CODE_00027);
//        }
//        // 验证 token
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getId())).build();
//        try {
//            jwtVerifier.verify(token);
//            RedisUtil.expire(RedisKeyConstants.LOGIN_TOKEN + loginName, 30 * 60);
//            UserObjectUtil.setCurrent(user);
//        } catch (JWTVerificationException e) {
//            throw new MiscException(ErrorCodeEnum.ERROR_CODE_00027);
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
