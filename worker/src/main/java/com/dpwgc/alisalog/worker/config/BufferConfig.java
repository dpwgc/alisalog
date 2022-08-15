package com.dpwgc.alisalog.worker.config;

import com.dpwgc.alisalog.common.util.LogUtil;
import com.dpwgc.alisalog.worker.buffer.BufferConsumer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

@Configuration
public class BufferConfig implements InitializingBean {

    //缓冲区消费者线程数量
    @Value("${buffer.consumer.number}")
    private int consumerNumber;
    @Value("${buffer.consumer.maxPoll}")
    private int consumerMaxPoll;
    @Value("${buffer.consumer.minPoll}")
    private int consumerMinPoll;
    public static int CONSUMER_NUMBER;
    public static int CONSUMER_MAX_POLL;
    public static int CONSUMER_MIN_POLL;

    @Resource
    BufferConsumer bufferConsumer;

    @Override
    public void afterPropertiesSet() {

        //读取消费者配置
        CONSUMER_NUMBER = consumerNumber;
        CONSUMER_MAX_POLL = consumerMaxPoll;
        CONSUMER_MIN_POLL = consumerMinPoll;

        for(int i = 0; i< BufferConfig.CONSUMER_NUMBER; i++) {
            //启动消费者线程
            Thread thread = new Thread(bufferConsumer::consume);
            thread.start();
            LogUtil.info("buffer consumer thread",String.format("%s%s%s%s","buffer consumer [",i,"] start, consumer thread: ",thread.getName()));
        }
    }
}
