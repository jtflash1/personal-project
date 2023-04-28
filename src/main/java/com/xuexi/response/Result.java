package com.xuexi.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;


/**
 * 返回工具类
 *
 * @author zwl
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {

    private static final Result<?> EMPTY = new Result<>();

    /* 成功码 */
    private static int CODE_SUCCESS = 200;

    /* 失败码 */
    private static int CODE_FAIL = 500;

    /* 成功消息 */
    private static String MSG_SUCCESS = "成功";

    /* 失败消息 */
    private static String MSG_FAIL = "成功";

    /* 返回码 */
    private int code;

    /* 返回消息 */
    private String msg;

    /* 返回数据 */
    private T data;

    /* 返回描述信息 */
    private String description;

    /**
     * 返回成功
     *
     * @param <T> 泛型
     * @return 结果
     */
    public static <T> Result<T> success() {
        return success(StringUtils.EMPTY);
    }

    /**
     * 返回失败
     *
     * @param <T> 泛型
     * @return 结果
     */
    public static <T> Result<T> fail() {
        return fail(StringUtils.EMPTY);
    }

    /**
     * 返回成功
     *
     * @param data 数据
     * @param <T>  泛型
     * @return 结果
     */
    public static <T> Result<T> success(T data) {
        return success(CODE_SUCCESS, MSG_SUCCESS, data, StringUtils.EMPTY);
    }

    /**
     * 返回成功
     *
     * @param description 描述
     * @param <T>         泛型
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> success(String description) {
        return (Result<T>) success(CODE_SUCCESS, MSG_SUCCESS, Collections.EMPTY_MAP, description);
    }

    /**
     * 返回失败
     *
     * @param description 描述
     * @param <T>         泛型
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> fail(String description) {
        return (Result<T>) fail(CODE_FAIL, MSG_FAIL, Collections.EMPTY_MAP, description);
    }

    /**
     * 返回成功
     *
     * @param code        返回码
     * @param msg         返回消息
     * @param data        返回数据
     * @param description 返回描述
     * @param <T>         泛型
     * @return 结果
     */
    public static <T> Result<T> success(int code, String msg, T data, String description) {
        return new Result<>(code, msg, data, description);
    }

    /**
     * 返回失败
     *
     * @param errorCode 错误码
     * @param <T>       泛型
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> fail(ErrorCode errorCode) {
        return (Result<T>) fail(errorCode.getCode(), errorCode.getMessage(), Collections.EMPTY_MAP, errorCode.getDescription());
    }

    /**
     * 返回失败
     *
     * @param code        返回码
     * @param msg         返回消息
     * @param data        返回数据
     * @param description 返回描述
     * @param <T>         泛型
     * @return 结果
     */
    public static <T> Result<T> fail(int code, String msg, T data, String description) {
        return new Result<>(code, msg, data, description);
    }
}

