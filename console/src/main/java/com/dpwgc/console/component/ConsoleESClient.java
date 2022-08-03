package com.dpwgc.console.component;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dpwgc.common.base.PageBase;
import com.dpwgc.common.util.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ConsoleESClient {

    @Resource
    private ElasticsearchClient client;

    @Value("${elasticsearch.indexPrefix}")
    private String indexPrefix;

    // ============ 命令操作 ============

    /**
     * 根据关键词检索应用内的所有文档
     * @param indexName 索引名称
     * @param keyword 关键词
     * @return List<Hit<Object>>
     */
    public PageBase<List<Hit<Object>>> searchByKeyword(String indexName, String keyword, Integer pageIndex, Integer pageSize) {

        try {
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    //按关键词检索文档标题、内容、标签、摘要(模糊查询，不允许错字)
                    .query(query -> query
                            .bool(bool -> bool
                                    .should(should -> should
                                            .fuzzy(fuzzy -> fuzzy
                                                    .field("title")
                                                    .value(keyword)
                                                    .fuzziness("0")
                                            )
                                    )
                                    .should(should -> should
                                            .fuzzy(fuzzy -> fuzzy
                                                    .field("content")
                                                    .value(keyword)
                                                    .fuzziness("0")
                                            )
                                    )
                                    .should(should -> should
                                            .fuzzy(fuzzy -> fuzzy
                                                    .field("tags")
                                                    .value(keyword)
                                                    .fuzziness("0")
                                            )
                                    )
                                    .should(should -> should
                                            .fuzzy(fuzzy -> fuzzy
                                                    .field("summary")
                                                    .value(keyword)
                                                    .fuzziness("0")
                                            )
                                    )
                            )
                    )
                    //分页查询
                    .from(pageIndex)
                    .size(pageSize)
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field("log_time").order(SortOrder.Desc))),Object.class
            );
            return PageBase.getPageBase(search.hits().total().value(),search.hits().hits());
        } catch (Exception e) {
            LogUtil.error("es search error", e.toString());
            return null;
        }
    }
}
