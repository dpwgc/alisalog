package com.dpwgc.console.controller;

import com.dpwgc.console.base.ApiResult;
import com.dpwgc.console.base.PageResult;
import com.dpwgc.console.model.response.Log;
import com.dpwgc.console.model.request.QueryLog;
import com.dpwgc.console.service.LogService;
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
    @ApiImplicitParams({@ApiImplicitParam (name = "username", paramType = "header",dataType = "String"),
                        @ApiImplicitParam (name = "token", paramType = "header",dataType = "String")})
    @GetMapping(value = "/query")
    public ApiResult<PageResult<List<Log>>> query(QueryLog queryLog) {
        return ApiResult.getSuccessResult(logService.query(queryLog));
    }
}
