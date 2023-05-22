package com.demo.boot.core.client.requestImpl;

import com.demo.boot.core.client.RequestCounter;

/**
 * @author kis
 * @since 2023/05/22 17:05:10
 */
public abstract class AbstractCacheRelateRequestCounter implements RequestCounter {

    @Override
    public String buildParam() {
        return "cacheRelateRequest";
    }
}
