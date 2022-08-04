package com.dpwgc.monitor.config.aop;

import com.dpwgc.monitor.cache.Cache;

public class TokenCheck {

    public static Boolean check(String username,String token) {
        if (username == null && token == null) {
            return false;
        }
        return token.equals(Cache.get(username));
    }
}
