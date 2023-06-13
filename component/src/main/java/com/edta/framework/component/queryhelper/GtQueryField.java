package com.edta.framework.component.queryhelper;

/**
 * @author wangluyao
 * @date 2022/5/18 20:00
 * @description
 */
public class GtQueryField<T> extends RangeQueryField<T> {

    public GtQueryField(String fieldName, T fieldValue, Boolean containBoundary) {
        super(fieldName, fieldValue, containBoundary);
    }

    public GtQueryField(String fieldName, T fieldValue) {
        super(fieldName, fieldValue);
    }
}
