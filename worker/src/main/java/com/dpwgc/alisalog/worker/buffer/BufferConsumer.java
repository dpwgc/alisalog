package com.dpwgc.alisalog.worker.buffer;

import com.dpwgc.alisalog.common.util.GzipUtil;
import com.dpwgc.alisalog.common.util.JsonUtil;
import com.dpwgc.alisalog.common.util.LogUtil;
import com.dpwgc.alisalog.worker.config.BufferConfig;
import com.dpwgc.alisalog.worker.input.LogInput;
import com.dpwgc.alisalog.worker.store.LogModel;
import com.dpwgc.alisalog.worker.store.LogStore2DB;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
            batchSave();
        }
    }

    public void batchSave() {

        //要写入chickhouse的日志列表（聚合后）
        List<LogModel> logModelList = new ArrayList<>();

        //批量取出缓冲队列中的日志列表
        for (int i = 0; i< BufferConfig.CONSUMER_BATCH; i++) {
            //从缓冲区中取出压缩日志列表
            String zipLogs = BufferQueue.poll();
            if(zipLogs != null) {
                try {
                    //解压日志列表
                    String log = GzipUtil.uncompress(zipLogs);
                    LogInput logInput = JsonUtil.fromJson(log, LogInput.class);
                    //聚和日志列表
                    logModelList.addAll(logInput.getLogs());
                } catch (Exception e) {
                    LogUtil.error("buffer consume error",e.toString());
                }
            } else {
                break;
            }
        }
        //将日志信息写入chickhouse
        logStore2DB.save(logModelList);
    }
}
