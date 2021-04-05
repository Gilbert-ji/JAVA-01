package com.example.mybatis_demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @desc: 从库数据源
 * @author: biao
 * @create: 2021-04-04 18:19
 **/
@Configuration
@MapperScan(basePackages = "com.example.mybatis_demo.mapper.slave" ,sqlSessionFactoryRef ="slaveSqlSessionFactory" )
public class SlaveDsConfig {

    private static final String MAPPER_LOCATION = "classpath:mapper/slave/*.xml";

    private static final String ENTITY_PACKAGE = "com.example.mybatis_demo.bean";

    @Value("${spring.datasource.slave.url}")
    private String url;

    @Value("${spring.datasource.slave.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.slave.username}")
    private String userName;

    @Value("${spring.datasource.slave.password}")
    private String password;

    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.url);
        datasource.setUsername(userName);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        return datasource;
    }

    @Bean(name = "slaveDataSourceTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(slaveDataSource());
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        sqlSessionFactoryBean.setTypeAliasesPackage(ENTITY_PACKAGE);
        //mybatis数据库字段与实体类字段驼峰映射配置
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

}
