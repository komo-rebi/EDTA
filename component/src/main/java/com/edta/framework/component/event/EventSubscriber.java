package com.edta.framework.component.event;

/**
 * @author wangluyao
 * @date 2022/6/21 16:36
 * @description
 */
public interface EventSubscriber<T> {

    void handle(T event);
}
