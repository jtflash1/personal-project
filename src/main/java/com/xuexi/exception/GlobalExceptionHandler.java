package com.xuexi.exception;

import com.xuexi.response.BaseResponse;
import com.xuexi.response.ErrorCode;
import com.xuexi.response.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(Exception.class)//全局捕获这个异常进行处理
    public BaseResponse exceptionHandler(Exception e) {
        log.error("exception:" + e.getMessage(), e);
        return ResultUtils.error(ErrorCode.EXCEPTION);
    }
}