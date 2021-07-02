package com.demo.boot.core.client;

import com.demo.boot.api.exception.ApiCommonException;

import java.util.Map;

public interface MethodExecClient {

    Map<String, Object> exec(Map<String,Object> targetService, Map<String,Object> method, Map<String,Object> methodExecute) throws ApiCommonException;

}
