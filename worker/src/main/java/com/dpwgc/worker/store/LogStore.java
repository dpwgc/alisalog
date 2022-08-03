package com.dpwgc.worker.store;

import com.dpwgc.common.util.IdGenUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class LogStore {

    @Resource
    LogMapper logMapper;

    public void save(List<LogStoreModel> logStoreModelList) {
        for (LogStoreModel logStoreModel : logStoreModelList) {
            logStoreModel.setId(IdGenUtil.uuid());
            logMapper.insert(logStoreModel);
        }
    }
}
