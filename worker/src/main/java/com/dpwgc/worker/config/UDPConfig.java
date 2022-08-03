package com.dpwgc.worker.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * UDP服务配置
 */
@Configuration
public class UDPConfig implements InitializingBean {

    @Value("${udp.port}")
    private int udpPort;

    @Value("${udp.maxDataSize}")
    private int udpMaxDataSize;

    public static int UDP_PORT;
    public static int UDP_MAX_DATA_SIZE;

    /**
     * spring boot项目启动后自动执行
     */
    @Override
    public void afterPropertiesSet() {
        //将配置文件中的信息加载到静态变量中
        UDP_PORT = udpPort;
        UDP_MAX_DATA_SIZE = udpMaxDataSize;
    }
}
