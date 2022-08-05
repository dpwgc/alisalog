package com.dpwgc.alisalog.client.buffer;

import com.dpwgc.alisalog.client.log.SendLog;
import com.dpwgc.alisalog.common.model.LogBatch;
import com.dpwgc.alisalog.common.model.LogBatchSub;
import com.dpwgc.alisalog.common.util.LogUtil;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

/**
 * 缓冲队列消费者 TODO
 */
@Component
public class BufferConsumer {

    /**
     * 消费者服务
     */
    public void consume(int consumerBatch) {
        while (true) {
            batchSend(consumerBatch);
        }
    }

    public void batchSend(int consumerBatch) {

        LogBatch logBatch = new LogBatch();
        logBatch.setLogs(new ArrayList<>());

        //批量取出缓冲队列中的日志列表
        for (int i = 0; i< consumerBatch; i++) {
            //从缓冲区中取出日志列表
            LogBatchSub logBatchSub = BufferQueue.poll();
            if(logBatchSub != null) {
                try {
                    logBatch.getLogs().add(logBatchSub);
                } catch (Exception e) {
                    LogUtil.error("buffer consume error",e.toString());
                }
            } else {
                break;
            }
        }
        // TODO
        SendLog.sendLogByUdp("",0,logBatch);
    }
}
