package com.forever.proxyPattern.staticProxy.dbRoute;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class Order {

    private Object OrderInfo;

    //以时间作为分库依据
    //DB_2017   DB_2018
    private Long createtime;
    private Long id;

    public Object getOrderInfo() {
        return OrderInfo;
    }

    public void setOrderInfo(Object orderInfo) {
        OrderInfo = orderInfo;
    }

    public Long getCreateTime() {
        return createtime;
    }

    public void setCreateTime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
