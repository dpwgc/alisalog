package com.dpwgc.alisalog.worker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.dpwgc.worker.store")
@ComponentScan("com.dpwgc.alisalog.worker.config")
@ComponentScan("com.dpwgc.alisalog.worker.buffer")
@ComponentScan("com.dpwgc.alisalog.common.util")
@ServletComponentScan
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class WorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkerApplication.class, args);
    }

}
