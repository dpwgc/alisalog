package com.dpwgc.alisalog.monitor.config.aop;

import com.dpwgc.alisalog.monitor.cache.Cache;

public class TokenCheck {

    public static Boolean check(String username,String token) {
        if (username == null && token == null) {
            return false;
        }
        return token.equals(Cache.get(username));
    }
}
