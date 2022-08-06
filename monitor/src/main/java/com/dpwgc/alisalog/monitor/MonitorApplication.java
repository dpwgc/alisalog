package com.dpwgc.alisalog.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.dpwgc.alisalog.monitor.mapper")
@ComponentScan("com.dpwgc.alisalog.common.util")
@ComponentScan("com.dpwgc.alisalog.monitor.config")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

}
