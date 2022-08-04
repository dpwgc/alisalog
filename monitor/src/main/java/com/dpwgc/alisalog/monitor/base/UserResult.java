package com.dpwgc.alisalog.monitor.base;

import com.dpwgc.alisalog.monitor.model.response.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "用户返回模版")
public class UserResult {
    @ApiModelProperty(value = "登陆令牌")
    private String token;
    @ApiModelProperty(value = "用户信息")
    private User info;
}
