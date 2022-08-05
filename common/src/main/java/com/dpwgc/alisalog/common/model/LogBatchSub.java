package com.dpwgc.alisalog.common.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogBatchSub {

    /**
     * 日志id（uuid）
     */
    private String id;

    /**
     * 跟踪id
     */
    private String traceId;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 所属类别
     */
    private String category;

    /**
     * 所属子类别
     */
    private String subCategory;

    /**
     * 查询过滤条件1
     */
    private String filter1;

    /**
     * 查询过滤条件2
     */
    private String filter2;

    // ----- 日志详细定位 -----

    /**
     * 所属代码文件
     */
    private String file;

    /**
     * 所属代码位置
     */
    private String position;

    // ----- 日志基本信息 -----

    /**
     * 日志等级（控制台精确检索）
     */
    private Integer level;

    /**
     * 日志标签（存储版本号或者其他标识）
     */
    private String tag;

    /**
     * 日志标题（对日志的简短描述，控制台模糊检索）
     */
    private String title;

    /**
     * 日志内容（日志详细内容，控制台模糊检索）
     */
    private String content;

    /**
     * 日志额外备注信息
     */
    private String remarks;

    /**
     * 记录时间（控制台区间检索）
     */
    private Long logTime;
}
