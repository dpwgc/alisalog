package com.dpwgc.worker.store;

import com.dpwgc.common.util.IdGenUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class LogStore2DB {

    @Resource
    LogMapper logMapper;

    public void save(List<LogModel> logModelList) {
        for (LogModel logModel : logModelList) {
            logModel.setId(IdGenUtil.uuid());
            logMapper.insert(logModel);
        }
    }
}