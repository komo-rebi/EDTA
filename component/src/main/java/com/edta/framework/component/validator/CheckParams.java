package com.edta.framework.component.validator;

import java.lang.annotation.*;

/**
 * @author wangluyao
 * @date 2022年4月8日
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckParams {
}
