package com.edta.framework.component.queryhelper;

/**
 * @author wangluyao
 * @date 2022/5/18 15:27
 * @description
 */
public interface QueryField<T> {

    String getFieldName();

    T getFieldValue();
}
