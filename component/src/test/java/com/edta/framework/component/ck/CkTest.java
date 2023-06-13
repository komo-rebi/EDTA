package com.edta.framework.component.ck;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author wangluyao
 * @date 2022/10/9 19:35
 * @description
 */
//@SpringBootTest(classes = CkStarter.class)
@ActiveProfiles(value = "ck")
public class CkTest {

    //    @Autowired
    private RoleService roleService;

    @Test
    void getOne() {
        roleService.getOne();
    }
}
