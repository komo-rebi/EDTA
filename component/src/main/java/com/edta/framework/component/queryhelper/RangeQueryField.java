package com.edta.framework.component.queryhelper;

/**
 * @author wangluyao
 * @date 2022/5/18 19:50
 * @description
 */
public abstract class RangeQueryField<T> extends AbstractQueryField<T> {

    private final Boolean containBoundary;

    public RangeQueryField(String fieldName, T fieldValue, Boolean containBoundary) {
        super(fieldName, fieldValue);
        this.containBoundary = containBoundary;
    }

    public RangeQueryField(String fieldName, T fieldValue) {
        super(fieldName, fieldValue);
        this.containBoundary = false;
    }

    public Boolean getContainBoundary() {
        return containBoundary;
    }
}
