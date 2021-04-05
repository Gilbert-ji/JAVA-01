package com.example.mybatis_demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @desc: 主数据源配置
 * @author: biao
 * @create: 2021-04-04 18:02
 **/
@Configuration
@MapperScan(basePackages = "com.example.mybatis_demo.mapper.master", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDsConfig {

    private static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    private static final String ENTITY_PACKAGE = "com.example.mybatis_demo.bean";

    @Value("${spring.datasource.master.url}")
    private String url;

    @Value("${spring.datasource.master.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.master.username}")
    private String userName;

    @Value("${spring.datasource.master.password}")
    private String password;

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(this.url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean(name = "masterDataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
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
