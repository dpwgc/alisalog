package com.dpwgc.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogMessageIn {

    @JsonProperty("level")
    private Integer level;         //日志等级

    @JsonProperty("title")
    private String title; //日志标题

    @JsonProperty("content")
    private String content; //日志内容

    @JsonProperty("tag")
    private String tag;     //日志标签

    @JsonProperty("id")
    private String note;    //日志备注（自定义扩展）

    @JsonProperty("filter1")
    private String filter1;  //日志查询过滤条件1

    @JsonProperty("filter2")
    private String filter2;  //日志查询过滤条件2

    @JsonProperty("host")
    private String host;    //日志所属主机名称

    @JsonProperty("file")
    private String file;    //产生该日志的文件路径

    @JsonProperty("position")
    private String position;    //日志定位（产生于文件的哪个地方）

    @JsonProperty("id")
    private Long createTime;      //日志产生时间
}
