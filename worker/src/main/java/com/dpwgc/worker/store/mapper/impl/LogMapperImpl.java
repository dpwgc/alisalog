package com.dpwgc.worker.store.mapper.impl;

import com.dpwgc.common.model.LogMessageIn;
import com.dpwgc.worker.store.component.ESClient;
import com.dpwgc.worker.store.mapper.LogMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LogMapperImpl implements LogMapper {

    @Resource
    ESClient esClient;

    @Override
    public void save(LogMessageIn logMessageIn) {
        esClient.insert(logMessageIn);
    }
}
