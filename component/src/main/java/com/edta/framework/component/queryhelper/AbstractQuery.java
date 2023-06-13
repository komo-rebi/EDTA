package com.edta.framework.component.queryhelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangluyao
 * @date 2022/5/18 15:57
 * @description
 */
public abstract class AbstractQuery implements Query {

    private final List<QueryField<?>> queryFields;
    private final List<OrderField> orderFields;
    private Page page;
    private SourceField sourceField;
    private GroupField groupField;


    public AbstractQuery() {
        this.queryFields = new ArrayList<>();
        this.orderFields = new ArrayList<>();
        this.page = new PageField();
        this.sourceField = new SourceField();
    }

    @Override
    public List<QueryField<?>> getQueryField() {
        return queryFields;
    }

    @Override
    public List<OrderField> getOrderField() {
        return orderFields;
    }

    @Override
    public void add(QueryField<?> queryField) {
        queryFields.add(queryField);
    }

    @Override
    public void add(OrderField orderField) {
        orderFields.add(orderField);
    }

    @Override
    public void add(Page page) {
        this.page = page;
    }

    @Override
    public Page getPage() {
        return page;
    }

    @Override
    public void add(SourceField sourceField) {
        this.sourceField = sourceField;
    }

    @Override
    public SourceField getSourceField() {
        return sourceField;
    }

    @Override
    public void add(GroupField groupField) {
        this.groupField = groupField;
    }

    @Override
    public GroupField getGroupField() {
        return groupField;
    }
}
