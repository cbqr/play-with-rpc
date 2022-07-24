package com.cbqr.rpc.provider.registry.loadbalancer;

import java.util.List;

/**
 * 负载均衡接口
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public interface ServiceLoadBalancer<T> {

    /**
     * 选择服务实例
     *
     * @param servers 注册中心中，服务实例的元数据信息 List<ServiceInstance<ServiceMeta>>
     * @param hashCode 请求对象参数列表中第一个参数的 hashCode
     * @return 选中后的服务实例
     */
    T select(List<T> servers, int hashCode);

}
