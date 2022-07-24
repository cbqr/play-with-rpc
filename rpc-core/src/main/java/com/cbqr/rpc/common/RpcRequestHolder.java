package com.cbqr.rpc.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 服务消费者在发起调用时，维护了请求 requestId 和 RpcFuture<RpcResponse> 的映射关系，
 * RpcResponseHandler 会根据请求的 requestId 找到对应发起调用的 RpcFuture，然后为 RpcFuture 设置响应结果。
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public class RpcRequestHolder {

    public static final AtomicLong REQUEST_ID_GEN = new AtomicLong(0);

    public static final Map<Long, RpcFuture<RpcResponse>> REQUEST_MAP = new ConcurrentHashMap<>();

}
