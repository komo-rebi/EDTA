package com.edta.framework.component.queryhelper;

/**
 * @author wangluyao
 * @date 2022/5/18 15:36
 * @description
 */
public class EqQueryField<T> extends AbstractQueryField<T> {

    public EqQueryField(String fieldName, T fieldValue) {
        super(fieldName, fieldValue);
    }
}
