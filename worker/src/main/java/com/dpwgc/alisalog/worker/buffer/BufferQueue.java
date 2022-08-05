package com.dpwgc.alisalog.worker.buffer;

import com.dpwgc.alisalog.common.model.LogBatch;
import com.dpwgc.alisalog.common.util.GzipUtil;
import com.dpwgc.alisalog.common.util.JsonUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 本地缓冲队列消费者服务
 */
@Component
public class BufferQueue implements InitializingBean {

    //缓冲区
    public static ConcurrentLinkedQueue<String> BUFFER;

    public static Boolean add(String e) {
        return BUFFER.add(e);
    }

    public static LogBatch poll() {

        //判断是否为空
        String data = BUFFER.poll();
        if (data == null) {
            return null;
        }
        //解压并返回
        return JsonUtil.fromJson(GzipUtil.uncompress(data), LogBatch.class);
    }

    @Override
    public void afterPropertiesSet() {
        //初始化缓冲区
        BUFFER = new ConcurrentLinkedQueue<>();
    }
}
