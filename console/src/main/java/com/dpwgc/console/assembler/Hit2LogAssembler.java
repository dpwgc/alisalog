package com.dpwgc.console.assembler;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dpwgc.console.model.LogMessage;
import com.dpwgc.common.util.JsonUtil;
import com.dpwgc.common.util.LogUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 装配器
 */
@Component
public class Hit2LogAssembler {

    /**
     * elasticsearch的Hit对象转Log对象
     */
    public LogMessage assemblerLogMessageOut(Hit<Object> hit) {
        try {
            String hitJson = JsonUtil.toJson(hit.source());
            return JsonUtil.fromJson(hitJson, LogMessage.class);
        } catch (Exception e) {
            LogUtil.error("Hit2LogAssembler.assemblerLogMessageOut error",e.toString());
            return null;
        }
    }

    /**
     * elasticsearch的Hit列表转Log列表
     */
    public List<LogMessage> assemblerLogMessageOutList(List<Hit<Object>> hits) {
        List<LogMessage> logMessageList = new ArrayList<>();
        for (Hit<Object> hit : hits) {
            logMessageList.add(assemblerLogMessageOut(hit));
        }
        return logMessageList;
    }
}
