package com.cbqr.rpc.serialization;

import lombok.Getter;

/**
 * 序列化类型枚举
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public enum SerializationTypeEnum {

    HESSIAN(0x10),
    JSON(0x20);

    @Getter
    private int type;

    SerializationTypeEnum(int type) {
        this.type = type;
    }

    public static SerializationTypeEnum findByType(byte SerializationType) {
        for (SerializationTypeEnum typeEnum : SerializationTypeEnum.values()) {
            if (typeEnum.getType() == SerializationType) {
                return typeEnum;
            }
        }
        return HESSIAN;
    }

}
