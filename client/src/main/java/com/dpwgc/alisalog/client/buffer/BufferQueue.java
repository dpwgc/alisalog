package com.dpwgc.alisalog.client.buffer;

import com.dpwgc.alisalog.client.config.ClientConfig;
import com.dpwgc.alisalog.common.model.LogBatchSub;
import com.dpwgc.alisalog.common.util.GzipUtil;
import com.dpwgc.alisalog.common.util.JsonUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 本地缓冲队列消费者服务
 */
@Component
public class BufferQueue {

    @Resource
    BufferConsumer bufferConsumer;

    //缓冲区
    protected static ConcurrentLinkedQueue<String> BUFFER;

    protected static Boolean add(LogBatchSub logBatchSub) {
        return BUFFER.add(GzipUtil.compress(JsonUtil.toJson(logBatchSub)));
    }

    protected static LogBatchSub poll() {
        String data = BUFFER.poll();
        if (data == null) {
            return null;
        }
        return JsonUtil.fromJson(GzipUtil.uncompress(data),LogBatchSub.class);
    }

    public void start(ClientConfig clientConfig) {
        //初始化缓冲区
        BUFFER = new ConcurrentLinkedQueue<>();

        for(int i=0;i<clientConfig.getConsumerNumber();i++) {
            bufferConsumer.consume(clientConfig);
        }
    }
}
