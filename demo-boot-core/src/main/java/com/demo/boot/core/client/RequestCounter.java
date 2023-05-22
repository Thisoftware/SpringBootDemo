package com.demo.boot.core.client;

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
     * buildParam
     * @return
     */
    String buildParam();

}
