package com.dpwgc.alisalog.worker.input;

import com.dpwgc.alisalog.common.model.LogBatch;
import com.dpwgc.alisalog.common.model.LogBatchSub;
import com.dpwgc.alisalog.common.util.IdGenUtil;
import com.dpwgc.alisalog.worker.store.LogModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogAssembler {

    /**
     * LogBatch转LogModel
     */
    public static List<LogModel> assembler(LogBatch logBatch) {

        Date nowDate = new Date();

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

            if (logBatchSub.getLogTime() == null || logBatchSub.getLogTime() == 0) {
                logModel.setLogTime(System.currentTimeMillis());
            }

            logModel.setStoreTime(nowDate);

            logModelList.add(logModel);
        }
        return logModelList;
    }
}
