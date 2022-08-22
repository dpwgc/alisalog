package com.dpwgc.alisalog.client.log;

import com.dpwgc.alisalog.client.udp.UDPClient;
import com.dpwgc.alisalog.common.model.LogBatch;
import com.dpwgc.alisalog.common.model.LogBatchSub;
import com.dpwgc.alisalog.common.util.JsonUtil;
import java.util.List;

/**
 * 日志收集客户端
 */
public class LogClient {

    private static String idc;
    private static String env;
    private static String host;
    private static String appId;
    private static String token;

    /**
     * 日志收集客户端初始化
     * 填充公共信息
     * @param idc 数据中心
     * @param env 环境
     * @param host 主机名
     * @param appId 应用id
     * @param token 应用token
     */
    public LogClient(String idc, String env, String host, String appId,String token) {
        LogClient.idc = idc;
        LogClient.env = env;
        LogClient.host = host;
        LogClient.appId = appId;
        LogClient.token = token;
    }

    /**
     * UDP发送日志
     * @param address 地址
     * @param port UDP端口
     * @param logBatchSubList 日志列表
     */
    public static void sendLogByUdp(String address, Integer port, List<LogBatchSub> logBatchSubList) {
        LogBatch logBatch = new LogBatch();
        logBatch.setIdc(idc);
        logBatch.setEnv(env);
        logBatch.setHost(host);
        logBatch.setAppId(appId);
        logBatch.setToken(token);
        logBatch.setLogs(logBatchSubList);
        UDPClient.send(address,port, JsonUtil.toJson(logBatch));
    }

    /**
     * HTTP发送日志
     * @param address 地址
     * @param port HTTP端口
     * @param logBatchSubList 日志列表
     */
    public static void sendLogByHttp(String address, Integer port, List<LogBatchSub> logBatchSubList) {
        LogBatch logBatch = new LogBatch();
        logBatch.setIdc(idc);
        logBatch.setEnv(env);
        logBatch.setHost(host);
        logBatch.setAppId(appId);
        logBatch.setToken(token);
        logBatch.setLogs(logBatchSubList);
        return;
    }
}
