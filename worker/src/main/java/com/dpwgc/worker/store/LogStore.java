package com.dpwgc.worker.store;

import com.dpwgc.worker.component.WorkerESClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LogStore {

    @Resource
    WorkerESClient workerEsClient;

    public void save(LogStoreModel logStoreModel) {
        workerEsClient.create(logStoreModel);
    }
}
