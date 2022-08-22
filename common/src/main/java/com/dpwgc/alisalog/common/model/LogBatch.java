package com.dpwgc.alisalog.common.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LogBatch {

    /**
     * 该批次日志-所属数据中心
     */
    private String idc;

    /**
     * 该批次日志-所属主机名称
     */
    private String host;

    /**
     * 该批次日志-所属环境
     */
    private String env;

    /**
     * 该批次日志-所属应用id
     */
    private String appId;

    /**
     * 该批次日志-所属应用的权限令牌token
     */
    private String token;

    /**
     * 日志详细数据列表
     */
    private List<LogBatchSub> logs;
}
