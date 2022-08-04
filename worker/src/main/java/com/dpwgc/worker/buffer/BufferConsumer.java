package com.dpwgc.worker.buffer;

import com.dpwgc.common.util.GzipUtil;
import com.dpwgc.common.util.JsonUtil;
import com.dpwgc.common.util.LogUtil;
import com.dpwgc.worker.store.LogStore2DB;
import com.dpwgc.worker.input.LogInput;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 缓冲队列消费者
 */
@Component
public class BufferConsumer {

    @Resource
    LogStore2DB logStore2DB;

    /**
     * 消费者服务
     */
    public void consume() {
        while (true) {
            //从缓冲区中取出压缩日志信息
            String zipLog = BufferQueue.poll();
            if(zipLog != null) {
                try {
                    //解压
                    String log = GzipUtil.uncompress(zipLog);
                    LogInput logInput = JsonUtil.fromJson(log, LogInput.class);
                    //将日志信息写入chickhouse
                    logStore2DB.save(logInput.getLogs());
                } catch (Exception e) {
                    LogUtil.error("buffer consume error",e.toString());
                }
            }
        }
    }
}
