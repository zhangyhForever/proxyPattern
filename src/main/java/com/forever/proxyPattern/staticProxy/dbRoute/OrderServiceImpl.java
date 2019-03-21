package com.forever.proxyPattern.staticProxy.dbRoute;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public OrderServiceImpl(){
        this.orderDao = new OrderDao();
    }

    public int createOrder(Order order) {
        System.out.println("创建Order成功，等待插入数据库");
        return orderDao.insertOrder(order);
    }
}
