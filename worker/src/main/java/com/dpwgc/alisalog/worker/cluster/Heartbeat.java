package com.dpwgc.alisalog.worker.cluster;

import com.dpwgc.alisalog.common.util.HttpUtil;
import com.dpwgc.alisalog.common.util.LogUtil;
import com.dpwgc.alisalog.worker.config.ClusterConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 发送心跳至路由中心
 */
@Component
public class Heartbeat {

    @Resource
    HttpUtil httpUtil;

    public void start() {
        if (ClusterConfig.URL == null || ClusterConfig.URL.length() == 0) {
            return;
        }
        String[] urls = ClusterConfig.URL.split(",");

        for (int i=0;i<urls.length;i++) {
            //拼接http请求链接
            urls[i] = String.format("%s%s%s%s%s%s",urls[i] + "/worker/node/heartbeat?address=",ClusterConfig.ADDRESS,"&udpPort=",ClusterConfig.UDP_PORT,"&httpPort=",ClusterConfig.HTTP_PORT);
        }
        new Thread(() -> {
            while (true){
                try {
                    //心跳周期间隔休眠
                    Thread.sleep(ClusterConfig.CYCLE*1000);
                    //可同时向多个路由中心发送心跳
                    for (String u : urls) {
                        //发送心跳
                        String res = httpUtil.doGet(u);
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
