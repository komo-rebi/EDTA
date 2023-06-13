package com.edta.framework.component.queryhelper;

import java.util.List;

/**
 * @author wangluyao
 * @date 2022/5/18 15:49
 * @description
 */
public interface Query {

    List<QueryField<?>> getQueryField();

    List<OrderField> getOrderField();

    void add(QueryField<?> queryField);

    void add(OrderField orderField);

    void add(Page page);

    Page getPage();

    void add(SourceField sourceField);

    SourceField getSourceField();

    void add(GroupField groupField);

    GroupField getGroupField();
}
