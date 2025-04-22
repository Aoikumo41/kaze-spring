package com.kaze.spring.beans;

import com.kaze.spring.core.NestedRuntimeException;

/**
 * beans包中异常的抽象超类
 * beans的异常都是致命的，因此为运行时异常
 * */
public abstract class BeansException extends NestedRuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
