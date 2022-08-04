package com.dpwgc.alisalog.client.log;

import com.dpwgc.alisalog.client.udp.UDPClient;
import com.dpwgc.alisalog.common.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SendLog {
    private List<LocalLog> logs;

    public static void sendLogsByUdp(String address, Integer port, List<LocalLog> localLogList) {
        SendLog sendLog = new SendLog();
        sendLog.setLogs(localLogList);
        UDPClient.send(address,port, JsonUtil.toJson(sendLog));
    }

    public static void sendLogsByHttp(String url, LocalLog localLog) {
        return;
    }
}
