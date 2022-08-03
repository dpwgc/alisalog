package com.dpwgc.console.controller;

import com.dpwgc.console.base.PageBase;
import com.dpwgc.console.base.Result;
import com.dpwgc.console.model.LogMessage;
import com.dpwgc.console.model.QueryLogMessage;
import com.dpwgc.console.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@Api(value = "日志控制台接口")
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    LogService logService;

    /**
     * 日志查询
     */
    @ApiOperation(value = "日志查询")
    @PostMapping(value = "queryLog")
    public Result<PageBase<List<LogMessage>>> queryLog(QueryLogMessage queryLogMessage) {
        return Result.getSuccessResult(logService.queryLog(queryLogMessage));
    }

}
