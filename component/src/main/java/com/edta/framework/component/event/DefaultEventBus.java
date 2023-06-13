package com.edta.framework.component.event;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangluyao
 * @date 2022/6/21 16:56
 * @description
 */
@Slf4j
public class DefaultEventBus implements EventBus {

    private final Map<Type, List<EventSubscriber<?>>> eventHandlerMap = new LinkedHashMap<>();

    @Override
    public synchronized <T> void register(EventSubscriber<T> eventSubscriber, Class<T> type) {
        var list = eventHandlerMap.get(type);
        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<>();
        }
        list.add(eventSubscriber);
        eventHandlerMap.put(type, list);
    }

    @Override
    public <T> void unregister(EventSubscriber<T> eventSubscriber, Class<T> type) {
        if (!eventHandlerMap.containsKey(type)) {
            return;
        }
        eventHandlerMap.get(type).remove(eventSubscriber);
    }

    @Override
    public <T> void publish(T event) {
        List<Class<?>> listSuperClass = new ArrayList<>();
        listSuperClass.add(event.getClass());
        Class<?> superclass = event.getClass().getSuperclass();
        while (superclass != null) {
            listSuperClass.add(superclass);
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                break;
            }
        }
        for (Class<?> tClass : listSuperClass) {
            publish(event, tClass);
        }
    }

    private <T> void publish(T event, Type type) {
        var list = eventHandlerMap.get(type);
        if (list == null) {
            return;
        }
        list.forEach(eventHandler -> {
            ((EventSubscriber<T>) eventHandler).handle(event);
        });
    }
}
