package com.edta.framework.component.catchlog;

import com.edta.framework.component.event.EventBus;
import com.edta.framework.component.exception.BadParamException;
import com.edta.framework.component.exception.BizException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author wly
 * @date 2020/12/22 23:00:16
 */
@Slf4j
@Aspect
public class CatchLogAspect {

    private final ThreadLocal<LogEntity> localLogEntity = new ThreadLocal<>();
    private final ThreadLocal<String> localSessionId = new ThreadLocal<>();

    public CatchLogAspect() {
        log.debug("CatchLogAspect new");
    }

    @Pointcut("@annotation(com.edta.framework.component.catchlog.CatchAndLog)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        LogEntity logEntity = new LogEntity();
        localLogEntity.set(logEntity);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        CatchAndLog logAnnotation = methodSignature.getMethod().getAnnotation(CatchAndLog.class);
        if (logAnnotation.isStart() || StringUtils.isBlank(localSessionId.get())) {
            localSessionId.set(UUID.randomUUID().toString().replace("-", ""));
        }
        if (StringUtils.isNotBlank(localSessionId.get())) {
            logEntity.setParentSessionId(localSessionId.get());
        }
        logEntity.setStartTime(System.currentTimeMillis());
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        logEntity.setMethod(StringUtils.isNotBlank(logAnnotation.method()) ? logAnnotation.method() : method.toString());
        logEntity.setParams(joinPoint.getArgs());
        return logResponse(joinPoint);
    }

    @SneakyThrows
    private Object logResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        LogEntity logEntity = localLogEntity.get();
        Object response = null;
        try {
            response = joinPoint.proceed();
            logEntity.success();
        } catch (Throwable e) {
            logEntity.setStackTrace(ExceptionUtils.getStackTrace(e));
            logEntity.setError(e.getMessage());
            logEntity.fail();
            throw e;
        } finally {
            logEntity.setEndTime(System.currentTimeMillis());
            logEntity.setResult(response);
            EventBus.INSTANCE.publish(logEntity);
        }
        return response;
    }

    public String getCurrentSessionId() {
        return localSessionId.get();
    }

    @Deprecated
    private Object handleException(ProceedingJoinPoint joinPoint, Throwable e) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Class returnType = ms.getReturnType();
        if (e instanceof BadParamException) {
            log.error(e.getMessage());
            return ResponseHandler.handle(returnType, (BadParamException) e);
        } else if (e instanceof BizException) {
            log.error(e.getMessage());
            return ResponseHandler.handle(returnType, (BizException) e);
        }
        throw e;
    }


}
