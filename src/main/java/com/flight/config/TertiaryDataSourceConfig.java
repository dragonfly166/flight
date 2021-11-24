package com.flight.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库airline3配置
 * @author sunlongfei
 */
@Configuration
@MapperScan(basePackages = "com.flight.mapper.airline3", sqlSessionFactoryRef = "TertiarySqlSessionFactory")
public class TertiaryDataSourceConfig {

    @Bean(name = "TertiaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.tertiary")
    public DataSource getTertiaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "TertiarySqlSessionFactory")
    public SqlSessionFactory tertiarySqlSessionFactory(
        @Qualifier("TertiaryDataSource") DataSource datasource)
        throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        return bean.getObject();
    }

    @Bean("TertiarySqlSessionTemplate")
    public SqlSessionTemplate tertiarySqlSessionTemplate(
        @Qualifier("TertiarySqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}