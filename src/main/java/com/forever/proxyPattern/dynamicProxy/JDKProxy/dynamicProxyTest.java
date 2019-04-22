package com.forever.proxyPattern.dynamicProxy.JDKProxy;

import com.forever.proxyPattern.dynamicProxy.Girl;
import com.forever.proxyPattern.dynamicProxy.Persion;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class dynamicProxyTest {

    public static void main(String[] args) {

        try{
            Persion jdkMeipo = (Persion)new JDKMeipo().getInstance(new Girl());

            jdkMeipo. findLove();

            //将代理类生成文件保存
           /* byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Persion.class});
            FileOutputStream fos = new FileOutputStream("./$Proxy0.class");
            fos.write(bytes);
            fos.flush();
            fos.close();*/
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
