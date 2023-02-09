package com.demo.boot.core.client.requestImpl;

import com.demo.boot.api.enums.LimitTypeEnum;
import com.demo.boot.api.exception.ApiCommonException;
import com.demo.boot.core.client.RequestCounter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wyl
 * @since 2023/02/07 10:29:29
 */
@Component
public class RequestCounterManager {

    @Resource
    private List<RequestCounter> requestCounters;

    public RequestCounter getRequestCounter(int code){
        for (RequestCounter requestCounter : requestCounters){
            if (requestCounter.getLimitTypeEnum() == LimitTypeEnum.getLimitTypeEnum(code)){
                return requestCounter;
            }
        }
        throw new ApiCommonException("No Matched Request Counter Found");
    }
}
