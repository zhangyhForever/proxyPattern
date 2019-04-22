package com.forever.proxyPattern.dynamicProxy.GPProxy;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/4/22
 * @ Description  : what to do ...
 */
public class JDKProxyTest {

    public static void main(String[] args) throws Exception {
        Person instance = (Person)new Meipo().getProxyInstance(new Girl());
        instance.findLove();
    }
}
