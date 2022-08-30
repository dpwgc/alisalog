package com.dpwgc.alisalog.monitor.config.aop;

import lombok.Builder;
import lombok.Data;

/**
 * 请求响应情况日志记录
 */
@Data
@Builder
public class RequestLog {

    // ip
    private String ip;

    // 请求参数
    private Object requestParams;

    // 接口耗时
    private String timeCost;
}
