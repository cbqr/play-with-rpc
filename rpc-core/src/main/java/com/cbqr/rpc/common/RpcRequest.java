package com.cbqr.rpc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * RPC 请求对象，包含远程调用所需要的必要参数
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@Data
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -6592811836152983569L;

    /**
     * 服务版本
     */
    private String serviceVersion;

    /**
     * 服务接口名
     */
    private String className;

    /**
     * 服务方法名
     */
    private String methodName;

    /**
     * 方法参数列表
     */
    private Object[] params;

    /**
     * 方法参数类型列表
     */
    private Class<?>[] parameterTypes;

}
