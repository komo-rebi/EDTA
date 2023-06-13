package com.edta.framework.component.domain;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author wangluyao
 * @date 2022/4/18 14:34
 * @description
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface Application {
}
