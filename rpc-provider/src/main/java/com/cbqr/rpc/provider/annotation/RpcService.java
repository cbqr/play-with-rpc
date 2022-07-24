package com.cbqr.rpc.provider.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务提供者通过 @RpcService 去暴露 RPC 服务
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcService {

    /*
        @RpcService 注解本质上就是 @Component，可以将服务实现类注册成 Spring 容器所管理的 Bean，
        那么 serviceInterface、serviceVersion 的属性值怎么才能和 Bean 关联起来呢？

        这就需要我们对 Bean 的生命周期以及 Bean 的可扩展点有所了解。

        Spring 的 BeanPostProcessor 接口给提供了对 Bean 进行再加工的扩展点，BeanPostProcessor 常用于处理自定义注解。
        自定义的 Bean 可以通过实现 BeanPostProcessor 接口，在 Bean 实例化的前后加入自定义的逻辑处理。
        我们通过 RpcProvider 类实现 BeanPostProcessor 接口，来实现对 声明 @RpcService 注解服务的自定义处理。
     */

    /**
     * 服务类型，服务消费者必须指定完全一样的属性才能正确调用
     *
     * @return 服务类型
     */
    Class<?> serviceInterface() default Object.class;

    /**
     * 服务版本，服务消费者必须指定完全一样的属性才能正确调用
     *
     * @return 服务版本
     */
    String serviceVersion() default "1.0";
}
