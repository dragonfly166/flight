package com.flight.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源实现类
 * @author sunlongfei
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final byte[] lock = new byte[0];

    private static DynamicDataSource instance;

    private DynamicDataSource() {}

    public static synchronized DynamicDataSource getInstance(){
        if(instance == null)
        {
            synchronized (lock){
                if (instance==null)
                {
                    instance = new DynamicDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return "airline1";
    }

    /**
     * 设置默认数据源
     * @param defaultDataSource
     *          默认数据源
     */
    public void setDefaultDataSource(Object defaultDataSource){
        super.setDefaultTargetDataSource(defaultDataSource);
    }
}
