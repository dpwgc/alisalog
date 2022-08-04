package com.dpwgc.worker.cluster;

import com.dpwgc.common.util.HttpUtil;
import com.dpwgc.common.util.LogUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 发送心跳至路由中心
 */
@Component
public class Heartbeat implements InitializingBean {

    @Value("${udp.port}")
    private String udpPort;

    @Value("${server.port}")
    private String httpPort;

    @Value("${heartbeat.address}")
    private String address;

    @Value("${heartbeat.cycle}")
    private Integer cycle;

    @Value("${heartbeat.router.url}")
    private String url;

    @Resource
    HttpUtil httpUtil;

    @Override
    public void afterPropertiesSet() {
        if (url == null || url.length() == 0) {
            return;
        }
        String[] urls = url.split(",");
        new Thread(() -> {
            while (true){
                try {
                    //心跳周期间隔休眠
                    Thread.sleep(cycle*1000);
                    //可同时向多个路由中心发送心跳
                    for (String u : urls) {
                        //发送心跳
                        String res = httpUtil.doGet(u + "/node/heartbeat?address=" + address + "&udpPort=" + udpPort + "&httpPort=" + httpPort);
                        if (Integer.parseInt(res) == -1) {
                            LogUtil.error("Heartbeat error", "-1");
                        }
                    }
                } catch (InterruptedException e) {
                    LogUtil.error("Heartbeat thread error",e.toString());
                }
            }
        }).start();
    }
}
