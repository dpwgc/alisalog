package com.dpwgc.console.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dpwgc.console.base.PageBase;
import com.dpwgc.console.model.LogMessage;
import com.dpwgc.console.mapper.LogMapper;
import com.dpwgc.console.model.QueryLogMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogService {

    @Resource
    LogMapper logMapper;

    public PageBase<List<LogMessage>> queryLog(QueryLogMessage queryLogMessage) {

        //分页
        Page<LogMessage> page = new Page<>(queryLogMessage.getPageIndex(), queryLogMessage.getPageSize());

        //查询
        QueryWrapper<LogMessage> queryWrapper = new QueryWrapper<>();

        //检索条件
        if (queryLogMessage.getIdc().length() > 0){
            queryWrapper.eq("idc", queryLogMessage.getIdc());
        }
        if (queryLogMessage.getHost().length() > 0){
            queryWrapper.eq("host", queryLogMessage.getHost());
        }
        if (queryLogMessage.getEnv().length() > 0){
            queryWrapper.eq("env", queryLogMessage.getEnv());
        }
        if (queryLogMessage.getAppId().length() > 0){
            queryWrapper.eq("app_id", queryLogMessage.getAppId());
        }
        if (queryLogMessage.getTraceId().length() > 0){
            queryWrapper.eq("trace_id", queryLogMessage.getTraceId());
        }
        if (queryLogMessage.getModule().length() > 0){
            queryWrapper.eq("module", queryLogMessage.getModule());
        }
        if (queryLogMessage.getCategory().length() > 0){
            queryWrapper.eq("category", queryLogMessage.getCategory());
        }
        if (queryLogMessage.getSubCategory().length() > 0){
            queryWrapper.eq("sub_category", queryLogMessage.getSubCategory());
        }
        if (queryLogMessage.getFilter1().length() > 0){
            queryWrapper.eq("filter1", queryLogMessage.getFilter1());
        }
        if (queryLogMessage.getFilter2().length() > 0){
            queryWrapper.eq("filter2", queryLogMessage.getFilter2());
        }
        if (queryLogMessage.getLevel() > 0){
            queryWrapper.eq("level", queryLogMessage.getLevel());
        }
        if (queryLogMessage.getKeyword().length() > 0){
            queryWrapper.like("title", queryLogMessage.getKeyword());
            queryWrapper.like("content", queryLogMessage.getKeyword());
        }
        queryWrapper.ge("log_time", queryLogMessage.getLogTimeStart());
        queryWrapper.lt("log_time", queryLogMessage.getLogTimeEnd());

        //装配返回
        Page<LogMessage> logMessagePage = logMapper.selectPage(page,queryWrapper);
        PageBase<List<LogMessage>> pageBase = new PageBase<>();
        pageBase.setList(logMessagePage.getRecords());
        pageBase.setTotal(logMessagePage.getTotal());
        return pageBase;
    }
}
