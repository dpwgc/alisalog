package com.dpwgc.router.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 路由中心本地缓存配置
 * （存储节点列表）
 */
@Configuration
public class CacheConfig implements InitializingBean {

    @Value("${heartbeat.timeout}")
    private int timeout;
    public static int TIMEOUT;

    public static ConcurrentHashMap<String,Long> CACHE_MAP;

    public void afterPropertiesSet() {
        CACHE_MAP = new ConcurrentHashMap<>();
        TIMEOUT = timeout;
    }
}
