package com.huipeng1982.utils.text.type;

import com.huipeng1982.utils.exception.type.CloneableRuntimeException;

public class MessageDigestCreatingException extends CloneableRuntimeException {

    public MessageDigestCreatingException(String digest, Throwable cause) {
        super("unexpected exception creating MessageDigest instance for [" + digest + ']', cause);
    }
}
