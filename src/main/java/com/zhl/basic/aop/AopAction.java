package com.zhl.basic.aop;

import java.lang.annotation.*;

/**
 * @author Lenovo
 * @title: AopAction
 * @projectName basic
 * @description: TODO
 * @date 2019/12/4 10:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) //source class  runtime
@Documented
@Inherited
public @interface AopAction {

    String name() default "123";

}
