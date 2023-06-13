package com.edta.framework.component.queryhelper;

/**
 * @author wangluyao
 * @date 2022/5/18 15:33
 * @description
 */
public class InQueryField<T> extends AbstractQueryField<T> {

    public InQueryField(String fieldName, T fieldValue) {
        super(fieldName, fieldValue);
    }
}
