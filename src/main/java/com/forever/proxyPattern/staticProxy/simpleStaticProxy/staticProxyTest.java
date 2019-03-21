package com.forever.proxyPattern.staticProxy.simpleStaticProxy;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class staticProxyTest {

    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.findLove();
    }
}
