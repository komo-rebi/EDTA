package com.edta.framework.component.domain;

/**
 * 领域事件
 *
 * @author wly
 * @date 2021/01/11 10:09:44
 */
public abstract class Event<T extends Event<T>> {

    private final Long time = System.currentTimeMillis();

    public Long getTime() {
        return time;
    }
}
