package com.cbqr.rpc.common;

import io.netty.util.concurrent.Promise;
import lombok.Data;

/**
 * Rpc Future
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@Data
public class RpcFuture<T> {

    /*
        采用 Netty 提供的 Promise 工具来实现 RPC 请求的同步等待，Promise 基于 JDK 的 Future 扩展了更多新的特性，
        帮助我们更好地以同步的方式进行异步编程。

        Promise 模式本质是一种异步编程模型，我们可以先拿到一个查看任务执行结果的凭证，
        不必等待任务执行完毕，当我们需要获取任务执行结果时，再使用凭证提供的相关接口进行获取。
     */

    private Promise<T> promise;

    private long timeout;

    public RpcFuture(Promise<T> promise, long timeout) {
        this.promise = promise;
        this.timeout = timeout;
    }

}
