package com.dpwgc.alisalog.monitor.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "应用创建")
public class SetApp {
    @ApiModelProperty(value = "应用id")
    private String appId;
    @ApiModelProperty(value = "应用token")
    private String token;
    @ApiModelProperty(value = "操作类型：1-更新/插入、0-删除")
    private Integer opType;
}
