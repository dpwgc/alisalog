package com.dpwgc.alisalog.monitor.controller;

import com.dpwgc.alisalog.monitor.base.ApiResult;
import com.dpwgc.alisalog.monitor.base.PageResult;
import com.dpwgc.alisalog.monitor.model.request.QueryLog;
import com.dpwgc.alisalog.monitor.model.response.Log;
import com.dpwgc.alisalog.monitor.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

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
    public ApiResult<PageResult<List<Log>>> search(QueryLog queryLog) {

        //appId必填
        if (queryLog.getAppId() == null || queryLog.getAppId().length() == 0) {
            return ApiResult.getFailureResult("appId null");
        }
        return ApiResult.getSuccessResult(logService.search(queryLog));
    }
}
