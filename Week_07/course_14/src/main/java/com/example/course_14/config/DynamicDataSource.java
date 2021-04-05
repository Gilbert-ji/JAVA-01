package com.example.course_14.config;

import com.example.course_14.aop.DataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-13 13:01
 **/
@Component
@Primary
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    @Autowired
    @Qualifier("slaveDataSource1")
    private DataSource slaveDataSource1;

    @Autowired
    @Qualifier("slaveDataSource2")
    private DataSource slaveDataSource2;

    /**
     * 这个是主要的方法，返回的是生效的数据源名称
     */
    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("DataSourceContextHolder: "+ DataSourceContextHolder.getDbType());
        return DataSourceContextHolder.getDbType();
    }

    @Override
    public void afterPropertiesSet() {
        Map<Object,Object> map = new HashMap<>();
        map.put("slaveDataSource1",slaveDataSource1);
        map.put("slaveDataSource2",slaveDataSource2);
        map.put("masterDataSource",masterDataSource);
        setTargetDataSources(map);
        setDefaultTargetDataSource(masterDataSource);
        super.afterPropertiesSet();
    }
}
