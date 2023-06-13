package com.edta.framework.component.catchlog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangluyao
 * @date 2022/7/4 13:47
 * @description
 */
@SpringBootTest(classes = CatchAndLogTestStarter.class)
class CatchAndLogTest {

    @Autowired
    private UserService userService;

    @Test
    void logTest() {
        userService.addUser("wangluyao", "111231f");
    }

}
