package com.dpwgc.console.mapper.impl;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dpwgc.common.base.PageBase;
import com.dpwgc.console.model.LogMessage;
import com.dpwgc.console.assembler.Hit2LogAssembler;
import com.dpwgc.console.component.ESClient;
import com.dpwgc.console.mapper.LogMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class LogMapperImpl implements LogMapper {

    @Resource
    ESClient esClient;

    @Resource
    Hit2LogAssembler hit2LogAssembler;

    @Override
    public PageBase<List<LogMessage>> search(String indexName, String keyword, Integer pageIndex, Integer pageSize) {
        PageBase<List<Hit<Object>>> pageBase = esClient.searchByKeyword(indexName, keyword, pageIndex, pageSize);
        return PageBase.getPageBase(pageBase.getTotal(),hit2LogAssembler.assemblerLogMessageOutList(pageBase.getList()));
    }
}
