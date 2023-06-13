package com.edta.framework.component.catchlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangluyao
 * @date 2022/7/4 13:49
 * @description
 */
@SpringBootApplication(scanBasePackages = "com.edta.framework")
public class CatchAndLogTestStarter {

    public static void main(String[] args) {
        SpringApplication.run(CatchAndLogTestStarter.class, args);
    }
}
