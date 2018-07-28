package com.prod.springboot.prod.utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * @author zhanghesheng
 * @Description jdbc连接配置类.
 */

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.prod"})
public class DaoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(DaoConfiguration.class);
    //@Autowired
    //DaoProperties daoProperties;

    @Bean(name = "dataSource")
    @Qualifier(value = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "mysql.c3p0")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    }



    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "prodTemplate")
    public JdbcTemplate prodTemplate() {
        DataSource dataSource = this.dataSource();
        return new JdbcTemplate(this.dataSource());
    }


}