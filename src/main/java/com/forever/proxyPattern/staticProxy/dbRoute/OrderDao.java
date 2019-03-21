package com.forever.proxyPattern.staticProxy.dbRoute;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class OrderDao {

    public int insertOrder(Order order){
        System.out.println("Order插入数据库成功");
        return 1;
    }
}
