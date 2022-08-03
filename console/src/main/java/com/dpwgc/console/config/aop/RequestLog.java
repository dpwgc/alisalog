package com.dpwgc.console.config.aop;

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

    // url
    private String url;

    // 请求方式 GET POST
    private String httpMethod;

    // 类方法
    private String classMethod;

    // 请求参数
    private Object requestParams;

    // 接口耗时
    private Long timeCost;
}
