package com.edta.framework.component;

import com.edta.framework.component.es.UserDO;
import com.edta.framework.component.es.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

/**
 * @author wangluyao
 * @date 2022/6/14 11:34
 * @description
 */
@SpringBootTest(classes = Starter.class)
@ActiveProfiles(profiles = {"redis", "db", "es"})
@EnableElasticsearchRepositories(basePackages = {"com.edta.framework.component.es"})
class EsRestTemplateConfigTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private UserMapper userMapper;

    @Test
    void testEs() {
        UserDO userDO = new UserDO();
        userDO.setUsername("wangluyao");
        userDO.setPassword("123qwe");
        UserDO userDO1 = userMapper.save(userDO);
        System.out.println(userDO1);

        Optional<UserDO> userDO2 = userMapper.findById(userDO1.getId());
        System.out.println(userDO2.get());
        userMapper.deleteById(userDO2.get().getId());
    }
}