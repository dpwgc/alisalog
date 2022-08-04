package com.dpwgc.console.config;

import com.dpwgc.common.util.JsonUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 控制台本地缓存
 * （存储用户登陆token）
 */
@Configuration
public class CacheConfig implements InitializingBean {

    private static ConcurrentHashMap<String,String> CACHE_MAP;

    @Value("${cache.timeout}")
    private Integer timeout;
    private static Integer TIMEOUT;

    public void afterPropertiesSet() {
        CACHE_MAP = new ConcurrentHashMap<>();
        TIMEOUT = timeout;
    }

    /**
     * 插入缓存
     */
    public static void set(String key,String value) {

        Map<String,Object> map = new HashMap<>();
        Long time = System.currentTimeMillis()/1000;
        map.put("value",value);
        map.put("time",time);
        CACHE_MAP.put(key, JsonUtil.toJson(map));
    }

    /**
     * 读取缓存
     */
    public static String get(String key) {
        if (key == null) {
            return null;
        }
        String json = CACHE_MAP.get(key);
        if (json == null) {
            return null;
        }
        Map map = JsonUtil.fromJson(json,Map.class);

        //比对时间
        Long time = Long.parseLong(map.get("time").toString());
        Long now = System.currentTimeMillis()/1000;

        //超时删除
        if (now - time > TIMEOUT) {
            CACHE_MAP.remove(key);
            return null;
        }

        return map.get("value").toString();
    }
}
