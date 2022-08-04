package com.dpwgc.monitor.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 控制台本地缓存配置
 * （存储用户登陆token）
 */
@Configuration
public class CacheConfig implements InitializingBean {

    public static ConcurrentHashMap<String,String> CACHE_MAP;

    @Value("${cache.timeout}")
    private Integer timeout;
    public static Integer TIMEOUT;

    public void afterPropertiesSet() {
        CACHE_MAP = new ConcurrentHashMap<>();
        TIMEOUT = timeout;
    }
}
