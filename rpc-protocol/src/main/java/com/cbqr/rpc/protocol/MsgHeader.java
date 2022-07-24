package com.cbqr.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * 协议实体类 {@link RpcProtocol#getHeader()}
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@Data
public class MsgHeader implements Serializable {

    private static final long serialVersionUID = 4236699289407199851L;

    /**
     * 魔数
     */
    private short magic;

    /**
     * 协议版本号
     */
    private byte version;

    /**
     * 序列化算法
     */
    private byte serialization;

    /**
     * 报文类型
     */
    private byte msgType;

    /**
     * 状态
     */
    private byte status;

    /**
     * 消息 ID
     */
    private long requestId;

    /**
     * 数据长度
     */
    private int msgLen;

}
