package com.dpwgc.console.mapper;

import com.dpwgc.common.base.PageBase;
import com.dpwgc.console.model.LogMessage;

import java.util.List;

public interface LogMapper {

    PageBase<List<LogMessage>> search(String indexName, String keyword, Integer pageIndex, Integer pageSize);
}
