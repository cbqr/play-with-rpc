package com.cbqr.rpc.provider;

import com.cbqr.rpc.common.RpcProperties;
import com.cbqr.rpc.provider.registry.RegistryFactory;
import com.cbqr.rpc.provider.registry.RegistryService;
import com.cbqr.rpc.provider.registry.RegistryType;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 服务提供者自动装配核心类
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@Configuration
@EnableConfigurationProperties(RpcProperties.class)
public class RpcProviderAutoConfiguration {

    @Resource
    private RpcProperties rpcProperties;

    @Bean
    public RpcProvider init() throws Exception {
        RegistryType type = RegistryType.valueOf(rpcProperties.getRegistryType());
        RegistryService serviceRegistry = RegistryFactory.getInstance(rpcProperties.getRegistryAddr(), type);
        return new RpcProvider(rpcProperties.getServicePort(), serviceRegistry);
    }

}
