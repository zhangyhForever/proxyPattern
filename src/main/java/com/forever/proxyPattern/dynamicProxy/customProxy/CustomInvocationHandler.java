package com.forever.proxyPattern.dynamicProxy.customProxy;

import java.lang.reflect.Method;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public interface CustomInvocationHandler {

    public Object invoke(Object obj, Method method, Object[] args) throws Throwable;
}
