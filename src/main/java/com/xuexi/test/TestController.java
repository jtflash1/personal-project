package com.xuexi.test;

import com.xuexi.log.LogAopAnnotation;
import com.xuexi.response.Result;
import com.xuexi.util.UtilsMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class TestController {

    @PostMapping("/queryList")
    @LogAopAnnotation(isParam = true, isDetail = true, isTime = true)
    public Result<Map<String, Object>> queryList(@RequestBody Map<String, Object> params) {
        if (params.isEmpty())
            return Result.fail();
        if (params.get("flag") == null)
            return Result.success();
        return Result.success(UtilsMap.toMap("params", params));
    }
}
