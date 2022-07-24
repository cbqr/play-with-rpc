package com.cbqr.rpc.provider;

import com.cbqr.rpc.provider.registry.RegistryFactory;
import com.cbqr.rpc.provider.registry.RegistryService;
import com.cbqr.rpc.provider.registry.RegistryType;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Proxy;

/**
 * 通过 {@link FactoryBean#getObject()} 来实现自定义的 Bean。
 *
 * 对于声明 @RpcReference 注解的成员变量，我们需要构造出一个可以真正进行 RPC 调用的 Bean，然后将它注册到 Spring 的容器中。
 *
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public class RpcReferenceBean implements FactoryBean<Object> {

    private Class<?> interfaceClass;

    private String serviceVersion;

    private String registryType;

    private String registryAddr;

    private long timeout;

    private Object object;

    @Override
    public Object getObject() throws Exception {
        return object;
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }

    /**
     * 生成动态代理对象并赋值给 object，并通过代理对象完成 RPC 调用。
     *
     * 对于使用者来说只是通过 @RpcReference 订阅了服务，并不感知底层调用的细节。
     * 对于如何实现 RPC 通信、服务寻址等，都是在动态代理类中完成的。
     *
     * @throws Exception 报错就抛出异常
     */
    public void init() throws Exception {
        RegistryService registryService = RegistryFactory.getInstance(this.registryAddr, RegistryType.valueOf(this.registryType));
        this.object = Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new RpcInvokerProxy(serviceVersion, timeout, registryService));
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public void setRegistryType(String registryType) {
        this.registryType = registryType;
    }

    public void setRegistryAddr(String registryAddr) {
        this.registryAddr = registryAddr;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
