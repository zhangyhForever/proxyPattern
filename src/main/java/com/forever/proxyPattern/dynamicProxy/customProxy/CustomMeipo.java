package com.forever.proxyPattern.dynamicProxy.customProxy;

import com.forever.proxyPattern.dynamicProxy.Persion;

import java.lang.reflect.Method;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class CustomMeipo implements CustomInvocationHandler{

    private Persion target;

    public Object getInstance(Persion persion) {
        this.target = persion;
        Class<?> clazz = target.getClass();
        return CustomProxy.newInstance(new CustomClassLoader(), clazz.getInterfaces(),this);
    }

    private void before(){
        System.out.println("说出你的要求，我帮你物色物色");
    }

    private void after(){
        System.out.println("找到了，如果合适就办事吧！");
    }

    public Object invoke(Object obj, Method method, Object[] args) throws Throwable{
        before();
        Object o = method.invoke(this.target, args);
        after();
        return o;
    }
}
