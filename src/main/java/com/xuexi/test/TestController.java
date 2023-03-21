package com.xuexi.test;

import com.xuexi.exception.BusinessException;
import com.xuexi.exception.GlobalExceptionHandler;
import com.xuexi.log.LogAopAnnotation;
import com.xuexi.response.BaseResponse;
import com.xuexi.response.ErrorCode;
import com.xuexi.response.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class TestController {
    @Autowired
    GlobalExceptionHandler globalExceptionHandler;

    @PostMapping("/queryList")
    @LogAopAnnotation(isParam = true, isDetail = true, isTime = true)
    public BaseResponse<Map<String, Object>> queryList(@RequestBody Map<String, Object> params, @PathVariable  String id) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("status", "success");
        result.put("params", params);
        try {
            double b = 1/0;
        } catch (ArithmeticException e) {
            throw new BusinessException(ErrorCode.EXCEPTION);
        }

        return ResultUtils.success(result);
    }
}
