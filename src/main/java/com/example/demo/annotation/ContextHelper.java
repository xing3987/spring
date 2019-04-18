package com.example.demo.annotation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    public ContextHelper() {
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

}
