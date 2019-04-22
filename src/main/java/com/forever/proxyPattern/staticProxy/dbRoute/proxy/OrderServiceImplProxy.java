package com.forever.proxyPattern.staticProxy.dbRoute.proxy;

import com.forever.proxyPattern.staticProxy.dbRoute.Order;
import com.forever.proxyPattern.staticProxy.dbRoute.OrderService;
import com.forever.proxyPattern.staticProxy.dbRoute.OrderServiceImpl;
import com.forever.proxyPattern.staticProxy.dbRoute.dbSource.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class OrderServiceImplProxy implements OrderService {

    private final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    private OrderService orderService;

    public OrderServiceImplProxy (OrderService orderService){
        this.orderService = orderService;
    }

    public int createOrder(Order order) {
        Long createTime = order.getCreateTime();
        int dbRoute = Integer.valueOf(yearFormat.format(new Date(createTime)));
        System.out.println("数据源更换，使用数据源【DB_" + dbRoute+"】");
        DynamicDataSourceEntity.set(dbRoute);

        DynamicDataSourceEntity.reset();
        return orderService.createOrder(order);
    }
}
