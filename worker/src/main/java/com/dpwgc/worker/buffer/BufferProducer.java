package com.dpwgc.worker.buffer;

import com.dpwgc.common.util.GzipUtil;
import com.dpwgc.common.util.JsonUtil;
import com.dpwgc.worker.input.LogInput;

/**
 * 缓存队列生产者
 */
public class BufferProducer {

    /**
     * 发布消息
     * @param logInput 日志信息
     */
    public static void publish(byte[] logInput) {
        //将日志信息压缩，再插入本地缓冲队列
        BufferQueue.add(GzipUtil.compress(logInput));
    }

    /**
     * 发布消息
     * @param logInput 日志信息
     */
    public static void publish(LogInput logInput) {
        //将日志信息压缩，再插入本地缓冲队列
        BufferQueue.add(GzipUtil.compress(JsonUtil.toJson(logInput)));
    }
}
