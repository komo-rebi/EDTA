package com.edta.framework.component.es;

import com.edta.framework.component.page.PageData;
import com.edta.framework.component.queryhelper.DefaultQuery;
import com.edta.framework.component.queryhelper.PageField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author wangluyao
 * @date 2023/3/1 18:59
 * @description
 */
@SpringBootTest(classes = Starter.class)
@ActiveProfiles(profiles = {"es"})
class EsCurdTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    void list() {
        EsCurd esCurd = new EsCurd(elasticsearchRestTemplate);
//        UserDO userDO = new UserDO();
//        userDO.setId(UUID.randomUUID().toString());
//        userDO.setUsername("wangluyao");
//        userDO.setPassword("123qwe");
//        esCurd.save(userDO, UserDO.class);
        DefaultQuery defaultQuery = new DefaultQuery();
        defaultQuery.add(new PageField(0, Integer.MAX_VALUE));
        PageData<UserRuleDO> userDOPageData = esCurd.list(defaultQuery, UserRuleDO.class);
        while (userDOPageData.hasNext()) {
            System.out.println(userDOPageData.next());
        }
    }

    @Test
    void count() {
    }
}