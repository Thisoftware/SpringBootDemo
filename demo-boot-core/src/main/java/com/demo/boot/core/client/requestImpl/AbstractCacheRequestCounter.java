package com.demo.boot.core.client.requestImpl;

import com.demo.boot.core.client.RequestCounter;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wyl
 * @since 2023/02/06 17:57:00
 */
public abstract class AbstractCacheRequestCounter implements RequestCounter {

    protected Map<String, Object> objectMap;

    @PostConstruct
    public void init(){
        objectMap = new HashMap<>();
    }

    /**
     * getKey
     * @param bizType
     * @return
     */
    protected abstract String getKey(String bizType);
}
