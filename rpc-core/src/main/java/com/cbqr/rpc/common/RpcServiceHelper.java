package com.cbqr.rpc.common;

/**
 * RpcServiceHelper
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public class RpcServiceHelper {

    /*
        一般来说，我们会将相同版本的 RPC 服务归类在一起，所以可以将 ServiceInstance 的名称 name 根据服务名称和服务版本进行赋值
     */

    /**
     * 构建服务 KEY
     *
     * @param serviceName 服务名
     * @param serviceVersion 版本号
     * @return 构建好的服务 KEY
     */
    public static String buildServiceKey(String serviceName,String serviceVersion){
        return String.join("#", serviceName, serviceVersion);
    }

}
