package com.NewGen.MiniBank.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Around("execution (* com.NewGen.MiniBank.controller.*.*(..)")
    public Object logExecution(ProceedingJoinPoint jp) throws Throwable {
        String methodName=jp.getSignature().getName();
        String className=jp.getSignature().getDeclaringTypeName();
        Long start=System.currentTimeMillis();
        try {
            log.info("[START] {}.{}",methodName,className);
            Object obj=jp.proceed();
            long end=System.currentTimeMillis()-start;
            log.info("[END] {}.{} took {} ms", className, methodName, end);
            return obj;
        }
        catch (Throwable ex){
            long timeTaken = System.currentTimeMillis() - start;
            log.error("[ERROR] {}.{} failed after {} ms → {}",
                    className, methodName, timeTaken, ex.getClass().getSimpleName());
            throw ex;
        }
    }
}
