package com.xuexi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 异常码
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    PARAMS_ERROR(40000, "请求参数错误", ""),
    BUSINESS_EXCEPTION(40001, "业务异常", "业务异常"),
    EXCEPTION(40002, "异常", "");
    /**
     * 状态码信息
     */
    private final int code;
    /**
     * 状态码信息
     */
    private final String message;
    /**
     * 状态码描述（详情）
     */
    private final String description;
}
