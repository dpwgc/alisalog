package com.dpwgc.alisalog.client.log;

import com.dpwgc.alisalog.client.udp.UDPClient;
import com.dpwgc.alisalog.common.model.LogBatch;
import com.dpwgc.alisalog.common.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendLog {
    public static void sendLogByUdp(String address, Integer port, LogBatch logBatch) {
        UDPClient.send(address,port, JsonUtil.toJson(logBatch));
    }

    public static void sendLogByHttp(String url, LogBatch localLog) {
        return;
    }
}
