package com.forever.proxyPattern.staticProxy.dbRoute.dbSource;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class DynamicDataSourceEntity {

    private static final String DEFAULT_SOURCE = null;

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    private DynamicDataSourceEntity(){}

    public static String get(){
        return local.get();
    }

    public static void set(String source){
        local.set(source);
    }

    public static void set(Integer year){
        local.set("DB_"+year);
    }

    public static void reset(){
        local.set(DEFAULT_SOURCE);
    }

}
