package com.edta.framework.component;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author wangluyao
 * @date 2022/10/9 19:35
 * @description
 */
@SpringBootTest(classes = MysqlStarter.class)
@ActiveProfiles(value = "mysql")
public class MysqlTest {

    //    @Autowired
    private RoleService roleService;

    @Test
    void getOne() {
        roleService.getOne();
    }
}
