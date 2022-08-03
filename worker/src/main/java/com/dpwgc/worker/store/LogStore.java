package com.dpwgc.worker.store;

import com.dpwgc.worker.component.ESClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LogStore {

    @Resource
    ESClient esClient;

    public void save(LogStoreModel logStoreModel) {
        esClient.create(logStoreModel);
    }
}
