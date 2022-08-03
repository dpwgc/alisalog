package com.dpwgc.worker.buffer;

import com.dpwgc.worker.store.LogStoreModel;
import com.dpwgc.common.util.GzipUtil;
import com.dpwgc.common.util.JsonUtil;
import com.dpwgc.common.util.LogUtil;
import com.dpwgc.worker.config.BufferConfig;
import com.dpwgc.worker.store.LogStore;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class BufferConsumer implements InitializingBean {

    @Resource
    LogStore logStore;

    @Override
    public void afterPropertiesSet() {

        for(int i = 0; i< BufferConfig.CONSUMER_NUMBER; i++) {
            //启动消费者线程
            new Thread(this::consume).start();
            LogUtil.info("buffer consumer thread","buffer consumer ["+i+"] start");
        }
    }

    /**
     * 消费者服务
     */
    public void consume() {
        while (true) {
            //从缓冲区中取出压缩日志信息
            String zipLog = Buffer.poll();
            if(zipLog != null) {
                try {
                    //解压
                    String log = GzipUtil.uncompress(zipLog);
                    //将日志信息写入es
                    logStore.save(JsonUtil.fromJson(log, LogStoreModel.class));
                } catch (Exception e) {
                    LogUtil.error("buffer consume error",e.toString());
                }
            }
        }
    }
}
