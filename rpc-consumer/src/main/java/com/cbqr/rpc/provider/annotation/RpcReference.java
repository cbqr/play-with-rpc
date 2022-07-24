package com.cbqr.rpc.provider.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务消费者通过 @RpcReference 去调用 RPC 服务
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@Autowired
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcReference {

    /*
        @RpcReference 注解提供了服务版本 serviceVersion、
                      注册中心类型 registryType、
                      注册中心地址 registryAddress、
                      超时时间 timeout 四个属性
        接下来我们需要使用这些属性构造出一个自定义的 Bean，并对该 Bean 执行的所有方法进行拦截。

        Spring 的 FactoryBean 接口可以帮助我们实现自定义的 Bean，FactoryBean 是一种特种的工厂 Bean，
        通过 getObject() 方法返回对象，而并不是 FactoryBean 本身。
     */

    String serviceVersion() default "1.0";

    String registryType() default "ZOOKEEPER";

    String registryAddress() default "127.0.0.1:2181";

    long timeout() default 5000;

}
