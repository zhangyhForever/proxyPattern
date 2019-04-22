package com.forever.proxyPattern.dynamicProxy.GPProxy;

import com.forever.proxyPattern.dynamicProxy.GPProxy.core.GPClassLoader;
import com.forever.proxyPattern.dynamicProxy.GPProxy.core.GPInvocationHandler;
import com.forever.proxyPattern.dynamicProxy.GPProxy.core.GPProxy;

import java.lang.reflect.Method;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/4/22
 * @ Description  : what to do ...
 */
public class Meipo implements GPInvocationHandler {

    private Person target;

    public Object getProxyInstance(Person person) throws Exception {
        this.target = person;
        Class<?> clazz = person.getClass();
        return GPProxy.newProxyInstance(new GPClassLoader(), clazz.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o = method.invoke(target, args);
        after();
        return o;
    }

    public void before(){
        System.out.println("前期准备工作");
    }

    public void after(){
        System.out.println("后期完善工作");
    }
}
