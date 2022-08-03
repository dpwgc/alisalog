package com.dpwgc.console.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 控制台日志输出
 */
@Setter
@Getter
public class LogMessage {

    /**
     * ES主键id
     */
    @JsonProperty("id")
    private String id;

    // ----- 机房信息（控制台精确检索） -----

    /**
     * 所属数据中心
     */
    @JsonProperty("idc")
    private String idc;

    /**
     * 所属主机名称
     */
    @JsonProperty("host")
    private String host;

    /**
     * 所属环境
     */
    @JsonProperty("env")
    private String env;

    // ----- 日志初步定位（控制台精确检索） -----

    /**
     * 所属应用id
     */
    @JsonProperty("app_id")
    private String appId;

    /**
     * 跟踪id
     */
    @JsonProperty("trace_id")
    private String traceId;

    /**
     * 所属模块
     */
    @JsonProperty("module")
    private String module;

    /**
     * 所属类别
     */
    @JsonProperty("category")
    private String category;

    /**
     * 所属子类别
     */
    @JsonProperty("sub_category")
    private String subCategory;

    /**
     * 查询过滤条件1
     */
    @JsonProperty("filter1")
    private String filter1;

    /**
     * 查询过滤条件2
     */
    @JsonProperty("filter2")
    private String filter2;

    // ----- 日志详细定位（控制台精确检索） -----

    /**
     * 所属代码文件
     */
    @JsonProperty("file")
    private String file;

    /**
     * 所属代码位置（在代码文件的第几行或哪个函数产生产生）
     */
    @JsonProperty("position")
    private String position;

    // ----- 日志基本信息 -----

    /**
     * 日志等级（控制台区间检索）
     */
    @JsonProperty("level")
    private Integer level;

    /**
     * 日志标签（作为预警埋点）
     */
    @JsonProperty("tag")
    private String tag;

    /**
     * 日志标题（对日志的简短描述，控制台模糊检索）
     */
    @JsonProperty("title")
    private String title;

    /**
     * 日志内容（日志详细内容，控制台模糊检索）
     */
    @JsonProperty("content")
    private String content;

    /**
     * 日志额外备注信息
     */
    @JsonProperty("remarks")
    private String remarks;

    /**
     * 记录时间（控制台区间检索）
     */
    @JsonProperty("log_time")
    private Long logTime;
}
