package com.edta.framework.component.curd.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangluyao
 * @date 2022/7/29 13:51
 * @description
 */
@SpringBootApplication(scanBasePackages = "com.edta.framework")
public class DemoStarter {

    public static void main(String[] args) {
        SpringApplication.run(DemoStarter.class, args);
    }
}
