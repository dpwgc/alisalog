package com.dpwgc.alisalog.monitor.model.response;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "用户信息")
@TableName("user")
public class User {
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "权限等级")
    private String authLevel;
}
