package com.zhl.basic.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Lenovo
 * @title: MyObservable
 * @projectName basic
 * @description: TODO
 * @date 2019/12/4 10:53
 */
public class MyObservable extends Observable {
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }
}
