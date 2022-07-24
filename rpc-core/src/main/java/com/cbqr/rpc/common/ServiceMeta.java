package com.cbqr.rpc.common;

import lombok.Data;

/**
 * 注册中心元数据信息对象
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@Data
public class ServiceMeta {

    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 版本号
     */
    private String serviceVersion;

    /**
     * 地址
     */
    private String serviceAddr;

    /**
     * 端口号
     */
    private int servicePort;

}
