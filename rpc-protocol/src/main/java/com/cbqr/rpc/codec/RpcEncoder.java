package com.cbqr.rpc.codec;

import com.cbqr.rpc.protocol.MsgHeader;
import com.cbqr.rpc.protocol.RpcProtocol;
import com.cbqr.rpc.serialization.RpcSerialization;
import com.cbqr.rpc.serialization.SerializationFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器实现
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol<Object>> {

    /*
        在服务消费者或者服务提供者调用 writeAndFlush() 将数据写给对方前，都已经封装成 RpcRequest 或者 RpcResponse，
        所以可以采用 RpcProtocol<Object> 作为 RpcEncoder 编码器能够支持的编码类型。
     */

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcProtocol<Object> msg, ByteBuf byteBuf) throws Exception {
        MsgHeader header = msg.getHeader();
        byteBuf.writeShort(header.getMagic());
        byteBuf.writeByte(header.getVersion());
        byteBuf.writeByte(header.getSerialization());
        byteBuf.writeByte(header.getMsgType());
        byteBuf.writeByte(header.getStatus());
        byteBuf.writeLong(header.getRequestId());
        RpcSerialization rpcSerialization = SerializationFactory.getRpcSerialization(header.getSerialization());
        byte[] data = rpcSerialization.serialize(msg.getBody());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
    }
}
