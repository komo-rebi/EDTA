package com.edta.framework.component.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wly
 * @date 2020/12/22 22:46:16
 */
@Configuration
public class ApplicationContextAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ApplicationContextHelper.class)
    public ApplicationContextHelper applicationContextHelper() {
        return new ApplicationContextHelper();
    }
}
