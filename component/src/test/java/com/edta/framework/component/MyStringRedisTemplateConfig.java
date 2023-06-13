package com.edta.framework.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author wangluyao
 * @date 2022/4/28 16:00
 * @description
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class MyStringRedisTemplateConfig {

    private String host;
    private Integer port;
    private String password;
    private Integer database;

    @Bean("myRedisTemplate")
    public StringRedisTemplate redisTemplateConfig() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(password);
        redisStandaloneConfiguration.setDatabase(database);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        lettuceConnectionFactory.afterPropertiesSet();
        return new StringRedisTemplate(lettuceConnectionFactory);
    }
}