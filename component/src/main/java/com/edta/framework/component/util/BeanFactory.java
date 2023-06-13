package com.edta.framework.component.util;

import lombok.SneakyThrows;
import lombok.var;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangluyao
 * @date 2022/6/23 19:19
 * @description
 */
public class BeanFactory {

    private static final List<Class<? extends Annotation>> springStereotypes = Arrays.asList(
            Component.class,
            Controller.class,
            Repository.class,
            Service.class
    );

    @SneakyThrows
    public static Object create(Class<?> tClass) {
        if (isSpringStereotypes(tClass)) {
            return ApplicationContextHelper.getBean(tClass);
        } else {
            return tClass.newInstance();
        }
    }

    private static boolean isSpringStereotypes(Class<?> tClass) {
        for (var annoClass : springStereotypes) {
            if (tClass.isAnnotationPresent(annoClass)) {
                return true;
            }
        }
        return false;
    }
}
