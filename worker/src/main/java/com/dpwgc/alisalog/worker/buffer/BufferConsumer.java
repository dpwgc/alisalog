package com.dpwgc.alisalog.worker.buffer;

import com.dpwgc.alisalog.common.util.LogUtil;
import com.dpwgc.alisalog.worker.config.BufferConfig;
import com.dpwgc.alisalog.common.model.LogBatch;
import com.dpwgc.alisalog.worker.input.LogBatch2Model;
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
            //从缓冲区中取出日志列表
            LogBatch logBatch = BufferQueue.poll();
            if(logBatch != null) {
                try {
                    //将LogBatch批量日志信息展开，转换成LogModel列表，然后聚和多批次日志列表
                    logModelList.addAll(LogBatch2Model.assembler(logBatch));
                } catch (Exception e) {
                    LogUtil.error("buffer consume error",e.toString());
                }
            } else {
                break;
            }
        }
        if (logModelList.size() == 0) {
            return;
        }
        //将日志信息写入chickhouse
        logStore2DB.save(logModelList);
    }
}
