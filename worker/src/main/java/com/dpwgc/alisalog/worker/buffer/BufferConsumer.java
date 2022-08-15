package com.dpwgc.alisalog.worker.buffer;

import com.dpwgc.alisalog.common.constant.RedisPrefix;
import com.dpwgc.alisalog.common.util.LogUtil;
import com.dpwgc.alisalog.worker.config.BufferConfig;
import com.dpwgc.alisalog.common.model.LogBatch;
import com.dpwgc.alisalog.worker.store.LogAssembler;
import com.dpwgc.alisalog.worker.store.LogModel;
import com.dpwgc.alisalog.worker.store.LogStore2DB;
import com.dpwgc.alisalog.common.util.RedisUtil;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓冲队列消费者
 */
@Component
public class BufferConsumer {

    @Resource
    LogStore2DB logStore2DB;

    @Resource
    RedisUtil redisUtil;

    /**
     * 消费者服务
     */
    public void consume() {
        while (true) {
            try {
                batchSave();
            } catch (Exception e) {
                LogUtil.error("consume error",e.getMessage());
            }
        }
    }

    /**
     * 批量保存日志
     */
    public void batchSave() {

        //要写入chickHouse的日志列表（聚合后）
        List<LogModel> logModelList = new ArrayList<>();

        //如果当前缓冲队列中的消息数量小于minPoll，则不进行消费
        if (BufferQueue.count() < BufferConfig.CONSUMER_MIN_POLL) {
            return;
        }

        //批量取出缓冲队列中的日志列表
        for (int i = 0; i< BufferConfig.CONSUMER_MAX_POLL; i++) {

            //从缓冲区中取出日志列表
            LogBatch logBatch = BufferQueue.poll();

            //如果日志列表为空
            if (logBatch == null) {
                break;
            }

            //将日志通用信息写入Redis set，方便监控台查询

            /*
             * 级联关系
             * idc
             *   host
             * */

            redisUtil.sSet(RedisPrefix.IDC_SET,logBatch.getIdc());
            //host主机号key的后面加上idc数据中心名称，表明该主机是归属于这个idc的，用于监控台级联查询
            redisUtil.sSet(String.format("%s-%s", RedisPrefix.HOST_SET,logBatch.getIdc()),logBatch.getHost());

            //保存appId与env
            redisUtil.sSet(RedisPrefix.APP_ID_SET,logBatch.getAppId());
            redisUtil.sSet(RedisPrefix.ENV_SET,logBatch.getEnv());

            //本地set，用于暂时存储分类级联信息
            Map<String,List<String>> localSet = new HashMap<>();

            //将LogBatch批量日志信息展开，转换成LogModel列表，然后聚和多批次日志列表
            for (LogModel logModel : LogAssembler.assembler(logBatch)) {

                //将模块-分类-子分类信息先写入localSet，再发送至redis，避免频繁网络IO

                /*
                 * 级联关系
                 * app
                 *   module
                 *       category
                 *           subCategory
                 * */

                //module模块集合key的后面加上appId，表明该模块是归属于这个app的，用于监控台级联查询
                String moduleSetKey = String.format("%s-%s", RedisPrefix.MODULE_SET,logBatch.getAppId());
                localSet.put(moduleSetKey,new ArrayList<>());
                localSet.get(moduleSetKey).add(logModel.getModule());
                //category分类集合key的后面加上appId+module名称，表明该分类是归属于这个模块的，下面的子分类也同理
                String categorySetKey = String.format("%s-%s-%s", RedisPrefix.CATEGORY_SET,logBatch.getAppId(),logModel.getModule());
                localSet.put(categorySetKey,new ArrayList<>());
                localSet.get(categorySetKey).add(logModel.getCategory());
                //subCategory子分类集合key的后面加上appId+module名称+category名称，表明该子分类是归属于这个分类的
                String subCategorySetKey = String.format("%s-%s-%s-%s", RedisPrefix.SUB_CATEGORY_SET,logBatch.getAppId(),logModel.getModule(),logModel.getCategory());
                localSet.put(subCategorySetKey,new ArrayList<>());
                localSet.get(subCategorySetKey).add(logModel.getSubCategory());

                //加入logMode列表
                logModelList.add(logModel);
            }

            //将localSet中的信息写入redis
            for (String key : localSet.keySet()) {
                redisUtil.sSet(key,localSet.get(key));
            }
        }
        if (logModelList.size() > 0) {
            //将日志信息写入chickHouse
            logStore2DB.save(logModelList);
        }
    }
}
