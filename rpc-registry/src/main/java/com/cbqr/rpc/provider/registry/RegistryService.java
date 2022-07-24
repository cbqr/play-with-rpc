package com.cbqr.rpc.provider.registry;

import com.cbqr.rpc.common.ServiceMeta;

import java.io.IOException;

/**
 * 通用的注册中心接口，该接口主要的操作对象是 {@link ServiceMeta} ，不应该与其他任何第三方的注册中心工具库有任何联系
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public interface RegistryService {

    /**
     * 服务注册
     *
     * @param serviceMeta 元数据信息
     * @throws Exception 报错抛出该异常
     */
    void register(ServiceMeta serviceMeta) throws Exception;

    /**
     * 服务注销
     *
     * @param serviceMeta 元数据信息
     * @throws Exception 报错抛出该异常
     */
    void unRegister(ServiceMeta serviceMeta) throws Exception;

    /**
     * 服务发现
     *
     * @param serviceName 服务名
     * @param invokerHashCode 调用者hash码
     * @return 服务元数据信息
     * @throws Exception 报错抛出该异常
     */
    ServiceMeta discovery(String serviceName, int invokerHashCode) throws Exception;

    /**
     * 注册中心销毁
     *
     * @throws IOException 报错抛出该异常
     */
    void destroy() throws IOException;

}
