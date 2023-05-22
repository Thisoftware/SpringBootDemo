package com.demo.boot.core.client.requestImpl;

import com.demo.boot.api.enums.LimitTypeEnum;
import org.springframework.stereotype.Component;

/**
 * @author wyl
 * @since 2023/02/07 10:24:09
 */
@Component
public class CacheRelatedRequestCounter extends AbstractCacheRelateRequestCounter{
    @Override
    public int count() {
        return LimitTypeEnum.RELATED.getCode();
    }
}
