package com.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: suragi
 * @Date: 2023/5/30 15:47
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Pointcut("@annotation(com.example.annotation.SysLog)")
    public void pointCut(){
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        joinPoint.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //记录类名
        String clazzName = joinPoint.getTarget().getClass().getName();
        //记录方法名
        String methodName = joinPoint.getSignature().getName();
        log.info(clazzName + ": " + methodName + "方法执行时长：" + time + " ms");
    }
}

