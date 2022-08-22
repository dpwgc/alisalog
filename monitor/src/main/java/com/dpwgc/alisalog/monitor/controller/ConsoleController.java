package com.dpwgc.alisalog.monitor.controller;

import com.dpwgc.alisalog.monitor.base.ApiResult;
import com.dpwgc.alisalog.monitor.model.request.SetApp;
import com.dpwgc.alisalog.monitor.model.request.SetEnv;
import com.dpwgc.alisalog.monitor.model.request.SetIdc;
import com.dpwgc.alisalog.monitor.service.ConsoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "操作台接口")
@CrossOrigin
@RestController
@RequestMapping("/console")
public class ConsoleController {

    @Resource
    ConsoleService consoleService;

    /**
     * 创建/删除应用
     */
    @ApiOperation(value = "创建/删除应用")
    @PostMapping(value = "/app")
    public ApiResult<Boolean> setApp(@RequestBody SetApp setApp) {
        //保存appId与token
        Boolean res = consoleService.setApp(setApp.getAppId(), setApp.getToken(),setApp.getOpType());
        if (res) {
            return ApiResult.getSuccessResult(true);
        }
        return ApiResult.getFailureResult("set error");
    }

    /**
     * 创建/删除数据中心
     */
    @ApiOperation(value = "创建/删除数据中心")
    @PostMapping(value = "/idc")
    public ApiResult<Boolean> setIdc(@RequestBody SetIdc setIdc) {
        //保存appId与token
        Boolean res = consoleService.setIdc(setIdc.getIdc(),setIdc.getOpType());
        if (res) {
            return ApiResult.getSuccessResult(true);
        }
        return ApiResult.getFailureResult("set error");
    }

    /**
     * 创建/删除环境
     */
    @ApiOperation(value = "创建/删除环境")
    @PostMapping(value = "/env")
    public ApiResult<Boolean> setEnv(@RequestBody SetEnv setEnv) {
        //保存appId与token
        Boolean res = consoleService.setEnv(setEnv.getEnv(),setEnv.getOpType());
        if (res) {
            return ApiResult.getSuccessResult(true);
        }
        return ApiResult.getFailureResult("set error");
    }
}
