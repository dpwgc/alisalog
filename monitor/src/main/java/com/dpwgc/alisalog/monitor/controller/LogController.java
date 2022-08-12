package com.dpwgc.alisalog.monitor.controller;

import com.dpwgc.alisalog.monitor.base.ApiResult;
import com.dpwgc.alisalog.monitor.base.PageResult;
import com.dpwgc.alisalog.monitor.model.request.QueryLog;
import com.dpwgc.alisalog.monitor.model.response.Log;
import com.dpwgc.alisalog.monitor.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Api(value = "日志查询接口")
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    LogService logService;

    /**
     * 日志检索
     */
    @ApiOperation(value = "日志查询")
    @GetMapping(value = "/search")
    public ApiResult<PageResult<List<Log>>> search(@ModelAttribute QueryLog queryLog) {

        //appId必填
        if (queryLog.getAppId() == null || queryLog.getAppId().length() == 0) {
            return ApiResult.getFailureResult("appId null");
        }
        return ApiResult.getSuccessResult(logService.search(queryLog));
    }

    /**
     * 获取数据中心列表
     */
    @ApiOperation(value = "获取数据中心列表")
    @GetMapping(value = "/getIdcList")
    public ApiResult<Set<Object>> getIdcList() {

        return ApiResult.getSuccessResult(logService.getIdcList());
    }

    /**
     * 获取应用id列表
     */
    @ApiOperation(value = "获取应用id列表")
    @GetMapping(value = "/getAppIdList")
    public ApiResult<Set<Object>> getAppIdList() {

        return ApiResult.getSuccessResult(logService.getAppIdList());
    }

    /**
     * 根据数据中心名称获取旗下的主机列表
     */
    @ApiOperation(value = "根据数据中心名称获取旗下的主机列表")
    @GetMapping(value = "/getHostList")
    public ApiResult<Set<Object>> getHostList(@ApiParam("数据中心名称") String idc) {

        return ApiResult.getSuccessResult(logService.getHostList(idc));
    }

    /**
     * 根据应用id获取旗下的模块列表
     */
    @ApiOperation(value = "根据应用id获取旗下的模块列表")
    @GetMapping(value = "/getModuleList")
    public ApiResult<Set<Object>> getModuleList(@ApiParam("应用id") String appId) {

        return ApiResult.getSuccessResult(logService.getModuleList(appId));
    }

    /**
     * 根据模块名称获取旗下的分类列表
     */
    @ApiOperation(value = "根据模块名称获取旗下的分类列表")
    @GetMapping(value = "/getCategoryList")
    public ApiResult<Set<Object>> getCategoryList(@ApiParam("模块名称") String module) {

        return ApiResult.getSuccessResult(logService.getCategoryList(module));
    }

    /**
     * 根据分类名称获取旗下的子分类列表
     */
    @ApiOperation(value = "根据分类名称获取旗下的子分类列表")
    @GetMapping(value = "/getSubCategoryList")
    public ApiResult<Set<Object>> getSubCategoryList(@ApiParam("分类名称") String category) {

        return ApiResult.getSuccessResult(logService.getSubCategoryList(category));
    }
}
