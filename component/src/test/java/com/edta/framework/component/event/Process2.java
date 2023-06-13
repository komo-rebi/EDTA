package com.edta.framework.component.event;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangluyao
 * @date 2022/10/20 19:49
 * @description 消费
 */
@Slf4j
public class Process2 {

    public static void main(String[] args) throws InterruptedException {
        // register
        EventBus.INSTANCE.register(new EventSubscriber<CreateUserEvent>() {
            @Override
            public void handle(CreateUserEvent event) {
                log.info("接收到事件: {}", event.getUserId());
            }
        }, CreateUserEvent.class);

        while (true) {
            Thread.sleep(1000);
        }

    }
}
