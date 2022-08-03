package com.dpwgc.worker.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {
    @Value("${spring.datasource.click.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.click.url}")
    private String url;
    @Value("${spring.datasource.click.username}")
    private String username;
    @Value("${spring.datasource.click.password}")
    private String password;
    @Value("${spring.datasource.click.initialSize}")
    private String initialSize;
    @Value("${spring.datasource.click.maxActive}")
    private Integer maxActive;
    @Value("${spring.datasource.click.minIdle}")
    private Integer minIdle;
    @Value("${spring.datasource.click.maxWait}")
    private Integer maxWait;

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource dataSource = (DruidDataSource) DataSourceBuilder
                .create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
        dataSource.setMaxWait(maxWait);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        // 设置druid 连接池非公平锁模式
        dataSource.setUseUnfairLock(true);
        return dataSource;
    }
}
