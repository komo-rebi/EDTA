package com.edta.framework.component.event;

/**
 * @author wangluyao
 * @date 2022/6/21 16:50
 * @description
 */
public interface EventBus {

    EventBus INSTANCE = new DefaultEventBus();

    <T> void register(EventSubscriber<T> eventSubscriber, Class<T> tClass);

    <T> void unregister(EventSubscriber<T> eventSubscriber, Class<T> tClass);

    <T> void publish(T event);
}
