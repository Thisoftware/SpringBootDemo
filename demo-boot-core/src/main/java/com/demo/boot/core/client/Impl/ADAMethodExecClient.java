package com.demo.boot.core.client.Impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("adaMethodExecClient")
public class ADAMethodExecClient extends ADispatchMethodExecClient{

    @Override
    public Map<String, Object> assembleRequest(Map<String, Object> methodExecute) {
        log.info("this location at AMethodExecClient_request");
        return super.assembleRequest(methodExecute);
    }

    @Override
    public Map<String, Object> doExec(Map<String, Object> targetService, Map<String, Object> method, Map<String, Object> parameters) {
        log.info("this location at AMethodExecClient_doExec");
        parameters.putAll(targetService);
        parameters.putAll(method);
        return parameters;
    }

    @Override
    public Map<String, Object> assembleResponse(Map<String, Object> methodExec, Map<String, Object> responseParameters) {
        log.info("this location at AMethodExecClient_response");
        log.info("methodExec: {}", JSON.toJSONString(methodExec));
        log.info("responseParameters: {}", JSON.toJSONString(responseParameters));
        responseParameters.put("response","AMethodExecClient_response");
        return responseParameters;
    }

    @Override
    public void afterCompletion(Map<String, Object> methodExec) {
        log.info("this location at AMethodExecClient_afterCompletion");
        super.afterCompletion(methodExec);
    }


}

