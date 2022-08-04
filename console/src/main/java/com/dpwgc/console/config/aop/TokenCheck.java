package com.dpwgc.console.config.aop;

import com.dpwgc.console.config.CacheConfig;

public class TokenCheck {

    public static Boolean check(String username,String token) {
        if (username == null && token == null) {
            return false;
        }
        return token.equals(CacheConfig.get(username));
    }
}
