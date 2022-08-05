package com.dpwgc.alisalog.client.buffer;

import com.dpwgc.alisalog.common.model.LogBatchSub;

/**
 * 缓存队列生产者
 */
public class BufferProducer {

    /**
     * 发布消息
     * @param logBatchSub 日志信息
     */
    public static void publish(LogBatchSub logBatchSub) {
        //将日志信息压缩，再插入本地缓冲队列
        BufferQueue.add(logBatchSub);
    }
}
