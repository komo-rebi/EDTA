package com.edta.framework.component.common;

/**
 * @author wangluyao
 * @date 2022/6/20 19:45
 * @description
 */
@FunctionalInterface
public interface CallBack<T, R> {

    R handle(T t);
}
