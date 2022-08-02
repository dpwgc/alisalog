package com.dpwgc.worker.buffer;

import com.dpwgc.common.model.LogMessageIn;
import com.dpwgc.common.util.GzipUtil;
import com.dpwgc.common.util.JsonUtil;
import com.dpwgc.common.util.LogUtil;
import com.dpwgc.worker.store.mapper.LogMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class BufferConsumer implements InitializingBean {

    @Resource
    LogMapper logMapper;

    @Override
    public void afterPropertiesSet() {

        for(int i = 0; i< BufferConfig.CONSUMER_NUMBER; i++) {
            //启动消费者线程
            new Thread(this::consume).start();
            LogUtil.info("buffer consumer ["+i+"] start");
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
                    logMapper.save(JsonUtil.fromJson(log, LogMessageIn.class));
                } catch (Exception e) {
                    LogUtil.error("consume error: "+e);
                }
            }
        }
    }
}
