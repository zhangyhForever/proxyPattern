package com.forever.proxyPattern.dynamicProxy.orderDynamicProxy;

import com.forever.proxyPattern.staticProxy.dbRoute.Order;
import com.forever.proxyPattern.staticProxy.dbRoute.dbSource.DynamicDataSourceEntity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/22
 * @ Description  : what to do ...
 */
public class OrderServiceDynamicProxy implements InvocationHandler {

    private Object proxyObj;
    public Object getInstance(Object objInstance){
        this.proxyObj = objInstance;
        Class<?> clazz = proxyObj.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args[0]);
        Object o = method.invoke(this.proxyObj, args);
        after();
        return o;
    }

    public void before(Object target){
        try{
            Method m = target.getClass().getMethod("getCreateTime");
            Long time = (Long)m.invoke(target);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            int year = Integer.valueOf(sdf.format(new Date(time)));
            System.out.println("数据源切换至DB_"+year);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void after(){
        DynamicDataSourceEntity.reset();
        System.out.println("结束");
    }
}
