package com.demo.boot.core.aop;

import com.alibaba.fastjson.JSON;
import com.demo.boot.api.constants.ReData;
import com.demo.boot.api.enums.ErrorCodeEnum;
import com.demo.boot.api.enums.ReCode;
import com.demo.boot.api.exception.ApiCommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(value = Exception.class)
    public ReData<String> exceptionHandler(Exception e){
        log.error("##异常原因:" + ExceptionUtils.getStackTrace(e));
        ReData<String> reData = new ReData<>();
        reData.setCode(ReCode.FAIL.code());
        if(e instanceof ApiCommonException){
            ApiCommonException ex = (ApiCommonException) e;
            reData.setMessage(ex.getMessage());
            return reData;
        }
        if(e instanceof MethodArgumentNotValidException || e instanceof HttpMessageConversionException){
            List<String> args = new ArrayList<>();
            if(e instanceof MethodArgumentNotValidException){
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
                args.add(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
            }
            reData.setCode(ErrorCodeEnum.REQUEST_PARAM_NULL_KEY.getCode());
            reData.setMessage(ErrorCodeEnum.getFillMessageByCode(ErrorCodeEnum.REQUEST_PARAM_NULL_KEY,args));
        } else {
            ReCode.FAIL.setResponse(reData);
            reData.setMessage("系统异常，请联系管理员");
        }
        log.info("##异常返回:response={}", JSON.toJSONString(reData));
        return reData;
    }
}
