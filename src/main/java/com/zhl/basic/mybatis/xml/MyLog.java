package com.zhl.basic.mybatis.xml;

import org.apache.ibatis.logging.Log;

/**
 * @author Lenovo
 * @title: MyLog
 * @projectName basic
 * @description: TODO
 * @date 2019/12/2 9:17
 */
public class MyLog implements Log {

    public MyLog(String name){
        System.out.println("日志构造："+name);
    }
    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void error(String s, Throwable e) {
        System.out.println("自定义日志："+s);
    }

    @Override
    public void error(String s) {
        System.out.println("自定义日志："+s);
    }

    @Override
    public void debug(String s) {
        System.out.println("自定义日志："+s);
    }

    @Override
    public void trace(String s) {
        System.out.println("自定义日志："+s);
    }

    @Override
    public void warn(String s) {
        System.out.println("自定义日志："+s);
    }
}
