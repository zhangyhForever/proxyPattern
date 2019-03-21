package com.forever.proxyPattern.staticProxy.dbRoute;

import com.forever.proxyPattern.staticProxy.dbRoute.proxy.OrderServiceImplProxy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class DbRouteTest {

    public static void main(String[] args) throws ParseException {

        OrderService service = new OrderServiceImpl();
        OrderService orderService = new OrderServiceImplProxy(service);
        Order order = new Order();
        order.setCreatetime(new Date().getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2017-10-12");
        order.setCreatetime(date.getTime());



        orderService.createOrder(order);

    }
}
