package com.dpwgc.alisalog.monitor.service;

import com.dpwgc.alisalog.common.util.HttpUtil;
import com.dpwgc.alisalog.common.util.JsonUtil;
import com.dpwgc.alisalog.common.util.LogUtil;
import com.dpwgc.alisalog.monitor.model.response.Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NodeService {

    @Resource
    HttpUtil httpUtil;

    @Value("${router.url}")
    private String url;

    public List<Node> list() {
        if (url == null || url.length() == 0) {
            return null;
        }
        String[] urls = url.split(",");
        for (String u : urls) {
            try {
                String res = httpUtil.doGet(u + "/node/list");
                return JsonUtil.fromJson(res,List.class);
            } catch (Exception e) {
                LogUtil.error("NodeService list http doGet error",e.toString());
            }
        }
        return null;
    }
}
