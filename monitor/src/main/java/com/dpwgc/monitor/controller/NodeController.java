package com.dpwgc.monitor.controller;

import com.dpwgc.monitor.base.ApiResult;
import com.dpwgc.monitor.service.NodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "集群节点查询接口")
@RestController
@RequestMapping("/node")
public class NodeController {

    @Resource
    NodeService nodeService;

    /**
     * 用户登陆
     */
    @ApiOperation(value = "获取worker集群节点列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", paramType = "header",dataType = "String" ,required = true),
            @ApiImplicitParam (name = "token", paramType = "header",dataType = "String" ,required = true)})
    @GetMapping(value = "/list")
    public ApiResult<Object> list() {
        Object list = nodeService.list();
        if (list == null) {
            return ApiResult.getFailureResult("list fail");
        }
        return ApiResult.getSuccessResult(list);
    }

}
