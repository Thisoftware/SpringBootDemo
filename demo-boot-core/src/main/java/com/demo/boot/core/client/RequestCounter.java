package com.demo.boot.core.client;

import com.demo.boot.api.enums.LimitTypeEnum;

/**
 * @author wyl
 * @since 2023/02/06 17:37:29
 */
public interface RequestCounter {
    /**
     * get count
     * @return
     */
    int count();

    /**
     * get bean
     * @return
     */
    LimitTypeEnum getLimitTypeEnum();

}
