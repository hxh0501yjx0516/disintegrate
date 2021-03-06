package com.tieshan.disintegrate.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * @description: 配置数据源
 * @author: huxuanhua
 * @date: Created in 2019/8/28 18:07
 * @version: 1.0
 * @modified By:Leavonson
 */
@Configuration
@MapperScan(basePackages = "com.tieshan.disintegrate.dao", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DatabaseADConfiguration {
//    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource setDataSource() {
        return new DruidDataSource();
    }

 /**
 * 多数据源下Mybatis驼峰映射配置,若无此配置，驼峰字段会出现NULL值
 * */
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration configuration(){
        return new org.apache.ibatis.session.Configuration();
    }

//    @Primary
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource,org.apache.ibatis.session.Configuration configuration) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(configuration);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}