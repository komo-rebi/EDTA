package com.edta.framework.component.catchlog;

import com.edta.framework.component.event.EventBus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wly
 * @date 2020/12/22 23:01:01
 */
@Configuration
public class CatchLogAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(CatchLogAspect.class)
    public CatchLogAspect catchLogAspect() {
        EventBus.INSTANCE.register(new DefaultLogEventConsumer(), LogEntity.class);
        return new CatchLogAspect();
    }
}
