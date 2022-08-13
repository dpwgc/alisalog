package com.dpwgc.alisalog.monitor.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dpwgc.alisalog.common.constant.RedisPrefix;
import com.dpwgc.alisalog.common.util.HttpUtil;
import com.dpwgc.alisalog.common.util.JsonUtil;
import com.dpwgc.alisalog.common.util.LogUtil;
import com.dpwgc.alisalog.common.util.RedisUtil;
import com.dpwgc.alisalog.monitor.base.PageResult;
import com.dpwgc.alisalog.monitor.model.response.Log;
import com.dpwgc.alisalog.monitor.mapper.LogMapper;
import com.dpwgc.alisalog.monitor.model.request.QueryLog;
import com.dpwgc.alisalog.monitor.model.response.Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class MonitorService {

    @Resource
    LogMapper logMapper;

    @Resource
    RedisUtil redisUtil;

    @Resource
    HttpUtil httpUtil;

    @Value("${router.url}")
    private String url;

    public PageResult<List<Log>> getLogList(QueryLog queryLog) {

        //分页
        Page<Log> page = new Page<>(queryLog.getPageIndex(), queryLog.getPageSize());

        //查询
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();

        //检索条件
        if (queryLog.getIdc() != null && queryLog.getIdc().length() > 0){
            queryWrapper.eq("idc", queryLog.getIdc());
        }
        if (queryLog.getHost() != null && queryLog.getHost().length() > 0){
            queryWrapper.eq("host", queryLog.getHost());
        }
        if (queryLog.getEnv() != null && queryLog.getEnv().length() > 0){
            queryWrapper.eq("env", queryLog.getEnv());
        }
        if (queryLog.getTraceId() != null && queryLog.getTraceId().length() > 0){
            queryWrapper.eq("trace_id", queryLog.getTraceId());
        }
        if (queryLog.getModule() != null && queryLog.getModule().length() > 0){
            queryWrapper.eq("module", queryLog.getModule());
        }
        if (queryLog.getCategory() != null && queryLog.getCategory().length() > 0){
            queryWrapper.eq("category", queryLog.getCategory());
        }
        if (queryLog.getSubCategory() != null && queryLog.getSubCategory().length() > 0){
            queryWrapper.eq("sub_category", queryLog.getSubCategory());
        }
        if (queryLog.getFilter1() != null && queryLog.getFilter1().length() > 0){
            queryWrapper.eq("filter1", queryLog.getFilter1());
        }
        if (queryLog.getFilter2() != null && queryLog.getFilter2().length() > 0){
            queryWrapper.eq("filter2", queryLog.getFilter2());
        }
        if (queryLog.getLevel() != null && queryLog.getLevel() > 0){
            queryWrapper.eq("level", queryLog.getLevel());
        }
        if (queryLog.getTag() != null && queryLog.getTag().length() > 0){
            queryWrapper.eq("tag", queryLog.getTag());
        }
        if (queryLog.getKeyword() != null && queryLog.getKeyword().length() > 0){
            queryWrapper.like("title", queryLog.getKeyword());
            queryWrapper.like("content", queryLog.getKeyword());
        }

        queryWrapper.ge("log_time", queryLog.getLogTimeStart());
        queryWrapper.lt("log_time", queryLog.getLogTimeEnd());

        queryWrapper.eq("app_id", queryLog.getAppId());

        queryWrapper.orderByDesc("log_time");

        //装配返回
        Page<Log> logMessagePage = logMapper.selectPage(page,queryWrapper);
        PageResult<List<Log>> pageResult = new PageResult<>();
        pageResult.setList(logMessagePage.getRecords());
        pageResult.setTotal(logMessagePage.getTotal());
        return pageResult;
    }

    public List<Node> getNodeList() {
        if (url == null || url.length() == 0) {
            return null;
        }
        String[] urls = url.split(",");
        for (String u : urls) {
            try {
                String res = httpUtil.doGet(u + "/node/list");
                return JsonUtil.fromJson(res,List.class);
            } catch (Exception e) {
                LogUtil.error("NodeService list http doGet error",e.toString());
            }
        }
        return null;
    }

    /**
     * 获取数据中心列表
     */
    public Set<Object> getIdcList() {
        return redisUtil.sGet(RedisPrefix.IDC_SET);
    }

    /**
     * 获取应用id列表
     */
    public Set<Object> getAppIdList() {
        return redisUtil.sGet(RedisPrefix.APP_ID_SET);
    }

    /**
     * 获取环境列表
     */
    public Set<Object> getEnvList() {
        return redisUtil.sGet(RedisPrefix.ENV_SET);
    }

    /**
     * 根据数据中心名称获取旗下的主机列表
     */
    public Set<Object> getHostList(String idc) {
        return redisUtil.sGet(String.format("%s%s",RedisPrefix.HOST_SET,idc));
    }

    /**
     * 根据应用id获取旗下的模块列表
     */
    public Set<Object> getModuleList(String appId) {
        return redisUtil.sGet(String.format("%s%s",RedisPrefix.MODULE_SET,appId));
    }

    /**
     * 根据模块名称获取旗下的分类列表
     */
    public Set<Object> getCategoryList(String module) {
        return redisUtil.sGet(String.format("%s%s",RedisPrefix.CATEGORY_SET,module));
    }

    /**
     * 根据分类名称获取旗下的子分类列表
     */
    public Set<Object> getSubCategoryList(String category) {
        return redisUtil.sGet(String.format("%s%s",RedisPrefix.SUB_CATEGORY_SET,category));
    }
}
