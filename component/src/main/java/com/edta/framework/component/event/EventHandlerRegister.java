package com.edta.framework.component.event;

import cn.hutool.core.util.ClassUtil;
import com.edta.framework.component.exception.Assert;
import com.edta.framework.component.util.BeanFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author wangluyao
 * @date 2022/6/23 17:25
 * @description
 */
@Slf4j
public class EventHandlerRegister {

    private String path;
    private List<String> paths;

    public EventHandlerRegister(String path) {
        this(Collections.singletonList(path));
    }

    @SneakyThrows
    public EventHandlerRegister(List<String> paths) {
        Assert.notEmpty(paths);
        for (String path : paths) {
            Set<Class<?>> classes = ClassUtil.scanPackageBySuper(path, EventSubscriber.class);
            for (Class<?> tClass : classes) {
                try {
                    EventSubscriber handler = (EventSubscriber) BeanFactory.create(tClass);
                    Class type = ClassUtil.getTypeArgument(handler.getClass());
                    EventBus.INSTANCE.register(handler, type);
                } catch (Exception e) {
                    log.error("EventHandler register failed, class: {}, error: {}", tClass.getName(), e.getMessage(), e);
                }
            }
        }
    }
}
