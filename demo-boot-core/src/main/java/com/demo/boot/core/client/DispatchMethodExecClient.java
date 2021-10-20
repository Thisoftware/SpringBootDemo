package com.demo.boot.core.client;

import java.util.Map;

public interface DispatchMethodExecClient extends MethodExecClient{

    Map<String, Object> assembleRequest(Map<String,Object> methodExecute);

    Map<String, Object> doExec(Map<String,Object> targetService, Map<String,Object> method, Map<String, Object> parameters);

    Map<String, Object> assembleResponse(Map<String,Object> methodExec, Map<String, Object> responseParameters);

    void afterCompletion(Map<String,Object> methodExec);

}
