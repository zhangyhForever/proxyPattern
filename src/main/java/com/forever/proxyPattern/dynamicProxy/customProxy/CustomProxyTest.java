package com.forever.proxyPattern.dynamicProxy.customProxy;

import com.forever.proxyPattern.dynamicProxy.Girl;
import com.forever.proxyPattern.dynamicProxy.Persion;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class CustomProxyTest {

    public static void main(String[] args) {

        try{
            Persion customMeipo = (Persion)new CustomMeipo().getInstance(new Girl());

            customMeipo. findLove();

//            System.out.println(CustomProxyTest.class.getPackage().getName());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
