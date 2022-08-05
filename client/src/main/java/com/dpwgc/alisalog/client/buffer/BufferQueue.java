package com.dpwgc.alisalog.client.buffer;

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
    public static ConcurrentLinkedQueue<String> BUFFER;

    public static Boolean add(LogBatchSub logBatchSub) {
        return BUFFER.add(GzipUtil.compress(JsonUtil.toJson(logBatchSub)));
    }

    public static LogBatchSub poll() {
        String data = BUFFER.poll();
        if (data == null) {
            return null;
        }
        return JsonUtil.fromJson(GzipUtil.uncompress(data),LogBatchSub.class);
    }

    public void start(int consumerNumber,int consumerBatch) {
        //初始化缓冲区
        BUFFER = new ConcurrentLinkedQueue<>();

        for(int i=0;i<consumerNumber;i++) {
            bufferConsumer.consume(consumerBatch);
        }
    }
}
