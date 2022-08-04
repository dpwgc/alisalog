package com.dpwgc.monitor.cache;

import com.dpwgc.common.util.JsonUtil;
import com.dpwgc.monitor.config.CacheConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制台本地缓存
 * （存储用户登陆token）
 */
public class Cache {

    /**
     * 插入缓存
     */
    public static void set(String key,String value) {

        Map<String,Object> map = new HashMap<>();
        Long time = System.currentTimeMillis()/1000;
        map.put("value",value);
        map.put("time",time);
        CacheConfig.CACHE_MAP.put(key, JsonUtil.toJson(map));
    }

    /**
     * 读取缓存
     */
    public static String get(String key) {
        if (key == null) {
            return null;
        }
        String json = CacheConfig.CACHE_MAP.get(key);
        if (json == null) {
            return null;
        }
        Map map = JsonUtil.fromJson(json,Map.class);

        //比对时间
        Long time = Long.parseLong(map.get("time").toString());
        Long now = System.currentTimeMillis()/1000;

        //超时删除
        if (now - time > CacheConfig.TIMEOUT) {
            CacheConfig.CACHE_MAP.remove(key);
            return null;
        }

        return map.get("value").toString();
    }
}
