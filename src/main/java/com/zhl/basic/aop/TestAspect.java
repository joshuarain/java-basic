package com.zhl.basic.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Lenovo
 * @title: TestAspect
 * @projectName basic
 * @description: TODO
 * @date 2019/12/4 10:10
 */
@Aspect
@Component
public class TestAspect {

    @Pointcut("@annotation(com.zhl.basic.aop.AopAction)")
    public void annotationPoinCut(){}

    @After("annotationPoinCut()")
    public void after(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AopAction action = method.getAnnotation(AopAction.class);
        System.out.println("注解式拦截 "+action.name());
    }

    @Before("execution(* com.zhl.basic.aop.XXXXService.*(..))")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则式拦截,"+method.getName());
    }

}
