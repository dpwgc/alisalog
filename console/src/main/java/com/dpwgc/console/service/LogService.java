package com.dpwgc.console.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dpwgc.console.base.PageBase;
import com.dpwgc.console.model.LogMessage;
import com.dpwgc.console.mapper.LogMapper;
import com.dpwgc.console.model.QueryLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogService {

    @Resource
    LogMapper logMapper;

    public PageBase<List<LogMessage>> queryLog(QueryLog queryLog) {

        if (queryLog.getPageSize() == null) {
            queryLog.setPageSize(0);
        }
        if (queryLog.getPageIndex() == null) {
            queryLog.setPageIndex(0);
        }

        //分页
        Page<LogMessage> page = new Page<>(queryLog.getPageIndex(), queryLog.getPageSize());

        //查询
        QueryWrapper<LogMessage> queryWrapper = new QueryWrapper<>();

        //检索条件
        if (queryLog.getIdc() == null || queryLog.getIdc().length() > 0){
            queryWrapper.eq("idc", queryLog.getIdc());
        }
        if (queryLog.getHost() == null || queryLog.getHost().length() > 0){
            queryWrapper.eq("host", queryLog.getHost());
        }
        if (queryLog.getEnv() == null || queryLog.getEnv().length() > 0){
            queryWrapper.eq("env", queryLog.getEnv());
        }
        if (queryLog.getAppId() == null || queryLog.getAppId().length() > 0){
            queryWrapper.eq("app_id", queryLog.getAppId());
        }
        if (queryLog.getTraceId() == null || queryLog.getTraceId().length() > 0){
            queryWrapper.eq("trace_id", queryLog.getTraceId());
        }
        if (queryLog.getModule() == null || queryLog.getModule().length() > 0){
            queryWrapper.eq("module", queryLog.getModule());
        }
        if (queryLog.getCategory() == null || queryLog.getCategory().length() > 0){
            queryWrapper.eq("category", queryLog.getCategory());
        }
        if (queryLog.getSubCategory() == null || queryLog.getSubCategory().length() > 0){
            queryWrapper.eq("sub_category", queryLog.getSubCategory());
        }
        if (queryLog.getFilter1() == null || queryLog.getFilter1().length() > 0){
            queryWrapper.eq("filter1", queryLog.getFilter1());
        }
        if (queryLog.getFilter2() == null || queryLog.getFilter2().length() > 0){
            queryWrapper.eq("filter2", queryLog.getFilter2());
        }
        if (queryLog.getLevel() == null || queryLog.getLevel() > 0){
            queryWrapper.eq("level", queryLog.getLevel());
        }
        if (queryLog.getKeyword() == null || queryLog.getKeyword().length() > 0){
            queryWrapper.like("title", queryLog.getKeyword());
            queryWrapper.like("content", queryLog.getKeyword());
        }
        queryWrapper.ge("log_time", queryLog.getLogTimeStart());
        queryWrapper.lt("log_time", queryLog.getLogTimeEnd());

        //装配返回
        Page<LogMessage> logMessagePage = logMapper.selectPage(page,queryWrapper);
        PageBase<List<LogMessage>> pageBase = new PageBase<>();
        pageBase.setList(logMessagePage.getRecords());
        pageBase.setTotal(logMessagePage.getTotal());
        return pageBase;
    }
}
