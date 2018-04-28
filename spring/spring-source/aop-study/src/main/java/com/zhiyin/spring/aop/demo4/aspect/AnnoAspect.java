package com.zhiyin.spring.aop.demo4.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AnnoAspect {

    @Pointcut("execution(* com..*(..))")
    public void beforePointcut() {

    }

    @Before("beforePointcut()")
    public void before1() {
        log.info("-----------before-----------");
    }

}