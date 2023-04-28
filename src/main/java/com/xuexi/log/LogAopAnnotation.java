package com.xuexi.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Target：描述了注解修饰的对象范围
 * <p>
 * METHOD：用于描述方法
 * PACKAGE：用于描述包
 * PARAMETER：用于描述方法变量
 * TYPE：用于描述类、接口或enum类型
 */
// 指定注解使用在方法上
@Target(ElementType.METHOD)
/*
 * Retention: 表示注解保留时间长短
 *
 * SOURCE：在源文件中有效，编译过程中会被忽略
 * CLASS：随源文件一起编译在class文件中，运行时忽略
 * RUNTIME：在运行时有效
 */
// 指定生效至运行时
@Retention(RetentionPolicy.RUNTIME)
/*
 * 日志切面注解
 */
public @interface LogAopAnnotation {

    /**
     * 是否打印日志
     *
     * @return 打印日志开关
     */
    boolean isPrint() default true;

    /**
     * 是否打印入参
     *
     * @return 打印入参开关
     */
    boolean isParam() default false;

    /**
     * 指定是否详情显示,true 显示详情, 默认false
     *
     * @return 详情展示开关
     */
    boolean isDetail() default false;

    /**
     * 是否开启查看任务执行时间
     *
     * @return 执行时间开关
     */
    boolean isTime() default false;
}
