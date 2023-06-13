package com.edta.framework.component.domain;

import com.edta.framework.component.event.DataSource;

import java.lang.annotation.*;

/**
 * // TODO
 *
 * @author wangluyao
 * @date 2022/4/18 14:34
 * @description
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EventDesc {

    /**
     * 该事件使用的通道名称
     *
     * @return
     */
    String channel();

    /**
     * 该事件的名称前缀，用于区分领域
     *
     * @return
     */
    String prefix();

    DataSource dataSource() default DataSource.member;
}
