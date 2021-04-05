package com.example.course_14.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @desc:
 * @author: biao
 * @create: 2021-03-13 12:55
 **/
@Configuration
public class DataSourceConfig {

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveDataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slaveDataSource1(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveDataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slaveDataSource2(){
        return DataSourceBuilder.create().build();
    }

}
