package com.cbqr.rpc.provider;

import com.cbqr.rpc.codec.RpcDecoder;
import com.cbqr.rpc.codec.RpcEncoder;
import com.cbqr.rpc.common.RpcServiceHelper;
import com.cbqr.rpc.common.ServiceMeta;
import com.cbqr.rpc.handler.RpcRequestHandler;
import com.cbqr.rpc.provider.annotation.RpcService;
import com.cbqr.rpc.provider.registry.RegistryService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过重写 {@link BeanPostProcessor#postProcessAfterInitialization} 对所有初始化完成后的 Bean 进行扫描。
 *
 * 如果 Bean 包含 @RpcService 注解，那么通过注解读取服务的元数据信息并构造出 {@link ServiceMeta} 对象，并将
 * 服务的元数据信息发布至注册中心。
 *
 * 此外，RpcProvider 还维护了一个 rpcServiceMap，存放服务初始化后所对应的 Bean，rpcServiceMap 起到了缓存的角色，
 * 在处理 RPC 请求时可以直接通过 rpcServiceMap 拿到对应的服务进行调用。
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@Slf4j
public class RpcProvider implements InitializingBean, BeanPostProcessor {

    private String serverAddress;
    private final int serverPort;
    private final RegistryService serviceRegistry;

    /**
     * 存放服务初始化后处理 @RpcService 注解所对应的 Bean，起到了缓存的角色，
     * 在处理 RPC 请求时可以直接通过 rpcServiceMap 拿到对应的服务进行调用
     */
    private final Map<String, Object> rpcServiceMap = new HashMap<>();

    public RpcProvider(int serverPort, RegistryService serviceRegistry) {
        this.serverPort = serverPort;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void afterPropertiesSet() {
        new Thread(() -> {
            try {
                startRpcServer();
            } catch (Exception e) {
                log.error("start rpc server error.", e);
            }
        }).start();
    }

    /**
     * 服务提供者的启动入口
     *
     * @throws Exception 启动失败抛出异常
     */
    private void startRpcServer() throws Exception {
        // 启动时获取服务地址
        this.serverAddress = InetAddress.getLocalHost().getHostAddress();

        // 服务提供者采用的是主从 Reactor 线程模型，启动过程包括配置线程池、Channel 初始化、端口绑定
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new RpcEncoder())
                                    .addLast(new RpcDecoder())
                                    .addLast(new RpcRequestHandler(rpcServiceMap));
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = bootstrap.bind(this.serverAddress, this.serverPort).sync();
            log.info("server addr {} started on port {}", this.serverAddress, this.serverPort);

            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 处理 @RpcService 注解
        RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
        if (rpcService != null) {
            // 获取服务类型
            String serviceName = rpcService.serviceInterface().getName();
            // 获取服务版本
            String serviceVersion = rpcService.serviceVersion();

            try {
                // 组装服务元数据，该对象最终会上报到注册中心
                ServiceMeta serviceMeta = new ServiceMeta();
                serviceMeta.setServiceAddr(serverAddress);
                serviceMeta.setServicePort(serverPort);
                serviceMeta.setServiceName(serviceName);
                serviceMeta.setServiceVersion(serviceVersion);

                // 发布服务元数据到注册中心
                serviceRegistry.register(serviceMeta);

                // 缓存服务初始化后被 @RpcService 注解所修饰的 Bean
                rpcServiceMap.put(RpcServiceHelper.buildServiceKey(serviceMeta.getServiceName(), serviceMeta.getServiceVersion()), bean);
            } catch (Exception e) {
                log.error("failed to register service {}#{}", serviceName, serviceVersion, e);
            }
        }
        return bean;
    }
}
