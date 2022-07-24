package com.cbqr.rpc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Rpc 响应对象
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@Data
public class RpcResponse implements Serializable {

    private static final long serialVersionUID = 6476963974432645945L;

    /**
     * 请求结果
     */
    private Object data;

    /**
     * 错误信息
     */
    private String message;

}
