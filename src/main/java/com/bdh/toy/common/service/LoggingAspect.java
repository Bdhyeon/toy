package com.bdh.toy.common.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.bdh.toy..*(..))")
    private void cut(){

    }

    @Around("cut()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("{}.{}", className, methodName);

        // 파라미터 받아오기
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0){
            log.info("- Args is empty");
        } else {
            for (Object arg : args) {
                log.info("- parameter type = {} / value = {}", arg.getClass().getSimpleName(), arg);
            }
        }

        // proceed 호출하여 실제 메소드 실행
        Object returnObj = joinPoint.proceed();

        // 메서드 리턴값 로깅
        log.info("-- return type = {} / value = {}", returnObj.getClass().getSimpleName(), returnObj);

        return returnObj;
    }
}
