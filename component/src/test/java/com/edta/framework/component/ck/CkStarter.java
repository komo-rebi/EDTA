package com.edta.framework.component.ck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangluyao
 * @date 2022/10/9 19:27
 * @description
 */
@SpringBootApplication
//@MapperScan("com.edta.framework.component.mysql")
public class CkStarter {

    public static void main(String[] args) {
        SpringApplication.run(CkStarter.class, args);
    }
}
