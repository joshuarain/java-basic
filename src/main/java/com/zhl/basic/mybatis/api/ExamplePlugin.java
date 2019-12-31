package com.zhl.basic.mybatis.api;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

/**
 * @author Lenovo
 * @title: ExamplePlugin
 * @projectName basic
 * @description: TODO
 * @date 2019/12/1 17:20
 */
@Intercepts(@Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class}))
public class ExamplePlugin implements Interceptor {
    private Properties properties = new Properties();
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object returnObject = invocation.proceed();
        return returnObject;
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
