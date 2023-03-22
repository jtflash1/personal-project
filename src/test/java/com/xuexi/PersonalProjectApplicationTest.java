package com.xuexi;

import com.xuexi.test.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class PersonalProjectApplicationTest {

    @Autowired
    private TestController testController;

    @Test
    public void queryList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", "10000");
        params.put("username", "Tom");
        testController.queryList(params);
    }
}
