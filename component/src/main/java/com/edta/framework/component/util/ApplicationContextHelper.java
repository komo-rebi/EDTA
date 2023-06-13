package com.edta.framework.component.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wly
 * @date 2020/12/22 22:46:05
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> tClass) {
        T beanInstance = null;
        // 1.按type查
        try {
            beanInstance = applicationContext.getBean(tClass);
        } catch (Exception e) {
        }
        // 2.按name查
        if (beanInstance == null) {
            String simpleName = tClass.getSimpleName();
            simpleName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
            beanInstance = (T) applicationContext.getBean(simpleName);
        }
        if (beanInstance == null) {
            throw new RuntimeException("Component " + tClass + " can not be found in Spring Container");
        }
        return beanInstance;
    }

    public static Object getBean(String className) {
        return ApplicationContextHelper.applicationContext.getBean(className);
    }

    public static <T> T getBean(String name, Class<T> tClass) {
        return ApplicationContextHelper.applicationContext.getBean(name, tClass);
    }

    public static <T> T getBean(Class<T> requiredType, Object... params) {
        return ApplicationContextHelper.applicationContext.getBean(requiredType, params);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }
}
