package com.demo.boot.core.client.Impl;

import com.alibaba.fastjson.JSON;
import com.demo.boot.api.exception.ApiCommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;


@Slf4j
@Component("aDispatchMethodExecClient")
public class ADispatchMethodExecClient extends AbstractDispatchMethodExecClient{

    @Override
    public Map<String, Object> assembleRequest(Map<String, Object> methodExecute) {
        log.info("this location at ADispatchMethodExecClient_request");
        methodExecute.put("location","ADispatchMethodExecClient_assembleRequest");
        return methodExecute;
    }

    @Override
    public Map<String, Object> doExec(Map<String, Object> targetService, Map<String, Object> method, Map<String, Object> parameters) throws ApiCommonException {
        return null;
    }

    @Override
    public Map<String, Object> assembleResponse(Map<String, Object> methodExec, Map<String, Object> responseParameters) {
        return null;
    }

    @Override
    public void afterCompletion(Map<String, Object> methodExec) {
        log.info("this location at ADispatchMethodExecClient_response");
        log.info(JSON.toJSONString(methodExec));
    }
}
