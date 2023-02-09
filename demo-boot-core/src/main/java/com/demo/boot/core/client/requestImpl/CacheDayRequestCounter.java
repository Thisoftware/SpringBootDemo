package com.demo.boot.core.client.requestImpl;

import com.demo.boot.api.enums.LimitTypeEnum;
import org.springframework.stereotype.Component;

/**
 * @author wyl
 * @since 2023/02/06 18:04:09
 */
@Component
public class CacheDayRequestCounter extends AbstractCacheRequestCounter{
    @Override
    public int count() {
        return LimitTypeEnum.DAY.getCode();
    }

    @Override
    public LimitTypeEnum getLimitTypeEnum() {
        return LimitTypeEnum.DAY;
    }

    @Override
    protected String getKey(String bizType) {
        return bizType;
    }
}
