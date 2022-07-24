package com.cbqr.rpc.serialization;

/**
 * 序列化异常
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public class SerializationException extends RuntimeException {

    private static final long serialVersionUID = -8697217716417818123L;

    public SerializationException() {
        super();
    }

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializationException(Throwable cause) {
        super(cause);
    }
}
