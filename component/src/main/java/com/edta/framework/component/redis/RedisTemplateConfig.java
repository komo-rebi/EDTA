package com.edta.framework.component.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author wangluyao
 * @date 2022/4/24 19:13
 * @description
 */
@Slf4j
@ConditionalOnClass(RedisTemplate.class)
@Configuration
public class RedisTemplateConfig {

    public RedisTemplateConfig() {
        log.debug("Load");
    }
}
