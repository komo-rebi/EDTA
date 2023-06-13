package com.edta.framework.component.domain;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author ：wangluyao
 * @date ：2022/3/11 18:27
 * @description: 组装器
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface Assembler {
}
