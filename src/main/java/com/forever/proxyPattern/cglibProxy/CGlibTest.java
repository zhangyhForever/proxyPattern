package com.forever.proxyPattern.cglibProxy;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/22
 * @ Description  : what to do ...
 */
public class CGlibTest {

    public static void main(String[] args) {


        //jdk是采用读取接口的方法
        //cglib是覆盖父类方法
        //目的：都是生成一个新的代理类，去实现增强代码逻辑的功能

        //jdk Proxy 对于用户而言，必须有一个接口实现，目标类相对来说复杂
        //cglib  可以代理任意一个普通的类，没有任何要求

        //cglib 生成代理的逻辑更复杂，效率低，但执行效率高，生成一个包含了所有逻辑的FastClass,不需要反射调用
        //jdk Proxy生成代理的逻辑简单，执行效率相对要低，每次都要用反射动态调用


        //cglib 不能代理final的方法和类


        Customer obj = (Customer)new CGlibMeipo().getInstance(Customer.class);
        obj.findGirl();

    }
}
