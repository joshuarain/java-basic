package com.zhl.basic.thread.sequence;

/**
 * @author Lenovo
 * @title: ThreadJoin
 * @projectName basic
 * @description: TODO
 * @date 2019/11/29 14:23
 */
public class ThreadJoin {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(3);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(2);
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        });
        thread3.start();
        thread1.start();
        thread2.start();

    }
}
