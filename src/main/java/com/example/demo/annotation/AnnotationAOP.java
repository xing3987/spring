package com.example.demo.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAOP {

    @Pointcut("@annotation(com.example.demo.annotation.Agent)")
    public  void annotationPointCut() {
    }
}
