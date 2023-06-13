package com.edta.framework.component.catchlog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wly
 * @date 2020/12/22 22:59:32
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CatchAndLog {

    boolean isStart() default false;

    String method() default "";
}
