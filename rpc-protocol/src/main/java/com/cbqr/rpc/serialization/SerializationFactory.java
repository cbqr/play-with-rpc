package com.cbqr.rpc.serialization;

/**
 * 实现不同序列化算法之间的切换，使用相同的序列化接口指向不同的序列化算法，
 * 对于使用者来说只需要知道序列化算法的类型即可，不用关心底层序列化是如何实现的。
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public class SerializationFactory {

    public static RpcSerialization getRpcSerialization(byte serializationType) {
        SerializationTypeEnum typeEnum = SerializationTypeEnum.findByType(serializationType);
        switch (typeEnum) {
            case HESSIAN:
                return new HessianSerialization();
            case JSON:
                return new JsonSerialization();
            default:
                throw new IllegalArgumentException("serialization type is illegal, " + serializationType);
        }
    }

}
