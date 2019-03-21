package com.forever.proxyPattern.staticProxy.simpleStaticProxy;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class Father {

    private Son son;

    //静态代理，只为自己的儿子找对象，其他人不管
    public Father(Son son){
        this.son = son;
    }

    public void findLove(){
        System.out.println("我得出去转转观察观察");
        son.findLove();
        System.out.println("双方同意，确立关系");
    }

}
