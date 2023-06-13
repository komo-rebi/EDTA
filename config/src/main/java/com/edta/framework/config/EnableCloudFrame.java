package com.edta.framework.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wangluyao
 * @date 2022/6/11 12:01
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({FrameConfig.class})
public @interface EnableCloudFrame {

    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}



