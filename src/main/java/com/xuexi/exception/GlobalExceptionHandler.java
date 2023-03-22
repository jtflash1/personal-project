package com.xuexi.exception;

import com.xuexi.response.BaseResponse;
import com.xuexi.response.ErrorCode;
import com.xuexi.response.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 全局异常处理器
 */
@Slf4j
@Order(999999)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)//全局捕获这个异常进行处理
    public BaseResponse businessExceptionHandler(BusinessException e) {
        log.error("businessException:" + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler({
            NullPointerException.class,// 当应用程序试图访问空对象时，则抛出该异常
            SQLException.class,// 提供关于数据库访问错误或其他错误信息的异常
            IndexOutOfBoundsException.class,// 指示某排序索引（例如对数组、字符串或向量的排序）超出范围时抛出
            NumberFormatException.class,// 当应用程序试图将字符串转换成一种数值类型，但该字符串不能转换为适当格式时，抛出该异常
            FileNotFoundException.class,// 当试图打开指定路径名表示的文件失败时，抛出此异常
            IOException.class,// 当发生某种I/O异常时，抛出此异常。此类是失败或中断的I/O操作生成的异常的通用类
            ClassCastException.class,// 当试图将对象强制转换为不是实例的子类时，抛出该异常
            ArrayStoreException.class,// 试图将错误类型的对象存储到一个对象数组时抛出的异常
            IllegalArgumentException.class,// 抛出的异常表明向方法传递了一个不合法或不正确的参数
            ArithmeticException.class,// 当出现异常的运算条件时，抛出此异常。例如，一个整数“除以零”时，抛出此类的一个实例
            NegativeArraySizeException.class,// 如果应用程序试图创建大小为负的数组，则抛出该异常
            NoSuchMethodException.class,// 无法找到某一特定方法时，抛出该异常
            SecurityException.class,// 由安全管理器抛出的异常，指示存在安全侵犯
            UnsupportedOperationException.class,// 当不支持请求的操作时，抛出该异常
            RuntimeException.class// 是那些可能在Java虚拟机正常运行期间抛出的异常的超类
    })
    //全局捕获这个异常进行处理
    public BaseResponse commonExceptionHandler(Exception e) {
        log.error("Exception:" + e.getCause().toString());
        e.printStackTrace();
        return ResultUtils.error(ErrorCode.EXCEPTION);
    }

    @ExceptionHandler(Exception.class)//全局捕获这个异常进行处理
    public BaseResponse exceptionHandler(Exception e) {
        log.error("Exception:" + e.getMessage(), e);
        return ResultUtils.error(ErrorCode.EXCEPTION);
    }
}