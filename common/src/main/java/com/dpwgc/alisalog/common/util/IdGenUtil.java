package com.dpwgc.alisalog.common.util;

import java.security.SecureRandom;
import java.util.UUID;

public class IdGenUtil {
    private static final SecureRandom random = new SecureRandom();

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

}
