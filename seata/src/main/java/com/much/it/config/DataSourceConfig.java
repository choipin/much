package com.much.it.config;

import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
@Configuration
public class DataSourceConfig {
/*
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    @Primary
    @Bean("datasource")
    public DataSourceProxy dataSourceProxy(DataSource dataSource){
        return new DataSourceProxy(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("\"classpath:mappers/*.xml\""));
        return sqlSessionFactoryBean.getObject();
    }*/
}
