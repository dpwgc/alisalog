package com.dpwgc.alisalog.worker.store;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

@Component
public class LogStore2DB {

    @Resource
    LogServiceImpl logService;

    public void save(List<LogModel> logModelList) {
        logService.saveBatch(logModelList);
    }
}