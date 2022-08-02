package com.dpwgc.worker.buffer;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 本地缓冲队列消费者服务
 */
@Component
public class Buffer implements InitializingBean {

    //缓冲区
    public static ConcurrentLinkedQueue<String> BUFFER;

    public static Boolean add(String e) {
        return BUFFER.add(e);
    }

    public static String poll() {
        return BUFFER.poll();
    }

    @Override
    public void afterPropertiesSet() {
        //初始化缓冲区
        BUFFER = new ConcurrentLinkedQueue<>();
    }
}
