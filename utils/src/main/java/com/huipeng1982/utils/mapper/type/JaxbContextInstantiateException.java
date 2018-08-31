package com.huipeng1982.utils.mapper.type;

import com.huipeng1982.utils.exception.type.CloneableRuntimeException;

public class JaxbContextInstantiateException extends CloneableRuntimeException {

    public JaxbContextInstantiateException(Class clazz, String message, Throwable cause) {
        super("Could not instantiate JAXBContext for class [" + clazz + "]: " + message, cause);
    }

}
