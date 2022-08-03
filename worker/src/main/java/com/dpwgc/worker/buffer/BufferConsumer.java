package com.dpwgc.worker.buffer;

import com.dpwgc.common.util.GzipUtil;
import com.dpwgc.common.util.JsonUtil;
import com.dpwgc.common.util.LogUtil;
import com.dpwgc.worker.store.LogStore;
import com.dpwgc.worker.udp.LogIn;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class BufferConsumer {

    @Resource
    LogStore logStore;

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
                    LogIn logIn = JsonUtil.fromJson(log, LogIn.class);
                    //将日志信息写入chickhouse
                    logStore.save(logIn.getLogs());
                } catch (Exception e) {
                    LogUtil.error("buffer consume error",e.toString());
                }
            }
        }
    }
}
