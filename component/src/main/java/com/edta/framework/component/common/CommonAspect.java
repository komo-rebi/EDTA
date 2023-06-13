package com.edta.framework.component.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author wangluyao
 * @date 2022/6/23 19:50
 * @description
 */
@Aspect
@Slf4j
public class CommonAspect {

//    @Pointcut(value = "@within(com.edta.framework.component.catchlog.CatchAndLog) && execution(public * *(..))")
//    public void pointcut() {
//
//    }
//
//    @Before(value = "pointcut()")
//    public void before(JoinPoint joinPoint) {
//        log.info("[before] ==> ");
//    }
//
//    @Around(value = "pointcut()")
//    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        log.info("[around] ==> ");
//        try {
//            return proceedingJoinPoint.proceed();
//        } catch (Throwable e) {
//            throw e;
//        } finally {
//            log.info("[around end] ==> ");
//        }
//    }
//
//
//    @After(value = "pointcut()")
//    public void after(JoinPoint joinPoint) {
//        log.info("[after] ==> ");
//    }
//
//    @AfterReturning(value = "pointcut()")
//    public void afterReturning(JoinPoint joinPoint) {
//        log.info("[afterReturning] ==> ");
//    }
//
//    @AfterThrowing(value = "pointcut()", throwing = "throwable")
//    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) throws Throwable {
//        log.info("[afterThrowingAdvice] ==> ");
//    }

}
