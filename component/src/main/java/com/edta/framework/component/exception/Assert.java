package com.edta.framework.component.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author wly
 * @date 2021/01/14 18:43:40
 */
public final class Assert {

    private Assert() {
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BadParamException(message);
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new BizException(message);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "Assert failed: Must be true");
        if (!expression) {
            throw new BadParamException();
        }
    }

    public static void isFalse(boolean expression) {
        isTrue(expression, "Assert failed: Must be false");
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BadParamException(message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "Assert failed: Must not null");
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.size() <= 0) {
            throw new BadParamException(message);
        }
    }

    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, "Assert failed: Must not empty");
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw new BadParamException(message);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, "Assert failed: Must not empty");
    }

    public static void notBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BadParamException(message);
        }
    }

    public static void notBlank(String str) {
        notBlank(str, "Assert failed: Must not blank");
    }

}
