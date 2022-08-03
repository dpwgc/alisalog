package com.dpwgc.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录
 */
public class LogUtil {

    /**
     * 日志记录器对象
     */
    private static final Logger LOGGER= LoggerFactory.getLogger(LogUtil.class);


    public static void info(String title,String content) {
        LOGGER.info(String.format("<title> %s <content> %s",title,content));
    }

    public static void error(String title,String content) {
        LOGGER.error(String.format("<title> %s <content> %s",title,content));
    }

    public static void warn(String title,String content) {
        LOGGER.warn(String.format("<title> %s <content> %s",title,content));
    }

    public static void debug(String title,String content) {
        LOGGER.debug(String.format("<title> %s <content> %s",title,content));
    }

    public static void trace(String title,String content) {
        LOGGER.trace(String.format("<title> %s <content> %s",title,content));
    }
}
