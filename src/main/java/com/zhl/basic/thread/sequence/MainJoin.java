package com.zhl.basic.thread.sequence;

/**
 * @author Lenovo
 * @title: MainJoin
 * @projectName basic
 * @description: TODO
 * @date 2019/11/29 14:28
 */
public class MainJoin {
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(3);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });
        thread3.start();
        thread3.join();
        thread2.start();
        thread2.join();
        thread1.start();
        thread1.join();

    }

}
