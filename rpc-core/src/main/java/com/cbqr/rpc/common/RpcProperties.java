package com.cbqr.rpc.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 服务提供者启动时需要配置的参数
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@Data
@ConfigurationProperties(prefix = "rpc")
public class RpcProperties {

    /**
     * 服务暴露的端口
     */
    private int servicePort;

    /**
     * 注册中心的地址
     */
    private String registryAddr;

    /**
     * 注册中心的类型
     */
    private String registryType;

}
