package com.edta.framework.component.es;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.edta.framework.component.queryhelper.*;
import org.apache.commons.collections.CollectionUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SourceFilter;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author wangluyao
 * @date 2022/6/18 10:11
 * @description
 */
public class QueryProviderHelper {

    public static <Q extends AbstractQuery> NativeSearchQuery setQueryWrapper(Q query) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        for (QueryField<?> q : query.getQueryField()) {
            if (q instanceof EqQueryField) {
                boolQueryBuilder.must(QueryBuilders.termQuery(q.getFieldName(), q.getFieldValue()));
                continue;
            }
            if (q instanceof LikeQueryField) {
                boolQueryBuilder.must(QueryBuilders.wildcardQuery(q.getFieldName(), "*" + q.getFieldValue() + "*"));
                continue;
            }
            if (q instanceof NeQueryField) {
                boolQueryBuilder.mustNot(QueryBuilders.termQuery(q.getFieldName(), q.getFieldValue()));
                continue;
            }
            if (q instanceof InQueryField) {
                Collection<?> value;
                if (q.getFieldValue() instanceof Collection) {
                    value = (Collection<?>) q.getFieldValue();
                } else if (q.getFieldValue() instanceof String) {
                    value = StrUtil.split(((String) q.getFieldValue()).replace(" ", ""), ",");
                } else {
                    value = Arrays.asList(q.getFieldValue());
                }
                boolQueryBuilder.must(QueryBuilders.termsQuery(q.getFieldName(), value));
                continue;
            }
            if (q instanceof RangeQueryField) {
                RangeQueryField<?> rangeQueryField = (RangeQueryField<?>) q;
                if (rangeQueryField.getContainBoundary()) {
                    if (rangeQueryField instanceof GtQueryField) {
                        boolQueryBuilder.must(QueryBuilders.rangeQuery(q.getFieldName()).gte(q.getFieldValue()));
                    } else {
                        boolQueryBuilder.must(QueryBuilders.rangeQuery(q.getFieldName()).lte(q.getFieldValue()));
                    }
                } else {
                    if (rangeQueryField instanceof GtQueryField) {
                        boolQueryBuilder.must(QueryBuilders.rangeQuery(q.getFieldName()).gt(q.getFieldValue()));
                    } else {
                        boolQueryBuilder.must(QueryBuilders.rangeQuery(q.getFieldName()).lt(q.getFieldValue()));
                    }
                }
                continue;
            }
        }
        for (OrderField orderField : query.getOrderField()) {
            SortBuilder<?> sortBuilder = SortBuilders.fieldSort(orderField.getOrder());
            if (orderField.isAsc()) {
                sortBuilder.order(SortOrder.ASC);
            } else {
                sortBuilder.order(SortOrder.DESC);
            }
            nativeSearchQueryBuilder.withSort(sortBuilder);
        }
        if (query.getSourceField() != null) {
            String[] includes = null;
            String[] excludes = null;
            if (CollectionUtils.isNotEmpty(query.getSourceField().getIncludes())) {
                includes = query.getSourceField().getIncludes().toArray(new String[0]);
            }
            if (CollectionUtils.isNotEmpty(query.getSourceField().getExcludes())) {
                excludes = query.getSourceField().getExcludes().toArray(new String[0]);
            }
            SourceFilter sourceFilter = new FetchSourceFilter(includes, excludes);
            nativeSearchQueryBuilder.withSourceFilter(sourceFilter);
        }
        if (query.getGroupField() != null && StringUtils.isNotBlank(query.getGroupField().getField())) {
            // TODO
        }
        if (query.getPage() != null) {
            PageRequest pageRequest = PageRequest.of(query.getPage().getPageIndex(), query.getPage().getPageSize());
            nativeSearchQueryBuilder.withPageable(pageRequest);
        }
        return nativeSearchQueryBuilder.build();
    }
}
