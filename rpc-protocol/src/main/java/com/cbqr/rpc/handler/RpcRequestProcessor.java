package com.cbqr.rpc.handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 请求处理器
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public class RpcRequestProcessor {

    private static ThreadPoolExecutor threadPool;

    public static void submitRequest(Runnable task) {
        if (threadPool == null) {
            synchronized (RpcRequestProcessor.class) {
                if (threadPool == null) {
                    threadPool = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));
                }
            }
        }
        threadPool.submit(task);
    }

}