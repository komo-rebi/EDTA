package com.edta.framework.component.queryhelper;

import lombok.AllArgsConstructor;

/**
 * @author wangluyao
 * @date 2022/5/18 15:39
 * @description
 */
@AllArgsConstructor
public class AbstractQueryField<T> implements QueryField<T> {

    private String fieldName;
    private T fieldValue;

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public T getFieldValue() {
        return fieldValue;
    }
}
