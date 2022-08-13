package com.dpwgc.alisalog.monitor.controller;

import com.dpwgc.alisalog.monitor.base.ApiResult;
import com.dpwgc.alisalog.monitor.base.PageResult;
import com.dpwgc.alisalog.monitor.model.request.QueryLog;
import com.dpwgc.alisalog.monitor.model.response.Log;
import com.dpwgc.alisalog.monitor.model.response.Node;
import com.dpwgc.alisalog.monitor.service.MonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Api(value = "监控台接口")
@CrossOrigin
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Resource
    MonitorService monitorService;

    /**
     * 日志检索
     */
    @ApiOperation(value = "日志查询")
    @GetMapping(value = "/log/list")
    public ApiResult<PageResult<List<Log>>> getLogList(@ModelAttribute QueryLog queryLog) {

        //appId必填
        if (queryLog.getAppId() == null || queryLog.getAppId().length() == 0) {
            return ApiResult.getFailureResult("appId null");
        }
        return ApiResult.getSuccessResult(monitorService.getLogList(queryLog));
    }

    /**
     * 用户登陆
     */
    @ApiOperation(value = "获取worker集群节点列表")
    @GetMapping(value = "/node/list")
    public ApiResult<List<Node>> getNodeList() {
        List<Node> nodeList = monitorService.getNodeList();
        if (nodeList == null) {
            return ApiResult.getFailureResult("list fail");
        }
        return ApiResult.getSuccessResult(nodeList);
    }

    /**
     * 获取数据中心列表
     */
    @ApiOperation(value = "获取数据中心列表")
    @GetMapping(value = "/idc/list")
    public ApiResult<Set<Object>> getIdcList() {

        return ApiResult.getSuccessResult(monitorService.getIdcList());
    }

    /**
     * 获取应用id列表
     */
    @ApiOperation(value = "获取应用id列表")
    @GetMapping(value = "/appId/list")
    public ApiResult<Set<Object>> getAppIdList() {

        return ApiResult.getSuccessResult(monitorService.getAppIdList());
    }

    /**
     * 获取环境列表
     */
    @ApiOperation(value = "获取环境列表")
    @GetMapping(value = "/env/list")
    public ApiResult<Set<Object>> getEnvList() {

        return ApiResult.getSuccessResult(monitorService.getEnvList());
    }

    /**
     * 根据数据中心名称获取旗下的主机列表
     */
    @ApiOperation(value = "根据数据中心名称获取旗下的主机列表")
    @GetMapping(value = "/host/list")
    public ApiResult<Set<Object>> getHostList(@ApiParam("数据中心名称") String idc) {

        return ApiResult.getSuccessResult(monitorService.getHostList(idc));
    }

    /**
     * 根据应用id获取旗下的模块列表
     */
    @ApiOperation(value = "根据应用id获取旗下的模块列表")
    @GetMapping(value = "/module/list")
    public ApiResult<Set<Object>> getModuleList(@ApiParam("应用id") String appId) {

        return ApiResult.getSuccessResult(monitorService.getModuleList(appId));
    }

    /**
     * 根据模块名称获取旗下的分类列表
     */
    @ApiOperation(value = "根据模块名称获取旗下的分类列表")
    @GetMapping(value = "/category/list")
    public ApiResult<Set<Object>> getCategoryList(@ApiParam("模块名称") String module) {

        return ApiResult.getSuccessResult(monitorService.getCategoryList(module));
    }

    /**
     * 根据分类名称获取旗下的子分类列表
     */
    @ApiOperation(value = "根据分类名称获取旗下的子分类列表")
    @GetMapping(value = "/subCategory/list")
    public ApiResult<Set<Object>> getSubCategoryList(@ApiParam("分类名称") String category) {

        return ApiResult.getSuccessResult(monitorService.getSubCategoryList(category));
    }
}
