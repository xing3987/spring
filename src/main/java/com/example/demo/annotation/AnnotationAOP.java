package com.example.demo.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAOP {

    @Pointcut("@annotation(com.example.demo.annotation.Agent)") //对demo包下的agent注解生效AOP
    public void annotationPointCut() {
        System.out.println("add pointcut by method");
    }

/*    @Before("@annotation(com.example.demo.annotation.Agent)") //对demo包下的agent注解生效AOP
    public void before() {
        System.out.println("before aop..");
    }

    @After("@annotation(com.example.demo.annotation.Agent)") //对demo包下的agent注解生效AOP
    public void after() {
        System.out.println("after aop..");
    }*/

    @Around("annotationPointCut()")//ProceedingJoinPoint把原方法传递进入aop
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("执行环绕AOP：before...");
        Object object = pjp.proceed();//执行原注解的方法
        System.out.println("执行环绕AOP：after...");
        return object;//返回原方法的值
    }

    /*@Before("annotationPointCut()")
    public void before() {
        System.out.println("before aop method..");
    }*/
}
