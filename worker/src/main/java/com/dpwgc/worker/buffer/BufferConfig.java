package com.dpwgc.worker.buffer;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class BufferConfig implements InitializingBean {

    //缓冲区消费者线程数量
    @Value("${buffer.consumer.number}")
    private int consumerNumber;
    public static int CONSUMER_NUMBER;

    @Override
    public void afterPropertiesSet() {
        //读取消费者配置
        CONSUMER_NUMBER = consumerNumber;
    }
}
