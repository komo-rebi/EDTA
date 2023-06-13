package com.edta.framework.component.dto.response;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wly
 * @date 2021/01/13 17:06:35
 */
@Configuration
public class ResponseCodeConfiguration {

    @Bean
    @ConditionalOnMissingBean(IResponseCodeFactory.class)
    public IResponseCodeFactory responseCodeFactoryConfiguration() {
        return new ResponseCodeFactory();
    }
}
