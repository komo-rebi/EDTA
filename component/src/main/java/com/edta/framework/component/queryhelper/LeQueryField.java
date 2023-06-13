package com.edta.framework.component.queryhelper;

/**
 * @author wangluyao
 * @date 2022/5/18 20:01
 * @description
 */
public class LeQueryField<T> extends RangeQueryField<T> {

    public LeQueryField(String fieldName, T fieldValue, Boolean containBoundary) {
        super(fieldName, fieldValue, containBoundary);
    }

    public LeQueryField(String fieldName, T fieldValue) {
        super(fieldName, fieldValue);
    }
}
