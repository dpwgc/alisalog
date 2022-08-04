package com.dpwgc.alisalog.monitor.controller;

import com.dpwgc.alisalog.monitor.base.ApiResult;
import com.dpwgc.alisalog.monitor.base.PageResult;
import com.dpwgc.alisalog.monitor.model.request.QueryLog;
import com.dpwgc.alisalog.monitor.model.response.Log;
import com.dpwgc.alisalog.monitor.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
     * 日志查询
     */
    @ApiOperation(value = "日志查询")
    @ApiImplicitParams({@ApiImplicitParam (name = "username", paramType = "header",dataType = "String" ,required = true),
                        @ApiImplicitParam (name = "token", paramType = "header",dataType = "String" ,required = true)})
    @GetMapping(value = "/query")
    public ApiResult<PageResult<List<Log>>> query(QueryLog queryLog) {

        //appId必填
        if (queryLog.getAppId() == null || queryLog.getAppId().length() == 0) {
            return ApiResult.getFailureResult("appId null");
        }
        return ApiResult.getSuccessResult(logService.query(queryLog));
    }
}
