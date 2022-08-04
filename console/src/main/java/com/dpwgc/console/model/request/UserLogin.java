package com.dpwgc.console.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "用户登陆")
public class UserLogin {
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}
