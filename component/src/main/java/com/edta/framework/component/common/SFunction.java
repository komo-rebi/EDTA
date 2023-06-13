package com.edta.framework.component.common;

/**
 * @author wangluyao
 * @date 2022/7/1 23:39
 * @description
 */
@FunctionalInterface
public interface SFunction<T, R> {

    R apply(T t);
}
