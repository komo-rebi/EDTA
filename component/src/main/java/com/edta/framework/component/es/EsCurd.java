package com.edta.framework.component.es;

import com.edta.framework.component.curd.Curd;
import com.edta.framework.component.page.PageData;
import com.edta.framework.component.queryhelper.DefaultQuery;
import com.edta.framework.component.queryhelper.Page;
import lombok.var;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import java.util.List;

/**
 * @author wangluyao
 * @date 2023/3/1 16:44
 * @description
 */
public class EsCurd implements Curd {

    private static final Integer DEFAULT_QUERY_NUM = 10000;
    private final ElasticsearchRestTemplate esTemplate;

    public EsCurd(ElasticsearchRestTemplate esTemplate) {
        this.esTemplate = esTemplate;
    }

    @Override
    public <T> void save(T t, Class<T> tClass) {
        esTemplate.save(t, tClass);
    }

    @Override
    public <T> void saveAll(List<T> list, Class<T> tClass) {
        esTemplate.save(list, tClass);
    }

    @Override
    public <T, Q extends DefaultQuery> PageData<T> list(Q query, Class<T> tClass) {
        Page pageable = query.getPage();
        NativeSearchQuery nativeSearchQuery = QueryProviderHelper.setQueryWrapper(query);
        if (pageable.getPageSize() <= DEFAULT_QUERY_NUM) {
            SearchHits<T> searchHits = esTemplate.search(nativeSearchQuery, tClass);
            return PageData.results(searchHits);
        } else {
            nativeSearchQuery.setPageable(PageRequest.of(0, DEFAULT_QUERY_NUM));
            var searchHitsIterator = esTemplate.searchForStream(nativeSearchQuery, tClass);
            return PageData.results(searchHitsIterator);
        }
    }

    @Override
    public <T, Q extends DefaultQuery> long count(T query, Class<T> tClass) {
        return 0;
    }
}
