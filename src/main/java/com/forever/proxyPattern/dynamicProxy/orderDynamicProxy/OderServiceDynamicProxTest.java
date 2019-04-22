package com.forever.proxyPattern.dynamicProxy.orderDynamicProxy;

import com.forever.proxyPattern.staticProxy.dbRoute.Order;
import com.forever.proxyPattern.staticProxy.dbRoute.OrderDao;
import com.forever.proxyPattern.staticProxy.dbRoute.OrderService;
import com.forever.proxyPattern.staticProxy.dbRoute.OrderServiceImpl;

import java.util.Date;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/22
 * @ Description  : what to do ...
 */
public class OderServiceDynamicProxTest {


    public static void main(String[] args) {
        Order order = new Order();
        order.setCreateTime(new Date().getTime());
        OrderService service = new OrderServiceImpl();
        OrderService osdp = (OrderService)new OrderServiceDynamicProxy().getInstance(service);
        osdp.createOrder(order);

    }

}
