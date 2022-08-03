package com.dpwgc.worker.config;

import com.dpwgc.common.util.LogUtil;
import com.dpwgc.worker.buffer.BufferConsumer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class BufferConfig implements InitializingBean {

    //缓冲区消费者线程数量
    @Value("${buffer.consumer.number}")
    private int consumerNumber;
    public static int CONSUMER_NUMBER;

    @Resource
    BufferConsumer bufferConsumer;

    @Override
    public void afterPropertiesSet() {
        //读取消费者配置
        CONSUMER_NUMBER = consumerNumber;

        for(int i = 0; i< BufferConfig.CONSUMER_NUMBER; i++) {
            //启动消费者线程
            new Thread(bufferConsumer::consume).start();
            LogUtil.info("buffer consumer thread","buffer consumer ["+i+"] start");
        }
    }
}
