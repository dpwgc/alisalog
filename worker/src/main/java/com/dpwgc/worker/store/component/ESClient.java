package com.dpwgc.worker.store.component;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.dpwgc.common.model.LogMessageIn;
import com.dpwgc.common.util.JsonUtil;
import com.dpwgc.common.util.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Map;

@Component
public class ESClient {

    @Resource
    private ElasticsearchClient client;

    @Value("${elasticsearch.indexPrefix}")
    private String indexPrefix;

    // ============ 命令操作 ============

    /**
     * 判断索引是否存在
     * @param indexName 索引名称
     * @return Boolean
     */
    public Boolean existsIndex(String indexName) {
        try {
            BooleanResponse booleanResponse = client.indices().exists(exists -> exists.index(indexName));
            return booleanResponse.value();
        } catch (Exception e) {
            LogUtil.error("es exists index error: "+e);
            return false;
        }
    }

    /**
     * 创建索引
     * @param indexName 索引名称
     * @return Boolean
     */
    public void createIndex(String indexName) {
        try {
            CreateIndexResponse indexResponse = client.indices().create(create -> create.index(indexName));
            LogUtil.info("elasticsearch create index: "+indexResponse.index());
        } catch (Exception e) {
            LogUtil.error("es create index error: "+e);
        }
    }

    /**
     * 插入日志
     * @param logMessageIn 日志写入对象
     */
    public void insert(LogMessageIn logMessageIn) {

        //获取当天的存储索引名称
        String indexName = indexPrefix + LocalDate.now();

        //判断索引名称是否存在
        if (!existsIndex(indexName)) {
            LogUtil.info("elasticsearch index does not exist");
            createIndex(indexName);
        }

        try {
            //LogMessage转Json字符串
            String json = JsonUtil.toJson(logMessageIn);

            //写入ES
            client.index(index -> index
                    .index(indexName)
                    .document(JsonUtil.fromJson(json,Map.class)));

        } catch (Exception e) {
            LogUtil.error("es insert logMessage error: "+e);
        }
    }
}
