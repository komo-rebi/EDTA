package com.edta.framework.component.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.edta.framework.component.queryhelper.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

/**
 * @author wangluyao
 * @date 2022/5/18 15:20
 * @description
 */
public class QueryProviderHelper {

    public static <T, Q extends AbstractQuery> QueryWrapper<T> setQueryWrapper(Q query) {
        QueryWrapper<T> queryWrapper = Wrappers.query();
        for (QueryField<?> queryField : query.getQueryField()) {
            if (queryField instanceof EqQueryField) {
                queryWrapper.eq(queryField.getFieldName(), queryField.getFieldValue());
                continue;
            }
            if (queryField instanceof LikeQueryField) {
                queryWrapper.like(queryField.getFieldName(), queryField.getFieldValue());
                continue;
            }
            if (queryField instanceof NeQueryField) {
                queryWrapper.ne(queryField.getFieldName(), queryField.getFieldValue());
                continue;
            }
            if (queryField instanceof InQueryField) {
                queryWrapper.in(queryField.getFieldName(), (Collection<?>) queryField.getFieldValue());
                continue;
            }
            if (queryField instanceof InSqlQueryField) {
                queryWrapper.inSql(queryField.getFieldName(), ((InSqlQueryField) queryField).getFieldValue());
            }
            if (queryField instanceof RangeQueryField) {
                RangeQueryField<?> rangeQueryField = (RangeQueryField<?>) queryField;
                if (rangeQueryField.getContainBoundary()) {
                    if (rangeQueryField instanceof GtQueryField) {
                        queryWrapper.ge(rangeQueryField.getFieldName(), rangeQueryField.getFieldValue());
                    } else {
                        queryWrapper.le(rangeQueryField.getFieldName(), rangeQueryField.getFieldValue());
                    }
                } else {
                    if (rangeQueryField instanceof GtQueryField) {
                        queryWrapper.gt(rangeQueryField.getFieldName(), rangeQueryField.getFieldValue());
                    } else {
                        queryWrapper.lt(rangeQueryField.getFieldName(), rangeQueryField.getFieldValue());
                    }
                }
                continue;
            }
        }
        for (OrderField orderField : query.getOrderField()) {
            queryWrapper.orderBy(true, orderField.isAsc(), orderField.getOrder());
        }
        if (query.getSourceField() != null) {
            if (CollectionUtils.isNotEmpty(query.getSourceField().getIncludes())) {
                String[] includes = query.getSourceField().getIncludes().toArray(new String[0]);
                queryWrapper.select(includes);
            }
        }
        if (query.getGroupField() != null && StringUtils.isNotBlank(query.getGroupField().getField())) {
            queryWrapper.groupBy(query.getGroupField().getField());
        }
        return queryWrapper;
    }
}
