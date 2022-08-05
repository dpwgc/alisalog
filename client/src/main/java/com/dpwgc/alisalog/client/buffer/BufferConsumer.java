package com.dpwgc.alisalog.client.buffer;

import com.dpwgc.alisalog.client.config.ClientConfig;
import com.dpwgc.alisalog.client.log.SendLog;
import com.dpwgc.alisalog.common.model.LogBatch;
import com.dpwgc.alisalog.common.model.LogBatchSub;
import com.dpwgc.alisalog.common.model.Node;
import com.dpwgc.alisalog.common.util.HttpUtil;
import com.dpwgc.alisalog.common.util.JsonUtil;
import com.dpwgc.alisalog.common.util.LogUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 缓冲队列消费者 TODO
 */
@Component
public class BufferConsumer {

    @Resource
    HttpUtil httpUtil;

    /**
     * 消费者服务
     */
    public void consume(ClientConfig clientConfig) {
        while (true) {
            batchSend(clientConfig);
        }
    }

    public void batchSend(ClientConfig clientConfig) {

        LogBatch logBatch = new LogBatch();
        logBatch.setLogs(new ArrayList<>());

        //批量取出缓冲队列中的日志列表
        for (int i = 0; i< clientConfig.getConsumerBatch(); i++) {
            //从缓冲区中取出日志列表
            LogBatchSub logBatchSub = BufferQueue.poll();
            if(logBatchSub != null) {
                try {
                    logBatch.getLogs().add(logBatchSub);
                } catch (Exception e) {
                    LogUtil.error("buffer consume error",e.toString());
                }
            } else {
                break;
            }
        }

        //从路由中心获取节点列表
        List<Node> nodeList = null;
        String[] urls = clientConfig.getRouterUrl().split(",");
        for (String u : urls) {
            try {
                String res = httpUtil.doGet(u + "/node/list");
                if (res == null) {
                    return;
                }
                nodeList = JsonUtil.fromJson(res,List.class);
            } catch (Exception e) {
                LogUtil.error("consumer http doGet error",e.toString());
            }
        }

        SendLog.sendLogByUdp(nodeList.get(0).getAddress(),Integer.parseInt(nodeList.get(0).getUdpPort()),logBatch);
    }
}
