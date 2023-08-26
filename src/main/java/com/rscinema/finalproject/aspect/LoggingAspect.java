package com.rscinema.finalproject.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.rscinema.finalproject.service.*.*(..)) || execution(* com.rscinema.finalproject.controller.*.*(..))")
    public void beforePointcut(){
    }

    @Before("beforePointcut()")
    public void before(JoinPoint joinPoint){
        log.info("Method invoked :: {}",joinPoint.getSignature());
    }

    @After("execution(* com.rscinema.finalproject.service.*.*(..))")
    public void afterServiceMethodAccess(JoinPoint joinPoint){
        log.info("Method in service completed :: {}",joinPoint.getSignature());
    }

    @After("execution(* com.rscinema.finalproject.controller.*.*(..))")
    public void afterControllerMethodAccess(JoinPoint joinPoint){
        log.info("Method in controller completed :: {}",joinPoint.getSignature());
    }
    @AfterReturning("execution(* com.rscinema.finalproject.service.*.*(..))")
    public void logAfterReturnServiceMethodAccess(JoinPoint joinpoint){
        log.info("Service AfterReturn with signature {} is completed",joinpoint.getSignature());
    }
    @AfterReturning("execution(* com.rscinema.finalproject.service.*.*(..))")
    public void logAfterReturnControllerMethodAccess(JoinPoint joinpoint){
        log.info("Controller AfterReturn with signature {} is completed",joinpoint.getSignature());
    }

    @AfterThrowing(value = "execution(* com.rscinema.finalproject.service.*.*(..))",throwing = "exception")
    public void logAfterThrowingServiceMethodAccess(JoinPoint joinpoint, Throwable exception){
        log.info("Service After Exception with signature {} is completed",joinpoint.getSignature());
        log.info("With exception {}",exception.getMessage());
    }

    @AfterThrowing(value = "execution(* com.rscinema.finalproject.controller.*.*(..))",throwing = "exception")
    public void logAfterThrowingRestMethodAccess(JoinPoint joinpoint, Throwable exception){
        log.info("Controller AfterReturn with signature {} is completed",joinpoint.getSignature());
        log.info("With exception {}",exception.getMessage());
    }

    @Around("@annotation(com.rscinema.finalproject.aspect.annotation.MeasureTime)")
    public Object logAroundReturnMethodAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        log.info("Controller startTime {} {} ",startTime,joinPoint.getSignature());
        Object returnValue = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        log.info("Execution time is {} for controller {}",(endTime-startTime),joinPoint.getSignature());
        return returnValue;
    }

}
