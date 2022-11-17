package com.superarilo.blogsystem.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
public class LogAnnotationAdvice {

    @Pointcut("execution(public * com.superarilo.blogsystem.controller.*.*(..))")
    public void declareJointPointExpression() {
    }

    @Before("declareJointPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
//        Method method = ((MethodSignature) (joinPoint.getSignature())).getMethod();
//        System.out.println(Arrays.toString(joinPoint.getSignature().getClass().getAnnotatedSuperclass().getAnnotations()));
//        System.out.println(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getMethod());
    }

}
