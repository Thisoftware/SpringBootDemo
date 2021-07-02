package com.demo.boot.core.client.Impl;

import com.demo.boot.api.enums.ErrorCodeEnum;
import com.demo.boot.api.exception.ApiCommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("adbMethodExecClient")
public class ADBMethodExecClient extends ADispatchMethodExecClient{

    @Override
    public Map<String, Object> assembleRequest(Map<String, Object> methodExecute) {
        methodExecute.put("adb_assembleRequest","adbQ");
        return methodExecute;
    }

    @Override
    public Map<String, Object> exec(Map<String, Object> targetService, Map<String, Object> method, Map<String, Object> methodExecute) throws ApiCommonException {
        if(targetService.get("type").equals("bat")){
            targetService.putAll(method);
            targetService.putAll(methodExecute);
            handleParam(targetService);
            return targetService;
        }
        return super.exec(targetService, method, methodExecute);
    }

    @Override
    public Map<String, Object> assembleResponse(Map<String, Object> methodExec, Map<String, Object> responseParameters) {
        try {
            if(responseParameters != null){
                responseParameters.put("adb_assembleResponse","adbP");
                return responseParameters;
            }
            throw new ApiCommonException(ErrorCodeEnum.REQUEST_PARAM_NULL_KEY);
        } catch (ApiCommonException e) {
            log.info(ErrorCodeEnum.REQUEST_PARAM_NULL_KEY.getMessage());
        }
        return null;
    }
}
