package com.xuexi.exception;

import com.xuexi.response.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常类
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private final int code;

    private final String description;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }
}

