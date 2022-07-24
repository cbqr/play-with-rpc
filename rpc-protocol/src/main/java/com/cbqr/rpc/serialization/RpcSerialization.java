package com.cbqr.rpc.serialization;

import java.io.IOException;

/**
 * 通用的序列化接口，所有序列化算法扩展都必须实现该接口
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public interface RpcSerialization {

    /**
     * 序列化
     *
     * @param obj 待序列化的对象
     * @param <T> 对象类型
     * @return 序列化后的对象
     * @throws IOException 报错就抛出异常
     */
    <T> byte[] serialize(T obj) throws IOException;

    /**
     * 反序列化
     *
     * @param data 待反序列化的对象
     * @param clz 类对象
     * @param <T> 对象类型
     * @return 反序列化后的对象
     * @throws IOException 报错就抛出异常
     */
    <T> T deserialize(byte[] data, Class<T> clz) throws IOException;

}
