package com.cbqr.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * 通信协议对象
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@Data
public class RpcProtocol<T> implements Serializable {

    private static final long serialVersionUID = 8860234114643510184L;

    /*
         +----------------------------------------------------------------+
         | 魔数 2byte | 协议版本号 1byte | 序列化算法 1byte | 报文类型 1byte |
         +----------------------------------------------------------------+
         | 状态 1byte |        消息 ID 8byte     |      数据长度 4byte      |
         +----------------------------------------------------------------+
         |                   数据内容 （长度不定）                          |
         +----------------------------------------------------------------+
     */

    /**
     * 协议头
     */
    private MsgHeader header;

    /**
     * 协议体
     */
    private T body;

}
