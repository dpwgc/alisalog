package com.dpwgc.alisalog.worker.store;

import com.dpwgc.alisalog.common.util.IdGenUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class LogStore2DB {

    @Resource
    LogServiceImpl logService;

    public void save(List<LogModel> logModelList) {
        for (LogModel logModel : logModelList) {
            logModel.setId(IdGenUtil.uuid());
        }
        logService.saveBatch(logModelList);
    }
}
