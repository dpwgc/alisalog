package com.dpwgc.alisalog.client.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientConfig {

    /**
     * 所属数据中心
     */
    private String idc;

    /**
     * 所属主机名称
     */
    private String host;

    /**
     * 所属环境
     */
    private String env;

    /**
     * 所属应用id
     */
    private String appId;

    /**
     * 消费者线程数量
     */
    private int consumerNumber;

    /**
     * 单次消费的消息数量
     */
    private int consumerBatch;

    /**
     * 路由中心url
     */
    private String routerUrl;
}
