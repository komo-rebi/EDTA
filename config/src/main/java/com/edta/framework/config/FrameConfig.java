package com.edta.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：wangluyao
 * @date ：2022/3/20 19:49
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@Configuration
@ComponentScan("com.edta.framework")
public class FrameConfig {

    public FrameConfig() {
        log.info("Load FrameConfig");
    }
}
