package com.prod.springboot.prod.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "mysql.c3p0")
@Data
public class DaoProperties {


   /* @Value("${mysql.c3p0.url}")
    private String url;
    @Value("${mysql.c3p0.user}")
    private String user;
    @Value("${mysql.c3p0.password:root}")
    private String password;

    @Value("${mysql.c3p0.maxPoolSize:30}")
    private int maxPoolSize;
    @Value("${mysql.c3p0.minPoolSize:10}")
    private int minPoolSize;
    @Value("${mysql.c3p0.autoCommitOnClose:false}")
    private boolean autoCommitOnClose;
    @Value("${mysql.c3p0.checkoutTimeout:10000}")
    private long checkoutTimeout;

    @Value("${mysql.c3p0.acquireRetryAttempts:2}")
    private int acquireRetryAttempts;*/

}
