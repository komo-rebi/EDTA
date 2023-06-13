package com.edta.framework.component.event;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangluyao
 * @date 2022/6/23 17:41
 * @description
 */
@Configuration
public class EventHandlerRegisterConfiguration {

    @Bean
    @ConditionalOnMissingBean(EventHandlerRegister.class)
    public EventHandlerRegister eventHandlerRegister() {
        return new EventHandlerRegister("com.edta.framework");
    }
}
