package com.xuexi.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xuexi.exception.BusinessException;
import com.xuexi.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志切面切面类
 */
@Slf4j
@Aspect
@Component
public class LogAopAspect {

    /**
     * 指定切点, 切点的位置是存在该注解
     */
    @Pointcut("@annotation(com.xuexi.log.LogAopAnnotation)")
    public void logPointcut() {
    }

    /**
     * @Before: 前置通知, 在方法执行之前执行
     * @After: 后置通知, 在方法执行之后执行
     * @AfterRunning: 返回通知, 在方法返回结果之后执行
     * @AfterThrowing: 异常通知, 在方法抛出异常之后
     * @Around: 环绕通知, 围绕着方法执行
     */

    /**
     * 环绕通知, 该处写具体日志逻辑
     */
    @Around("logPointcut()")
    public void logAround(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取方法名称
        String methodName = signature.getName();
        // 获取方法上的注解，判断如果isDetail值为true，则打印结束日志
        Method method = signature.getMethod();
        LogAopAnnotation annotation = method.getAnnotation(LogAopAnnotation.class);
        boolean isParam = annotation.isParam();
        if (isParam) {
            // 获取入参
            String[] parameterNames = signature.getParameterNames();
            Object[] param = joinPoint.getArgs();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < parameterNames.length; i++) {
                sb.append(parameterNames[i] + ": ").append(param[i]).append("; ");
            }
            log.info("进入方法[{}], 参数: [{}].", methodName, sb.toString());
        } else {
            log.info("进入方法[{}].", methodName);
        }
        String resp = "";
        try {
            Object proceed = joinPoint.proceed();
            resp = JSON.toJSONString(proceed, SerializerFeature.WriteMapNullValue);
        } catch (Throwable throwable) {
            throw new BusinessException(ErrorCode.BUSINESS_EXCEPTION);
        }
        long endTime = System.currentTimeMillis();
        boolean isDetail = annotation.isDetail();
        boolean isTime = annotation.isTime();
        if (isDetail) {
            if (isTime) {
                log.info("方法[{}]执行结束, 返回值: [{}], 耗费时间: [{}]ms.", methodName, resp, endTime - startTime);
            } else {
                log.info("方法[{}]执行结束, 返回值: [{}].", methodName, resp);
            }
        } else {
            if (isTime) {
                log.info("方法[{}]执行结束, 耗费时间: [{}]ms.", methodName, resp, endTime - startTime);
            } else {
                log.info("方法[{}]执行结束.", methodName);
            }
        }
    }
}