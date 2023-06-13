package com.edta.framework.component.curd;

import com.edta.framework.component.dto.PageResult;

/**
 * @author wangluyao
 * @date 2022/7/28 17:47
 * @description
 */
@FunctionalInterface
public interface QFunction<Q, Domain> {

    PageResult<Domain> exec(Q query);

    class DefaultQFunction<Q, Domain> implements QFunction<Q, Domain> {

        @Override
        public PageResult<Domain> exec(Q query) {
            return PageResult.of();
        }
    }
}
