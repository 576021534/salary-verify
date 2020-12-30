package com.grid.salary.verify.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @description: 数据源配置类
 * @author: zhangyaofang
 * @date: 2020/03/09 09:15
 * @version: 1.0
 */
@Configuration
public class DatasourceConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DatasourceConfiguration.class);

    @Autowired
    private PaginationInterceptor paginationInterceptor;

    @ConfigurationProperties(prefix ="spring.datasource.druid")
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource mainDataSource() throws SQLException {
        log.info("--------------------DataSource init ---------------------");
        log.debug("-------------------- debug ---------------------");
        log.error("--------------------error---------------------");
        return new DruidDataSource();
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(@Qualifier(value ="mainDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*Mapper.xml"));
        bean.setPlugins(new Interceptor[] {paginationInterceptor});
        return bean;
    }
}
