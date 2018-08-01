package com.howen.controller;

import com.howen.config.OperateLog;
import com.howen.entity.LogEntity;
import com.howen.service.StudentService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

//这里不增加Component注解的话,该类是无法进行扫描的
@Aspect
@Component
public class LogAopAtion {
    @Resource
    StudentService studentService;

    @Pointcut("execution(* com.howen.controller.StudentController.*(..))")
    private void controllerAspect(){}

    @Around("controllerAspect()")
    public Object doAdd(ProceedingJoinPoint pjp) throws Throwable {
        LogEntity logEntity = new LogEntity();
        logEntity.setOperator("从系统获取");
        System.out.println("------------------------------------------");
        String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
        logEntity.setDate(time);
        //获取拦截的实体类，就是正在执行的controller
        Object target = pjp.getTarget();
        //拦截的方法名称。正在执行的方法
        String methodName = pjp.getSignature().getName();
        //拦截的方法参数
        Object [] args = pjp.getArgs();
        //拦截的放参数类型
        Signature sig = pjp.getSignature();

        MethodSignature msig = null;

        if (!(sig instanceof MethodSignature)){
            throw new IllegalArgumentException("该注解只能用于方法");
        }

        msig = (MethodSignature)sig;

        Class [] parameterTypes = msig.getMethod().getParameterTypes();
        Object object = null;
        //获得拦截的方法
        Method method = null;
        try{
            method = target.getClass().getMethod(methodName,parameterTypes);
        }catch (NoSuchMethodException e1){
            e1.printStackTrace();
        }catch (SecurityException e2){
            e2.printStackTrace();
        }
        if (null!=method){
            if (method.isAnnotationPresent(OperateLog.class)){
                OperateLog operateLog = method.getAnnotation(OperateLog.class);
                String operateModule = operateLog.operatorModule();
                System.out.println(operateModule);
            }else{
                object = pjp.proceed();
            }
        }else{
            object = pjp.proceed();
        }
        return object;
    }

}
