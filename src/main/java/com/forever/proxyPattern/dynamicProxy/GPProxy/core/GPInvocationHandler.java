package com.forever.proxyPattern.dynamicProxy.GPProxy.core;

import java.lang.reflect.Method;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/4/22
 * @ Description  : what to do ...
 */
public interface GPInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
