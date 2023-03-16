package com.demo.boot.core.client.requestImpl;

import cn.hutool.core.util.ObjectUtil;
import com.demo.boot.api.enums.LimitTypeEnum;
import com.demo.boot.api.exception.ApiCommonException;
import com.demo.boot.core.client.RequestCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author wyl
 * @since 2023/02/07 10:29:29
 */
@Component
@Slf4j
public class RequestCounterManager {

    @Resource
    private List<RequestCounter> requestCounters;

    @Resource
    private Map<String, RequestCounter> dispatchMethodExecClientMap;

    public RequestCounter getRequestCounter(int code){
        for (RequestCounter requestCounter : requestCounters){
            if (requestCounter.getLimitTypeEnum() == LimitTypeEnum.getLimitTypeEnum(code)){
                return requestCounter;
            }
        }
        throw new ApiCommonException("No Matched Request Counter Found");
    }

    public RequestCounter getRequestCounter(String type){
        String methodName = type + RequestCounter.class.getSimpleName();
        RequestCounter requestCounter = dispatchMethodExecClientMap.get(methodName);
        if (ObjectUtil.isNull(requestCounter)) {
            log.error("No corresponding loan processor found，requestName={}", methodName);
            throw new ApiCommonException("The request processor "+ methodName +" does not exist");
        }
        return requestCounter;
    }
}
