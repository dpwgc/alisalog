package com.dpwgc.alisalog.router.api;

import com.dpwgc.alisalog.common.util.JsonUtil;
import com.dpwgc.alisalog.common.util.LogUtil;
import com.dpwgc.alisalog.router.cache.Cache;
import com.dpwgc.alisalog.common.model.Node;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Worker节点相关接口
 */
@RestController
@RequestMapping("/worker")
public class WorkerApi {

    /**
     * 接收Worker节点的心跳注册
     * @param address 节点IP
     * @param udpPort 节点的udp端口
     * @param httpPort 节点的http端口
     * @return Integer
     */
    @RequestMapping("/node/heartbeat")
    public Integer heartbeat(String address,String udpPort,String httpPort){
        try {
            Node node = new Node();
            node.setAddress(address);
            node.setUdpPort(udpPort);
            node.setHttpPort(httpPort);

            Cache.set(JsonUtil.toJson(node),System.currentTimeMillis()/1000);
            return 0;
        } catch (Exception e) {
            LogUtil.error("NodeApi heartbeat error",e.toString());
            return -1;
        }
    }

    /**
     * 获取Worker节点列表
     * @return List<String>
     */
    @RequestMapping("/node/list")
    public List<Node> getWorkerNodeList(){
        try {
            return Cache.list();
        } catch (Exception e) {
            LogUtil.error("NodeApi listNode error",e.toString());
            return null;
        }
    }
}
