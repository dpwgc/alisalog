package com.dpwgc.alisalog.worker.buffer;

import com.dpwgc.alisalog.common.constant.RedisPrefix;
import com.dpwgc.alisalog.common.util.LogUtil;
import com.dpwgc.alisalog.worker.config.BufferConfig;
import com.dpwgc.alisalog.common.model.LogBatch;
import com.dpwgc.alisalog.worker.input.LogAssembler;
import com.dpwgc.alisalog.worker.store.LogModel;
import com.dpwgc.alisalog.worker.store.LogStore2DB;
import com.dpwgc.alisalog.common.util.RedisUtil;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
            batchSave();
        }
    }

    public void batchSave() {

        //要写入chickhouse的日志列表（聚合后）
        List<LogModel> logModelList = new ArrayList<>();

        //如果当前缓冲队列中的消息数量小于minPoll，则不进行消费
        if (BufferQueue.count() < BufferConfig.CONSUMER_MIN_POLL) {
            return;
        }

        //批量取出缓冲队列中的日志列表
        for (int i = 0; i< BufferConfig.CONSUMER_MAX_POLL; i++) {

            //从缓冲区中取出日志列表
            LogBatch logBatch = BufferQueue.poll();

            if(logBatch != null) {
                try {
                    //将日志通用信息写入Redis set，方便监控台查询

                    /*
                     * 级联关系
                     * idc
                     *   host
                     * */

                    redisUtil.sSet(RedisPrefix.IDC_SET,logBatch.getIdc());
                    //host主机号key的后面加上idc数据中心名称，表明该主机是归属于这个idc的，用于监控台级联查询
                    redisUtil.sSet(String.format("%s%s", RedisPrefix.HOST_SET,logBatch.getIdc()),logBatch.getHost());

                    redisUtil.sSet(RedisPrefix.APP_ID_SET,logBatch.getAppId());
                    redisUtil.sSet(RedisPrefix.ENV_SET,logBatch.getEnv());

                    //将LogBatch批量日志信息展开，转换成LogModel列表，然后聚和多批次日志列表
                    for (LogModel logModel : LogAssembler.assembler(logBatch)) {

                        //将模块-分类-子分类信息写入redis，方便监控台查询

                       /*
                        * 级联关系
                        * app
                        *   module
                        *       category
                        *           subCategory
                        * */

                        //module模块key的后面加上appId，表明该模块是归属于这个app的，用于监控台级联查询
                        redisUtil.sSet(String.format("%s%s", RedisPrefix.MODULE_SET,logBatch.getAppId()),logModel.getModule());
                        //category分类key的后面加上module模块名称，表明该分类是归属于这个模块的，用于监控台级联查询，下面的子分类也同理
                        redisUtil.sSet(String.format("%s%s", RedisPrefix.CATEGORY_SET,logModel.getModule()),logModel.getCategory());
                        redisUtil.sSet(String.format("%s%s", RedisPrefix.SUB_CATEGORY_SET,logModel.getCategory()),logModel.getSubCategory());

                        //加入列表
                        logModelList.add(logModel);
                    }

                } catch (Exception e) {
                    LogUtil.error("buffer consume error",e.toString());
                }
            } else {
                break;
            }
        }
        if (logModelList.size() == 0) {
            return;
        }
        //将日志信息写入chickhouse
        logStore2DB.save(logModelList);
    }
}
