package com.zhl.basic.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * @author Lenovo
 * @title: AopTest
 * @projectName basic
 * @description: TODO
 * @date 2019/12/4 10:27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AopTest {
    @Autowired
    private XXXService xxxService;
    @Autowired
    private XXXXService xxxxService;

    @Test
    public void testA(){
        xxxService.all();
        xxxxService.abc();
    }
}
