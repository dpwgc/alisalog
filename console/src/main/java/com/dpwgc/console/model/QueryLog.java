package com.dpwgc.console.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryLog {

    // ----- 机房信息（控制台精确检索） -----

    /**
     * 所属数据中心
     */
    @ApiModelProperty(value = "所属数据中心")
    private String idc;

    /**
     * 所属主机名称
     */
    @ApiModelProperty(value = "所属主机名称")
    private String host;

    /**
     * 所属环境
     */
    @ApiModelProperty(value = "所属环境")
    private String env;

    // ----- 日志初步定位（控制台精确检索） -----

    /**
     * 所属应用id
     */
    @ApiModelProperty(value = "所属应用id")
    private String appId;

    /**
     * 跟踪id
     */
    @ApiModelProperty(value = "分类所属应用id")
    private String traceId;

    /**
     * 所属模块
     */
    @ApiModelProperty(value = "所属模块")
    private String module;

    /**
     * 所属类别
     */
    @ApiModelProperty(value = "所属类别")
    private String category;

    /**
     * 所属子类别
     */
    @ApiModelProperty(value = "所属子类别")
    private String subCategory;

    /**
     * 查询过滤条件1
     */
    @ApiModelProperty(value = "查询过滤条件1")
    private String filter1;

    /**
     * 查询过滤条件2
     */
    @ApiModelProperty(value = "查询过滤条件2")
    private String filter2;

    /**
     * 日志等级
     */
    @ApiModelProperty(value = "日志等级")
    private Integer level;

    // ----- 模糊查询 -----

    /**
     * 搜索关键词
     */
    @ApiModelProperty(value = "搜索关键词")
    private String keyword;

    // ----- 时间区间检索 -----

    @ApiModelProperty(value = "开始时间")
    private Long LogTimeStart;
    @ApiModelProperty(value = "结束时间")
    private Long LogTimeEnd;

    // ----- 分页检索 -----

    @ApiModelProperty(value = "分页起始")
    private Integer pageIndex;
    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
}
