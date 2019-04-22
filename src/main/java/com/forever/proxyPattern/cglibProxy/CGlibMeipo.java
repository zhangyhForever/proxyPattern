package com.forever.proxyPattern.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/22
 * @ Description  : what to do ...
 */
public class CGlibMeipo implements MethodInterceptor {

    public Object getInstance(Class<?> clazz){
        //相当于proxy代理工具类，用来生成一个代理对象继承传入的类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object obj = methodProxy.invokeSuper(o, objects);
        after();
        return obj;
    }

    private void before(){
        System.out.println("说出你的要求，我帮你物色物色");
    }

    private void after(){
        System.out.println("找到了，如果合适就办事吧！");
    }
}
