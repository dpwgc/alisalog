package com.dpwgc.alisalog.router.cache;

import com.dpwgc.alisalog.common.util.JsonUtil;
import com.dpwgc.alisalog.router.config.CacheConfig;
import com.dpwgc.alisalog.common.model.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 路由中心本地缓存
 * （存储节点列表）
 */
public class Cache {

    /**
     * 插入缓存
     */
    public static void set(String key,Long value) {

        CacheConfig.CACHE_MAP.put(key, value);
    }

    /**
     * 遍历缓存
     */
    public static List<Node> list() {

        List<Node> nodes = new ArrayList<>();

        //遍历当前在线的会话key列表
        for (String node: CacheConfig.CACHE_MAP.keySet()) {

            //根据key获取value
            Long ts = CacheConfig.CACHE_MAP.get(node);

            //如果value为空
            if (ts == null) {
                CacheConfig.CACHE_MAP.remove(node);
                continue;
            }

            //如果该服务节点心跳超时，则删除该节点
            if (System.currentTimeMillis()/1000 > ts+CacheConfig.TIMEOUT) {
                CacheConfig.CACHE_MAP.remove(node);
                continue;
            }
            nodes.add(JsonUtil.fromJson(node,Node.class));
        }
        return nodes;
    }
}
