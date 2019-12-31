package com.zhl.basic.mybatis.xml;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;
import java.util.Properties;

/**
 * @author Lenovo
 * @title: MyObjectFactory
 * @projectName basic
 * @description: TODO
 * @date 2019/12/1 16:58
 */
public class MyObjectFactory extends DefaultObjectFactory {
    public MyObjectFactory() {
        super();
    }

    @Override
    public <T> T create(Class<T> type) {
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }

    @Override
    protected Class<?> resolveInterface(Class<?> type) {
        return super.resolveInterface(type);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type);
    }
}
