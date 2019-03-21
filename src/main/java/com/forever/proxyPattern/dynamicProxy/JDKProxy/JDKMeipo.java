package com.forever.proxyPattern.dynamicProxy.JDKProxy;

import com.forever.proxyPattern.dynamicProxy.Persion;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class JDKMeipo implements InvocationHandler {

    private Persion target;

    public Object getInstance(Persion persion){
        this.target = persion;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance (clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o = method.invoke(this.target, args);
        after();
        return o;
    }

    private void before(){
        System.out.println("说出你的要求，我帮你物色物色");
    }

    private void after(){
        System.out.println("找到了，如果合适就办事吧！");
    }

}
