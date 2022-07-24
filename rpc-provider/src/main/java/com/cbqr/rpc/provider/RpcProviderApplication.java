package com.cbqr.rpc.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Rpc Provider
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@SpringBootApplication
@EnableConfigurationProperties
public class RpcProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcProviderApplication.class, args);
    }
}
