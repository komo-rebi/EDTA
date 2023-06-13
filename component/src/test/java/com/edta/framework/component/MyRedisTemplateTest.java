package com.edta.framework.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author wangluyao
 * @date 2022/4/28 15:42
 * @description
 */
@SpringBootTest(classes = {Starter.class})
@ActiveProfiles(profiles = {"redis", "db", "es"})
public class MyRedisTemplateTest {

    @Autowired
    @Qualifier("myRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void set() {
        stringRedisTemplate.opsForValue().set("testKey", "key");
    }
}
