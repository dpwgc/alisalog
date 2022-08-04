package com.dpwgc.router.api;

import com.dpwgc.common.util.JsonUtil;
import com.dpwgc.common.util.LogUtil;
import com.dpwgc.router.cache.Cache;
import com.dpwgc.router.model.Node;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 节点操作接口
 */
@RestController
@RequestMapping("/node")
public class NodeApi {

    /**
     * 接收服务节点的心跳注册
     * @param address 节点IP
     * @param udpPort 节点的udp端口
     * @param httpPort 节点的http端口
     * @return Integer
     */
    @RequestMapping("/heartbeat")
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
     * 获取节点列表
     * @return List<String>
     */
    @RequestMapping("/list")
    public List<Node> listNode(){
        try {
            return Cache.list();
        } catch (Exception e) {
            LogUtil.error("NodeApi listNode error",e.toString());
            return null;
        }
    }
}
