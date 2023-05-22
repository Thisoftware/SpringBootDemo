package com.demo.boot.core.client.requestImpl;

import com.demo.boot.core.client.RequestCounter;

/**
 * @author wyl
 * @since 2023/02/06 17:57:00
 */
public abstract class AbstractCacheDayRequestCounter implements RequestCounter {

    @Override
    public String buildParam() {
        return "cacheDayRequest";
    }
}
