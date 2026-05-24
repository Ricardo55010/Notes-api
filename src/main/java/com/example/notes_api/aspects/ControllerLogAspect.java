package com.example.notes_api.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class ControllerLogAspect {

    Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("execution(public * com.example.notes_api.Controllers.*.*(..))")
    public void controllerPointcut() {}

    @Around("controllerPointcut()")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Starting execution of {} at time:{}", joinPoint.getSignature(), Instant.now());
        Object result = joinPoint.proceed();
        logger.info("Completed execution of {} at time {}", joinPoint.getSignature(), Instant.now());
        return result;
    }
}
