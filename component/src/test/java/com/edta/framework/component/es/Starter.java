package com.edta.framework.component.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author wangluyao
 * @date 2022/4/28 13:47
 * @description
 */
@SpringBootApplication(scanBasePackages = {"com.edta.framework.component.es", "com.edta.framework.component.config"})
@EnableElasticsearchRepositories("com.edta.framework.component.es")
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
