package com.edta.framework.component.validator;

import com.edta.framework.component.exception.BadParamException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.validation.ConstraintViolation;
import javax.validation.UnexpectedTypeException;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

/**
 * @author zlf
 */
@Slf4j
@Aspect
public class ParameterCheckAspect {

    private final Validator validator;

    public ParameterCheckAspect(Validator validator) {
        this.validator = validator;
    }

    @Pointcut(value = "@within(com.edta.framework.component.validator.CheckParams) && execution(public * *(..))")
    public void pointCut1() {
    }

    @Pointcut(value = "@annotation(com.edta.framework.component.validator.CheckParams)")
    public void pointCut2() {
    }


    @Before("pointCut1()")
    public void checkParameter1(JoinPoint joinPoint) {
        checkParameter(joinPoint);
    }

    @Before("pointCut2()")
    public void checkParameter2(JoinPoint joinPoint) {
        checkParameter(joinPoint);
    }

    private void checkParameter(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        for (Object o : objects) {
            if (o == null) {
                continue;
            }
            try {
                cycle(joinPoint, o);
            } catch (Exception e) {
                if (e instanceof BadParamException) {
                    throw e;
                }
                if (e instanceof UnexpectedTypeException) {
                    throw e;
                }
                log.warn("checkParameter cycle failed, use old method: {}", e.getMessage(), e);
                validateObj(joinPoint, o);
            }
        }
    }

    private void validateObj(JoinPoint joinPoint, Object object) {
        Set<ConstraintViolation<Object>> set = validator.validate(object);
        if (!set.isEmpty()) {
            String message = "";
            if (set.iterator().hasNext()) {
                message = "[" + joinPoint.getSignature().toShortString() + "] " +
                        set.iterator().next().getPropertyPath()
                        + ": " + set.iterator().next().getMessage();
            }
            log.debug(message);
            throw new BadParamException(message);
        }
    }

    private void cycle(JoinPoint joinPoint, Object object) {
        validateObj(joinPoint, object);
        if (object instanceof Collection) {
            Collection collections = (Collection) object;
            collections.forEach(collection -> cycle(joinPoint, collection));
        } else {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                boolean accessible = field.isAccessible();
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Object inObj = null;
                try {
                    inObj = field.get(object);
                } catch (Exception e) {
                    continue;
                }
                if (inObj instanceof Collection) {
                    Collection collections = (Collection) inObj;
                    collections.forEach(collection -> cycle(joinPoint, collection));
                } else {
                    validateObj(joinPoint, object);
                }
                field.setAccessible(accessible);
            }
        }
    }

}
