package com.dpwgc.console.controller;

import com.dpwgc.console.base.ApiResult;
import com.dpwgc.console.base.UserResult;
import com.dpwgc.console.model.request.UserLogin;
import com.dpwgc.console.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "用户操作接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 用户登陆
     */
    @ApiOperation(value = "用户登陆")
    @GetMapping(value = "/login")
    public ApiResult<UserResult> login(UserLogin userLogin) {
        UserResult userResult = userService.login(userLogin);
        if (userResult == null) {
            return ApiResult.getFailureResult("login fail");
        }
        return ApiResult.getSuccessResult(userResult);
    }
}
