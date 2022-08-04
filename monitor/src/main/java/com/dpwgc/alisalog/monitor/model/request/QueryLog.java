package com.dpwgc.alisalog.monitor.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "日志查询")
public class QueryLog {

    // ----- 机房信息（控制台精确检索） -----

    /**
     * 所属数据中心
     */
    @ApiModelProperty(value = "所属数据中心 <精确检索>")
    private String idc;

    /**
     * 所属主机名称
     */
    @ApiModelProperty(value = "所属主机名称 <精确检索>")
    private String host;

    /**
     * 所属环境
     */
    @ApiModelProperty(value = "所属环境 <精确检索>")
    private String env;

    // ----- 日志初步定位（控制台精确检索） -----

    /**
     * 所属应用id
     */
    @ApiModelProperty(value = "所属应用id <精确检索>", required = true)
    private String appId;

    /**
     * 跟踪id
     */
    @ApiModelProperty(value = "跟踪id <精确检索>")
    private String traceId;

    /**
     * 所属模块
     */
    @ApiModelProperty(value = "所属模块 <精确检索>")
    private String module;

    /**
     * 所属类别
     */
    @ApiModelProperty(value = "所属类别 <精确检索>")
    private String category;

    /**
     * 所属子类别
     */
    @ApiModelProperty(value = "所属子类别 <精确检索>")
    private String subCategory;

    /**
     * 查询过滤条件1
     */
    @ApiModelProperty(value = "查询过滤条件1 <精确检索>")
    private String filter1;

    /**
     * 查询过滤条件2
     */
    @ApiModelProperty(value = "查询过滤条件2 <精确检索>")
    private String filter2;

    /**
     * 日志等级
     */
    @ApiModelProperty(value = "日志等级 <精确检索>")
    private Integer level;

    /**
     * 日志标签
     */
    @ApiModelProperty(value = "日志标签 <精确检索>")
    private String tag;

    // ----- 模糊查询 -----

    /**
     * 搜索关键词
     */
    @ApiModelProperty(value = "搜索关键词 <模糊检索title和content>")
    private String keyword;

    // ----- 时间区间检索 -----

    @ApiModelProperty(value = "开始时间 <区间检索>", required = true)
    private long logTimeStart;
    @ApiModelProperty(value = "结束时间 <区间检索>", required = true)
    private long logTimeEnd;

    // ----- 分页检索 -----

    @ApiModelProperty(value = "分页起始 <区间检索>", required = true)
    private int pageIndex;
    @ApiModelProperty(value = "分页大小 <区间检索>", required = true)
    private int pageSize;
}
