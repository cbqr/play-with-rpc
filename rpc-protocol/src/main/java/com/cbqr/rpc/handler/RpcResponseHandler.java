package com.cbqr.rpc.handler;

import com.cbqr.rpc.common.RpcFuture;
import com.cbqr.rpc.common.RpcRequestHolder;
import com.cbqr.rpc.common.RpcResponse;
import com.cbqr.rpc.protocol.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * RPC 响应处理器，负责响应不同线程的请求结果。
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public class RpcResponseHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcResponse>> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol<RpcResponse> msg) throws Exception {
        long requestId = msg.getHeader().getRequestId();
        RpcFuture<RpcResponse> future = RpcRequestHolder.REQUEST_MAP.remove(requestId);
        future.getPromise().setSuccess(msg.getBody());
    }
}
