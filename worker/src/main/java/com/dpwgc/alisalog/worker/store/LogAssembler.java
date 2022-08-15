package com.dpwgc.alisalog.worker.store;

import com.dpwgc.alisalog.common.model.LogBatch;
import com.dpwgc.alisalog.common.model.LogBatchSub;
import com.dpwgc.alisalog.common.util.IdGenUtil;
import java.util.ArrayList;
import java.util.List;

public class LogAssembler {

    /**
     * LogBatch转LogModel
     */
    public static List<LogModel> assembler(LogBatch logBatch) {

        //获取当前秒级时间戳
        Long nowTime = System.currentTimeMillis()/1000;

        List<LogModel> logModelList = new ArrayList<>();
        List<LogBatchSub> logBatchSubList = logBatch.getLogs();

        for (LogBatchSub logBatchSub : logBatchSubList) {
            LogModel logModel = new LogModel();

            //生成id
            logModel.setId(IdGenUtil.uuid());

            logModel.setIdc(logBatch.getIdc());
            logModel.setHost(logBatch.getHost());
            logModel.setEnv(logBatch.getEnv());
            logModel.setAppId(logBatch.getAppId());

            logModel.setId(logBatchSub.getId());
            logModel.setTraceId(logBatchSub.getTraceId());
            logModel.setModule(logBatchSub.getModule());
            logModel.setCategory(logBatchSub.getCategory());
            logModel.setSubCategory(logBatchSub.getSubCategory());
            logModel.setFilter1(logBatchSub.getFilter1());
            logModel.setFilter2(logBatchSub.getFilter2());
            logModel.setFile(logBatchSub.getFile());
            logModel.setPosition(logBatchSub.getPosition());
            logModel.setLevel(logBatchSub.getLevel());
            logModel.setTag(logBatchSub.getTag());
            logModel.setTitle(logBatchSub.getTitle());
            logModel.setContent(logBatchSub.getContent());
            logModel.setRemarks(logBatchSub.getRemarks());

            //如果没有日志记录时间，则将当前时间戳插入
            if (logBatchSub.getLogTime() == null || logBatchSub.getLogTime() == 0) {
                logModel.setLogTime(System.currentTimeMillis());
            } else {
                logModel.setLogTime(logBatchSub.getLogTime());
            }

            logModel.setStoreTime(nowTime);

            logModelList.add(logModel);
        }
        return logModelList;
    }
}
