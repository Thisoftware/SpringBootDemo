package com.demo.boot.core.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author wyl
 * @since 2023/2/5
 * ThreadUtil.execute(() -> process);
 */
public class ThreadUtil {

    private static volatile ThreadPoolExecutor THREAD_POOL = null;

    private ThreadUtil() {
    }

    public static void execute(Runnable runnable) {
        THREAD_POOL.execute(runnable);
    }

    public static <T> Future<T> submit(Runnable runnable, T result) {
        return THREAD_POOL.submit(runnable, result);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return THREAD_POOL.submit(task);
    }

    public static void shutdown() {
        THREAD_POOL.shutdown();
    }

    public static ThreadPoolExecutor getExecutorObject() {
        return THREAD_POOL;
    }

    static {
        if (THREAD_POOL == null) {
            synchronized(ThreadUtil.class) {
                if (THREAD_POOL == null) {
                    int corePoolSize = Runtime.getRuntime().availableProcessors();
                    ThreadFactory nameThreadFactory = (new ThreadFactoryBuilder()).setNameFormat("bill-pool-%d").build();
                    THREAD_POOL = new ThreadPoolExecutor(corePoolSize, corePoolSize * 2 + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100000), nameThreadFactory);
                }
            }
        }

    }

}
