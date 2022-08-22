package com.dpwgc.alisalog.monitor.service;

import com.dpwgc.alisalog.common.constant.RedisPrefix;
import com.dpwgc.alisalog.common.util.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class ConsoleService {

    @Resource
    RedisUtil redisUtil;

    /**
     * 创建/删除应用
     */
    public Boolean setApp(String appId,String token,Integer opType) {
        if (opType != null && opType != 0) {
            //保存appId与token
            return redisUtil.sSet(RedisPrefix.APP_ID_SET,appId) > 0 && redisUtil.set(RedisKeyUtil.getAppKey(appId),token);
        }
        //删除
        return redisUtil.setRemove(RedisPrefix.APP_ID_SET,appId) > 0 && redisUtil.set(RedisKeyUtil.getAppKey(appId),null);
    }

    /**
     * 创建/删除数据中心
     */
    public Boolean setIdc(String idc,Integer opType) {
        if (opType != null && opType != 0) {
            //保存idc
            return redisUtil.sSet(RedisPrefix.IDC_SET,idc) > 0;
        }
        //删除
        return redisUtil.setRemove(RedisPrefix.IDC_SET,idc) > 0;
    }

    /**
     * 创建/删除环境
     */
    public Boolean setEnv(String env,Integer opType) {
        if (opType != null && opType != 0) {
            //保存env
            return redisUtil.sSet(RedisPrefix.ENV_SET,env) > 0;
        }
        //删除
        return redisUtil.setRemove(RedisPrefix.ENV_SET,env) > 0;
    }
}
