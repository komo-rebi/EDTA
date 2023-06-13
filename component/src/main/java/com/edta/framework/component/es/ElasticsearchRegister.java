package com.edta.framework.component.es;

import com.edta.framework.component.util.ApplicationContextHelper;
import lombok.var;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangluyao
 * @date 2022/6/13 16:52
 * @description
 */
@Component
@ConditionalOnClass(ElasticsearchRestTemplate.class)
public class ElasticsearchRegister {

    private static final int DEFAULT_QUERY_NUM = 10000;
    private volatile ElasticsearchRestTemplate elasticsearchRestTemplate;

    public ElasticsearchRestTemplate getElasticsearchTemplate() {
        if (elasticsearchRestTemplate == null) {
            synchronized (this) {
                if (elasticsearchRestTemplate == null) {
                    elasticsearchRestTemplate = ApplicationContextHelper.getBean(ElasticsearchRestTemplate.class);
                }
            }
        }
        return elasticsearchRestTemplate;
    }

    public <T> long count(NativeSearchQuery query, Class<T> tClass) {
        ElasticsearchRestTemplate elasticsearchRestTemplate = getElasticsearchTemplate();
        return elasticsearchRestTemplate.count(query, tClass);
    }

    public <T> List<T> list(NativeSearchQuery query, Class<T> tClass) {
        ElasticsearchRestTemplate elasticsearchRestTemplate = getElasticsearchTemplate();
        Pageable pageable = query.getPageable();
        if (pageable.getPageSize() <= DEFAULT_QUERY_NUM) {
            SearchHits<T> searchHits = elasticsearchRestTemplate.search(
                    query,
                    tClass);
            return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        } else {
            query.setPageable(PageRequest.of(0, DEFAULT_QUERY_NUM));
            List<T> list = new ArrayList<>();
            try (var searchHitsIterator = elasticsearchRestTemplate.searchForStream(query, tClass)) {
                while (searchHitsIterator.hasNext()) {
                    var searchHit = searchHitsIterator.next();
                    list.add(searchHit.getContent());
                    if (list.size() > pageable.getPageSize()) {
                        break;
                    }
                }
            }
            return list;
        }
    }

}
