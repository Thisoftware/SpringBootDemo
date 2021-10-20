package com.demo.boot.core.client.Impl;

import com.demo.boot.api.exception.ApiCommonException;
import com.demo.boot.core.client.DispatchMethodExecClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public abstract class AbstractDispatchMethodExecClient implements DispatchMethodExecClient {

    @Override
    public Map<String, Object> exec(Map<String, Object> targetService, Map<String, Object> method, Map<String, Object> methodExecute) {
        Map<String, Object> requestParameters = this.assembleRequest(methodExecute);
        log.info("【{}】组装请求, {}, {}", targetService, method, requestParameters);

        Map<String, Object> responseParameters = this.doExec(targetService,method,requestParameters);

        Map<String, Object> response = this.assembleResponse(methodExecute,responseParameters);
        log.info("应答 >>> {}", responseParameters);

        this.afterCompletion(methodExecute);

        return response;
    }
}
