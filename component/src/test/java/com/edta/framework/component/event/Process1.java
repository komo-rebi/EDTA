package com.edta.framework.component.event;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author wangluyao
 * @date 2022/10/20 19:49
 * @description 生产
 */
@Slf4j
public class Process1 {

    @Test
    void eventBusTest() {
        EventBus.INSTANCE.register(new EventSubscriber<CreateUserEvent>() {
            @Override
            public void handle(CreateUserEvent event) {
                log.info("接收到事件: {}", event);
            }
        }, CreateUserEvent.class);

        EventBus.INSTANCE.publish(new CreateUserEvent(1));
    }
}
