package com.example.demo.annotation;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 注解使用类
 */
public class AnnotationHelper {

    /**
     * 提供给外部调用
     * @param agentBean
     * @param methodName
     * @param arg
     */
    public static void getAgent(String agentBean, String methodName, String arg) {
        getAgent(agentBean, methodName, arg, String.class);
    }


    /**
     * 无参数static方法的代理
     * @param agentBean
     * @param methodName
     */
    public static void getStaticAgent(String agentBean, String methodName) {
        Object bean = ContextHelper.getContext().getBean(agentBean);//加载传入的bean
        if (null == bean) {
            throw new RuntimeException("no bean found by :" + agentBean);
        } else {
            Class<?> clazz = bean.getClass();
            if (null == clazz.getAnnotation(Agent.class)) {//判断传入的bean是否用@Agent管理
                throw new RuntimeException("this bean is not admin by @Agent");
            } else {
                MethodType methodType = MethodType.methodType(Void.TYPE);//设置方法的类型
                MethodHandle methodHandle = null;
                try {
                    //加载方法提交对象
                    methodHandle = MethodHandles.lookup().findStatic(clazz, methodName, methodType);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    methodHandle.invoke();//执行方法
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 有参数static方法的代理
     * @param agentBean
     * @param methodName
     * @param arg
     * @param argClass
     */
    private static void getStaticAgent(String agentBean, String methodName, Object arg, Class<?> argClass) {
        Object bean = ContextHelper.getContext().getBean(agentBean);//加载传入的bean
        if (null == bean) {
            throw new RuntimeException("no bean found by :" + agentBean);
        } else {
            Class<?> clazz = bean.getClass();
            if (null == clazz.getAnnotation(Agent.class)) {//判断传入的bean是否用@Agent管理
                throw new RuntimeException("this bean is not admin by @Agent");
            } else {
                MethodType methodType = MethodType.methodType(Void.TYPE, argClass);//设置方法的类型,参数的类型
                MethodHandle methodHandle = null;
                try {
                    //加载方法提交对象,如果是static方法使用findStatic
                    methodHandle = MethodHandles.lookup().findStatic(clazz, methodName, methodType);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    //如果是static方法使用只要传arg即可
                    methodHandle.invoke(arg);//执行方法
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    /**
     * 有参数非static方法的代理
     * @param agentBean
     * @param methodName
     * @param arg
     * @param argClass
     */
    private static void getAgent(String agentBean, String methodName, Object arg, Class<?> argClass) {
        Object bean = ContextHelper.getContext().getBean(agentBean);//加载传入的bean
        if (null == bean) {
            throw new RuntimeException("no bean found by :" + agentBean);
        } else {
            Class<?> clazz = bean.getClass();
            if (null == clazz.getAnnotation(Agent.class)) {//判断传入的bean是否用@Agent管理
                throw new RuntimeException("this bean is not admin by @Agent");
            } else {
                MethodType methodType = MethodType.methodType(Void.TYPE, argClass);//设置方法的类型,参数的类型
                MethodHandle methodHandle = null;
                try {
                    //加载方法提交对象，非static方法使用findVirtual加载方法
                    methodHandle = MethodHandles.lookup().findVirtual(clazz, methodName, methodType);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    //加载方法提交对象，非static方法使用findVirtual加载方法，传参数要传入bean
                    methodHandle.invoke(bean, arg);//执行方法
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 有参数，有返回值的代理
     * @param agentBean
     * @param methodName
     * @param arg
     * @return
     */
    public static Object returnAgent(String agentBean, String methodName, String arg, Class<?> backClazz) {
        return returnAgent(agentBean, methodName, arg, String.class, backClazz);
    }

    /**
     * 有参数非static方法的代理
     * @param agentBean
     * @param methodName
     * @param arg
     * @param argClass
     */
    private static Object returnAgent(String agentBean, String methodName, Object arg, Class<?> argClass, Class<?> backClazz) {
        Object bean = ContextHelper.getContext().getBean(agentBean);//加载传入的bean
        if (null == bean) {
            throw new RuntimeException("no bean found by :" + agentBean);
        } else {
            Class<?> clazz = bean.getClass();
            if (null == clazz.getAnnotation(Agent.class)) {//判断传入的bean是否用@Agent管理
                throw new RuntimeException("this bean is not admin by @Agent");
            } else {
                MethodType methodType = MethodType.methodType(backClazz, argClass);//设置方法返回的类型,参数的类型
                MethodHandle methodHandle = null;
                try {
                    //加载方法提交对象，非static方法使用findVirtual加载方法
                    methodHandle = MethodHandles.lookup().findVirtual(clazz, methodName, methodType);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    //加载方法提交对象，非static方法使用findVirtual加载方法，传参数要传入bean
                    return methodHandle.invoke(bean, arg);//执行方法
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
