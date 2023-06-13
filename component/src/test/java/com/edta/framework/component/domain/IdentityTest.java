package com.edta.framework.component.domain;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

/**
 * @author wangluyao
 * @date 2022/7/4 18:01
 * @description
 */
class IdentityTest {

    @Test
    void identityTest() {
        UserId userId = new UserId("wangluyao");

        System.out.println(JSON.toJSONString(userId));
    }

    static class UserId extends Identity<String> {

        public UserId(String id) {
            super(id);
        }
    }
}