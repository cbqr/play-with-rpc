package com.cbqr.rpc.protocol;

import lombok.Getter;

/**
 * 消息状态
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public enum MsgStatus {

    SUCCESS(0),
    FAIL(1);

    @Getter
    private final int code;

    MsgStatus(int code) {
        this.code = code;
    }
}
