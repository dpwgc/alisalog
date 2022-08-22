package com.dpwgc.alisalog.monitor.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "环境创建")
public class SetEnv {
    @ApiModelProperty(value = "环境名称")
    private String env;
    @ApiModelProperty(value = "操作类型：1-更新/插入、0-删除")
    private Integer opType;
}
