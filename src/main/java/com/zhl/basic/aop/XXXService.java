package com.zhl.basic.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

/**
 * @author Lenovo
 * @title: XXXService
 * @projectName basic
 * @description: TODO
 * @date 2019/12/4 9:56
 */
@Service
public class XXXService {

    @AopAction(name = "all 方法被调用")
    public String all() {
        return null;
    }
}
