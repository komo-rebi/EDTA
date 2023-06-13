package com.edta.framework.component.mq;

/**
 * @author wangluyao
 * @date 2022/6/23 11:36
 * @description
 */
public interface DataMapper<V> {

    <T> V entityToValue(T t);

    <T> T valueToEntity(V value, Class<T> data);
}
