package com.huipeng1982.utils.exception.type;

/**
 * CheckedException的wrapper.
 * <p>
 * 返回Message时, 将返回内层Exception的Message.
 */
public class UncheckedException extends RuntimeException {

    private static final long serialVersionUID = 2657394922560595080L;

    public UncheckedException(Throwable wrapped) {
        super(wrapped);
    }

    @Override
    public String getMessage() {
        return super.getCause().getMessage();
    }
}
