package com.edta.framework.component.domain;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author ：wangluyao
 * @date ：2022/3/11 15:00
 * @description：
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface Factory {
}
