package com.zhl.basic.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Lenovo
 * @title: MyObserver
 * @projectName basic
 * @description: TODO
 * @date 2019/12/4 10:52
 */
public class MyObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o.countObservers());
        System.out.println(arg);
    }
}
