package com.edta.framework.component;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangluyao
 * @date 2022/10/9 19:27
 * @description
 */
@SpringBootApplication
@MapperScan("com.edta.framework.component.mysql")
public class MysqlStarter {

    public static void main(String[] args) {
        SpringApplication.run(MysqlStarter.class, args);
    }
}
