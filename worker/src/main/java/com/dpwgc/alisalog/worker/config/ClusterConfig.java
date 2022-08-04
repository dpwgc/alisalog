package com.dpwgc.alisalog.worker.config;

import com.dpwgc.alisalog.worker.cluster.Heartbeat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ClusterConfig implements InitializingBean {

    @Value("${udp.port}")
    private String udpPort;
    public static String UDP_PORT;

    @Value("${server.port}")
    private String httpPort;
    public static String HTTP_PORT;

    @Value("${heartbeat.address}")
    private String address;
    public static String ADDRESS;

    @Value("${heartbeat.cycle}")
    private Integer cycle;
    public static Integer CYCLE;

    @Value("${heartbeat.router.url}")
    private String url;
    public static String URL;

    @Resource
    Heartbeat heartbeat;

    @Override
    public void afterPropertiesSet() {
        UDP_PORT = udpPort;
        HTTP_PORT = httpPort;
        ADDRESS = address;
        CYCLE = cycle;
        URL = url;

        heartbeat.start();
    }
}
